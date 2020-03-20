package com.company.listeners.prefsListeners;

import com.company.preferences.PreferencesDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class prefsFontListener implements ItemListener {

    final PreferencesDialog dialog;

    /**
     * Constructor to create the listener.
     *
     * @param dialog the parent prefs dialog
     */
    public prefsFontListener(PreferencesDialog dialog) {

        this.dialog = dialog;

    }

    /**
     * Takes a font name as a parameter and finds all available styles of the specified font, provided by the OS Help
     * from https://stackoverflow.com/questions/21091711/get-available-font-styles-for-font-family
     *
     * @param name font name to find styles for
     * @return a set containing the font styles available for the given font family
     */
    public static Set<String> getAvailableStyles(String name) {

        Set<String> styles = new HashSet<>();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();

        //Get all the available systems fonts
        Font[] fonts = e.getAllFonts();

        //Check if the current font matches what the user selected
        for (Font f : fonts) {
            if (f.getFamily().equals(name)) {
                //Add the style as it is from the family we want
                styles.add(f.getName());
            }

        }

        return styles;
    }

    /**
     * When a font is selected from the font family dropdown (in the prefs dialog), then this method will take the
     * selected font and find all of it's available styles (bold, italic etc.) and place each style into the font style
     * dropdown.
     * <p>
     * No font is changed here, the change is called in the fontStyle listener
     *
     * @param event state change event
     */
    @Override
    public void itemStateChanged(ItemEvent event) {

        //If the event was triggered from a user selecting something
        if (event.getStateChange() == ItemEvent.SELECTED) {

            //Get the item that was selected
            Object selectedFont = event.getItem();

            //Get all the styles available for the selected font
            Set<String> fontStyles = getAvailableStyles(selectedFont.toString());

            //Get the model for the font style combo box and remove all the elements from it
            DefaultComboBoxModel<String> currentModel = dialog.getDefaultComboBoxModel();
            currentModel.removeAllElements();

            //Add all the found font styles to the new model
            for (String currentStyle : fontStyles) {

                currentModel.addElement(currentStyle);

            }

        }
    }


}
