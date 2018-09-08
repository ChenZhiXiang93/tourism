/*
package com.bonc.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * 登录失败处理
 *
 * @author zh
 * @create 2018/6/23
 *//*

@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof BadCredentialsException) {
            httpServletRequest.setAttribute("error", "用户名或密码错误");
        } else if (e instanceof ValidateCodeException) {
            httpServletRequest.setAttribute("error", e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            httpServletRequest.setAttribute("error", e.getMessage());
        } else if (e instanceof AdminLoginException) {
            httpServletRequest.setAttribute("error", e.getMessage());
        }
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
    }
}
*/
