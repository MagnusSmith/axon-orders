package com.example.common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 11/04/2014
 * Time: 12:10
 */
@Component
public class PathUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final PathTokens tokens;

    @Autowired
    public PathUrlLogoutSuccessHandler(PathTokens tokens) {
        super();
        this.tokens = tokens;

    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        super.setDefaultTargetUrl(getTargetUrlFromPath(request));
        super.setAlwaysUseDefaultTargetUrl(true);
        super.handle(request, response, authentication);
    }

    private String getTargetUrlFromPath(HttpServletRequest request) {
        String refererUrl = request.getHeader("Referer");
        if (tokens.isTokenInPath(refererUrl)) {
            return "/" + tokens.getTokenFromPath(refererUrl) + "_signin";
        }
        throw new PathTokenNotFoundException("Token not found in referer URL " + refererUrl + " when retrieving logoutUrl.");
    }
}
