package keno.blogProjectDb.exception;

public class DuplicateRegisterException extends RuntimeException{
    public DuplicateRegisterException(String message) {
        super(message);
    }
}
