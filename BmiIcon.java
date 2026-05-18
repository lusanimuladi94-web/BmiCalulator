import javax.swing.*;
import java.awt.*;

public class BmiIcon implements Icon {
    private final int size;

    public BmiIcon(int size) {
        this.size = size;
    }

    @Override
    public int getIconWidth() { return size; }

    @Override
    public int getIconHeight() { return size; }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // background circle
            g2.setColor(Theme.WHITE);
            g2.fillOval(x, y, size, size);

            // ring
            g2.setColor(Theme.DARK_BLUE);
            g2.setStroke(new BasicStroke(3f));
            g2.drawOval(x + 3, y + 3, size - 6, size - 6);

            // arc
            g2.setStroke(new BasicStroke(6f));
            g2.setColor(Theme.MID_BLUE);
            g2.drawArc(x + 8, y + 10, size - 16, size - 20, 200, 120);

            // "BMI"
            g2.setColor(Theme.DARK_BLUE);
            Font font = new Font("SansSerif", Font.BOLD, Math.max(12, size / 4));
            g2.setFont(font);

            FontMetrics fm = g2.getFontMetrics();
            String text = "BMI";
            int tx = x + (size - fm.stringWidth(text)) / 2;
            int ty = y + size / 2 + fm.getAscent() / 4;

            g2.drawString(text, tx, ty);
        } finally {
            g2.dispose();
        }
    }
}
