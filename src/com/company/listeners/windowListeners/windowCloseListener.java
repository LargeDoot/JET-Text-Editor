package com.company.listeners.windowListeners;

import com.company.EditorWindow;
import com.company.SaveDialog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class windowCloseListener implements WindowListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public windowCloseListener(EditorWindow window) {

        this.window = window;

    }


    @Override
    public void windowOpened(WindowEvent e) {
        //Unused
    }

    /**
     * Triggered when an editor window is closing, and checks that the user has saved any changes made to the text area
     * by comparing the current contents of the text area to the text stored in the JETFile. If there are differences,
     * then a new save dialog is displayed to the user.
     *
     * @param e event
     */
    @Override
    public void windowClosing(WindowEvent e) {

        //Set the two thing to compare
        String currentText = window.getText();
        String savedText = window.getCurrentFile().getTextContents();

        //If they are different
        if (!currentText.equals(savedText)) {

            //Display a save dialog
            new SaveDialog(window);

        } else {
            //Close the window
            window.dispose();
        }

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
