package keno.blogProjectDb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeVote is a Querydsl query type for LikeVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeVote extends EntityPathBase<LikeVote> {

    private static final long serialVersionUID = 544366367L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeVote likeVote = new QLikeVote("likeVote");

    public final QAnswer answer;

    public final QArticle article;

    public final QComment comment;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isLikeVote = createBoolean("isLikeVote");

    public final QUser likeVoter;

    public QLikeVote(String variable) {
        this(LikeVote.class, forVariable(variable), INITS);
    }

    public QLikeVote(Path<? extends LikeVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeVote(PathMetadata metadata, PathInits inits) {
        this(LikeVote.class, metadata, inits);
    }

    public QLikeVote(Class<? extends LikeVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article"), inits.get("article")) : null;
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.likeVoter = inits.isInitialized("likeVoter") ? new QUser(forProperty("likeVoter")) : null;
    }

}

