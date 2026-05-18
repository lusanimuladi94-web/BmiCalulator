import javax.swing.*;
import java.awt.*;


public class RegisterFrame extends JFrame {
    private final AuthService auth = new AuthService();
	//private final AuthService auth = new AuthService(DBConnection.connect());
	//AuthService.register();

    public RegisterFrame() {
        super("Register");
        UIStyle.styleFrame(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 330);
        setLocationRelativeTo(null);

        JTextField userField = new JTextField(18);
        JPasswordField passField = new JPasswordField(18);
        JPasswordField confirmField = new JPasswordField(18);

        UIStyle.styleTextField(userField);
        UIStyle.stylePasswordField(passField);
        UIStyle.stylePasswordField(confirmField);

        JButton registerBtn = new JButton("Create Account");
        UIStyle.styleButton(registerBtn);

        JButton backBtn = new JButton("Back");
        UIStyle.styleSecondaryButton(backBtn);

        registerBtn.addActionListener(e -> {
            String u = userField.getText().trim();
            String p = new String(passField.getPassword());
            String c = new String(confirmField.getPassword());

            if (!p.equals(c)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean ok = auth.register(u, p);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Registration failed (invalid input or user exists).",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Account created! Please login.");
            dispose();
            new LoginFrame().setVisible(true);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new AuthGateFrame().setVisible(true);
        });

        JPanel card = new JPanel(new GridBagLayout());
        UIStyle.styleCard(card);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        UIStyle.styleLabel(userLabel, 13, false);

        JLabel passLabel = new JLabel("Password:");
        UIStyle.styleLabel(passLabel, 13, false);

        JLabel confirmLabel = new JLabel("Confirm:");
        UIStyle.styleLabel(confirmLabel, 13, false);

        c.gridx = 0; c.gridy = 0;
        card.add(userLabel, c);
        c.gridx = 1;
        card.add(userField, c);

        c.gridx = 0; c.gridy = 1;
        card.add(passLabel, c);
        c.gridx = 1;
        card.add(passField, c);

        c.gridx = 0; c.gridy = 2;
        card.add(confirmLabel, c);
        c.gridx = 1;
        card.add(confirmField, c);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bottom.setOpaque(false);
        bottom.add(backBtn);
        bottom.add(registerBtn);

        setLayout(new BorderLayout());
        add(card, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
