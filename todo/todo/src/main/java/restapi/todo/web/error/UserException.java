package restapi.todo.web.error;

public class UserException extends IllegalStateException{

    public UserException() {
    }

    public UserException(String s) {
        super(s);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }
}
