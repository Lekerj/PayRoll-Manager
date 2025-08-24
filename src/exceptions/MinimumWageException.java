package exceptions;
/**
 * Exception thrown when an employee's hourly wage is below the minimum wage
 */
public class MinimumWageException extends Exception {

    /**
     * Constructs a MinimumWageException with a default error message
     */
    public MinimumWageException(){
        super("Below minimum wage salary encountered!");
    }

    /**
     * Constructs a MinimumWageException with a custom error message
     * @param msg The custom error message
     */
    public MinimumWageException(String msg){
        super(msg);
    }
}