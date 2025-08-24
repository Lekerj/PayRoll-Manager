package deductions;
/**
 * Represents the federal tax calculation based on gross income
 */
public class FederalTax extends Deduction {

    /**
     * Constructs a FederalTax instance with a given gross income
     * @param GrossIncome The employee gross income
     */
    public FederalTax(double GrossIncome) {
        super(GrossIncome);
    }

    /**
     * Calculates the federal tax based on different income brackets
     * @return The calculated federal tax amount
     */
    public double calculateTax() {

        boolean NoTax = GrossIncome < 16129;
        boolean Margin15 = 16129 <= GrossIncome && GrossIncome <= 57375;
        boolean Margin21 = 57375 < GrossIncome && GrossIncome <= 114750;
        boolean Margin26 = 114751 < GrossIncome && GrossIncome <= 177882;
        boolean Margin29 = 177883 < GrossIncome && GrossIncome <= 253414;
        boolean Margin33 = 253414 < GrossIncome;

        if (NoTax) {
            return 0;
        } else if (Margin15) {
            return (GrossIncome * 15) / 100;
        } else if (Margin21) {
            return (GrossIncome * 20.5) / 100;
        } else if (Margin26) {
            return (GrossIncome * 26) / 100;
        } else if (Margin29) {
            return (GrossIncome * 29) / 100;
        } else if (Margin33) {
            return (GrossIncome * 33) / 100;
        } else return GrossIncome;
    }
}