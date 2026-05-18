import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class LoginFrame extends JFrame {
    private final AuthService auth = new AuthService();
	//private final AuthService auth = new AuthService(DBConnection.connect());
	//AuthService.login();

    private final JTextField userField = new JTextField(18);
    private final JPasswordField passField = new JPasswordField(18);

    private final JLabel timerLabel = new JLabel("Time left: 45s");
    private final JButton loginBtn = new JButton("Login");

    private javax.swing.Timer timer;
    private int secondsLeft = 45;

    public LoginFrame() {
        super("Login");
        UIStyle.styleFrame(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 300);
        setLocationRelativeTo(null);

        UIStyle.styleLabel(timerLabel, 14, true);

        UIStyle.styleTextField(userField);
        UIStyle.stylePasswordField(passField);

        UIStyle.styleButton(loginBtn);
        loginBtn.addActionListener(this::handleLogin);

        JButton backBtn = new JButton("Back");
        UIStyle.styleSecondaryButton(backBtn);
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

        c.gridx = 0; c.gridy = 0;
        card.add(userLabel, c);
        c.gridx = 1;
        card.add(userField, c);

        c.gridx = 0; c.gridy = 1;
        card.add(passLabel, c);
        c.gridx = 1;
        card.add(passField, c);

        c.gridx = 0; c.gridy = 2;
        card.add(timerLabel, c);
        c.gridx = 1;
        card.add(loginBtn, c);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.add(backBtn);

        setLayout(new BorderLayout());
        add(card, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        startLoginTimer();
    }

    private void startLoginTimer() {
        secondsLeft = 45;
        timerLabel.setText("Time left: " + secondsLeft + "s");

        loginBtn.setEnabled(true);
        userField.setEnabled(true);
        passField.setEnabled(true);

        timer = new javax.swing.Timer(1000, e -> {
            secondsLeft--;
            timerLabel.setText("Time left: " + secondsLeft + "s");
            if (secondsLeft <= 0) {
                timer.stop();
                loginBtn.setEnabled(false);
                userField.setEnabled(false);
                passField.setEnabled(false);

                JOptionPane.showMessageDialog(
                        this,
                        "Login window expired (45 seconds). Try again.",
                        "Time Expired",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    private void handleLogin(ActionEvent e) {
        String u = userField.getText().trim();
        String p = new String(passField.getPassword());

        boolean ok = auth.login(u, p);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        timer.stop();
        dispose();
        new DashboardFrame(u).setVisible(true);
    }
}
