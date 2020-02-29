package com.company.preferences;

import com.company.EditorWindow;
import com.company.preferences.listeners.prefsBoldListener;

import javax.swing.*;
import java.awt.*;

public class PreferencesWindow extends JFrame {

    EditorWindow window;

    JToggleButton boldButton;
    JComboBox fontList;

    public PreferencesWindow(EditorWindow window) {

        super("Preferences");

        this.window = window;

        //Set the layout to border layout
        GridLayout layout = new GridLayout(2, 3);
        setLayout(layout);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        boldButton = new JToggleButton("Bold", window.getPrefs().isBold());
        boldButton.addItemListener(new prefsBoldListener(window));

        add(boldButton);

        fontList = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontList.addActionListener(prefsFontListener);
        add(fontList);

        setSize(300, 300);

        pack();

    }

}
