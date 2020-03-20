package com.company.listeners.formatMenuListeners;

import com.company.EditorWindow;
import com.company.preferences.PreferencesDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formatFontListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public formatFontListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Opens a new preferences dialog
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new PreferencesDialog(window);

    }
}
