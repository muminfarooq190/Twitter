/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.User;
import Persistence.dbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfileController {
    
    public static Connection con = dbContext.Connect();
     public static String getUserid(User usr)
    {
        
        String id = "";
     try{
            usr.setQuery("Select `id` from `user` where username='"+usr.getUsername()+"'");
            usr.setPsmt(ProfileController.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("id");
            }
       }
        catch(SQLException e)
        {
           
          usr.setMessage(e.getMessage());
          return null;
        }
      
     return id;
    }
     public static String getUserName(User usr,String id)
    {
        
        String name = "";
     try{
            String query = "Select `username` from `user` where id='"+id+"'";
            PreparedStatement st = (ProfileController.con.prepareStatement(query));
            ResultSet rs = (st.executeQuery());
            while(rs.next())
            {
              name = rs.getString("username");
            }
       }
        catch(SQLException e)
        {
           
            
          usr.setMessage(e.getMessage());
          return null;
        }
      
     return name;
    }
      public static String getUserid(User usr,String username)
    {
        String id = "";
     try{
            usr.setQuery("Select `id` from `user` where username='"+username+"'");
            usr.setPsmt(ProfileController.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("id");
            }
       }
        catch(SQLException e)
        {
           
          usr.setMessage(e.getMessage());
          return null;
        }
      
     return id;
    }
      private static String getCountryId(User usr)
    {
         String id = "";
     try{
            usr.setQuery("Select `id` from `country` where userid='"+getUserid(usr)+"'");
            
           // System.out.println(">>"+usr.getQuery());
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("id");
            }
       }
        catch(SQLException e)
        {
            //System.out.println(e.getMessage());
          usr.setMessage(e.getMessage());
          return null;
        }
      
     return id;
    
    }
    private static  String getCountryUser(User usr)
    {
        String id = "" ;
        try {
            ProfileController.con =  dbContext.Connect();
            
            usr.setQuery("SELECT `name` from `country` where `userid`='"+getUserid(usr)+"'");
            usr.setPsmt(ProfileController.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("name");
            }
            
            
           
        } catch (SQLException ex)
        {
             
             return "";
           
        }
        return id;
    }
     private static  String getStateUser(User usr)
    {
        String id = "" ;
        try {
            ProfileController.con =  dbContext.Connect();
            
            usr.setQuery("SELECT `name` from `state` where `country`='"+getCountryId(usr)+"'");
            usr.setPsmt(ProfileController.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("name");
            }
            
            
           
        } catch (SQLException ex)
        {
            
             return "";
           
        }
        return id;
    }
     private static String getbirthday(User usr)
     {
       String id = "" ;
        try {
            ProfileController.con =  dbContext.Connect();
            
            usr.setQuery("SELECT `DOB` from `user` where `id`='"+getUserid(usr)+"'");
            usr.setPsmt(ProfileController.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("DOB");
            }
            
            
           
        } catch (SQLException ex)
        {
             
             return "";
           
        }
        return id;
     }
    public static String locate(User usr)
    {
        String id="";
      id = getCountryUser(usr);
      id += " ,";
      id += "<h6 style = 'padding-left:10px;'>";
      id += getStateUser(usr);
      id += "</h6>";
      return id;
    }
    
    public static String Birthday(User usr)
    {
      String id = "";
      id = getbirthday(usr);
      return id;
    }
    public static String getFollowandMessageButtons(User usr)
    {
        
      if(usr.getUsername().equals(AuthoriseUser.getUser(usr)))
      {
       return "<input id=\"EditProfilebutton\" type=\"button\" class=\"btn xss  btn-secondary\" value=\"edit profile\" onclick=editProfileInProfile()>\n" +
                 "<button id=\"quotebutton\" type=\"button\" class=\"btn xss btn-secondary\" data-toggle=\"modal\" data-target=\"#exampleModal\" value=\"Get a quote\">Get a quote</button>\n" +
                 "";
      }
      else
      {
         return "<input id=\"followbutton\" type=\"button\" class=\"btn xss  btn-primary\" value=\"Follow\">\n" +
                 "<input id=\"unfollowbutton\" type=\"button\" class=\"btn xss btn-primary\" value=\"unFollow\">\n" +
                 "<input id=\"messagebutton\" type=\"button\" class=\"btn xss btn-default\" value=\"Message\">\n" +
                 
                 "";
      }
    }
    
    private static boolean addFollowerTodatabase(User usr)
    {
        try {
          
            usr.setQuery("INSERT INTO `followers` values(0,"+usr.getId()+",CURRENT_TIMESTAMP,"+getUserid(usr, AuthoriseUser.getUser(usr))+");");
            
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            return usr.getPsmt().execute();
        } catch (SQLException ex) 
        { 
           
             return false;
          }
      
    }
    
    public static void addFollower(User usr)
    {
      if(addFollowerTodatabase(usr))
      {
        usr.setMessage("Follow success");
      }
      else
      {
        
      }
      
    }
    public static boolean checkFollower(User usr)
      {
           boolean flag = false;
        try {
           
            usr.setQuery("SELECT `follower` from `followers` where `following`="+getUserid(usr, AuthoriseUser.getUser(usr))+" and `follower`="+ProfileController.getUserid(usr, usr.getUsername())+"");
           
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              flag = true; 
            }
            
            
        } catch (SQLException ex) {
             }
        return flag;
      }
    public static void unFollower(User usr)
    {
        try {
             usr.setQuery("DELETE  from `followers` where `following`="+getUserid(usr, AuthoriseUser.getUser(usr))+" and `follower`="+usr.getId()+"");
        
             
             usr.setPsmt(con.prepareStatement(usr.getQuery()));
            usr.getPsmt().executeUpdate();
        } catch (SQLException ex) {
            
             }
    }
    public static boolean isFriend(User usr, String userid,String loggedinusername)
    {
        String friendid = "";
      boolean isFriend = false;
      usr.setQuery("SELECT `follower` from `followers` where `following`= '"+getUserid(usr, loggedinusername)+"'");
      try{
      usr.setPsmt(con.prepareStatement(usr.getQuery()));
      usr.setRs(usr.getPsmt().executeQuery());
        while(usr.getRs().next())
        {
            friendid = usr.getRs().getString("follower");
            if(friendid.equals(userid))
                {
                  isFriend = true;
                  return isFriend;
                  }
        }
         
        
      }
      catch(SQLException e)
      {
      }
      return isFriend;
    }
    
}
