package keno.blogProjectDb.webapi.user;

import keno.blogProjectDb.domain.Article;
import keno.blogProjectDb.domain.User;
import lombok.Data;

@Data // reputation = total sum of likes from articles and answers the user wrote
public class LoginDetailedInfoDto {
    private String loginId;
    private Integer reputation;

    public static LoginDetailedInfoDto userToLoginDto(User user) {
        LoginDetailedInfoDto dto = new LoginDetailedInfoDto();
        dto.loginId = user.getLoginId();
        int totalNumLikes = 0;
        //user.getArticles().forEach(a -> {
        //    totalNumLikes += a.getNumLikes();
        //});
        for (Article a : user.getArticles()) {
            totalNumLikes += a.getNumLikes();
        }
        dto.reputation = totalNumLikes;
        return dto;
    }
}
