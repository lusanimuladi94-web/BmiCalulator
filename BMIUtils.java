public class BMIUtils {
    public static double calcBMI(double weightKg, double heightCm) {
        double hMeters = heightCm / 100.0;
        return weightKg / (hMeters * hMeters);
    }

    public static String category(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        return "Obesity";
    }

    public static String bmiRangeHint() {
        return "BMI: <18.5 Underweight | 18.5–24.9 Normal | 25–29.9 Overweight | ≥30 Obesity";
    }
}
