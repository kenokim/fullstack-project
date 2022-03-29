package keno.blogProjectDb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTag is a Querydsl query type for HashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTag extends EntityPathBase<HashTag> {

    private static final long serialVersionUID = 402407406L;

    public static final QHashTag hashTag = new QHashTag("hashTag");

    public final ListPath<ArticleTag, QArticleTag> articles = this.<ArticleTag, QArticleTag>createList("articles", ArticleTag.class, QArticleTag.class, PathInits.DIRECT2);

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> numArticles = createNumber("numArticles", Integer.class);

    public final StringPath tagName = createString("tagName");

    public QHashTag(String variable) {
        super(HashTag.class, forVariable(variable));
    }

    public QHashTag(Path<? extends HashTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTag(PathMetadata metadata) {
        super(HashTag.class, metadata);
    }

}

