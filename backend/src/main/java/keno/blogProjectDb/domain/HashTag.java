package keno.blogProjectDb.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.List;

@Entity @Getter
public class HashTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hash_tag_id")
    private Long id;

    private String tagName;

    private String explanation;

    private Integer numArticles = 0;

    @OneToMany(mappedBy = "hashTag", fetch = FetchType.LAZY)
    private List<ArticleTag> articles;

    protected HashTag() {};

    private HashTag(String tagName) {};

    public static HashTag createHashTag(String tagName) {
        HashTag hashTag = new HashTag();
        hashTag.tagName = tagName;
        hashTag.explanation = "아직 작성되지 않았습니다.";
        return hashTag;
    }

    public void changeExplanation(String explanation) {
        this.explanation = explanation;
    }
}
