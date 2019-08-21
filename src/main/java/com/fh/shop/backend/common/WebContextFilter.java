package com.fh.shop.backend.common;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WebContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        WebContext.setRequest((HttpServletRequest) servletRequest);
        try {
            /*放行，继续执行后续逻辑*/
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            WebContext.remove();
        }

    }

    @Override
    public void destroy() {

    }
}
