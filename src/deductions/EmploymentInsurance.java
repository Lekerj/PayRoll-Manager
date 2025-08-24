package deductions;
/**
 * Represents Employment Insurance (EI) deductions
 */
public class EmploymentInsurance extends Deduction {

    /** The maximum salary considered for EI deductions */
    final double MAX_EI_SALARY = 65700;

    /** The maximum EI deduction amount */
    final double MAX_EI_DEDUCTION = 1077.48;

    /** The EI tax rate */
    final double EIRate = (1.64 / 100);

    /**
     * Constructs an EmploymentInsurance instance with a given gross income
     * @param GrossIncome The employee gross income
     */
    public EmploymentInsurance(double GrossIncome){
        super(GrossIncome);
    }

    /**
     * Calculates the Employment Insurance deduction
     * @return The calculated EI deduction amount
     */
    public double calculateTax() {
        if (GrossIncome >= MAX_EI_SALARY) {
            return MAX_EI_DEDUCTION;
        } else {
            return GrossIncome * EIRate;
        }
    }
}