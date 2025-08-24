package deductions;
/**
 * Represents the Quebec Pension Plan (QPP) deductions
 */
public class QCPensionPlan extends Deduction {

    /** The maximum salary considered for QPP deductions */
    final double MAX_QPP_SALARY = 71300;

    /** The maximum QPP deduction amount */
    final double MAX_QPP_DEDUCTION = 7700.40;

    /** The QPP tax rate */
    final double QPPRate = (10.8 / 100);

    /**
     * Constructs a QCPensionPlan instance with a given gross income
     * @param GrossIncome The employee gross income
     */
    public QCPensionPlan(double GrossIncome){
        super(GrossIncome);
    }

    /**
     * Calculates the Quebec Pension Plan deduction
     * @return The calculated QPP deduction amount
     */
    public double calculateTax() {
        if (GrossIncome >= MAX_QPP_SALARY) {
            return MAX_QPP_DEDUCTION;
        } else {
            return GrossIncome * QPPRate;
        }
    }
}