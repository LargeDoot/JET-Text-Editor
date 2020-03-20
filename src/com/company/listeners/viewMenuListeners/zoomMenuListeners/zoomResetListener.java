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
public class zoomResetListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public zoomResetListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Set the zoom level to 0 in the window font and the preferences
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        window.setZoomLevel(0);
        window.getPrefs().setZoomAmount(0);

    }
}
