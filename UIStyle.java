import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class UIStyle {
    public static void styleFrame(JFrame f) {
        Theme.applyFrameColors(f);
        f.setForeground(Theme.DARK_BLUE);
    }

    public static void styleButton(JButton b) {
        b.setBackground(Theme.DARK_BLUE);
        b.setForeground(Theme.TEXT);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new CompoundBorder(
                new LineBorder(new Color(0x78B7FF), 2, true),
                new EmptyBorder(8, 14, 8, 14)
        ));
    }

    public static void styleSecondaryButton(JButton b) {
        b.setBackground(Theme.WHITE);
        b.setForeground(Theme.DARK_BLUE);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBorder(new CompoundBorder(
                new LineBorder(new Color(0x78B7FF), 2, true),
                new EmptyBorder(8, 14, 8, 14)
        ));
    }

    public static void styleLabel(JLabel l, int fontSize, boolean bold) {
        l.setForeground(Theme.DARK_BLUE);
        l.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, fontSize));
    }

    public static void styleTextField(JTextField t) {
        t.setBackground(Theme.WHITE);
        t.setForeground(Theme.DARK_BLUE);
        t.setCaretColor(Theme.DARK_BLUE);
        t.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xBBD7F2), 2, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    public static void stylePasswordField(JPasswordField t) {
        t.setBackground(Theme.WHITE);
        t.setForeground(Theme.DARK_BLUE);
        t.setCaretColor(Theme.DARK_BLUE);
        t.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xBBD7F2), 2, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    public static void styleCombo(JComboBox<?> cb) {
        cb.setBackground(Theme.WHITE);
        cb.setForeground(Theme.DARK_BLUE);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cb.setBorder(new CompoundBorder(
                new LineBorder(new Color(0xBBD7F2), 2, true),
                new EmptyBorder(2, 6, 2, 6)
        ));
    }

    public static void styleCard(JComponent c) {
        c.setBackground(Theme.ICE_BLUE);
        c.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0xBBD7F2), 2, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
    }
}
