package keno.blogProjectDb.repository.queryengine;

import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FilteredArticleRepository {
    Page<Article> findAllByQueryDto(ArticleListQueryDto queryDto);
}
