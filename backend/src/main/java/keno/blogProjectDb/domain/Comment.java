package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer numLikes = 0;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeVote> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    protected Comment() {}

    public static Comment createArticleComment(User author, Article article, String content) {
        Comment comment = new Comment();
        comment.article = article;
        article.addComment(comment);
        comment.author = author;
        comment.content = content;
        comment.dateTime = LocalDateTime.now();
        return comment;
    }


    public static Comment createAnswerComment(User author, Answer answer, String content) {
        Comment comment = new Comment();
        comment.answer = answer;
        answer.addComment(comment);
        comment.author = author;
        comment.content = content;
        comment.dateTime = LocalDateTime.now();
        return comment;
    }

    public LikeVote addLike(User liker) {
        LikeVote likeVote = LikeVote.createCommentLikeVote(liker, this, true);
        this.likes.add(likeVote);
        numLikes++;
        return likeVote;
    }
    public LikeVote addDislike(User liker) {
        LikeVote likeVote = LikeVote.createCommentLikeVote(liker, this, false);
        this.likes.add(likeVote);
        numLikes++;
        return likeVote;
    }
}
