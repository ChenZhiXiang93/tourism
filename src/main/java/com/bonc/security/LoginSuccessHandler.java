package com.bonc.security;

import com.bonc.response.CommResponseEnum;
import com.bonc.response.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description: 自定义登录成功处理
 * @Date:Created in 21:13 2018/8/28
 * @Modified By:
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /*private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();*/

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        /*//重定向到首页
        redirectStrategy.sendRedirect(request,response,"/html/testAPI.html");*/
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(
                objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.SUCCESS)));
    }
}
