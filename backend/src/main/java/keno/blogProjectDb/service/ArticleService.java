package keno.blogProjectDb.service;

import keno.blogProjectDb.domain.*;
import keno.blogProjectDb.repository.ArticleRepository;
import keno.blogProjectDb.repository.CommentRepository;
import keno.blogProjectDb.repository.LikeVoteRepository;
import keno.blogProjectDb.repository.UserRepository;
import keno.blogProjectDb.webapi.article.ArticleListQueryDto;
import keno.blogProjectDb.webapi.comment.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final HashTagService hashTagService;
    private final LikeVoteRepository likeVoteRepository;

    /*************************
     * SEARCH METHODS
     *************************/

    public Page<Article> getAllArticles(int page, int numArticles, List<String> tags, List<String> filters, String sort) {
        return articleRepository.findAll(PageRequest.of(page, numArticles, Sort.unsorted()));
    }

    public Page<Article> getAllArticles(ArticleListQueryDto queryDto) {
        return articleRepository.findAllByQueryDto(queryDto);
    }

    public Article getArticleById(Long id) {
        return articleRepository.getById(id);
    }

    public Article getArticleByIdAndAddView(Long id) {
        Article article = articleRepository.getById(id);
        article.addView();
        return article;
    }

    /*************************
     * CREATE METHODS
     *************************/

    @Transactional
    public Article addArticle(String loginId, String title, LocalDateTime localDateTime, String content, List<String> tags) {

        User author = userRepository.findByLoginId(loginId);
        if (author == null) throw new RuntimeException("유저가 존재하지 않습니다.");

        List<HashTag> hashTagList = hashTagService.stringListToHashTagList(tags);
        Article article = Article.createArticle(author, title, localDateTime, content, hashTagList);
        articleRepository.save(article);
        return article;
    }

    /*************************
     * UPDATE METHODS
     *************************/

    @Transactional
    public void addArticleView(Long id) {
        articleRepository.getById(id).addView();
    }

    @Transactional
    public void addArticleLike(Long id, String loginId) {
        System.out.println("Somebody's added like");
        User liker = userRepository.findByLoginId(loginId);
        if (liker == null) throw new RuntimeException("유저가 존재하지 않습니다.");
        Article article = articleRepository.getById(id);
        if (article == null) throw new RuntimeException("아티클이 존재하지 않습니다.");
        if (dupArticleLike(liker, article)) throw new RuntimeException("이미 좋아요한 게시글 입니다.");
        likeVoteRepository.save(article.addLike(liker));
    }


    /*************************
     * DELETE METHODS
     *************************/

    @Transactional
    public void deleteArticleById(Long id) {
        Article article = articleRepository.getById(id);
        User author = userRepository.findByLoginId(article.getAuthor().getLoginId());
        author.deleteArticle(article);
        articleRepository.delete(article);
    }



    /*************************
     * HELPER METHODS
     *************************/
    private boolean dupArticleLike(User liker, Article article) {
        LikeVote likeVote = likeVoteRepository.findLikeVoteByArticleAndLikeVoter(article, liker);
        if (likeVote == null) return false;
        return true;
    }


    @Transactional
    public Long addArticleComment(String loginId, Long articleId, CommentForm commentForm) {
        Article article = articleRepository.getById(articleId);
        User author = userRepository.findByLoginId(loginId);
        if (author == null) throw new NoSuchElementException("No such user");
        Comment comment = Comment.createArticleComment(author, article, commentForm.getContent());
        commentRepository.save(comment);
        return comment.getId();
    }
}
