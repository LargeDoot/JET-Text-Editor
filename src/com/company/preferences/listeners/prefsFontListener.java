package com.company.preferences.listeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class prefsFontListener extends JFrame implements ActionEvent {

    EditorWindow window;

    public prefsFontListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ActionEvent actionEvent) {

        if (actionEvent.getStateChange() == ItemEvent.SELECTED) {

            window.getPrefs().setBold(true);
            window.setTextFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            System.out.println("Setting BOLD to TRUE");
        } else {

            window.getPrefs().setBold(false);
            window.setTextFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
            System.out.println("Setting BOLD to FALSE");
        }


    }
}
