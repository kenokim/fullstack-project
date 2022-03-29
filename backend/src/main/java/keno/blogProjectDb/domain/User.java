package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String password;

    private String userName = "";
    private String explanation = "아직 작성되지 않았습니다.";

    private Integer numArticles = 0;

    private Integer numAnswers = 0;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    // Constructor

    protected User() {}

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public static User createUser(String loginId, String password) {
        User user = new User();
        user.loginId = loginId;
        user.password = password;
        return user;
    }


    public void addArticle(Article article) {
        this.articles.add(article);
        this.numArticles++;
    }

    public void deleteArticle(Article article) {
        articles.remove(article);
        this.numArticles--;
    }

}
