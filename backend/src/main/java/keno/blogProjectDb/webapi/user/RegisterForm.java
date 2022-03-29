package keno.blogProjectDb.webapi.user;

import lombok.Data;

@Data
public class RegisterForm {
    private String loginId;
    private String password;
}
