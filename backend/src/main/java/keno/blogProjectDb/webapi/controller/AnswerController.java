package keno.blogProjectDb.webapi.controller;

import keno.blogProjectDb.service.AnswerService;
import keno.blogProjectDb.service.LikeVoteService;
import keno.blogProjectDb.support.LikeVoteType;
import keno.blogProjectDb.webapi.answer.AnswerForm;
import keno.blogProjectDb.webapi.comment.CommentForm;
import keno.blogProjectDb.webapi.support.ControllerSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final LikeVoteService likeVoteService;

    @PostMapping("/answer/add")
    public ResponseEntity addAnswer(@RequestBody AnswerForm answerForm,
                                     HttpServletRequest request) throws IllegalAccessException {
        answerService.addAnswer(answerForm.getArticleId(), ControllerSupport.getLoginIdFromSession(request), LocalDateTime.now(), answerForm.getContent());
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/answer/{id}/comment/add")
    public ResponseEntity addArticleComment(@PathVariable("id") Long id, @RequestBody CommentForm commentForm, HttpServletRequest request) throws IllegalAccessException {
        String loginId = ControllerSupport.getLoginIdFromSession(request);
        answerService.addAnswerComment(loginId, id, commentForm);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/answer/{id}/like")
    public ResponseEntity addLikeAnswer(@PathVariable("id") Long id,
                                         HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.LIKE_ANSWER, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/answer/{id}/dislike")
    public ResponseEntity addDislikeAnswer(@PathVariable("id") Long id,
                                         HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.DISLIKE_ANSWER, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }


}
