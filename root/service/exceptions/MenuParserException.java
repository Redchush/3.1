package root.service.exceptions;

/**
 * Created by user on 10.06.2016.
 */
public class MenuParserException extends Exception {
    public MenuParserException() {
        super();
    }

    public MenuParserException(Throwable cause) {
        super(cause);
    }

    public MenuParserException(String message) {
        super(message);
    }

    public MenuParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
