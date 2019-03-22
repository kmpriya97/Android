/*
 * Copyright (C) 2014 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.internal.codegen;

import static com.google.auto.common.AnnotationMirrors.getAnnotationValue;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.consumingIterable;
import static dagger.internal.codegen.ComponentKind.annotationsFor;
import static dagger.internal.codegen.ComponentKind.builderAnnotationsFor;
import static dagger.internal.codegen.ComponentKind.subcomponentKinds;
import static dagger.internal.codegen.DaggerElements.getAnyAnnotation;
import static dagger.internal.codegen.DaggerElements.isAnyAnnotationPresent;
import static dagger.internal.codegen.ModuleAnnotation.isModuleAnnotation;
import static dagger.internal.codegen.ModuleAnnotation.moduleAnnotation;
import static dagger.internal.codegen.MoreAnnotationMirrors.getTypeListValue;
import static dagger.internal.codegen.MoreAnnotationValues.asAnnotationValues;
import static javax.lang.model.util.ElementFilter.typesIn;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.producers.ProductionComponent;
import dagger.producers.ProductionSubcomponent;
import java.lang.annotation.Annotation;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * Utility methods related to dagger configuration annotations (e.g.: {@link Component}
 * and {@link Module}).
 */
final class ConfigurationAnnotations {

  static Optional<AnnotationMirror> getComponentAnnotation(TypeElement component) {
    return getAnyAnnotation(component, Component.class, ProductionComponent.class);
  }

  static Optional<AnnotationMirror> getSubcomponentAnnotation(TypeElement subcomponent) {
    return getAnyAnnotation(subcomponent, Subcomponent.class, ProductionSubcomponent.class);
  }

  static Optional<AnnotationMirror> getComponentOrSubcomponentAnnotation(TypeElement type) {
    Optional<AnnotationMirror> componentAnnotation = getComponentAnnotation(type);
    if (componentAnnotation.isPresent()) {
      return componentAnnotation;
    }
    return getSubcomponentAnnotation(type);
  }

  static boolean isSubcomponent(Element element) {
    return isAnyAnnotationPresent(element, annotationsFor(subcomponentKinds()));
  }

  static Optional<TypeElement> getSubcomponentCreator(TypeElement subcomponent) {
    checkArgument(isSubcomponent(subcomponent));
    for (TypeElement nestedType : typesIn(subcomponent.getEnclosedElements())) {
      if (isSubcomponentCreator(nestedType)) {
        return Optional.of(nestedType);
      }
    }
    return Optional.empty();
  }

  static boolean isSubcomponentCreator(Element element) {
    return isAnyAnnotationPresent(element, builderAnnotationsFor(subcomponentKinds()));
  }

  /**
   * Returns the annotation values for the modules directly installed into a component or included
   * in a module.
   *
   * @param annotatedType the component or module type
   * @param annotation the component or module annotation
   */
  static ImmutableList<AnnotationValue> getModules(
      TypeElement annotatedType, AnnotationMirror annotation) {
    if (ComponentKind.forAnnotatedElement(annotatedType)
        .filter(kind -> !kind.isForModuleValidation())
        .isPresent()) {
      return asAnnotationValues(getAnnotationValue(annotation, MODULES_ATTRIBUTE));
    }
    if (isModuleAnnotation(annotation)) {
      return moduleAnnotation(annotation).includesAsAnnotationValues();
    }
    throw new IllegalArgumentException(String.format("unsupported annotation: %s", annotation));
  }

  private static final String MODULES_ATTRIBUTE = "modules";

  static ImmutableList<TypeMirror> getComponentModules(AnnotationMirror componentAnnotation) {
    checkNotNull(componentAnnotation);
    return getTypeListValue(componentAnnotation, MODULES_ATTRIBUTE);
  }

  private static final String DEPENDENCIES_ATTRIBUTE = "dependencies";

