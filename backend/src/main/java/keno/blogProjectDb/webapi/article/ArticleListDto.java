package keno.blogProjectDb.webapi.article;

import keno.blogProjectDb.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ArticleListDto {
    private Long totalNumArticles;
    private List<ArticleThumbnailDto> articles;

    public static ArticleListDto createArticleListDto(Page<Article> articles) {
        ArticleListDto dto = new ArticleListDto();
        ArticleDtoConverter converter = new ArticleDtoConverter();
        dto.totalNumArticles = articles.getTotalElements();
        dto.articles = converter.articleListToThumbnail(articles.getContent());
        return dto;
    }



}
