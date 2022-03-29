package keno.blogProjectDb.testonly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TestRepositoryTest {
    @Autowired TestArticleRepository testArticleRepository;
    @Autowired TestAuthorRepository testAuthorRepository;
    @Autowired EntityManager em;

    @BeforeEach
    @Transactional
    public void prework() {
        TestAuthor author = new TestAuthor();
        testAuthorRepository.save(author);

        TestAuthor author2 = new TestAuthor();
        testAuthorRepository.save(author2);

        TestAuthor author3 = new TestAuthor();
        testAuthorRepository.save(author3);

        TestArticle article = TestArticle.createTestArticle(author);
        testArticleRepository.save(article);


        TestArticle article2 = TestArticle.createTestArticle(author2);
        testArticleRepository.save(article2);

        TestArticle article3 = TestArticle.createTestArticle(author3);
        testArticleRepository.save(article3);
        testArticleRepository.flush();
        //testArticleRepository.deleteAllInBatch();
        //em.flush();
        em.clear();
    }

    @Test
    public void testManyToOne1() {
        System.out.println("------------------------------");
        List<TestArticle> articles =
                        testArticleRepository.findAll();
                //        testArticleRepository.getArticles();
        for (TestArticle a : articles) {
            System.out.println(a.getAuthor().getName());
        }

    }

    @Test
    public void testOneToMany1() {
        List<TestAuthor> authors =
                testAuthorRepository.findAll();

        for (TestAuthor a : authors) {
            System.out.println(a.getArticles());
            for (TestArticle at : a.getArticles()) {
                System.out.println(at.getTitle());
            }
        }
    }

    /*
    @Test
    public void test() {
        TestAuthor author = new TestAuthor();
        testAuthorRepository.save(author);

        TestArticle article = TestArticle.createTestArticle(author);
        testArticleRepository.save(article);


        TestArticle article2 = TestArticle.createTestArticle(author);
        testArticleRepository.save(article2);

        TestArticle article3 = TestArticle.createTestArticle(author);
        testArticleRepository.save(article3);


        List<TestArticle> articles =
        //        testArticleRepository.findAll();
                testArticleRepository.getArticles();
        for (TestArticle a : articles) {
            System.out.println(a.getAuthor().getName());
        }


    }*/
}