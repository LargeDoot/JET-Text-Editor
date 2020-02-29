package com.company.preferences.listeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class prefsBoldListener implements ItemListener {

    EditorWindow window;

    public prefsBoldListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getStateChange() == ItemEvent.SELECTED) {

            setFontStyle(true, Font.BOLD, "Setting BOLD to TRUE");
        } else {

            setFontStyle(false, Font.PLAIN, "Setting BOLD to FALSE");
        }


    }

    private void setFontStyle(boolean prefsSetBold, int style, String message) {

        Font initialFont = window.getFont();

        window.getPrefs().setBold(prefsSetBold);
        window.setWindowFont( new Font( initialFont.getFontName(), style, initialFont.getSize() ) );
        System.out.println(message);
    }

}
