package nz.ac.massey;

import lombok.Getter;
import nz.ac.massey.gui.TextEditorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents a menu action that is assigned to a keybinding
 */
@Getter
public class SimpleKeybindAction extends AbstractAction {

    /**
     * GUI Instance
     */
    private final TextEditorGUI gui;

    /**
     * Name of the {@link nz.ac.massey.action.TextEditorAction} to run
     */
    private final String name;

    /**
     * Keybind associated with this action
     */
    private final KeyStroke keybind;

    public SimpleKeybindAction(TextEditorGUI gui, String name, KeyStroke keybind) {
        super(name);
        this.gui = gui;
        this.name = name;
        this.keybind = keybind;

        putValue(Action.ACCELERATOR_KEY, keybind);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.runAction(name);
    }
}
