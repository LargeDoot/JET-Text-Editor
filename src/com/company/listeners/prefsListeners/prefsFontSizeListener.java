package com.company.listeners.prefsListeners;

import com.company.EditorWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

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

            Font initialFont = window.getWindowFont();

            int newFontSize = (int) model.getValue() + window.getZoomLevel();

            window.getPrefs().setFontSize((int) model.getValue());
            window.setWindowFont(new Font(
                    initialFont.getFontName(), initialFont.getStyle(), newFontSize));

        }

        lastValue = model.getValue();

    }
}
