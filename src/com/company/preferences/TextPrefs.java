package com.company.preferences;

import com.company.EditorWindow;

import java.awt.*;
import java.util.prefs.Preferences;

public class TextPrefs {

    private final String BOLD = "bold";
    private final String FONT_NAME = "fontName";
    private final String FONT_SIZE = "fontSize";
    private Preferences prefs;

    private int fontSize;
    private boolean bold, italic, underline;
    private String fontName;

    public TextPrefs(EditorWindow currentWindow) {

        updatePrefs();

    }

    public void updatePrefs() {

        //TODO Make this static

        //https://www.vogella.com/tutorials/JavaPreferences/article.html
        //Site used for help on java's prefs API

        prefs = Preferences.userRoot().node("TextPrefs");

        // First we will get the values
        System.out.println(prefs.getBoolean(BOLD, false));
        System.out.println(prefs.getInt(FONT_SIZE, 11));
        System.out.println(prefs.get(FONT_NAME, "Tahoma"));


        bold = prefs.getBoolean(BOLD, false);
        fontSize = prefs.getInt(FONT_SIZE, 11);
        fontName = prefs.get(FONT_NAME, "Tahoma");

    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        prefs.putInt(FONT_SIZE, fontSize);
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {

        this.bold = bold;
        prefs.putBoolean(BOLD, bold);
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        prefs.put(FONT_NAME, fontName);
    }

}
