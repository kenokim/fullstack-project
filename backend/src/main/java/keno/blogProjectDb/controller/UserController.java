package keno.blogProjectDb.controller;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.service.UserService;
import keno.blogProjectDb.webapi.user.UserDto;
import keno.blogProjectDb.webapi.user.UserDtoConverter;
import keno.blogProjectDb.webapi.user.UserListDto;
import keno.blogProjectDb.webapi.user.UserListQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping("/user/users")
    public UserListDto getAllUsers(@ModelAttribute UserListQueryDto queryDto) {
        System.out.println(queryDto);
        List<UserDto> users = new ArrayList<>();
        Page<User> queryResult = userService.getAllByQueryDto(queryDto);
        queryResult
                .stream()
                .forEach(u -> {
                    users.add(userDtoConverter.userEntityToUserDtoConversion(u));
                });
        UserListDto dto = new UserListDto();
        dto.setUsers(users);
        dto.setNumUsers(queryResult.getTotalElements());
        return dto;
    }


}
