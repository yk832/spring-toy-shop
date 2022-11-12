package com.insung.core.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        successRedirectUrl(request,response,authentication);
    }

    public void successRedirectUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        clearSession(request);
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        log.info("로그인 이전 요청한 URL : {}" , savedRequest);
        log.info("인증 유저의 권한 :" + authentication.getAuthorities());

        // 이전 요청 처리
        if (savedRequest != null) {
            String targetURI = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,targetURI);
        } else {
            redirectStrategy.sendRedirect(request,response,"/common/main");
        }

    }

    // 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}
