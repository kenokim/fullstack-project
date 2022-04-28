package keno.blogProjectDb.controller;

import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.service.ArticleService;
import keno.blogProjectDb.service.LikeVoteService;
import keno.blogProjectDb.support.LikeVoteType;
import keno.blogProjectDb.webapi.article.*;
import keno.blogProjectDb.webapi.comment.CommentForm;
import keno.blogProjectDb.webapi.support.ControllerSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

//import keno.blogProjectDb.webapi.article.stackexchange.QuestionListDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleDtoConverter articleDtoConverter;
    private final LikeVoteService likeVoteService;

    /**
     *
     * @param articleForm
     * @param request
     * @return 200 Ok if successful, 401 Unauthorized where login context is invalid
     * @throws IllegalAccessException
     */
    @PostMapping("/article/add")
    public ResponseEntity addArticle(@RequestBody ArticleForm articleForm,
                                     HttpServletRequest request) throws IllegalAccessException {
        LocalDateTime articleCreatedTime = LocalDateTime.now();
        if (articleForm.getDateTime() != null) articleCreatedTime = articleForm.getDateTime();
        articleService.addArticle(ControllerSupport.getLoginIdFromSession(request), articleForm.getTitle(), articleCreatedTime, articleForm.getContent(), articleForm.getTagList());
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public AriticleDetailedDto getArticleById(@PathVariable("id") Long id,
                                              HttpServletRequest request) throws IllegalAccessException {
        Article article = articleService.getArticleByIdAndAddView(id);
        String loginId = null;
        try {
            loginId = ControllerSupport.getLoginIdFromSession(request);
        } catch (Exception e) {}
        AriticleDetailedDto dto = articleDtoConverter.articleEntityToDtoConversion(article, loginId);
        return dto;
    }


    /**
     * Article list query api
     * Specify or not filters via query parameters
     * Filter validation: tag=java+python&filter=noanswer+noaccepted&sort=newest&page=0&numarticles=10
     * @param queryDto
     * @return dto
     */
    @GetMapping("/article/articles")
    public ArticleListDto getAllArticles(@ModelAttribute ArticleListQueryDto queryDto) {
        Page<Article> articles = articleService.getAllArticles(queryDto);
        ArticleListDto resultDto = ArticleListDto.createArticleListDto(articles);
        return resultDto;
    }



    @PostMapping("/article/{id}/like")
    public ResponseEntity addLikeArticle(@PathVariable("id") Long id,
                                         HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.LIKE_ARTICLE, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @PostMapping("/article/{id}/dislike")
    public ResponseEntity addDislikeArticle(@PathVariable("id") Long id,
                                         HttpServletRequest request) throws IllegalAccessException {
        likeVoteService.addLikeVote(LikeVoteType.DISLIKE_ARTICLE, id, ControllerSupport.getLoginIdFromSession(request));
        return new ResponseEntity("Ok", HttpStatus.OK);
    }



    private boolean isAuthorOfArticle(Long articleId, String loginId) {
        Article article = articleService.getArticleById(articleId);
        return article.getAuthor().getLoginId().equals(loginId);
    }


    @PostMapping("/article/{id}/authority")
    public ResponseEntity checkAuthorOfArticle(@PathVariable("id") Long id,
                                         HttpServletRequest request) throws IllegalAccessException {
        log.info("checkAuthorOfArticle");
        if (isAuthorOfArticle(id, ControllerSupport.getLoginIdFromSession(request))) {
            return new ResponseEntity("Ok", HttpStatus.OK);
        }
        else throw new IllegalAccessException("Unauthorized");
    }

    @PostMapping("/article/{id}/delete")
    public ResponseEntity deleteArticle(@PathVariable("id") Long id,
                                               HttpServletRequest request) throws IllegalAccessException {
        if (isAuthorOfArticle(id, ControllerSupport.getLoginIdFromSession(request))) {
            articleService.deleteArticleById(id);
            return new ResponseEntity("Ok", HttpStatus.OK);
        }
        else throw new IllegalAccessException("Unauthorized");
    }

    @PostMapping("/article/{id}/comment/add")
    public ResponseEntity addArticleComment(@PathVariable("id") Long id, @RequestBody CommentForm commentForm, HttpServletRequest request) throws IllegalAccessException {
        String loginId = ControllerSupport.getLoginIdFromSession(request);
        articleService.addArticleComment(loginId, id, commentForm);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity articleNotExistException(NoSuchElementException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity unauthorizedAccessException(IllegalAccessException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity duplicateLikeException(IllegalStateException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }




}
