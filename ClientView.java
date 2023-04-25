import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class ClientView extends JFrame {
    private JPanel displayPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JTextArea screen = new JTextArea(5, 20);
    private static int width = 400, height = 250;

    public ClientView () {
        screen.setLineWrap(true);
        screen.setText("Welcome to hillboy's voting machine");
        displayPanel.add(screen);
        displayPanel.setBackground(Color.WHITE);

        this.add(displayPanel, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}