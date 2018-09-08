package com.bonc.code;


import com.bonc.security.LoginFailureHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description: OncePerRequestFilter : 保证过滤器只被调用一次
 * @Date:Created in 22:57 2018/8/30
 * @Modified By:
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private LoginFailureHandler loginFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // 过滤 逻辑
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 是一个登陆请求
        if (StringUtils.equals("/form/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                // 有异常就返回自定义失败处理器
                loginFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        // 不是一个登录请求，不做校验 直接通过
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //存在session中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidController.SESSION_KEY);
        //表单提交的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest) || codeInSession == null) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (!StringUtils.equals(codeInSession.getCode().toLowerCase(), codeInRequest.toLowerCase())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidController.SESSION_KEY);
    }

    public LoginFailureHandler getLoginFailureHandler() {
        return loginFailureHandler;
    }

    public void setLoginFailureHandler(LoginFailureHandler loginFailureHandler) {
        this.loginFailureHandler = loginFailureHandler;
    }
}
