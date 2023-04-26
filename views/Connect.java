import javax.swing.*;
import java.awt.event.*;

public class Connect {
    private static JFrame frame = new JFrame("hill.net");
    private static int width = 400;
    private static int height = 300;
    private static final JLabel displayText = new JLabel("Welcome to hill.net!", SwingConstants.CENTER);
    private static JLabel usernameLabel = new JLabel("Name:");
    private static JLabel hostLabel = new JLabel("Host Name:");
    private static JLabel portLabel = new JLabel("Port Number:");
    private static JTextField usernameField = new JTextField();
    private static JTextField hostField = new JTextField();
    private static JTextField portField = new JTextField();
    private static JButton submitButton = new JButton("Login");

    public static void main (String args[]) {
        displayText.setBounds(25, 25, 350, 25);
        usernameLabel.setBounds(25, 75, 150, 25);
        hostLabel.setBounds(25, 125, 150, 25);
        portLabel.setBounds(25, 175, 150, 25);
        usernameField.setBounds(200, 75, 150, 25);
        hostField.setBounds(200, 125, 150, 25);
        portField.setBounds(200, 175, 150, 25);
        submitButton.setBounds(150, 225, 100, 25);

        
        frame.add(displayText);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(hostLabel);
        frame.add(hostField);
        frame.add(portLabel);
        frame.add(portField);
        frame.add(submitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setVisible(true);
        return;
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
