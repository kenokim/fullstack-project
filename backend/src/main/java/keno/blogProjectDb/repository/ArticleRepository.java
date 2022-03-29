package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.repository.queryengine.FilteredArticleRepository;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, FilteredArticleRepository {

    Page<Article> findAll(Pageable pageRequest);

    @Override Page<Article> findAllByQueryDto(ArticleListQueryDto queryDto);


    //@Query("select a from Article a left join fetch Tag t on a.id = t.id group by a.id order by count(a.id) desc")
    //Page<Article> findAllBySortByNumTags(Pageable pageRequest);

    /**
     * 테그 개수가 많은 거
     * @return
     */
    //@Query("select a from Article a left join a.tags group by a.id order by a.tags.size desc")
    //Page<Article> findOrderByNumTags(Pageable pageable);

    /**
     * 조회수가 많은 거
     * @return
     */
    @Query("select a from Article a order by a.views")
    Page<Article> findAllOrderByViews(Pageable pageable);

    /**
     *
     * @param pageable
     * @return
     */
    @Query("select a from Article a order by a.numLikes")
    Page<Article> findAllOrderByNumLikes(Pageable pageable);

    @Query("select a from Article a order by a.dateTime")
    Page<Article> findAllOrderByCreatedAt(Pageable pageable);

    @Query("select a from Article a where a.author.loginId = :loginId")
    Page<Article> findAllByUser(@Param("loginId") String loginId, Pageable pageable);

    //@Query("select a from Article a left join Tag t where t.tag = :tagName")
    //Page<Article> findAllByTagName(@Param("tagName") String tagName, Pageable pageable);

    //@Query("select a from Article a left join fetch Tag t")
    //List<Article> findAllOrderByNumTags2();

    //Page<Article> findAllByOrderByDateTimeAcs(Pageable pageable);

    //Page<Article> findAllByOrderByTags(Pageable pageRequest);

    //Page<Article> findByTitle(String title);

    //@Query(value = "select a from Article a where a.author.name = :name")
    //Page<Article> randomquery1(@Param("name") String name, Pageable pageRequest);
    //Page<Article> findByAuthorName(@Param("name") String name, Pageable pageRequest);


    //@Query("select a from Article a")
    //List<Article> getRandomQuery();


    //Like findLikeByArticleAndLiker(Long articleId, Long likerId);
}
