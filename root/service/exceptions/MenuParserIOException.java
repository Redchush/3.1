package root.service.exceptions;

/**
 * Created by user on 11.06.2016.
 */
public class MenuParserIOException extends MenuParserException {
    public MenuParserIOException() {
    }

    public MenuParserIOException(String message) {
        super(message);
    }

    public MenuParserIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
