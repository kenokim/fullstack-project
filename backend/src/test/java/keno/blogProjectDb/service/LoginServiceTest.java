package keno.blogProjectDb.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
class LoginServiceTest {
    @Autowired
    UserService loginService;

    @Test
    public void registerTest() {
        try {
            loginService.register("test", "test!");
            loginService.register("test", "test!");
        }
        catch (Exception e) {
            return;
        }
        fail("failed to prevent duplicate user registrations");
    }

    @Test
    public void loginTest() {
        loginService.register("test", "test!");
        boolean login = loginService.login("test", "test!");
        Assertions.assertThat(login);

        try {
            // LoginId (o), password (x)
            boolean login2 = loginService.login("test", "???");
            fail("Login must be unsuccessful in case wrong password given");
        }
        catch (Exception e) {

        }
        // LoginId (x), password(?)
        boolean login3 = loginService.login("???", "test!");
        boolean login4 = loginService.login("???", "???");
        Assertions.assertThat(!login3);
        Assertions.assertThat(!login4);
    }
}