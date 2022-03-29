package keno.blogProjectDb.webapi.comment;

import keno.blogProjectDb.domain.Comment;
import keno.blogProjectDb.webapi.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id = 0L;
    private UserDto author;
    private Integer numLikes = 0;
    private String content = "Empty comment";
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isAuthor = false;

    public static CommentDto createCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.author = UserDto.createUserDto(comment.getAuthor());
        dto.numLikes = comment.getNumLikes();
        dto.content = comment.getContent();
        dto.createdAt = comment.getDateTime();
        return dto;
    }
}
