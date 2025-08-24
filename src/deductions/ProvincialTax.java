package deductions;
/**
 * Represents the provincial tax calculation based on gross income
 */
public class ProvincialTax extends Deduction {

    /**
     * Constructs a ProvincialTax instance with a given gross income
     * @param GrossIncome The employee gross income
     */
    public ProvincialTax(double GrossIncome){
        super(GrossIncome);
    }

    /**
     * Calculates the provincial tax based on different income brackets
     * @return The calculated provincial tax amount
     */
    public double calculateTax() {

        boolean Margin14 = 18571 < GrossIncome && GrossIncome <= 53255;
        boolean Margin19 = 53255 < GrossIncome && GrossIncome <= 106495;
        boolean Margin24 = 106495 < GrossIncome && GrossIncome <= 129590;
        boolean Margin26 = 129590 < GrossIncome;

        if (Margin14) {
            return (GrossIncome * 14) / 100;
        } else if (Margin19) {
            return (GrossIncome * 19) / 100;
        } else if (Margin24) {
            return (GrossIncome * 24) / 100;
        } else if (Margin26) {
            return (GrossIncome * 25.75) / 100;
        } else return GrossIncome;
    }
}