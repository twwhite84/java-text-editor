package nz.ac.massey.action;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nz.ac.massey.gui.TextEditorGUI;

public class SearchAction extends TextEditorAction {

  public SearchAction() {
    super("Search");
  }

  @Override
  public void performAction(TextEditorGUI gui) {
    /*
     * String query = JOptionPane.showInputDialog(null, "Search for...", "Search",
     * JOptionPane.PLAIN_MESSAGE, null, null,
     * null).toString();
     * 
     * // just find the first instance matching search. highlight it in editor.
     * // gui.getGuiContentPane().getTextArea().
     * String myText = gui.getGuiContentPane().getTextArea().getText();
     * System.out.println(myText.indexOf(query));
     * 
     * //need to highlight
     */

    JInternalFrame searchFrame = new JInternalFrame("Search", false, true);

    JPanel searchPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = 0;
    JLabel lblSearch = new JLabel("Search");
    searchPanel.add(lblSearch, c);

    c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    JTextField txtSearchField = new JTextField(20);
    searchPanel.add(txtSearchField, c);

    c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 0;
    JButton btnSearchNext = new JButton("Next");
    searchPanel.add(btnSearchNext);

    c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 1;
    JButton btnSearchPrev = new JButton("Prev");
    searchPanel.add(btnSearchPrev);

    // components here

    searchFrame.setContentPane(searchPanel);
    searchFrame.pack();

    c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTH;
    gui.getGuiContentPane().add(searchFrame, c);
    searchFrame.setVisible(true);
  }

}
