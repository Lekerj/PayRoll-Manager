package exceptions;
/**
 * Exception thrown when a negative or invalid value is encountered
 */
public class NegativeValueException extends Exception{

    /**
     * Constructs a NegativeValueException with a default error message
     */
    public NegativeValueException(){
        super("A non-positive value has been encountered!");
    }

    /**
     * Constructs a NegativeValueException with a custom error message
     * @param msg The custom error message
     */
    public NegativeValueException(String msg) {
        super(msg);
    }
}
