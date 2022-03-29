package keno.blogProjectDb.webapi.user;

import keno.blogProjectDb.domain.HashTag;
import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.service.UserService;
import keno.blogProjectDb.webapi.tag.HashTagDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String loginId;
    private String explanation;

    private Integer reputation = 0;
    private Integer numArticles = 0;
    private Integer numAnswers = 0;
    private Integer numComments = 0;
    private Integer numLikeVotes = 0;

    private LocalDateTime registeredAt = LocalDateTime.now();
    private List<HashTagDto> tags = new ArrayList<>();

    public static UserDto createUserDto(User user) {
        UserDto dto = new UserDto();
        dto.loginId = user.getLoginId();
        return dto;
    }
}
