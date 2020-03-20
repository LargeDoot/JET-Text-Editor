package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.SaveDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class fileExitListener implements ActionListener {

    final EditorWindow window;
    final boolean checkSave;

    /**
     * Constructor to create the listener.
     *
     * @param window    the parent Editor Window
     * @param checkSave a boolean that when true, will make the listener check for differences in the text (see below)
     */
    public fileExitListener(EditorWindow window, boolean checkSave) {

        this.window = window;
        this.checkSave = checkSave;

    }

    /**
     * Compares the text stored in the JETFile to that in the Editor Window's text area. If there is a difference then a
     * save dialog will be created to prompt the user to choose between "Save", "Don't Save", or "Cancel". It only does
     * this check if the checkSave variable was set to true via the listener's constructor.
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String currentText = window.getText();
        String savedText = window.getCurrentFile().getTextContents();

        //Check if there is a difference in the saves text and the current text inside the JTextArea, and also if the
        // listener has been set to check for a save (set to false in some circumstances)
        if (!currentText.equals(savedText) && checkSave) {

            new SaveDialog(window);

        } else {
            window.dispose();

        }

    }

}
