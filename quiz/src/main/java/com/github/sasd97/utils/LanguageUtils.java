package com.github.sasd97.utils;

import static com.github.sasd97.utils.LanguageUtils.Language.UNKNOWN;

/**
 * Created by Alexadner Dadukin on 12/22/2016.
 */
public class LanguageUtils {

    public enum Language {

        RUSSIAN("ru"),
        ENGISH("en"),
        UNKNOWN("");

        private String ln;

        Language(String ln) {
            this.ln = ln;
        }

        public boolean compare(String ln) {
            return this.ln.equals(ln);
        }
    }

    private LanguageUtils() {}

    public static Language toLanguage(String ln) {
        String holder = ln.trim().toLowerCase();

        for (Language l: Language.class.getEnumConstants()) {
            if (l.compare(holder)) return l;
        }

        return UNKNOWN;
    }
}
