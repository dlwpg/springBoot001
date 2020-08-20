package com.wpg.springboot001.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestViewIntercepter implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestViewIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("========pre=======");
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("========post=======");
        //处理空指针异常
        if ( modelAndView == null ||modelAndView.getViewName().startsWith("redirect")){
            return;
        }
        String path=request.getServletPath();
        String template= (String) modelAndView.getModelMap().get("template");


        if (StringUtils.isBlank(template)){
            //得到的path是/test/index 但是需要的是test/index所以去掉/
            if (path.startsWith("/")){
               path= path.substring(1);
            }
            modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
        }
        HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("========after=======");
        HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
