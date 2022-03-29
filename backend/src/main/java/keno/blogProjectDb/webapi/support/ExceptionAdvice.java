package keno.blogProjectDb.webapi.support;

import keno.blogProjectDb.exception.DuplicateLikeVoteException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "keno.blogProjectDb.webapi")
public class ExceptionAdvice {
    @ExceptionHandler(DuplicateLikeVoteException.class)
    public ResponseEntity illegalExHandler(DuplicateLikeVoteException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }
}
