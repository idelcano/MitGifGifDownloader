/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

/**
 *
 * @author ina
 */
public class Language {

    public static int lang;

    public static final int ENG = 0;
    public static final int ESP = 1;

    public static void setLang(int lang) {
        Language.lang = lang;
    }

    public int getLang() {
        return Language.lang;
    }

    public static String getString(int lang, Translation.Strings string) {
        return Translation.getStringFromEnum(lang, string);
    }
}
