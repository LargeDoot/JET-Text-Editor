package com.company.preferences.listeners;

import com.company.EditorWindow;
import com.company.preferences.PreferencesDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

public class prefsFontListener implements ItemListener {

    EditorWindow window;
    PreferencesDialog dialog;

    public prefsFontListener(EditorWindow window, PreferencesDialog dialog) {

        this.window = window;
        this.dialog = dialog;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        //No font is changed here, the change is called in the fontStyle listener

        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object selectedFont = event.getItem();

            Set<String> fontStyles = getAvailableStyles(selectedFont.toString());

            DefaultComboBoxModel currentModel = dialog.getDefaultComboBoxModel();
            currentModel.removeAllElements();

//            ItemListener[] listener =  currentModel.getListeners(ItemListener.class);//todo get the listener and disable it somehow to stop the listneer triggering when the combno box is populated!!

            System.out.println("testomng");

            for (String currentStyle : fontStyles) {

                currentModel.addElement(currentStyle);

            }

        }
    }

    //Method from https://stackoverflow.com/questions/21091711/get-available-font-styles-for-font-family
    public static Set<String> getAvailableStyles(String name) {
        Set<String> styles = new HashSet<String>();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        for (Font f : fonts)
        {
            if ( f.getFamily().equals(name) ){
                styles.add(f.getName());
            }


        }

        return styles;
    }

}
