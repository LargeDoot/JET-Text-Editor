package com.company.preferences.listeners;

import com.company.EditorWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class prefsFontSizeListener implements ChangeListener {

    EditorWindow window;

    Object lastValue;
    SpinnerModel model;

    public prefsFontSizeListener(EditorWindow window) {

        this.window = window;
        lastValue = window.getWindowFont().getSize();

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        model = (SpinnerModel) e.getSource();

        if (lastValue != null && !model.getValue().equals(lastValue)) {

            System.out.println( (int)model.getValue() );

            Font initialFont = window.getWindowFont();

            window.getPrefs().setFontSize( (int)model.getValue() );
            window.setWindowFont( new Font (
                    initialFont.getFontName(), initialFont.getStyle(), (int)model.getValue() ) );

        }

        lastValue = model.getValue();

    }
}
