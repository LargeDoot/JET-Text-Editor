package com.company.preferences;

import com.company.EditorWindow;

import java.awt.*;
import java.util.prefs.Preferences;

public class TextPrefs {

    private final String FONT_NAME = "fontName";
    private final String FONT_SIZE = "fontSize";
    private final String SHOW_TOOLBAR = "showToolbar";
    private final String WRAP_TEXT = "wrapText";
    private final String ZOOM_AMOUNT = "zoomAmount";
    private Preferences prefs;

    private int fontSize, zoomAmount;
    private boolean showToolbar, wrapText;
    private String fontName;

    public TextPrefs(EditorWindow currentWindow) {

        updatePrefs();

    }

    public void updatePrefs() {

        //https://www.vogella.com/tutorials/JavaPreferences/article.html
        //Site used for help on java's prefs API

        prefs = Preferences.userRoot().node("TextPrefs");

        fontSize = prefs.getInt(FONT_SIZE, 11);
        fontName = prefs.get(FONT_NAME, "Tahoma");

        zoomAmount = prefs.getInt(ZOOM_AMOUNT, 0);

        showToolbar = prefs.getBoolean(SHOW_TOOLBAR, true);
        wrapText = prefs.getBoolean(WRAP_TEXT, true);

    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        prefs.putInt(FONT_SIZE, fontSize);
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        prefs.put(FONT_NAME, fontName);
    }

    public int getZoomAmount() {
        return zoomAmount;
    }

    public void setZoomAmount(int zoomAmount) {
        this.zoomAmount = zoomAmount;
        prefs.putInt(ZOOM_AMOUNT, zoomAmount);
    }

    public boolean isShowToolbar() {
        return showToolbar;
    }

    public void setShowToolbar(boolean showToolbar) {
        this.showToolbar = showToolbar;
        prefs.putBoolean(SHOW_TOOLBAR, showToolbar);
    }

    public boolean isWrapText() {
        return wrapText;
    }

    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;
        prefs.putBoolean(WRAP_TEXT, wrapText);
    }
}
