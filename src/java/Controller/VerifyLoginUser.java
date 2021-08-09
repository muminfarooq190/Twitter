

package Controller;


import Model.login;
import Persistence.dbContext;
import java.sql.Connection;
import java.sql.SQLException;

public class VerifyLoginUser {
    private Connection con;
    
    public VerifyLoginUser(){
     this.con = dbContext.Connect();
    }
    private boolean checkUser(login user)
    {
        user.setPassword(encrypt.Md5(encrypt.Sha_1(encrypt.Md5(encrypt.Sha512(user.getPassword())))));
        boolean userExists = false;
        try {
            
            user.setQuery("SELECT `username` and `password` from `user` where `username`='"+user.getUsername()+"' and `password`='"+user.getPassword()+"';");
            user.setPsmt(this.con.prepareStatement(user.getQuery()));
            user.setRs(user.getPsmt().executeQuery());
            while(user.getRs().next())
            {
              userExists = true;
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            userExists = false;
        }
       return userExists;
    }
    private boolean Authorise(login user)
    {
        boolean flag = false;
        if(user.getChecked().equals("false"))
        {      user.getRequest().getSession().setAttribute("AUTHSESSION", user.getUsername());
                 flag = true;
        }
        return flag;
    }
    public boolean  loginUser(login user)
    {
      boolean flag =  false;
      if(this.checkUser(user))
      {
          if( this.Authorise(user))
          {
             flag = true;
          }
      }
      else
      {
          user.setMessage("Incorrect credentials");
          flag = false;
      }
      return flag;
    }
    
}
