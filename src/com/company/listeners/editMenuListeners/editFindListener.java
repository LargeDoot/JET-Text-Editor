package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;
import com.company.FindDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class editFindListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public editFindListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Method to open a find dialog
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Simply opens a find dialog
        new FindDialog(window);

    }
}
