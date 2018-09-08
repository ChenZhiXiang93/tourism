package com.bonc.security;

import com.bonc.code.ValidateCodeException;
import com.bonc.response.CommResponseEnum;
import com.bonc.response.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description: 登录失败处理类
 * @Date:Created in 10:58 2018/9/1
 * @Modified By:
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER1)));
        } else if (exception instanceof ValidateCodeException){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER3)));
        } else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.FAILURE)));
        }
    }
}
