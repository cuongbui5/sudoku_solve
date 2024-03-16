package exceptions;


public class DbException extends RuntimeException{
    private final String message;

    public DbException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
