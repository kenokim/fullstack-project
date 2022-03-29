package keno.blogProjectDb.webapi.answer;

import keno.blogProjectDb.webapi.comment.CommentDto;
import keno.blogProjectDb.webapi.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class AnswerDto {
    private Long id = 0L;
    //private String author = "test";
    private UserDto author;
    private String createdAt = "지금";
    private String content = "WTF this is weird";
    private Boolean isAuthor = false;
    private Integer numLikes = 0;
    private List<CommentDto> comments = new ArrayList<>();
}
