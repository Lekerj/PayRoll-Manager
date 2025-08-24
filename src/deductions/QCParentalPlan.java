package deductions;
/**
 * Represents the Quebec Parental Insurance Plan (QPIP) deductions
 */
public class QCParentalPlan extends Deduction {

    /** The maximum salary considered for QPIP deductions */
    final double MAX_QPIP_SALARY = 98000;

    /** The maximum QPIP deduction amount */
    final double MAX_QPIP_DEDUCTION = 494.12;

    /** The QPIP tax rate */
    final double QPIPRate = 0.494 / 100;

    /**
     * Constructs a QCParentalPlan instance with a given gross income
     * @param GrossIncome The employee gross income.
     */
    public QCParentalPlan(double GrossIncome){
        super(GrossIncome);
    }

    /**
     * Calculates the Quebec Parental Insurance Plan deduction
     * @return The calculated QPIP deduction amount
     */
    public double calculateTax() {
        if (GrossIncome >= MAX_QPIP_SALARY) {
            return MAX_QPIP_DEDUCTION;
        } else {
            return GrossIncome * QPIPRate;
        }
    }
}
