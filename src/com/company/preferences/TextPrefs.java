package com.company.preferences;

import com.company.EditorWindow;

import java.awt.*;
import java.util.prefs.Preferences;

public class TextPrefs {

    private final String BOLD = "bold";
    private Preferences prefs;
    private int fontSize;
    private boolean bold, italic, underline;
    private String colour;
    private EditorWindow currentWindow;

    public TextPrefs(EditorWindow currentWindow) {

        this.currentWindow = currentWindow;
        updatePrefs();

    }

    public void updatePrefs() {

        //TODO Make this static

        //https://www.vogella.com/tutorials/JavaPreferences/article.html
        //Site used for help on java's prefs API

        prefs = Preferences.userRoot().node("TextPrefs");

        // First we will get the values
        System.out.println(prefs.getBoolean(BOLD, false));
        System.out.println(prefs.getBoolean("italic", false));
        System.out.println(prefs.getBoolean("underline", false));

        bold = prefs.getBoolean(BOLD, false);

    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {

        this.bold = bold;
        prefs.putBoolean(BOLD, bold);
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
