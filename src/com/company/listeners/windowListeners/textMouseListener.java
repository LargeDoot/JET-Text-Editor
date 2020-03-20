package com.company.listeners.windowListeners;

import com.company.EditorWindow;
import com.company.JetUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class textMouseListener implements MouseListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public textMouseListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Listener for mouse clicks inside of the text area, used for updating the Ln and Cl labels in the status bar of
     * the editor window. Uses a jet utils static method for doing this.
     *
     * @param e mouse click event
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        JetUtils.setLineNumbers(window);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Unused
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Unused
    }
}