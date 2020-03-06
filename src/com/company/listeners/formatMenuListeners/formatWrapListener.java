package com.company.listeners.formatMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class formatWrapListener implements ItemListener {

    EditorWindow window;

    public formatWrapListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        Boolean result;

        if (event.getStateChange() == ItemEvent.SELECTED) {

            result = true;
        } else {

            result = false;
        }

        window.setTextWrap(result);
        window.getPrefs().setWrapText(result);


    }

}
