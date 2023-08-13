package nz.ac.massey;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIContentPane {

  public Container getContentPane() {

    // main text area
    Container pane = new Container();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = 0;

    JTextArea textArea = new JTextArea(40, 120);
    textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.add(scrollPane, c);

    // status bar
    c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;

    JPanel statusBar = new JPanel();
    statusBar.setLayout(new GridBagLayout());

    GridBagConstraints d = new GridBagConstraints();
    d.gridx = 0;
    d.ipady = 20;
    d.ipadx = 20;
    JLabel lblCurrentLine = new JLabel("Current Line: 0");
    statusBar.add(lblCurrentLine, d);

    d = new GridBagConstraints();
    d.gridx = 1;
    d.ipady = 20;
    d.ipadx = 20;
    JLabel lblWordWrap = new JLabel("Word Wrap: OFF");
    statusBar.add(lblWordWrap, d);

    pane.add(statusBar, c);

    return pane;
  }
}