  static ImmutableList<TypeMirror> getComponentDependencies(AnnotationMirror componentAnnotation) {
    checkNotNull(componentAnnotation);
    return getTypeListValue(componentAnnotation, DEPENDENCIES_ATTRIBUTE);
  }

  // Dagger 1 support.
  static ImmutableList<TypeMirror> getModuleInjects(AnnotationMirror moduleAnnotation) {
    checkNotNull(moduleAnnotation);
    return getTypeListValue(moduleAnnotation, "injects");
  }

  /** Returns the first type that specifies this' nullability, or empty if none. */
  static Optional<DeclaredType> getNullableType(Element element) {
    List<? extends AnnotationMirror> mirrors = element.getAnnotationMirrors();
    for (AnnotationMirror mirror : mirrors) {
      if (mirror.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return Optional.of(mirror.getAnnotationType());
      }
    }
    return Optional.empty();
  }

  /**
   * Returns the full set of modules transitively {@linkplain Module#includes included} from the
   * given seed modules. If a module is malformed and a type listed in {@link Module#includes} is
   * not annotated with {@link Module}, it is ignored.
   *
   * @deprecated Use {@link ComponentDescriptor#modules()}.
   */
  @Deprecated
  static ImmutableSet<TypeElement> getTransitiveModules(
      DaggerTypes types, DaggerElements elements, Iterable<TypeElement> seedModules) {
    TypeMirror objectType = elements.getTypeElement(Object.class).asType();
    Queue<TypeElement> moduleQueue = new ArrayDeque<>();
    Iterables.addAll(moduleQueue, seedModules);
    Set<TypeElement> moduleElements = Sets.newLinkedHashSet();
    for (TypeElement moduleElement : consumingIterable(moduleQueue)) {
      moduleAnnotation(moduleElement)
          .ifPresent(
              moduleAnnotation -> {
                ImmutableSet.Builder<TypeElement> moduleDependenciesBuilder =
                    ImmutableSet.builder();
                moduleDependenciesBuilder.addAll(moduleAnnotation.includes());
                // We don't recur on the parent class because we don't want the parent class as a
                // root that the component depends on, and also because we want the dependencies
                // rooted against this element, not the parent.
                addIncludesFromSuperclasses(
                    types, moduleElement, moduleDependenciesBuilder, objectType);
                ImmutableSet<TypeElement> moduleDependencies = moduleDependenciesBuilder.build();
                moduleElements.add(moduleElement);
                for (TypeElement dependencyType : moduleDependencies) {
                  if (!moduleElements.contains(dependencyType)) {
                    moduleQueue.add(dependencyType);
                  }
                }
              });
    }
    return ImmutableSet.copyOf(moduleElements);
  }

  /** Returns the enclosed types annotated with the given annotation. */
  static ImmutableList<DeclaredType> enclosedAnnotatedTypes(
      TypeElement typeElement, Class<? extends Annotation> annotation) {
    final ImmutableList.Builder<DeclaredType> builders = ImmutableList.builder();
    for (TypeElement element : typesIn(typeElement.getEnclosedElements())) {
      if (MoreElements.isAnnotationPresent(element, annotation)) {
        builders.add(MoreTypes.asDeclared(element.asType()));
      }
    }
    return builders.build();
  }

  /** Traverses includes from superclasses and adds them into the builder. */
  private static void addIncludesFromSuperclasses(
      DaggerTypes types,
      TypeElement element,
      ImmutableSet.Builder<TypeElement> builder,
      TypeMirror objectType) {
    // Also add the superclass to the queue, in case any @Module definitions were on that.
    TypeMirror superclass = element.getSuperclass();
    while (!types.isSameType(objectType, superclass)
        && superclass.getKind().equals(TypeKind.DECLARED)) {
      element = MoreElements.asType(types.asElement(superclass));
      moduleAnnotation(element)
          .ifPresent(moduleAnnotation -> builder.addAll(moduleAnnotation.includes()));
      superclass = element.getSuperclass();
    }
  }

  private ConfigurationAnnotations() {}
}
