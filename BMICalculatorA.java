import javax.swing.*;
import java.awt.*;

public class BMICalculatorA extends JFrame {
    private JComboBox<String> unitChoiceBox;
    private JTextField weightField;
    private JTextField heightField;
    private JButton calculateButton;
    private JTextArea resultArea;

    public BMICalculatorA() {
        setTitle("BMI Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(30, 60, 120)); // deep blue background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels styled in white
        JLabel unitLabel = new JLabel("Select Unit System:");
        unitLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(unitLabel, gbc);

        unitChoiceBox = new JComboBox<>(new String[]{"Metric (kg, m)", "Imperial (lbs, in)"});
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(unitChoiceBox, gbc);

        JLabel weightLabel = new JLabel("Enter Weight:");
        weightLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(weightLabel, gbc);

        weightField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(weightField, gbc);

        JLabel heightLabel = new JLabel("Enter Height:");
        heightLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(heightLabel, gbc);

        heightField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(heightField, gbc);

        calculateButton = new JButton("Calculate BMI");
        calculateButton.setBackground(new Color(70, 130, 180)); // lighter blue
        calculateButton.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(calculateButton, gbc);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(240, 248, 255)); // very light blue
        resultArea.setForeground(new Color(0, 0, 80));
        resultArea.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(new JScrollPane(resultArea), gbc);

        add(panel);

        // Action listener
        calculateButton.addActionListener(e -> calculateBMI());
    }

    private void calculateBMI() {
        try {
            int unitChoice = unitChoiceBox.getSelectedIndex() + 1;
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            double bmi = (unitChoice == 1) ? weight / (height * height) : (703 * weight) / (height * height);

            String category;
            if (bmi >= 35) category = "YOU ARE SEVERELY OBESE....";
            else if (bmi >= 30) category = "YOU ARE OBESE.....";
            else if (bmi >= 25) category = "YOU ARE OVERWEIGHT.....";
            else if (bmi >= 18.5) category = "YOU ARE NORMAL WEIGHT....";
            else category = "YOU ARE UNDERWEIGHT....";

            resultArea.setText("Your BMI is: " + String.format("%.2f", bmi) + "\n" + category);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMICalculatorA().setVisible(true));
    }
}
