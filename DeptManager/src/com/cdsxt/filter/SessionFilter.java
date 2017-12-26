package com.cdsxt.filter;


import com.cdsxt.util.HibUtils;

import javax.servlet.*;
import java.io.IOException;

// 统一打开和关闭 Session
public class SessionFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HibUtils.getSession();
        System.out.println("filter1: " + HibUtils.getSession().hashCode());
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter2: " + HibUtils.getSession().hashCode());
        HibUtils.closeSession();
        System.out.println("filter3: " + HibUtils.getSession().hashCode());
    }

    @Override
    public void destroy() {

    }
}
