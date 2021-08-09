/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.User;
import java.sql.SQLException;

/**
 *
 * @author Mumin
 */
public class FriendsController extends Accounts {
    
    public String getFriends(User usr)
    {
        int friend = 0;
     String returned = "";
     usr.setQuery("SELECT `follower` from `followers` where `following`='"+usr.getUsername()+"'");
     try
     {
      usr.setPsmt(con.prepareStatement(usr.getQuery()));
      usr.setRs(usr.getPsmt().executeQuery());
      
      while(usr.getRs().next())
      {
       returned += getInfoAboutFollowing(new User(),usr.getRs().getInt("follower"));
         
      }
     }
     catch(SQLException e)
     {
         System.out.println(e.getMessage());
     }
     
     
    return returned;
     
    }
    public String getInfoAboutFollowing(User usr,int following)
    {
      
      String returned = "";
      usr.setQuery("SELECT `handle` from `profile` where `userid`='"+following+"'");
      try
      {
        usr.setPsmt(con.prepareStatement(usr.getQuery()));
        usr.setRs(usr.getPsmt().executeQuery());
        while(usr.getRs().next())
         {
          returned = "<div id='FriendImage' style='min-height:200px' border:1px solid #ccc; margin20px;>"+FileController.getProfilePic(usr,usr.getRs().getString("handle"))+"<p><strong><a style='text-decoration:none; color:black' href='userprofile.jsp?user="+usr.getRs().getString("handle")+"'>"+usr.getRs().getString("handle")+"</a></strong></p></div>";
                  
           
         }
      }
      catch(SQLException e)
      {
          System.out.println(e.getMessage());
      }
      return returned;
    }
    
}
