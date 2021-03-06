/*
 * Copyright (C) 2016 The Dagger Authors.
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

import static com.google.common.collect.Iterables.getOnlyElement;
import static dagger.internal.codegen.ConfigurationAnnotations.getComponentOrSubcomponentAnnotation;
import static dagger.internal.codegen.ModuleAnnotation.moduleAnnotation;
import static dagger.internal.codegen.MoreAnnotationMirrors.simpleName;
import static javax.lang.model.element.Modifier.ABSTRACT;

import com.google.auto.common.MoreElements;
import com.google.common.collect.ImmutableSet;
import dagger.BindsInstance;
import java.lang.annotation.Annotation;
import java.util.Set;
import javax.annotation.processing.Messager;
import javax.inject.Inject;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * Processing step that validates that the {@code BindsInstance} annotation is applied to the
 * correct elements.
 */
final class BindsInstanceProcessingStep extends TypeCheckingProcessingStep<ExecutableElement> {
  private final Messager messager;

  @Inject
  BindsInstanceProcessingStep(Messager messager) {
    super(MoreElements::asExecutable);
    this.messager = messager;
  }

  @Override
  public Set<? extends Class<? extends Annotation>> annotations() {
    return ImmutableSet.of(BindsInstance.class);
  }

  @Override
  protected void process(
      ExecutableElement method, ImmutableSet<Class<? extends Annotation>> annotations) {
    ValidationReport.Builder<ExecutableElement> report = ValidationReport.about(method);
    if (!method.getModifiers().contains(ABSTRACT)) {
      report.addError("@BindsInstance methods must be abstract");
    }
    if (method.getParameters().size() != 1) {
      report.addError(
          "@BindsInstance methods should have exactly one parameter for the bound type");
    } else {
      VariableElement parameter = getOnlyElement(method.getParameters());
      if (FrameworkTypes.isFrameworkType(parameter.asType())) {
        report.addError("@BindsInstance parameters may not be framework types", parameter);
      }
    }
    TypeElement enclosingType = MoreElements.asType(method.getEnclosingElement());
    moduleAnnotation(enclosingType)
        .ifPresent(moduleAnnotation -> report.addError(didYouMeanBinds(moduleAnnotation)));
    getComponentOrSubcomponentAnnotation(enclosingType)
        .ifPresent(
            componentAnnotation ->
                report.addError(
                    String.format(
                        "@BindsInstance methods should not be included in @%1$ss. "
                            + "Did you mean to put it in a @%1$s.Builder?",
                        simpleName(componentAnnotation))));
    report.build().printMessagesTo(messager);
  }

  private static String didYouMeanBinds(ModuleAnnotation moduleAnnotation) {
    return String.format(
        "@BindsInstance methods should not be included in @%ss. Did you mean @Binds?",
        moduleAnnotation.annotationClass().getSimpleName());
  }
}
