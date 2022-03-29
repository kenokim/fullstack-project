package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class ArticleTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    protected ArticleTag() {}

    public static ArticleTag createArticleTag(Article article, HashTag hashTag) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.article = article;
        articleTag.hashTag = hashTag;
        return articleTag;
    }
}
