import java.awt.*;

public class Theme {
    public static final Color DARK_BLUE = new Color(0x0B2A4A);   // primary
    public static final Color MID_BLUE  = new Color(0x2F7FD1);   // white-blue accents
    public static final Color ICE_BLUE  = new Color(0xDCEFFF);  // pale blue background
    public static final Color WHITE     = Color.WHITE;
    public static final Color TEXT      = new Color(0xEAF2FF);   // near-white for text on dark

    public static void applyFrameColors(javax.swing.JFrame f) {
        f.getContentPane().setBackground(ICE_BLUE);
    }
}
