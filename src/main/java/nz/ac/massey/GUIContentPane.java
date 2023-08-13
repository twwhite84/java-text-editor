package nz.ac.massey;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIContentPane {

  public Container getContentPane() {

    Container pane = new Container();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;

    JTextArea textArea = new JTextArea(40, 120);
    textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.add(scrollPane);

    return pane;
  }
}