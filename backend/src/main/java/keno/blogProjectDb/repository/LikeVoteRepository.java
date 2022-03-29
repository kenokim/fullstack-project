package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.Answer;
import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.domain.LikeVote;
import keno.blogProjectDb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeVoteRepository extends JpaRepository<LikeVote, Long> {
    LikeVote findLikeVoteByArticleAndLikeVoter(Article article, User likeVoter);

    LikeVote findLikeVoteByArticleIdAndLikeVoterId(Long articleId, Long likeVoterId);

    LikeVote findLikeVoteByAnswerAndLikeVoter(Answer answer, User liker);

    LikeVote findLikeVoteByAnswerIdAndLikeVoterId(Long targetId, Long id);

    LikeVote findLikeVoteByCommentIdAndLikeVoterId(Long targetId, Long id);
}
