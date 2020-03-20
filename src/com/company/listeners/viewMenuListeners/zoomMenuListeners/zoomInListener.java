package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class zoomInListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public zoomInListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Increase the zoom level by 10, set the preferences and window font accordingly.
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Increase the zoom level by 10
        int newZoomLevel = window.getZoomLevel() + 10;

        //Set prefs and new font
        window.setZoomLevel(newZoomLevel);
        window.getPrefs().setZoomAmount(newZoomLevel);

    }
}
