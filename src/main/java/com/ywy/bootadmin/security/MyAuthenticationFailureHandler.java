package com.ywy.bootadmin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywy.bootadmin.common.rest.BaseResponse;
import com.ywy.bootadmin.common.rest.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 *
 * @author ywy
 * @date 2020-04-09 10:10
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        BaseResponse response;
        if (e instanceof LockedException) {
            response = new BaseResponse(ResponseCode.LOGIN_LOCKED);
        } else if (e instanceof BadCredentialsException) {
            response = new BaseResponse(ResponseCode.LOGIN_BAD_CREDENTIALS);
        } else if (e instanceof DisabledException) {
            response = new BaseResponse(ResponseCode.LOGIN_DISABLED);
        } else if (e instanceof AccountExpiredException) {
            response = new BaseResponse(ResponseCode.LOGIN_ACCOUNT_EXPIRED);
        } else if (e instanceof CredentialsExpiredException) {
            response = new BaseResponse(ResponseCode.LOGIN_CREDENTIALS_EXPIRED);
        } else {
            response = new BaseResponse(ResponseCode.LOGIN_FAILURE);
        }
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(response));
    }
}
