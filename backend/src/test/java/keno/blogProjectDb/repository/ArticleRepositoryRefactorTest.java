package keno.blogProjectDb.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


@PropertySource("application.properties")
@ActiveProfiles("test")
@SpringBootTest
@Transactional
@TestPropertySource(locations="/application-testnolog.properties", properties = "logging.level.org.hibernate.SQL")
class ArticleRepositoryRefactorTest {
   /* @Autowired private ArticleRepository repo;

    // Test data initializing purpose
    @Autowired private ArticleServiceOld articleService;
    @Autowired private UserService userService;

    @PersistenceContext
    EntityManager em;


    private Long testArticleId;


    @BeforeEach
    //@DynamicPropertySource(locations="/application-testnolog.properties", properties = "logging.level.org.hibernate.SQL")
    public void preWork() {
        로그끄기();
        //setLoggingLevel(Level.INFO);
        userService.register("test", "test!");
        userService.register("master", "test!");

        Long tagTest = articleService.addArticle("test", "Answer Test Article", LocalDateTime.now(), "Hello there! Please kindly ask you to put reply on this.");
        testArticleId = tagTest;
        articleService.addAnswer(tagTest, "test", LocalDateTime.now(), "This is test answer");
        articleService.addTag(tagTest, "JAVA");
        articleService.addTag(tagTest, "PYTHON");
        articleService.addTag(tagTest, "C++");
        articleService.addTag(tagTest, "C#");

        Long tagTest2 = articleService.addArticle("test", "Answer Test Article", LocalDateTime.now(), "Hello there! Please kindly ask you to put reply on this.");
        articleService.addAnswer(tagTest2, "test", LocalDateTime.now(), "This is test answer");
        articleService.addTag(tagTest2, "JAVA");
        articleService.addTag(tagTest2, "PYTHON");
        articleService.addTag(tagTest2, "C++");


        Long tagTest3 = articleService.addArticle("master", "Answer Test Article", LocalDateTime.now(), "Hello there! Please kindly ask you to put reply on this.");
        articleService.addAnswer(tagTest3, "test", LocalDateTime.now(), "This is test answer");
        articleService.addTag(tagTest3, "JAVA");
        articleService.addTag(tagTest3, "PYTHON");

        pageDataGen();

        em.clear();
        //로그켜기();
    }

    private void pageDataGen() {
        try {userService.register("test", "test!");} catch (Exception e) {}
        for (int i = 0; i < 3; i++) {
            Long id =articleService.addArticle("test", new Integer(i).toString(), LocalDateTime.now(), "Hello there! Please kindly ask you to put reply on this.");
            Article article = repo.getById(id);
            for (int j = 0; j < i; j++) { article.addView(); }
        }
    }

    @Test
    public void checkDbName() throws SQLException {
        org.hibernate.engine.spi.SessionImplementor sessionImp =
                (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        DatabaseMetaData metadata = sessionImp.connection().getMetaData();
        System.out.println(metadata.getDatabaseProductName());
    }

    public static void setLoggingLevel(Level level) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(level);
    }

    public void 로그끄기() {
        System.out.println("로그를 끕니다.");
        setLoggingLevel(Level.WARN);
    }

    public void 로그켜기() {
        System.out.println("로그를 켭니다.");
        setLoggingLevel(Level.DEBUG);
    }


    @Test
    @Transactional
    public void test1() throws SQLException {
        //System.out.println(repo.count());
        //로그켜기();
        setLoggingLevel(Level.DEBUG);
        articleService.getArticleById(testArticleId);
        org.hibernate.engine.spi.SessionImplementor sessionImp =
                (org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
        DatabaseMetaData metadata = sessionImp.connection().getMetaData();
        System.out.println(metadata.getDatabaseProductName());
        setLoggingLevel(Level.INFO);
    }


    @Test
    //@Transactional
    public void test2() {
        로그끄기();
        articleService.getArticleById(testArticleId);
    }


    @Test
    public void pagingTest1() {
        로그끄기();
        pageDataGen();
        Pageable pageRequest = PageRequest.of(1, 10, Sort.unsorted());
        Page<Article> res = repo.findAll(pageRequest);
        System.out.println(res.getTotalElements());
        for (Article a : res) {
            System.out.println(a.getId());
        }
        Assertions.assertThat(res.getSize() == 10);
    }

    @Test
    public void pagingTest2() {
        pageDataGen();
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        Page<Article> res = repo.findAll(pageRequest);
        res.stream().forEach(System.out::println);
    }




    @Test
    public void pagingTest3() {
        pageDataGen();
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by("title").descending());
        Page<Article> res = repo.findAll(pageRequest);
        res.stream().forEach(System.out::println);

    }

    @Test
    public void pagingTest4() {
        pageDataGen();
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        Page<Article> res = repo.findAllBySortByNumTags(pageRequest);
        res.stream().forEach(System.out::println);
    }

    @Test
    public void tagNumberSortTest() {
        pageDataGen();
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        Page<Article> res = repo.findOrderByNumTags(pageRequest);
        res.stream().forEach(System.out::println);
    }

    @Test
    public void pagingTests() {
        pageDataGen();
        List<Article> res = repo.findAllOrderByNumTags2();
        res.stream().forEach(System.out::println);
    }



    @Test
    public void orderByViewsTest() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        Page<Article> res = repo.findAllOrderByViews(pageRequest);
        res.stream().forEach(System.out::println);
    }

    @Test
    public void findByTagNameTest() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        Page<Article> res = repo.findAllByTagName("JAVA", pageRequest);
        res.stream().forEach(System.out::println);
    }

    @Test
    public void findByDateTime() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        //Page<Article> res = repo.findAllByOrderByDateTimeAcs(pageRequest);
        //res.stream().forEach(System.out::println);
    }

    @Test
    public void findById() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.unsorted());
        //Page<Article> res = repo.findAllByOrderByIdAcs(pageRequest);
        //res.stream().forEach(System.out::println);
    }
*/
}

