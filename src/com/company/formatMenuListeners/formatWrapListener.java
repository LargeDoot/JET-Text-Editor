package com.company.formatMenuListeners;

import com.company.EditorWindow;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class formatWrapListener implements ItemListener {

    EditorWindow window;

    public formatWrapListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getStateChange() == ItemEvent.SELECTED) {

            window.setTextWrap(true);
            System.out.println("Setting wrap true");
        } else {

            window.setTextWrap(false);
            System.out.println("Setting wrap true");
        }


    }

}
