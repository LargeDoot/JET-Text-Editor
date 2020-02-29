package com.company.fileMenuListeners;

import com.company.EditorWindow;
import com.company.JETFile;
import com.company.preferences.PreferencesWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formatFontListener extends JFrame implements ActionListener {

    EditorWindow window;

    public formatFontListener() {
    }

    public formatFontListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new PreferencesWindow(window).setVisible(true);

    }
}
