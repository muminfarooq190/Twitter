/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.User;
import java.sql.SQLException;


public class ResetPasswordController extends Accounts {
    
    private boolean isValidUsernameAndQuote(User usr)
    {
      return (!usr.getUsername().equals("") && !usr.getFavquote().equals(""));
    }
    public boolean resetPassword(User usr)
    {
      if(this.isValidUsernameAndQuote(usr))
      {
          checkUserNameAndQuote(usr);
          return true;
       }
      else{usr.setMessage("Cant have empty fields"); return false;}
    }
    
    private  void checkUserNameAndQuote(User usr)
    {
       String quote = "";
        usr.setQuery("SELECT `username` from `user` where `username` ='"+usr.getUsername()+"'");
      try{
          usr.setSmt(con.createStatement());
          usr.setRs(usr.getSmt().executeQuery(usr.getQuery()));
          if(usr.getRs().next())
          {
              usr.setQuery("SELECT `favquote` from `profile` where `handle` ='"+usr.getUsername()+"'");
              usr.setSmt(con.createStatement());
              usr.setRs(usr.getSmt().executeQuery(usr.getQuery()));
              while(usr.getRs().next())
              {
                  quote=(usr.getRs().getString("favquote"));
                 
              }
              quote = quote.trim();
              usr.setFavquote(usr.getFavquote().trim());
              if(quote.equals(usr.getFavquote()))
              {
                  System.out.println("here");
                  updatePassword(usr);
              }
              else{usr.setMessage("Identity not matched yet!! please try again");}
          }
       }
      catch(SQLException e)
      {
      }
    }
    private void updatePassword(User usr)
    {
       usr.setPassword(encrypt.Md5(encrypt.Sha_1(encrypt.Md5(encrypt.Sha512(usr.getPassword())))));
        System.out.println(usr.getPassword());  
       usr.setQuery("UPDATE `user` set `password` ='"+usr.getPassword()+"' where `username`='"+usr.getUsername()+"'");
      try{
          usr.setPsmt(con.prepareStatement(usr.getQuery()));
          usr.getPsmt().execute();
          usr.setMessage("Password Reseted Successfully");
          
         
      }
      catch(SQLException e)
      {}
    }
    
}
