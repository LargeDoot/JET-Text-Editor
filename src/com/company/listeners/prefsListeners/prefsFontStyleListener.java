package com.company.listeners.prefsListeners;

import com.company.EditorWindow;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class prefsFontStyleListener implements ItemListener {

    EditorWindow window;

    public prefsFontStyleListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object selectedFont = event.getItem();
            Font initialFont = window.getWindowFont();

            window.getPrefs().setFontName(selectedFont.toString());
            window.setWindowFont( new Font (
                    selectedFont.toString(), initialFont.getStyle(), initialFont.getSize() ) );

        }

    }
}
