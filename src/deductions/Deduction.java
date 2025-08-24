package deductions;
/**
 * Abstract class representing a tax deduction based on gross income
 * All subclasses must implement the calculateTax() method
 */
public abstract class Deduction {
    /** The employee gross income used for tax calculations */
    protected double GrossIncome;

    /**
     * Constructs a Deduction initiator with a given gross income
     * @param GrossIncome The employee gross income
     */
    public Deduction(double GrossIncome) {
        this.GrossIncome = GrossIncome;
    }

    /**
     * Abstract method that calculates the specific tax or deduction
     * @return The calculated tax or deduction amount
     */
    public abstract double calculateTax();
}

