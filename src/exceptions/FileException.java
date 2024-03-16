package exceptions;

public class FileException extends RuntimeException{
    private final String message;


    public FileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
