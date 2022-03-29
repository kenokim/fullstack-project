package keno.blogProjectDb.service;

import keno.blogProjectDb.domain.*;
import keno.blogProjectDb.repository.AnswerRepository;
import keno.blogProjectDb.repository.CommentRepository;
import keno.blogProjectDb.repository.LikeVoteRepository;
import keno.blogProjectDb.repository.UserRepository;
import keno.blogProjectDb.webapi.comment.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;
    private final LikeVoteRepository likeVoteRepository;

    /*************************
     * CREATE METHODS
     *************************/

    @Transactional
    public Long addAnswer(Long articleId, String loginId, LocalDateTime localDateTime, String content) {
        User author = userRepository.findByLoginId(loginId);
        if (author == null) throw new RuntimeException("유저가 존재하지 않습니다.");
        Article article = articleService.getArticleById(articleId);
        if (article == null) throw new RuntimeException("아티클이 존재하지 않습니다.");
        Answer answer = Answer.createAnswer(article, author, localDateTime, content);
        answerRepository.save(answer);
        return answer.getId();
    }

    @Transactional
    public void addAnswerLike(Long id, String loginId) {
        User liker = userRepository.findByLoginId(loginId);
        if (liker == null) throw new RuntimeException("유저가 존재하지 않습니다.");
        Answer answer = answerRepository.getById(id);
        if (answer == null) throw new RuntimeException("Answer 가 존재하지 않습니다.");
        if (dupAnswerLike(liker, answer)) throw new RuntimeException("이미 좋아요한 Answer 입니다.");
        likeVoteRepository.save(answer.addLike(liker));
    }

    public void addArticleDislike(Long id, String loginId) {
        User liker = userRepository.findByLoginId(loginId);
        if (liker == null) throw new RuntimeException("유저가 존재하지 않습니다.");
        Answer answer = answerRepository.getById(id);
        if (answer == null) throw new RuntimeException("Answer 가 존재하지 않습니다.");
        if (dupAnswerLike(liker, answer)) throw new RuntimeException("이미 좋아요한 Answer 입니다.");
        likeVoteRepository.save(answer.addDislike(liker));
    }

    @Transactional
    public Long addAnswerComment(String loginId, Long answerId, CommentForm commentForm) {
        //Article article = articleRepository.getById(articleId);
        Answer answer = answerRepository.getById(answerId);
        User author = userRepository.findByLoginId(loginId);
        if (answer == null) throw new NoSuchElementException("No such answer");
        if (author == null) throw new NoSuchElementException("No such user");
        Comment comment = Comment.createAnswerComment(author, answer, commentForm.getContent());
        commentRepository.save(comment);
        return comment.getId();
    }

    /*************************
     * HELPER METHODS
     *************************/
    private boolean dupAnswerLike(User liker, Answer answer) {
        LikeVote likeVote = likeVoteRepository.findLikeVoteByAnswerAndLikeVoter(answer, liker);
        if (likeVote == null) return false;
        return true;
    }



}
