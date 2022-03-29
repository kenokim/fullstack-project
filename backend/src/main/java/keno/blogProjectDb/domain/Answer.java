package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    private int numLikes = 0;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeVote> likes = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    protected Answer() {}

    public static Answer createAnswer(Article article, User author, LocalDateTime dateTime, String content) {
        Answer answer = new Answer();
        answer.article = article;
        answer.author = author;
        answer.dateTime = dateTime;
        answer.content = content;
        answer.mappingAnswer(article, author);
        return answer;
    }

    public void mappingAnswer(Article article, User author) {
        article.addAnswer(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Comment addComment2(User author, LocalDateTime dateTime, String content) {
        Comment comment = Comment.createAnswerComment(author, this, content);
        this.comments.add(comment);
        return comment;
    }


    public LikeVote addLike(User liker) {
        LikeVote likeVote = LikeVote.createAnswerLikeVote(liker, this, true);
        this.likes.add(likeVote);
        numLikes++;
        return likeVote;
    }

    public LikeVote addDislike(User liker) {
        LikeVote likeVote = LikeVote.createAnswerLikeVote(liker, this, false);
        this.likes.add(likeVote);
        numLikes++;
        return likeVote;
    }
}
