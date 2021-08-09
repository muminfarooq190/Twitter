/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interceptors;

import Controller.AuthoriseUser;
import Model.User;
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

@WebFilter(urlPatterns = "/editProfile.jsp" )
public class editProfileFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User usr = new User();
        usr.setRequest(req);
        usr.setResponse(res);
        PrintWriter out = res.getWriter();
        AuthoriseUser user = new AuthoriseUser();
        if(user.isAuthorised(usr))
        {
            
          chain.doFilter(request, response);
        }
        else{
        
          user.redirectUser(usr);
        }
        
        
        
    }

    @Override
    public void destroy() {
         }
    
}
