package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.domain.User;

import keno.blogProjectDb.service.ArticleService;
import keno.blogProjectDb.service.UserService;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class ArticleRepositoryTest2 {
    @Autowired ArticleRepository articleRepository;
    @Autowired UserRepository userRepository;
    // Test data generators
    @Autowired private ArticleService articleService;
    @Autowired private UserService userService;




    @Test // 좋아요 를 두 번 이상 할 수 없음
    public void duplicateLikeTest() {
        /*//given
        User user = userService.register("test", "test!");
        Article article = articleService.addArticle("test", "test article", LocalDateTime.now(), "test content", new ArrayList<>());
        //when
        ArticleLike like = articleRepository.findArticleLikeByArticleIdAndLikerId(article.getId(), user.getId());
        Assertions.assertThat(like).isNull();
        article.addLike(user); // self like
        //then
        like = articleRepository.findArticleLikeByArticleIdAndLikerId(article.getId(), user.getId());
        Assertions.assertThat(like).isNotNull();*/
    }

    @Test
    public void filteredQueryTest() {
        /*//given
        User user = userService.register("test", "test!");
        Article article1 = articleService.addArticle("test", "test article", LocalDateTime.now(), "test content", new ArrayList<>());
        Article article2 = articleService.addArticle("test", "test article", LocalDateTime.now(), "test content", new ArrayList<>());
        ArticleListQueryDto queryDto = new ArticleListQueryDto(null, null, null, 0, 10);
        //when
        Page<Article> queryRes = articleRepository.findAllByQueryDto(queryDto);
        //then
        System.out.println(queryRes.getTotalElements());
        for (Article a : queryRes) {
            System.out.println(a);
        }
        Assertions.assertThat(queryRes.getContent().size()).isEqualTo(2);
*/
    }

    @Test
    public void oldPagingTest() {
        /*//given
        User user = userService.register("test", "test!");
        Article article1 = articleService.addArticle("test", "test article", LocalDateTime.now(), "test content", new ArrayList<>());
        Article article2 = articleService.addArticle("test", "test article", LocalDateTime.now(), "test content", new ArrayList<>());
        ArticleListQueryDto queryDto = new ArticleListQueryDto(null, null, null, 0, 10);
        //when
        Page<Article> queryRes =
                articleRepository.findAll(queryDto.getPageRequest());
        //        articleRepository.findAllByQueryDto(queryDto);
        //then
        System.out.println(queryRes.getTotalElements());
        for (Article a : queryRes) {
            System.out.println(a);
        }
        Assertions.assertThat(queryRes.getSize()).isEqualTo(2);*/
    }


}























    /*@Autowired ArticleViewRepository articleViewRepository;

    private String tester = "test";
    private String testPassword = "test!";
    private String articleTitle = "Hello";
    private String articleContent = "Hello";

    @PersistenceContext
    public EntityManager em;

    @Test
    public void articleViewCheckTest() {
        // given
        User user = new User(tester, testPassword);
        em.persist(user);
        Article article = new Article(user, articleTitle, LocalDateTime.now(), articleContent);
        em.persist(article);
        articleViewRepository.save(new ArticleView(user, article));
        // when
        List<ArticleView> res = articleViewRepository.findByArticleIdAndUserId(user.getId(), article.getId());
        // then
        Assertions.assertThat(res.size() == 1);
    }*/
