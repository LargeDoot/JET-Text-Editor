package com.company.listeners.prefsListeners;

import com.company.preferences.PreferencesDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

public class prefsFontListener implements ItemListener {

    final PreferencesDialog dialog;

    public prefsFontListener(PreferencesDialog dialog) {

        this.dialog = dialog;

    }

    //Method from https://stackoverflow.com/questions/21091711/get-available-font-styles-for-font-family
    public static Set<String> getAvailableStyles(String name) {
        Set<String> styles = new HashSet<>();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        for (Font f : fonts) {
            if (f.getFamily().equals(name)) {
                styles.add(f.getName());
            }


        }

        return styles;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        //No font is changed here, the change is called in the fontStyle listener

        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object selectedFont = event.getItem();

            Set<String> fontStyles = getAvailableStyles(selectedFont.toString());

            DefaultComboBoxModel<String> currentModel = dialog.getDefaultComboBoxModel();
            currentModel.removeAllElements();

            for (String currentStyle : fontStyles) {

                currentModel.addElement(currentStyle);

            }

        }
    }

}
