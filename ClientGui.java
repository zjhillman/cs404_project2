import javax.swing.*;

public class ClientGui {
    public static void main (String[] args) {
        JFrame frame = new JFrame("hill.net");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JButton button = new JButton("Press Me");
        frame.getContentPane().add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        return;
    }
}