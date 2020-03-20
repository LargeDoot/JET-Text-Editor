package com.company.listeners.prefsListeners;

import com.company.EditorWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class prefsFontSizeListener implements ChangeListener {

    final EditorWindow window;

    SpinnerModel model;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public prefsFontSizeListener(EditorWindow window) {

        this.window = window;
//        lastValue = window.getWindowFont().getSize();

    }

    /**
     * Listens for a state change of the font size spinner (in the prefs dialog), and changes the font size of the
     * window accordingly. Zoom level is also taken into account when working out the font size so that it works
     * alongside the zoom feature. Preferences are also set so that font size will persist.
     *
     * @param e change event
     */
    @Override
    public void stateChanged(ChangeEvent e) {

        //Get the font size spinner model
        model = (SpinnerModel) e.getSource();

        Font initialFont = window.getWindowFont();

        //Work out the font size
        int newFontSize = (int) model.getValue() + window.getZoomLevel();

        //Set the preferences and the window font
        window.getPrefs().setFontSize((int) model.getValue());
        window.setWindowFont(new Font(
                initialFont.getFontName(), initialFont.getStyle(), newFontSize));


    }
}
