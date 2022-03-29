package keno.blogProjectDb.webapi.user;

import lombok.Data;

import java.util.List;

@Data
public class UserListDto {
    private Long numUsers;
    private List<UserDto> users;
}
