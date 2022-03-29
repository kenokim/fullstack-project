package keno.blogProjectDb.repository.queryengine;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import keno.blogProjectDb.domain.*;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class FilteredArticleRepositoryImpl implements FilteredArticleRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<Article> findAllByQueryDto(ArticleListQueryDto queryDto) {
        if (queryDto.getTags().isEmpty()) {
            return queryWithoutTag(queryDto);
        }
        else return queryWithTag(queryDto);
    }



    private Page<Article> queryWithoutTag(ArticleListQueryDto queryDto) {
        Pageable pageable = queryDto.getPageRequest();
        QArticle article = QArticle.article;
        QUser user = QUser.user;
        QArticleTag articleTag = QArticleTag.articleTag;
        QHashTag hashTag = QHashTag.hashTag;
        //JPQLQuery<Article> q = query.selectFrom(article);
        JPQLQuery<Article> q = query.selectFrom(article)
                .leftJoin(article.author, user)
                .fetchJoin()
                .leftJoin(article.articleTags, articleTag)
                .fetchJoin();
        switch (queryDto.getSort()) {
            case "active":
                q.orderBy(article.id.desc());
                break;
            case "frequent":
                q.orderBy(article.views.desc());
                break;
            case "unanswered":
                q.orderBy(article.answers.size().asc());
                break;
            case "votes":
                q.orderBy(article.numLikes.desc());
                break;
            default:
                q.orderBy(article.dateTime.desc());
                break;
        }

        //q.where(hashTag.tagName.contains(queryDto.getQuery()));
        q.where(article.title.contains(queryDto.getQuery()));

        q.offset(pageable.getOffset());
        q.limit(pageable.getPageSize());

        List<Article> res = q.fetch();
        Long count = query.selectFrom(article)
                .stream().count();
        return new PageImpl<>(res, pageable, count);
    }

    private Page<Article> queryWithTag(ArticleListQueryDto queryDto) {
        Pageable pageable = queryDto.getPageRequest();
        QArticle article = QArticle.article;
        QUser user = QUser.user;
        QArticleTag articleTag = QArticleTag.articleTag;
        QHashTag hashTag = QHashTag.hashTag;
        //JPQLQuery<Article> q = query.selectFrom(article);
        JPQLQuery<Article> q = query.selectDistinct(article)
                .from(article)
                .join(articleTag).on(article.id.eq(articleTag.article.id))
                .join(hashTag).on(articleTag.hashTag.id.eq(hashTag.id))
                .where(hashTag.tagName.eq(queryDto.getTags().get(0)));

        List<Article> res = q.fetch();
        Long count = query.selectFrom(article)
                .stream().count();
        return new PageImpl<>(res, pageable, count);
    }



}
