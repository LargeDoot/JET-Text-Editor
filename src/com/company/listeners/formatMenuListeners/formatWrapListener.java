package com.company.listeners.formatMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class formatWrapListener implements ItemListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public formatWrapListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Triggers when the wrap text button's state is changed, and will set the wrapText variable in the parent
     * editorWindow to whatever the state is (true for wrap), and also sets the preference so that the setting will
     * persist.
     *
     * @param event state change event
     */
    @Override
    public void itemStateChanged(ItemEvent event) {

        boolean result;

        result = event.getStateChange() == ItemEvent.SELECTED;

        window.setTextWrap(result);
        window.getPrefs().setWrapText(result);


    }

}
