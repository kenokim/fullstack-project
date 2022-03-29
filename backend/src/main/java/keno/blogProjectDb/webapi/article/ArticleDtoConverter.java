package keno.blogProjectDb.webapi.article;

import keno.blogProjectDb.domain.Answer;
import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.domain.ArticleTag;
import keno.blogProjectDb.domain.Comment;
import keno.blogProjectDb.webapi.answer.AnswerDto;
import keno.blogProjectDb.webapi.comment.CommentDto;
import keno.blogProjectDb.webapi.user.UserDto;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleDtoConverter {
    /**
     *
     * @param articleEntity
     * @return Article Thumbnail Dto
     */
    public ArticleThumbnailDto articleEntityToThumbnailDtoConversion(Article articleEntity) {
        ArticleThumbnailDto dto = new ArticleThumbnailDto();
        dto.setId(articleEntity.getId());


        dto.setAuthor(
                UserDto.createUserDto(articleEntity.getAuthor())
        );

        dto.setContent(articleEntity.getContent());
        dto.setTitle(articleEntity.getTitle());
        dto.setCreatedAt(localDateTimeToString(articleEntity.getDateTime()));
        dto.setNumLikes(articleEntity.getNumLikes());
        dto.setNumViews(articleEntity.getViews());
        dto.setNumAnswers(articleEntity.getNumAnswers());
        for (ArticleTag at : articleEntity.getArticleTags()) {
            dto.getTags().add(at.getHashTag().getTagName());
        }
        /*dto.setTags(
                tagEntityListToStringListConversion(
                    articleEntity.getTags()
                )
        );*/
        return dto;
    }


    public List<ArticleThumbnailDto> articleListToThumbnail(List<Article> articles) {
        List<ArticleThumbnailDto> dtos = new ArrayList<>();
        articles.stream().forEach(a -> {
            dtos.add(articleEntityToThumbnailDtoConversion(a));
        });
        return dtos;
    }


    /**
     *
     * @param articleEntity
     * @return Article Detailed Dto
     */
    public AriticleDetailedDto articleEntityToDtoConversion(Article articleEntity, String loginId) {
        AriticleDetailedDto dto = new AriticleDetailedDto();
        dto.setId(articleEntity.getId());
        //dto.setAuthor(articleEntity.getAuthor().getLoginId());
        dto.setAuthor(UserDto.createUserDto(articleEntity.getAuthor()));
        dto.setContent(articleEntity.getContent());
        dto.setTitle(articleEntity.getTitle());
        dto.setCreatedAt(localDateTimeToString(articleEntity.getDateTime()));
        dto.setNumLikes(articleEntity.getNumLikes());
        dto.setNumViews(articleEntity.getViews());
        dto.setNumAnswers(articleEntity.getNumAnswers());
        dto.setComments(commentEntityToDto(articleEntity.getComments()));
        if (loginId != null) {
            dto.setIsAuthor(loginId.equals(articleEntity.getAuthor().getLoginId()));
        }
        /*dto.setTags(
                tagEntityListToStringListConversion(
                        articleEntity.getHashTags()
                )
        );*/
        dto.setAnswers(
                answerEntityListToAnswerDtoConversion(
                        articleEntity.getAnswers(),
                        loginId
                )
        );

        return dto;
    }

    private List<CommentDto> commentEntityToDto(List<Comment> comments) {
        List<CommentDto> dtos = new ArrayList<>();
        comments.stream().forEach(c -> {
            dtos.add(CommentDto.createCommentDto(c));
        });
        return dtos;
    }


    public List<AnswerDto> answerEntityListToAnswerDtoConversion(List<Answer> answers, String loginId) {
        List<AnswerDto> lists = new ArrayList<>();
        answers.stream().forEach(a -> {
            lists.add(answerEntityToAnswerDtoConversion(a, loginId));
        });
        return lists;
    }

    public AnswerDto answerEntityToAnswerDtoConversion(Answer answerEntity, String loginId) {
        return AnswerDto.builder()
                .id(answerEntity.getId())
                //.author(answerEntity.getAuthor().getLoginId())
                .author(UserDto.createUserDto(answerEntity.getAuthor()))
                .content(answerEntity.getContent())
                .createdAt(localDateTimeToString(answerEntity.getDateTime()))
                .numLikes(answerEntity.getNumLikes())
                .isAuthor(answerEntity.getAuthor().getLoginId().equals(loginId))
                .comments(commentEntityToDto(answerEntity.getComments()))
                .build();
    }

    /*public List<String> tagEntityListToStringListConversion(List<Tag> tags) {
        List<String> lists = new ArrayList<>();
        tags.stream().forEach(t -> {
            lists.add(t.getTag());
        });/
        return lists;
    }*/



    /**
     * LocalDateTime 이 지금과 같다면 "지금",
     * 지금보다 1시간 이내라면 "?분 전"
     * 지금과 같은 날이라면 "hh:mm"
     * 지금과 같은 해라면 "mm/dd hh:mm"
     * 다른 해라면 "yy/mm/dd hh:mm"
     * @param dateTime
     * @return
     */
    public String localDateTimeToString(LocalDateTime dateTime) {
        String time = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm"));
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm"));
        try {
            if (time.equals(nowTime)) return "지금";
            Duration duration = Duration.between(dateTime, LocalDateTime.now());
            int minAgo = (int) (duration.getSeconds() / 60);
            if (minAgo < 60) return minAgo + "분 전";
            if (time.substring(0, 10).equals(nowTime.substring(0, 10))) return time.substring(11, time.length());
            if (time.substring(0, 4).equals(nowTime.substring(0, 4))) return time.substring(5, time.length());
        }
        catch (Exception e) {

        }
        return time;
    }
}
