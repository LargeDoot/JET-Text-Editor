package com.company.listeners.windowListeners;

import com.company.EditorWindow;
import com.company.JetUtils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class textKeyListener implements KeyListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public textKeyListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Unused
    }

    /**
     * Calls the set line nums method contained in JET Utils class whenever a key is pressed in the text area.
     *
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        JetUtils.setLineNumbers(window);

    }
}
