package keno.blogProjectDb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollows is a Querydsl query type for Follows
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollows extends EntityPathBase<Follows> {

    private static final long serialVersionUID = -978110684L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollows follows = new QFollows("follows");

    public final QAnswer answer;

    public final QArticle article;

    public final QUser follower;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFollows(String variable) {
        this(Follows.class, forVariable(variable), INITS);
    }

    public QFollows(Path<? extends Follows> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollows(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollows(PathMetadata metadata, PathInits inits) {
        this(Follows.class, metadata, inits);
    }

    public QFollows(Class<? extends Follows> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article"), inits.get("article")) : null;
        this.follower = inits.isInitialized("follower") ? new QUser(forProperty("follower")) : null;
    }

}

