package keno.blogProjectDb.controller;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.exception.LoginFailureException;
import keno.blogProjectDb.service.UserService;
import keno.blogProjectDb.webapi.user.LoginDetailedInfoDto;
import keno.blogProjectDb.webapi.user.LoginForm;
import keno.blogProjectDb.webapi.user.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService loginService;

    /**
     * Try to login
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/login/login")
    public ResponseEntity loginRequestHandle(
            @RequestBody LoginForm loginForm,
            HttpServletRequest request) {

        if (loginService.login(loginForm.getLoginId(), loginForm.getPassword())) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("loginId", loginForm.getLoginId());
            return new ResponseEntity(HttpStatus.OK);
        }
        else {// Failed to log-in
            return new ResponseEntity("Failed to login", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Check whether the client having valid session
     * @param request
     * @return
     */
    @PostMapping("/login/check")
    public ResponseEntity sessionCheck(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            return new ResponseEntity("Ok", HttpStatus.OK);
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Logout to delete the session
     * @param request
     * @return
     */
    @PostMapping("/login/logout")
    public ResponseEntity logoutRequestHandle(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * Register a user
     * @param registerForm
     * @return
     */
    @PostMapping("/login/register")
    public ResponseEntity registerRequestHandle(@RequestBody RegisterForm registerForm) {
        loginService.register(registerForm.getLoginId(), registerForm.getPassword());
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    // Get detailed information of yourself
    @GetMapping("login/info")
    public LoginDetailedInfoDto getLoginInfo(HttpServletRequest request) throws IllegalAccessException {
        User user = loginService.getByLoginId(getLoginIdFromSession(request));
        return LoginDetailedInfoDto.userToLoginDto(user);
    }


    public String getLoginIdFromSession(HttpServletRequest request) throws IllegalAccessException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            throw new IllegalAccessException("Unauthorized");
        }
        return (String) httpSession.getAttribute("loginId");
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity unauthorizedAccessException(IllegalAccessException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginFailureException.class)
    public ResponseEntity loginFailtureException(LoginFailureException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
