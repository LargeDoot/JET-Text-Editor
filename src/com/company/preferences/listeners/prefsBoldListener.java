package com.company.preferences.listeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class prefsBoldListener extends JFrame implements ItemListener {

    EditorWindow window;

    public prefsBoldListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent actionEvent) {

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
