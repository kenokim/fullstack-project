package keno.blogProjectDb.controller;

import keno.blogProjectDb.service.CommentService;
import keno.blogProjectDb.service.LikeVoteService;
import keno.blogProjectDb.support.LikeVoteType;
import keno.blogProjectDb.webapi.support.ControllerSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final LikeVoteService likeVoteService;

    @PostMapping("/comment/{id}/like")
    public ResponseEntity addLikeAnswer(@PathVariable("id") Long id,
                                        HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.LIKE_COMMENT, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/comment/{id}/dislike")
    public ResponseEntity addDislikeAnswer(@PathVariable("id") Long id,
                                           HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.DISLIKE_COMMENT, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }
}
