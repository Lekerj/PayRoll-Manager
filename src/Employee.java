/**
 * Represents an Employee with attributes such as
 * ID, first name, last name, hours worked, hourly rate, and gross income
 *
 * The class provides constructors, getter and setter methods, and overrides
 * the equals and toString methods
 *
 * @author Ahmed Gara Ali (40310578)
 */
public class Employee {

    /** The unique identifier for the employee. */
    private long ID;

    /** The first name of the employee. */
    private String FirstName;

    /** The last name of the employee. */
    private String LastName;

    /** The total number of hours worked per week by the employee. */
    private double HoursWorked;

    /** The hourly rate of pay for the employee. */
    private double HourRate;

    /** The calculated gross income of the employee. */
    private double GrossIncome;

    /**
     * Default constructor for Employee
     * Initializes an empty Employee object with default values
     */
    public Employee() {
    }

    /**
     * Constructs an Employee object with specified attributes
     * The Gross Income is calculated for 52 weeks in a year
     * @param ID          The ID for the employee
     * @param firstName   The first name of the employee
     * @param lastName    The last name of the employee
     * @param hoursWorked The number of hours worked per week
     * @param hourRate    The hourly pay rate
     */
    public Employee(long ID, String firstName, String lastName, double hoursWorked, double hourRate) {
        this.ID = ID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.HoursWorked = hoursWorked;
        this.HourRate = hourRate;
        this.GrossIncome = Math.round((52.00 * HourRate * HoursWorked) * 100.00) / 100.00;
    }

    /**
     * Gets the Employee ID
     * @return The ID of the employee
     */
    public long getID() {
        return ID;
    }

    /**
     * Gets the first name of the employee
     * @return The first name of the employee
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Gets the last name of the employee
     * @return The last name of the employee
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Gets the number of hours worked per week
     * @return The number of hours worked per week
     */
    public double getHoursWorked() {
        return HoursWorked;
    }

    /**
     * Gets the hourly pay rate of the employee
     * @return The hourly rate of the employee
     */
    public double getHourRate() {
        return HourRate;
    }

    /**
     * Gets the gross income of the employee
     * @return The calculated gross income
     */
    public double getGrossIncome() {
        return GrossIncome;
    }

    /**
     * Sets the Employee ID
     * @param ID The new ID for the employee
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * Sets the first name of the employee
     * @param firstName The new first name of the employee
     */
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    /**
     * Sets the last name of the employee
     * @param lastName The new last name of the employee
     */
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    /**
     * Sets the number of hours worked per week
     * @param hoursWorked The new number of hours worked
     */
    public void setHoursWorked(double hoursWorked) {
        this.HoursWorked = hoursWorked;
    }

    /**
     * Sets the hourly rate of the employee
     * @param hourRate The new hourly rate
     */
    public void setHourRate(double hourRate) {
        this.HourRate = hourRate;
    }

    /**
     * Sets the gross income of the employee
     * @param grossIncome The new gross income value
     */
    public void setGrossIncome(double grossIncome) {
        this.GrossIncome = grossIncome;
    }

    /**
     * Checks if two Employee objects are equal based on their attributes
     * @param o The object to compare with
     * @return true if the Employee are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;
        return getID() == employee.getID() &&
                getHourRate() == employee.getHourRate() &&
                getHoursWorked() == employee.getHoursWorked() &&
                getFirstName().equals(employee.getFirstName()) &&
                getLastName().equals(employee.getLastName());
    }

    /**
     * Returns a string representation of the Employee object
     * @return A formatted string containing employee details
     */
    public String toString() {
        return "ID: " + ID +
                "\nFirst Name: " + FirstName +
                "\nLast Name: " + LastName +
                "\nHours Worked: " + HoursWorked +
                "\nHour Rate: " + HourRate +
                "\nGross Income: " + GrossIncome;
    }
}
