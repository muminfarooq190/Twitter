/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interceptors;

import Controller.AuthoriseUser;
import Model.login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/userprofile.jsp")
public class userProfileFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = res.getWriter();
        login user = new login();
        user.setRequest(req);
        user.setResponse(res);
        AuthoriseUser auth = new AuthoriseUser();
        if(auth.isAuthorised(user)){
           //String username = AuthoriseUser.getLoggedinUser(user);
            System.out.println(req.getServerName()+":"+req.getServerPort()+"//"+req.getServletContext().toString());
          if(req.getParameter("user")==null || req.getParameter("user").equals(""))
          {
            res.sendRedirect("login.jsp?ID=99");
          }
          chain.doFilter(request, response);
          
        }
        else{
          auth.redirectUser(user);
        }
        
        
    }

    @Override
    public void destroy() {
        }
    
}
