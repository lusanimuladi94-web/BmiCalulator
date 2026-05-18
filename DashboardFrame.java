import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final String username;
	private final JLabel lblWeight = new JLabel("Weight (kg):");
    private final JLabel lblHeight = new JLabel("Height (cm):");

	//private final JComboBox<String> unitBox = new JComboBox<>(new String[]{"Metric (kg/cm)", "Imperial (lb/in)"});
    private final JTextField weightField = new JTextField("70", 10);
    private final JTextField heightField = new JTextField("170", 10);
	private final JComboBox<String> unitBox = new JComboBox<>(new String[]{
        "Metric (kg/cm)", "Imperial (lb/in)"
});

    private final JTextField ageField = new JTextField("30", 8);
    
	private final JComboBox<String> sexBox = new JComboBox<>(new String[]{"Male", "Female"});
    
    private final JComboBox<String> activityBox = new JComboBox<>(
            new String[]{"Sedentary", "Light", "Moderate", "Very Active"}
    );

    private final JLabel resultLabel = new JLabel("Enter data and calculate.");
    private final JTextArea recArea = new JTextArea();

    private final JButton calcBtn = new JButton("Calculate BMI & Plan");

    public DashboardFrame(String username) {
        super("BMI Dashboard");
        this.username = username;

        UIStyle.styleFrame(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 720);
        setLocationRelativeTo(null);

        // Root layout
        setLayout(new BorderLayout(12, 12));

        // ----- Header (with BMI icon) -----
        JPanel header = new JPanel(new BorderLayout(12, 10));
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(14, 16, 4, 16));

        JPanel headerText = new JPanel(new BorderLayout(0, 4));
        headerText.setOpaque(false);

        JLabel title = new JLabel("BMI Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Theme.DARK_BLUE);

        JLabel sub = new JLabel(username == null ? "Guest mode" : ("User: " + username));
        sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        sub.setForeground(Theme.DARK_BLUE);

        headerText.add(title, BorderLayout.CENTER);
        headerText.add(sub, BorderLayout.SOUTH);

        JLabel icon = new JLabel(new BmiIcon(70));
        icon.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(headerText, BorderLayout.CENTER);
        header.add(icon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ----- Main content -----
		
		
		
		
        JPanel main = new JPanel(new GridBagLayout());
        main.setOpaque(false);
        main.setBorder(BorderFactory.createEmptyBorder(0, 16, 16, 16));

        // Left card: inputs
		
		
        JPanel inputCard = new JPanel(new GridBagLayout());
        UIStyle.styleCard(inputCard);
		
		
		

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
		
		

        
		
		

        // Styles for inputs/buttons
        UIStyle.styleTextField(weightField);
        UIStyle.styleTextField(heightField);
        UIStyle.styleTextField(ageField);

        UIStyle.styleCombo(sexBox);
        UIStyle.styleCombo(activityBox);

        UIStyle.styleButton(calcBtn);
		
		JLabel lblUnit = new JLabel("Units:");
        UIStyle.styleLabel(lblUnit, 13, false);
		
		//JLabel lblUnit = new JLabel("Units:");
        //UIStyle.styleLabel(lblUnit, 13, false);

        c.gridx = 0; c.gridy = 6;
        inputCard.add(lblUnit, c);
        c.gridx = 1;
        inputCard.add(unitBox, c);

		
		c.gridx = 0; c.gridy = 6;
        inputCard.add(lblUnit, c);
        c.gridx = 1;
        inputCard.add(unitBox, c);
		
		unitBox.addActionListener(e -> {
       String unit = (String) unitBox.getSelectedItem();
       if (unit.startsWith("Imperial")) {
          lblWeight.setText("Weight (lbs):");
          lblHeight.setText("Height (inches):");
        } else {
           lblWeight.setText("Weight (kg):");
           lblHeight.setText("Height (cm):");
        }
});


        //JLabel lblWeight = new JLabel("Weight (kg):");
        UIStyle.styleLabel(lblWeight, 13, false);
        //JLabel lblHeight = new JLabel("Height (cm):");
        UIStyle.styleLabel(lblHeight, 13, false);
			
        JLabel lblAge = new JLabel("Age:");
        UIStyle.styleLabel(lblAge, 13, false);
        JLabel lblSex = new JLabel("Sex:");
        UIStyle.styleLabel(lblSex, 13, false);
        JLabel lblActivity = new JLabel("Activity:");
        UIStyle.styleLabel(lblActivity, 13, false);
		
		

        c.gridx = 0; c.gridy = 0;
        inputCard.add(lblWeight, c);
        c.gridx = 1;
        inputCard.add(weightField, c);

        c.gridx = 0; c.gridy = 1;
        inputCard.add(lblHeight, c);
        c.gridx = 1;
        inputCard.add(heightField, c);

        c.gridx = 0; c.gridy = 2;
        inputCard.add(lblAge, c);
        c.gridx = 1;
        inputCard.add(ageField, c);

        c.gridx = 0; c.gridy = 3;
        inputCard.add(lblSex, c);
        c.gridx = 1;
        inputCard.add(sexBox, c);

        c.gridx = 0; c.gridy = 4;
        inputCard.add(lblActivity, c);
        c.gridx = 1;
        inputCard.add(activityBox, c);

        c.gridx = 0; c.gridy = 5;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        inputCard.add(calcBtn, c);

        // Right card: results
        JPanel resultCard = new JPanel(new BorderLayout(10, 10));
        UIStyle.styleCard(resultCard);

        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel.setForeground(Theme.DARK_BLUE);

        recArea.setEditable(false);
        recArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        recArea.setBackground(Theme.WHITE);
        recArea.setForeground(Theme.DARK_BLUE);

        JScrollPane scroll = new JScrollPane(recArea);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0xBBD7F2), 2, true));
        scroll.getViewport().setBackground(Theme.WHITE);

        resultCard.add(resultLabel, BorderLayout.NORTH);
        resultCard.add(scroll, BorderLayout.CENTER);

        // Add both cards to main
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0, 0, 0, 0);
        gc.fill = GridBagConstraints.BOTH;

        gc.gridx = 0; gc.gridy = 0;
        gc.weightx = 0.32; gc.weighty = 1.0;
        main.add(inputCard, gc);

        gc.gridx = 1; gc.gridy = 0;
        gc.weightx = 0.68;
        main.add(resultCard, gc);

        add(main, BorderLayout.CENTER);

        // ----- Bottom (Close) -----
        JButton closeBtn = new JButton("Close");
        UIStyle.styleSecondaryButton(closeBtn);
        closeBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 16));
        bottom.add(closeBtn);

        add(bottom, BorderLayout.SOUTH);

        // Action
        calcBtn.addActionListener(e -> calculate());
    }

    private void calculate() {
        try {
            //double w = Double.parseDouble(weightField.getText().trim());
            //double h = Double.parseDouble(heightField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());
			
			String unit = (String) unitBox.getSelectedItem();
			double w = Double.parseDouble(weightField.getText().trim());
			double h = Double.parseDouble(heightField.getText().trim());

			if (unit.startsWith("Imperial")) {
				JLabel lblWeight = new JLabel("Weight (lbs):");
                UIStyle.styleLabel(lblWeight, 13, false);
                JLabel lblHeight = new JLabel("Height (Inches):");
                UIStyle.styleLabel(lblHeight, 13, false);
				// Convert pounds → kg, inches → cm
				w = w * 0.453592;
				h = h * 2.54;
			}


            if (w <= 0 || h <= 0) throw new NumberFormatException();
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, "Age seems invalid (1–120).");
                return;
            }
            if (h < 80 || h > 250) {
                JOptionPane.showMessageDialog(this, "Height seems out of range (80–250 cm).");
                return;
            }

            double bmi = BMIUtils.calcBMI(w, h);
            String cat = BMIUtils.category(bmi);

            String sex = (String) sexBox.getSelectedItem();
            String activity = (String) activityBox.getSelectedItem();

            RecommendationEngine.Rec rec = RecommendationEngine.build(bmi, w, h, age, sex, activity);

            resultLabel.setText(String.format("Your BMI: %.2f (%s)", bmi, cat));

            recArea.setText(
                    "Health Recommendations (with profile):\n" +
                    "------------------------------------------------\n" +
                    "Category: " + rec.bmiCategory + "\n\n" +
                    "Calories:\n" + rec.calories + "\n\n" +
                    "Water Intake:\n" + rec.water + "\n\n" +
                    "Exercise Plan:\n• " + rec.exercise.replace("\n• ", "\n• ") + "\n\n" +
                    "Nutrients:\n" + rec.nutrients + "\n\n" +
                    "BMI ranges: " + BMIUtils.bmiRangeHint()
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric weight/height/age.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
