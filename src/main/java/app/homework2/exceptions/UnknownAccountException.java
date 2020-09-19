package app.homework2.exceptions;

public class UnknownAccountException extends RuntimeException {

    public UnknownAccountException(String message) {
        super(message);
    }
}
