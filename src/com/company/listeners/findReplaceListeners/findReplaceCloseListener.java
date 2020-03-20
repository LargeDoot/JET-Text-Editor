package com.company.listeners.findReplaceListeners;

import com.company.EditorWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class findReplaceCloseListener implements WindowListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public findReplaceCloseListener(EditorWindow window) {

        this.window = window;

    }


    @Override
    public void windowOpened(WindowEvent e) {
        //Unused
    }

    /**
     * Removes all highlighting in the text area when the find/replace dialog is closed.
     *
     * @param e event
     */
    @Override
    public void windowClosing(WindowEvent e) {

        window.getTextArea().getHighlighter().removeAllHighlights();

    }

    @Override
    public void windowClosed(WindowEvent e) {
        //Unused
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //Unused
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //Unused
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //Unused
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //Unused
    }
}
