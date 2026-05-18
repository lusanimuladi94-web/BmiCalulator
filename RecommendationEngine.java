import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {

    public static class Rec {
        public String bmiCategory;
        public String exercise;
        public String water;
        public String calories;
        public String nutrients;
    }

    public static Rec build(double bmi, double weightKg, double heightCm, int age, String sex, String activityLevel) {
        String cat = BMIUtils.category(bmi);
        Rec r = new Rec();
        r.bmiCategory = cat;

        // --- Calories: Mifflin-St Jeor ---
        // BMR = 10W + 6.25H - 5A + s
        // s = +5 male, -161 female
        double s = "Male".equalsIgnoreCase(sex) ? 5 : -161;
        double bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) + s;

        double activityFactor;
        switch (activityLevel) {
            case "Sedentary" -> activityFactor = 1.2;
            case "Light" -> activityFactor = 1.375;
            case "Moderate" -> activityFactor = 1.55;
            case "Very Active" -> activityFactor = 1.725;
            default -> activityFactor = 1.2;
        }

        double tdee = bmr * activityFactor;

        // Simple BMI-based adjustment (demo heuristic)
        double target = tdee;
        if (cat.equals("Underweight")) target = tdee * 1.10;
        else if (cat.equals("Normal")) target = tdee;
        else if (cat.equals("Overweight")) target = tdee * 0.88;
        else target = tdee * 0.80;

        r.calories = "Estimated daily target: ~" + Math.round(target) +
                " kcal/day (BMR/TDEE + BMI adjustment; refine with progress).";

        // --- Water ---
        // Base: 0.033 L/kg (normal) with BMI adjustment + slight activity bump.
        double base = 0.033 * weightKg;

        double bmiAdj;
        if (cat.equals("Underweight")) bmiAdj = 1.05;
        else if (cat.equals("Normal")) bmiAdj = 1.00;
        else if (cat.equals("Overweight")) bmiAdj = 0.98;
        else bmiAdj = 0.95;

        double actAdj;
        switch (activityLevel) {
            case "Sedentary" -> actAdj = 1.00;
            case "Light" -> actAdj = 1.03;
            case "Moderate" -> actAdj = 1.06;
            case "Very Active" -> actAdj = 1.10;
            default -> actAdj = 1.00;
        }

        double waterL = base * bmiAdj * actAdj;
        waterL = clamp(waterL, 1.2, 6.5);

        r.water = "Aim ~" + String.format("%.1f", waterL) +
                " L/day. Split across the day; add more if you sweat heavily.";

        // --- Exercise ---
        r.exercise = exercisePlan(cat, bmi, activityLevel);

        // --- Nutrients (macro guidance) ---
        // Use protein per kg by category, and emphasize fiber + food quality.
        double proteinGPerKg;
        if (cat.equals("Underweight")) proteinGPerKg = 1.6;
        else if (cat.equals("Normal")) proteinGPerKg = 1.3;
        else if (cat.equals("Overweight")) proteinGPerKg = 1.6;
        else proteinGPerKg = 1.7;

        double proteinTarget = proteinGPerKg * weightKg;

        if (cat.equals("Underweight")) {
            r.nutrients = "Protein ~" + Math.round(proteinTarget) + " g/day (≈" + proteinGPerKg + " g/kg).\n" +
                    "Add nutrient-dense calories: nuts, olive oil, eggs, dairy/alternatives, legumes.\n" +
                    "Carbs support training; include vitamin/mineral-rich fruits/vegetables.";
        } else if (cat.equals("Normal")) {
            r.nutrients = "Protein ~" + Math.round(proteinTarget) + " g/day.\n" +
                    "Carbs from whole grains/legumes; fiber-rich veggies daily.\n" +
                    "Healthy fats: olive oil, avocado, nuts; limit ultra-processed snacks.";
        } else if (cat.equals("Overweight")) {
            r.nutrients = "Protein ~" + Math.round(proteinTarget) + " g/day for fullness + muscle.\n" +
                    "Increase fiber to ~25–35 g/day; reduce refined carbs/sugary drinks.\n" +
                    "Use unsaturated fats; prioritize lean proteins and vegetables.";
        } else {
            r.nutrients = "Protein ~" + Math.round(proteinTarget) + " g/day.\n" +
                    "Higher fiber (25–40 g/day) + lower added sugar.\n" +
                    "Choose mostly whole foods; watch portion sizes and liquid calories.";
        }

        return r;
    }

    private static String exercisePlan(String cat, double bmi, String activityLevel) {
        List<String> lines = new ArrayList<>();

        // Base recommendation by category
        if (cat.equals("Underweight")) {
            lines.add("Strength training 3–4x/week (full-body): squats/leg press, rows, push/pull, hip hinges.");
            lines.add("Progress gradually; aim for 8–12 reps, keep 1–3 reps in reserve.");
            lines.add("Optional light cardio 1–2x/week (zone 2) if recovery is good.");
        } else if (cat.equals("Normal")) {
            lines.add("Maintain: strength 2–3x/week + cardio 150–300 min/week (zone 2).");
            lines.add("Add mobility/posture work 10 min/day.");
        } else if (cat.equals("Overweight")) {
            lines.add("Lower-impact cardio 4–5x/week: brisk walking/cycling/swimming (30–45 min).");
            lines.add("Strength 2–3x/week to protect muscle (legs/back/hips/core).");
            lines.add("Daily steps: gradually increase (+500/day over baseline).");
        } else {
            lines.add("Start gentle and joint-friendly: walking, cycling, swimming—consistency first.");
            lines.add("Strength 2–3x/week with safe form (core + glute/leg work).");
            lines.add("Because BMI is high: consider medical guidance; increase intensity slowly.");
        }

        // Activity-level note
        if ("Sedentary".equals(activityLevel)) lines.add("If sedentary: begin with 10–20 min walks and build week-by-week.");
        return String.join("\n• ", lines);
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
