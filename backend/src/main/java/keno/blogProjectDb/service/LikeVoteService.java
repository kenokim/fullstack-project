package keno.blogProjectDb.service;

import keno.blogProjectDb.domain.*;
import keno.blogProjectDb.exception.DuplicateLikeVoteException;
import keno.blogProjectDb.repository.*;
import keno.blogProjectDb.support.LikeVoteType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeVoteService {
    private final LikeVoteRepository likeVoteRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;

    public void addLikeVote(LikeVoteType likeVoteType, Long targetId, String loginId) {
        System.out.println("hello");
        User user = userRepository.findByLoginId(loginId);
        if (user == null) throw new NoSuchElementException("유저 정보가 없습니다.");
        if (isDuplicate(likeVoteType, targetId, user)) throw new DuplicateLikeVoteException("이미 좋아요/싫어요 한 대상입니다.");
        Article article; Answer answer; Comment comment;
        switch (likeVoteType) {
            case LIKE_ARTICLE:
                article = articleRepository.getById(targetId);
                likeVoteRepository.save(article.addLike(user));
                break;
            case DISLIKE_ARTICLE:
                article = articleRepository.getById(targetId);
                likeVoteRepository.save(article.addDislike(user));
                break;
            case LIKE_ANSWER:
                answer = answerRepository.getById(targetId);
                likeVoteRepository.save(answer.addLike(user));
                break;
            case DISLIKE_ANSWER:
                answer = answerRepository.getById(targetId);
                likeVoteRepository.save(answer.addDislike(user));
                break;
            case LIKE_COMMENT:
                comment = commentRepository.getById(targetId);
                likeVoteRepository.save(comment.addLike(user));
                break;
            case DISLIKE_COMMENT:
                comment = commentRepository.getById(targetId);
                likeVoteRepository.save(comment.addDislike(user));
                break;
        }
    }

    public boolean isDuplicate(LikeVoteType likeVoteType, Long targetId, User user) {
        LikeVote likeVote = null;
        switch (likeVoteType) {
            case LIKE_ARTICLE:
            case DISLIKE_ARTICLE:
                likeVote = likeVoteRepository.findLikeVoteByArticleIdAndLikeVoterId(targetId, user.getId());
                break;
            case LIKE_ANSWER:
            case DISLIKE_ANSWER:
                likeVote = likeVoteRepository.findLikeVoteByAnswerIdAndLikeVoterId(targetId, user.getId());
                break;
            case LIKE_COMMENT:
            case DISLIKE_COMMENT:
                likeVote = likeVoteRepository.findLikeVoteByCommentIdAndLikeVoterId(targetId, user.getId());
                break;
        }
        if (likeVote == null) return false;
        return true;
    }
}
