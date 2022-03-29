package keno.blogProjectDb.webapi.support;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class ControllerSupport {
    public static String getLoginIdFromSession(HttpServletRequest request) throws IllegalAccessException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            throw new IllegalAccessException("Unauthorized");
        }
        return (String) httpSession.getAttribute("loginId");
    }
}
