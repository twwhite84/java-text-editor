package nz.ac.massey.action;

import javax.swing.JOptionPane;

import nz.ac.massey.gui.TextEditorGUI;

public class ToggleStatusAction extends TextEditorAction {

    public ToggleStatusAction() {
        super("Status Bar");
    }

    @Override
    public boolean performAction(TextEditorGUI gui) {
        System.out.println("toggle status bar");

        /* menuItemStatusbar.addActionListener(e -> {
            String message;
            if (menuItemStatusbar.isSelected())
                message = "Status bar on";
            else
                message = "Status bar off";
            JOptionPane.showMessageDialog(menuItemWrap, message + "\nStatus bar not implemented yet",
                    "Todo", 0);
        }); */
        
        return true;
    }

}
