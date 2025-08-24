package exceptions;
/**
 * Exception thrown when an invalid file format or unexpected value is encountered
 */
public class InvalidFileFormatException extends Exception{

    /**
     * Constructs an InvalidFileFormatException with a default error message
     */
    public InvalidFileFormatException(){
        super("An unexpected value has been encountered!");
    }

    /**
     * Constructs an InvalidFileFormatException with a custom error message
     * @param msg The custom error message
     */
    public InvalidFileFormatException(String msg) {
        super(msg);
    }
}
