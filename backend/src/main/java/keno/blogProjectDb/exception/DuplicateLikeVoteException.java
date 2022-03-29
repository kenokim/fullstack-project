package keno.blogProjectDb.exception;

public class DuplicateLikeVoteException extends IllegalStateException {
    public DuplicateLikeVoteException(String message) {
        super(message);
    }
}
