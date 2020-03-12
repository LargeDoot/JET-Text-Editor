package com.company.listeners.formatMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class formatWrapListener implements ItemListener {

    final EditorWindow window;

    public formatWrapListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        boolean result;

        result = event.getStateChange() == ItemEvent.SELECTED;

        window.setTextWrap(result);
        window.getPrefs().setWrapText(result);


    }

}
