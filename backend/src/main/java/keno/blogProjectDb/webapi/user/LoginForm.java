package keno.blogProjectDb.webapi.user;

import lombok.Data;

@Data
public class LoginForm {
    private String loginId;
    private String password;
}