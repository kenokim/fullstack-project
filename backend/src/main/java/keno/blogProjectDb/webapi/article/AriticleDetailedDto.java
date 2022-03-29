package keno.blogProjectDb.webapi.article;

import keno.blogProjectDb.webapi.answer.AnswerDto;
import keno.blogProjectDb.webapi.comment.CommentDto;
import keno.blogProjectDb.webapi.user.UserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AriticleDetailedDto {
    private Long id = 0L;
    private String title = "";
    private String createdAt = "";
    private String content = "";
    private Integer numComments = 0;
    private Integer numAnswers = 0;
    private Integer numLikes = 0;
    private Integer numViews = 0;
    private Boolean isAuthor = false;
    private UserDto author;
    private List<CommentDto> comments = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    public List<AnswerDto> answers = new ArrayList<>();
}

