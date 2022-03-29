package keno.blogProjectDb.repository.queryengine;


import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.domain.QArticle;
import keno.blogProjectDb.domain.QHashTag;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import keno.blogProjectDb.webapi.tag.HashTagListQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class FilteredHashTagRepositoryImpl implements FilteredHashTagRepository{
    private final JPAQueryFactory query;

    /*public Page<HashTag> findAllByQueryDto(HashTagListQueryDto queryDto) {
        System.out.println(queryDto);
        Pageable pageable = queryDto.getPageRequest();
        QHashTag hashTag = QHashTag.hashTag;
        List<HashTag> res = query
                .select(hashTag)
                .from(hashTag)
                .orderBy(hashTag.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        Long count = query.selectFrom(hashTag)
                .stream().count();
        return new PageImpl<>(res, pageable, count);
    }*/

    public Page<HashTag> findAllByQueryDto(HashTagListQueryDto queryDto) {
        Pageable pageable = queryDto.getPageRequest();
        QHashTag hashTag = QHashTag.hashTag;
        JPQLQuery<HashTag> q = query.selectFrom(hashTag);

        switch (queryDto.getSort()) {
            //case "new":
            //    q.orderBy(hashTag.id.desc());
            //    break;
            case "name":
                q.orderBy(hashTag.tagName.asc());
                break;
            case "popular":
                q.orderBy(hashTag.articles.size().desc());
                break;
            default:
                q.orderBy(hashTag.id.desc());
                break;
        }

        q.where(hashTag.tagName.contains(queryDto.getQuery()));


        q.offset(pageable.getOffset());
        q.limit(pageable.getPageSize());

        List<HashTag> res = q.fetch();
        Long count = query.selectFrom(hashTag)
                .stream().count();
        return new PageImpl<>(res, pageable, count);
    }

}
