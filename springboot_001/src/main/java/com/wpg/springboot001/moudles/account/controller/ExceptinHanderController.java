package com.wpg.springboot001.moudles.account.controller;


import com.wpg.springboot001.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptinHanderController {

    //AuthorizationException没有权限异常
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> errorPage403(){

        return new Result<String>(Result.Resultstatus.FAILD.status,"","/account/403");
    }
}
