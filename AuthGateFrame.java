import javax.swing.*;
import java.awt.*;

public class AuthGateFrame extends JFrame {
    public AuthGateFrame() {
        super("Health Dashboard - Auth");
        UIStyle.styleFrame(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome");
        UIStyle.styleLabel(title, 20, true);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");
        UIStyle.styleButton(loginBtn);
        UIStyle.styleButton(registerBtn);

        loginBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });

        JPanel card = new JPanel(new GridBagLayout());
        UIStyle.styleCard(card);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        card.add(title, c);

        c.gridy = 1; c.gridwidth = 1; c.gridx = 0; c.anchor = GridBagConstraints.CENTER;
        card.add(loginBtn, c);

        c.gridx = 1;
        card.add(registerBtn, c);

        setContentPane(card);
    }
}
