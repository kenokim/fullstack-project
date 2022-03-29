package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Follows {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follows_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    protected Follows() {}

    public static Follows createArticleFollow(User follower, Article article) {
        Follows follows = new Follows();
        follows.follower = follower;
        follows.article = article;
        return follows;
    }

}
