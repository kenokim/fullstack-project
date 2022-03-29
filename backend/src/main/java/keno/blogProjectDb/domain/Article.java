package keno.blogProjectDb.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
public class Article {

    // Fields

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    private String title;

    private Integer numLikes = 0;

    private Integer numAnswers = 0;

    private LocalDateTime dateTime;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeVote> likes = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    private Integer views = 0;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleTag> articleTags = new ArrayList<>();

    // Constructors

    protected Article() {}

    protected Article(User author, String title, LocalDateTime localDateTime, String content) {
        this.author = author;
        this.title = title;
        this.dateTime = localDateTime;
        this.content = content;
    }

    protected Article(User author, String title, LocalDateTime localDateTime, String content, List<HashTag> hashTags) {
        this.author = author;
        this.title = title;
        this.dateTime = localDateTime;
        this.content = content;
        hashTags.stream().forEach(t -> {
            ArticleTag articleTag = ArticleTag.createArticleTag(this, t);
            this.articleTags.add(articleTag);
        });
    }


    public static Article createArticle(User author, String title, LocalDateTime dateTime, String content, List<HashTag> hashTags) {
        Article article = new Article(author, title, dateTime, content, hashTags);
        author.addArticle(article);
        return article;
    }


    // Methods


    public LikeVote addLike(User liker) {
        LikeVote like = LikeVote.createArticleLikeVote(liker, this, true);
        this.likes.add(like);
        numLikes++;
        return like;
    }

    public LikeVote addDislike(User liker) {
        LikeVote like = LikeVote.createArticleLikeVote(liker, this, false);
        this.likes.add(like);
        numLikes--;
        return like;
    }

    public Answer addAnswer2(User author, LocalDateTime dateTime, String content) {
        Answer answer = Answer.createAnswer(this, author, dateTime, content);
        this.answers.add(answer);
        this.numAnswers++;
        return answer;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        this.numAnswers++;
    }

    public void addView() {
        this.views++;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Comment addComment2(User author, LocalDateTime dateTime, String content) {
        Comment comment = Comment.createArticleComment(author, this, content);
        this.comments.add(comment);
        return comment;
    }

}
