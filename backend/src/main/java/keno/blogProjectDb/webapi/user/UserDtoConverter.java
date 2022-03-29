package keno.blogProjectDb.webapi.user;

import keno.blogProjectDb.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter {
    public UserDto userEntityToUserDtoConversion(User user) {
        UserDto dto = new UserDto();
        dto.setLoginId(user.getLoginId());
        dto.setExplanation(user.getExplanation());
        dto.setNumArticles(user.getNumArticles());
        dto.setNumAnswers(user.getNumAnswers());
        return dto;
    }
}
