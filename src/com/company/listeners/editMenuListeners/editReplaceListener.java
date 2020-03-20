package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;
import com.company.ReplaceDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class editReplaceListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public editReplaceListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Method to open a new replace dialog.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Simply create a replace dialog
        new ReplaceDialog(window);

    }
}
