

package Controller;

import Model.User;
import Model.login;
import java.io.IOException;


public class AuthoriseUser {
    public boolean isAuthorised(User usr)
    {
        boolean flag = false;
      if(usr.getRequest().getSession().getAttribute("AUTHSESSION") !=null)
      {
        flag = true;
      }
      return flag;
    }
    public boolean isAuthorised(login usr)
    {
        boolean flag = false;
      if(usr.getRequest().getSession().getAttribute("AUTHSESSION") !=null)
      {
        flag = true;
      }
      return flag;
    }
    
    public void redirectUser(User usr)
    {
        try {
            usr.getResponse().sendRedirect("login.jsp?ID=101");
            
        } 
        catch (IOException ex)
        {
        }
    }
     public void redirectUser(login usr)
    {
        try {
            usr.getResponse().sendRedirect("login.jsp?ID=99");
            
        } 
        catch (IOException ex)
        {
        }
    }
    public static String  setMessageUser(User usr)
    {
        String message = "";
      if(usr.getRequest().getParameter("ID")!=null)
      {
            switch (usr.getRequest().getParameter("ID")) {
                case "101":
                    message = "Not Authorised to visit";
                    break;
                 case "99":
                    message = "Not Logged in ";
                    break;    
                case "200":
                    message = "Welcome";
                    break;
            }
      }
      else{
          return "";      }
      return message;
    }
    public static String getUser(User usr)
    {
        String user = "";
       if(usr.getRequest().getSession()!=null)
       {
          user  = (String)usr.getRequest().getSession().getAttribute("AUTHSESSION");
       }
       return user;
      }
    public static String getLoggedinUser(login usr)
    {
        String user = "";
      if(usr.getRequest().getParameter("ID")!=null)
      {
        if(usr.getRequest().getParameter("ID").equals("200"))
        {
          user=(String)usr.getRequest().getSession().getAttribute("AUTHSESSION");
        }
      }
      
      return user;
    }
    
}
