import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // If you want OPTIONAL login, show auth choice:
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Login/Register to save your profile?",
                    "Welcome",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                new AuthGateFrame().setVisible(true);
            } else {
                new DashboardFrame(null).setVisible(true); // anonymous mode
            }
        });
    }
}

/**
 * Simple gate: choose Login or Register
 */
class AuthGateFrame extends JFrame {
    public AuthGateFrame() {
        super("Health Dashboard - Auth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        loginBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });

        JPanel p = new JPanel();
        p.add(loginBtn);
        p.add(registerBtn);
        add(p);
    }
}
