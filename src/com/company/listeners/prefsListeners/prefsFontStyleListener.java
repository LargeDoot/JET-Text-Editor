package com.company.listeners.prefsListeners;

import com.company.EditorWindow;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class prefsFontStyleListener implements ItemListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public prefsFontStyleListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Check what caused the event, and if the user selected a font style, then get the current font and set the window
     * font to the initial font with the new style. Preferences are also set so that the font choice will persist.
     *
     * @param event state change event
     */
    @Override
    public void itemStateChanged(ItemEvent event) {

        //If the event was triggered from the user selecting an option
        if (event.getStateChange() == ItemEvent.SELECTED) {

            //Get the font and selected style
            Object selectedFont = event.getItem();
            Font initialFont = window.getWindowFont();

            //Set the font in preferences object and window
            window.getPrefs().setFontName(selectedFont.toString());
            window.setWindowFont(new Font(
                    selectedFont.toString(), initialFont.getStyle(), initialFont.getSize()));

        }

    }
}
