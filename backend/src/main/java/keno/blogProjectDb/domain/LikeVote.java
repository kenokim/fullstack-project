package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class LikeVote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_vote_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User likeVoter;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private Boolean isLikeVote = true;

    protected LikeVote() {}

    public static LikeVote createArticleLikeVote(User liker, Article article, Boolean isLike) {
        LikeVote like = new LikeVote();
        like.article = article;
        like.likeVoter = liker;
        like.isLikeVote = isLike;
        return like;
    }

    public static LikeVote createAnswerLikeVote(User likeVoter, Answer answer, Boolean isLikeVote) {
        LikeVote LikeVote = new LikeVote();
        LikeVote.answer = answer;
        LikeVote.likeVoter = likeVoter;
        LikeVote.isLikeVote = isLikeVote;
        return LikeVote;
    }

    public static LikeVote createCommentLikeVote(User likeVoter, Comment comment, Boolean isLikeVote) {
        LikeVote LikeVote = new LikeVote();
        LikeVote.comment = comment;
        LikeVote.likeVoter = likeVoter;
        LikeVote.isLikeVote = isLikeVote;
        return LikeVote;
    }
}
