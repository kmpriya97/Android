package producttracking.iexemplar.com.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class Validator {


    private static int MINIMUM_LENGTH=6;
    static int values=0;

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN="(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()"
                + "<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*" +
                "(?:[^" + "()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"" + "" + "" + "" +
                "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] "
                + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ " + "\\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)"
                + "?[" + " " + "\\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*" + "(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" +
                "" + "(?=[\\[\"()" + "<>@,;" + ":\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\" + ".)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;" +
                ":\\\\\"" + "" + ".\\[\\] \\000-\\031]+" + "(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\"" + ".\\[\\]]))|\"" + "" + "" +
                "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "(?:\\r\\n)?[ \\t]))*\"(?:" + "(?:\\r\\n)?[ " + "\\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;"
                + ":\\\\\".\\[\\] " + "\\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" + "(?=[\\[\"()<>@,;" + ":\\\\\".\\[\\]]))|\\[" + "" + "" +
                "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:" + "(?:\\r\\n)?[ " + "\\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*" + "(?:[^()<>@,;:\\\\\"" + ".\\[\\] " +
                "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])" + "+|\\Z|(?=[\\[\"()" + "<>@,;:\\\\\".\\[\\]]))|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:" +
                "(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)" + "?[ \\t])*(?:[^()<>@,;" + ":\\\\\"" + ".\\[\\] \\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ "
                + "\\t])+|\\Z|" + "(?=[\\[\"()<>@,;:\\\\\"" + ".\\[\\]]))|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)" + "?[ \\t])*)" + "" +
                "(?:\\." + "(?:(?:\\r\\n)?[ \\t])*(?:[^()" + "<>@,;" + ":\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()"
                + "<>@,;" + ":\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)" + "*\\](?:" + "(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()" +
                "" + "<>@,;" + ":\\\\\"" + ".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)" + "?[ \\t])" + "+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\""
                + "" + "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "(?:\\r\\n)?[ \\t]))*\"" + "(?:(?:\\r\\n)?[ " + "\\t])*)(?:\\." + "(?:" + "(?:\\r\\n)?[ " +
                "\\t])*" + "(?:[^" + "()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"" + "()<>@,;:\\\\\"" + "" +
                ".\\[\\]]))|\"" + "" + "(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:" + "(?:\\r\\n)?[ \\t])*))*@(?:" + "(?:\\r\\n)?[ \\t])*" +
                "(?:[^" + "" + "()" + "<>@,;" + ":\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" + "(?=[\\[\"()<>@,;" + ":\\\\\"" +
                ".\\[\\]]))|\\[" + "" + "" + "([^\\[\\]\\r\\\\]|\\\\.)*\\]" + "(?:(?:\\r\\n)?[ " + "\\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*" + "(?:[^()" +
                "" + "<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"" + "()<>@,;" + ":\\\\\".\\[\\]]))|\\[" + "" + "" +
                "([^\\[\\]\\r\\\\]|\\\\.)*\\]" + "(?:" + "(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\"" + "" + ".\\[\\] " +
                "\\000-\\031]+" + "(?:(?:" + "" + "(?:\\r\\n)?[ \\t])" + "+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:" +
                "(?:\\r\\n)?[ " + "\\t]))*\"(?:" + "" + "(?:\\r\\n)?[ \\t])*)*:(?:" + "(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+(?:(?:(?:\\r\\n)" + "?[ \\t])" + "+|\\Z|" + "(?=[\\[\"()<>@,;:\\\\\"" + ".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:" +
                "(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ " + "\\t])*)" + "(?:\\.(?:" + "" + "" + "(?:\\r\\n)?[ \\t])*" + "(?:[^()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" + "" + "" + "(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"" + "" + "" + "" +
                "(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:" + "(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] "
                + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))" + "|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[" +
                " " + "\\t])" + "*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"" +
                "()<>@,;" + ":\\\\\"" + "" + ".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:" + "(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+(?:" + "(?:(?:\\r\\n)?[ " + "\\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\"" + ".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:" +
                "(?:\\r\\n)?[ \\t]))*\"(?:" + "(?:\\r\\n)" + "?[ \\t])*)*\\<(?:" + "(?:\\r\\n)?[ \\t])*" + "(?:@" + "(?:[^()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" + "(?=[\\[\"()<>@," + ";:\\\\\".\\[\\]]))" + "|\\[" + "" + "([^\\[\\]\\r\\\\]|\\\\.)" +
                "*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])" + "*(?:[^()<>@,;:\\\\\"" + ".\\[\\] " + "" + "\\000-\\031]+" + "(?:(?:" +
                "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\]" + "(?:" + "(?:\\r\\n)?[ " + "\\t])" +
                "*))*(?:,@" + "(?:" + "(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:" + "(?:\\r\\n)?[ " + "\\t])+|\\Z|" +
                "" + "(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))" + "|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:" + "" + "(?:\\r\\n)?[ \\t])*)(?:\\." + "(?:" +
                "(?:\\r\\n)?[ \\t])" + "*" + "(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+" + "(?:(?:" + "(?:\\r\\n)" + "?[ \\t])" + "+|\\Z|(?=[\\[\"()" +
                "" + "<>@,;:\\\\\".\\[\\]]))|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:" + "(?:" + "(?:\\r\\n)" + "?[ " +
                "\\t])*)?" + "(?:[^()<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()" + "<>@,;:\\\\\"" + "" +
                ".\\[\\]]))|\"" + "" + "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "(?:\\r\\n)?[ \\t]))*\"(?:" + "(?:\\r\\n)?[ \\t])*)(?:\\." + "(?:(?:\\r\\n)" +
                "?[ \\t])*(?:[^()<>@,;" + ":\\\\\"" + ".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" + "(?=[\\[\"()<>@,;" + ":\\\\\"" +
                ".\\[\\]]))|\"" + "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "" + "" + "(?:\\r\\n)?[ \\t]))*\"" + "(?:(?:\\r\\n)?[ \\t])*))*@(?:" + "" + "" +
                "(?:\\r\\n)?[ \\t])*(?:[^()" + "<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()" + "<>@,;:\\\\\"" +
                ".\\[\\]]))|\\[" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\." + "(?:" + "(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\"" +
                ".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;" + ":\\\\\"" + ".\\[\\]]))" + "|\\[" +
                "([^\\[\\]\\r\\\\]|\\\\" + ".)*\\](?:" + "(?:\\r\\n)" + "?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:" + "(?:[^()<>@,;" +
                ":\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|" + "(?=[\\[\"()" + "<>@,;:\\\\\".\\[\\]]))|\"" + "" +
                "(?:[^\\\"\\r\\\\]|\\\\.|(?:" + "(?:\\r\\n)?[ \\t]))" + "*\"" + "(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:" + "(?:\\r\\n)?[ \\t])*(?:[^" + "" +
                "()<>@,;" + ":\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])" + "+|\\Z|" + "(?=[\\[\"()<>@,;:\\\\\"" + ".\\[\\]]))|\"" +
                "(?:[^\\\"\\r\\\\]|\\\\.|" + "(?:" + "(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ " + "\\t])*))*@(?:(?:\\r\\n)" + "?[ \\t])*(?:[^()" +
                "<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ " + "\\t])+|\\Z|" + "(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[" + "" + "" +
                "([^\\[\\]\\r\\\\]|\\\\.)" + "*\\]" + "(?:(?:\\r\\n)?[ " + "\\t])*)(?:\\.(?:(?:\\r\\n)?[ " + "\\t])*" + "(?:[^()<>@,;:\\\\\".\\[\\]" +
                " \\000-\\031]+(?:" + "(?:" + "(?:\\r\\n)?[ " + "\\t])+|\\Z|(?=[\\[\"" + "()<>@,;:\\\\\"" + ".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\"
                + ".)*\\](?:(?:\\r\\n)?[ \\t])" + "*))*|" + "(?:[^()" + "<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|" +
                "(?=[\\[\"()<>@,;:\\\\\"" + "" + ".\\[\\]]))|\"" + "" + "(?:[^\\\"\\r\\\\]|\\\\.|" + "(?:" + "(?:\\r\\n)?[" + " \\t]))*\"(?:" +
                "(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ " + "\\t])*(?:@(?:[^()<>@,;" + ":\\\\\".\\[\\] \\000-\\031]+(?:(?:" + "(?:\\r\\n)" + "?[ " +
                "\\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))" + "|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\]" + "(?:" + "(?:\\r\\n)?[ \\t])*)(?:\\." + "(?:"
                + "(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] " + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ " + "\\t])" + "+|\\Z|(?=[\\[\"()" + "<>@,;" +
                ":\\\\\"" + ".\\[\\]]))" + "|\\[([^\\[\\]\\r\\\\]|\\\\.)" + "*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:" + "(?:\\r\\n)?[ " + "\\t])*" +
                "(?:[^()<>@,;:\\\\\"" + "" + ".\\[\\] \\000-\\031]+" + "(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;" + ":\\\\\".\\[\\]]))" +
                "|\\[" + "" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:" + "(?:\\r\\n)" + "?[ " + "\\t])*)(?:\\." + "(?:(?:\\r\\n)?[ \\t])*(?:[^()" + "<>@,;" +
                ":\\\\\".\\[\\] " + "\\000-\\031]+" + "(?:(?:(?:\\r\\n)?[ \\t])" + "+|\\Z|" + "(?=[\\[\"()<>@,;" + ":\\\\\".\\[\\]]))" + "|\\[" + "" +
                "([^\\[\\]\\r\\\\]|\\\\.)" + "*\\](?:(?:\\r\\n)?[ \\t])*))" + "*)*:(?:" + "(?:\\r\\n)?[ " + "\\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+" + "(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;" + ":\\\\\".\\[\\]]))|\"" + "(?:[^\\\"\\r\\\\]|\\\\.|(?:" +
                "(?:\\r\\n)?[ \\t]))*\"" + "" + "(?:" + "(?:\\r\\n)?[ \\t])*)(?:\\.(?:" + "(?:\\r\\n)?[ \\t])*" + "(?:[^()<>@,;" + ":\\\\\".\\[\\] " +
                "" + "\\000-\\031]+(?:(?:(?:\\r\\n)?[ " + "\\t])+|\\Z|(?=[\\[\"" + "()<>@,;" + ":\\\\\".\\[\\]]))|\"" + "(?:[^\\\"\\r\\\\]|\\\\.|"
                + "(?:" + "(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)" + "?[ \\t])*))*@(?:(?:\\r\\n)?[" + " \\t])*(?:[^" + "()<>@,;:\\\\\".\\[\\] " +
                "\\000-\\031]+(?:(?:" + "(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"" + "()<>@,;:\\\\\".\\[\\]]))" + "|\\[([^\\[\\]\\r\\\\]|\\\\.)" + "*\\]"
                + "(?:" + "(?:\\r\\n)?[ \\t])*)" + "(?:\\.(?:(?:\\r\\n)?[ \\t])*" + "(?:[^()<>@,;:\\\\\"" + ".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)" +
                "?[ \\t])+|\\Z|" + "" + "(?=[\\[\"" + "()<>@,;" + ":\\\\\".\\[\\]]))|\\[" + "" + "([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])" +
                "*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";
        pattern=Pattern.compile( EMAIL_PATTERN );
        matcher=pattern.matcher( email );
        return matcher.matches();
    }

    public static boolean isEmptyField(EditText text) {
        return (text == null || text.length() <= 0 || text.toString().trim().isEmpty());
    }

    public static boolean isValidPassword(EditText password) {
        return password.toString().isEmpty() || password.length() < 4;
    }

}
