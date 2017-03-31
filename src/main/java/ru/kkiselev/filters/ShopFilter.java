package ru.kkiselev.filters;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kv on 09.01.17.
 */

//@WebFilter(urlPatterns = { "/login" })
public class ShopFilter implements Filter{
    private static Set<String> adminList = new HashSet<>();
    private static Set<String> userList = new HashSet<>();

    static {
        adminList.add("/admin.jsp");
        userList.add("/products.jsp");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String query = httpRequest.getRequestURI();

        if ((session == null || session.getAttribute("logged") == null)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");

        } else {
            if((boolean)session.getAttribute("isAdmin") == true){
                if(! adminList.contains(query)){
                    //без localhost вообще не работает
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "localhost:8080/login.jsp");
                } else{
                    chain.doFilter(httpRequest, httpResponse);
                }
            }
            else{
                if(! userList.contains(query)){
                    //без localhost выдает "Привет, null"
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "localhost:8080/login.jsp");
                } else{
                    chain.doFilter(httpRequest, httpResponse);
                }
            }
        }
    }

    public void destroy() {}
    public void init(FilterConfig filterConfig){}

}
