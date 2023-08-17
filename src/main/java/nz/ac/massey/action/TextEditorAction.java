package nz.ac.massey.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nz.ac.massey.gui.TextEditorGUI;

/**
 * Represents a function or action that the editor can perform.
 * Usually invoked from the menu bar
 */
@RequiredArgsConstructor
public abstract class TextEditorAction {


    /**
     * The name of the action
     */
    @Getter
    private final String name;

    /**
     * The action function that consumes the main GUI window
     */
    public abstract void performAction(TextEditorGUI gui);

}
