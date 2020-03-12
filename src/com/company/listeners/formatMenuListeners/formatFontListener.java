package com.company.listeners.formatMenuListeners;

import com.company.EditorWindow;
import com.company.preferences.PreferencesDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formatFontListener implements ActionListener {

    final EditorWindow window;

    public formatFontListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new PreferencesDialog(window);

    }
}
