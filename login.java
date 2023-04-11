import javax.swing.*;
import java.awt.event.*;

public class Login {
    private static JFrame frame = new JFrame("LoginServices.exe");
    private static int width = 400;
    private static int height = 250;

    public static void main (String args[]) {
        final JLabel infoDisplay = new JLabel("Welcome to hill.net!", SwingConstants.CENTER);
        JLabel usernameLabel = new JLabel("Username/Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Login");
        
        infoDisplay.setBounds(25, 25, 350, 25);
        usernameLabel.setBounds(25, 75, 150, 25);
        passwordLabel.setBounds(25, 125, 150, 25);
        usernameField.setBounds(200, 75, 150, 25);
        passwordField.setBounds(200, 125, 150, 25);
        submitButton.setBounds(150, 175, 100, 25);

        
        frame.add(infoDisplay);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(submitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String data = "Logging in as " + usernameField.getText();
                data += " with password " + new String(passwordField.getPassword());
                infoDisplay.setText(data);
                infoDisplay.setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
        return;
    }
}
