package views;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class ClientView extends JFrame {
    private JPanel displayPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JTextArea screen = new JTextArea(5, 20);
    private static int width = 400, height = 250;

    public ClientView () {
        // set display panel
        screen.setLineWrap(true);
        screen.setText("Welcome to hillboy's voting machine");
        displayPanel.add(screen);
        displayPanel.setBackground(Color.WHITE);

        
        this.setTitle("hill.net");
        this.add(displayPanel, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showArguements () {
        JTextField nameField = new JTextField();
        JTextField portField = new JTextField();
        JTextField hostField = new JTextField();
        
        controlPanel.add(nameField);
        controlPanel.add(portField);
        controlPanel.add(hostField);
        this.repaint();
    }
}