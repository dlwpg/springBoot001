package com.wpg.springboot001.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "requestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {

    private final static Logger LOGGER= LoggerFactory.getLogger(RequestParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("=========init filter========");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        LOGGER.debug("=======dofilter filter=======");

        HttpServletRequest request= (HttpServletRequest) servletRequest;

        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(request){
            //通过Httpservletrequest request 获取到 路径后的键值对
            @Override
            public String getParameter(String name) {
                String value=request.getParameter(name);
                if (!StringUtils.isBlank(value)){
                    value=value.replaceAll("fuck","****");
                    System.err.println(value);
                    return value;
                }
                return super.getParameter(name);
            }

            //通过@RequestParam获取到的值给他替换
            @Override
            public String[] getParameterValues(String name) {
                String[] values=request.getParameterValues(name);
                if (values!=null && values.length>0){
                    for (int i=0;i<values.length;i++)
                    {
                        if ("fuck".equals(values[i])){
                            values[i]="****";
                        }
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };


        //不管如何都要返回一个filterchain
        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {

        LOGGER.debug("=========destroy filter=======");
    }
}
