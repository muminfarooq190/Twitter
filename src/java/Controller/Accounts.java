
package Controller;

import Model.User;
import Persistence.dbContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Accounts {
   public Connection con;
   public Accounts()
    {
      this.con = dbContext.Connect();
        
    }
    private String getUserid(User usr)
    {
        String id = "";
     try{
            usr.setQuery("Select `id` from `user` where username='"+usr.getRequest().getSession().getAttribute("AUTHSESSION")+"'");
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            while(usr.getRs().next())
            {
              id = usr.getRs().getString("id");
            }
       }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
          usr.setMessage(e.getMessage());
          return null;
        }
      
     return id;
    }
     private boolean checkuser(User usr) throws IOException,SQLException
    {
        
        boolean userExists =false;
        usr.setQuery("select * from user where username ='" + usr.getUsername()+"';");
        usr.setSmt(this.con.prepareStatement(usr.getQuery()));
        usr.setRs(usr.getSmt().executeQuery(usr.getQuery()));
        
        while(usr.getRs().next())
        {
         userExists =true;
        }
       // System.out.println(userExists);
        return userExists;
        
    }
     private boolean addUser(User usr)
    {
        String DOB = usr.getYear()+"-"+usr.getMonth()+"-"+usr.getDay();
        try 
        {
            usr.setQuery("insert into user values('"+"0"+"','"+"1"+"','"+usr.getFname()+"','"+usr.getLname()+"','"+usr.getContact()+"','"+DOB+"','"+usr.getUsername()+"','"+usr.getPassword()+"');");
            //System.out.println(usr.getQuery());
            //System.out.println(con);
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            
            int res = usr.getPsmt().executeUpdate();
            //System.out.println(res);
            return res > 0;
        } 
        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
            usr.setMessage(ex.getMessage());
            return false;
        }
             
        } 
     
    private boolean setProfile (User usr)
    {
        String desc = "I am wonderful";
        String quote = "I tryna fix my pride , but thats shit is broken";
        boolean flag = false;
        try
        {
            usr.setQuery("INSERT INTO `profile` values('"+"0"+"','"+this.getUserid(usr)+"','"+usr.getContact()+"','"+usr.getLname()+"','"+usr.getUsername()+"','"+desc+"','"+quote+"') ");
           // System.out.println(usr.getQuery());
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            int res = usr.getPsmt().executeUpdate();
             if(res > 0)
            {
              flag = true;
            }

        }
        catch(SQLException e)
        {
            flag = false;
          usr.setMessage(e.getMessage());
            //System.out.println(e.getMessage());
        }
        return flag;
    }
    
    private void setAuthorization(User usr)
    {
      usr.getRequest().getSession().setAttribute("AUTHSESSION", usr.getUsername());
    }
    private String getCountryId(User usr)
    {
         String id = "";
     try{
            usr.setQuery("Select `id` from `country` where userid='"+this.getUserid(usr)+"'");
            
           // System.out.println(">>"+usr.getQuery());
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
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
    private void setState(User usr)
    {
      try
        {
            usr.setQuery("INSERT INTO `state` values('"+"0"+"','"+this.getCountryId(usr)+"','null') ");
           
    //System.out.println(usr.getQuery());
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            int res = usr.getPsmt().executeUpdate();
             if(res > 0)
            {
                //System.out.println("hahha");
            }

        }
        catch(SQLException e)
        {
            
          usr.setMessage(e.getMessage());
           // System.out.println(e.getMessage());
        } 
    }
    private boolean setCountry(User usr)
    {
        boolean flag = false;
       try
        {
         //   usr.setId(this.getUserid(usr));
            usr.setQuery("INSERT INTO `country` values('"+"0"+"','null','"+this.getUserid(usr)+"') ");
            
   // System.out.println(usr.getQuery());
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            int res = usr.getPsmt().executeUpdate();
             if(res > 0)
            {
               flag = true;
            }

        }
        catch(SQLException e)
        {
            flag = false;
          usr.setMessage(e.getMessage());
            //System.out.println(e.getMessage());
        }
       return flag;
    }
       
   public void Signup(User usr)
   {
       
        try {
            if(this.checkuser(usr))
             {
                 
               usr.setMessage("user already exists ");
             }
            else
            {
              
               if(this.addUser(usr))
               {
                   usr.setMessage("User addded successfully");
                   this.setAuthorization(usr);
                   this.setProfile(usr);
                   if(this.setCountry(usr)){this.setState(usr);}
                   //System.out.println(usr.getMessage());
               }
               else
               {
                usr.setMessage(usr.getMessage());
               }
              
            }
            } 
        catch (IOException | SQLException ex) {
            usr.setMessage(ex.getMessage());
            System.out.println("hahha yhi hai");
           }
   }
   
   public ArrayList getInfo(User usrs)
   {
         ArrayList <String> mylist = new ArrayList();
   
       String user = usrs.getUsername();
           try {
          usrs.setQuery("SELECT `userid`, `about`,`favName`,`handle`,`shortdescp`,`favquote` from `profile` where `handle`='"+user+"'");
         // System.out.println(usrs.getQuery());
          usrs.setPsmt(this.con.prepareStatement(usrs.getQuery())); 
          usrs.setRs(usrs.getPsmt().executeQuery());
         
         while(usrs.getRs().next())
         {
                   usrs.setMessage(usrs.getRs().getString("userid"));
                   mylist.add(usrs.getMessage());
                   usrs.setMessage(usrs.getRs().getString("shortdescp"));
                   mylist.add(usrs.getMessage());
                   usrs.setMessage(usrs.getRs().getString("handle"));
                   mylist.add(usrs.getMessage());
                   usrs.setMessage(usrs.getRs().getString("favName"));
                   mylist.add(usrs.getMessage());
                   usrs.setMessage(usrs.getRs().getString("favquote"));
                   mylist.add(usrs.getMessage());
                   usrs.setMessage(usrs.getRs().getString("about"));
                   mylist.add(usrs.getMessage());
                   
                  
                   
               }
          } 
       catch (SQLException ex) 
         {
              usrs.setMessage(ex.getMessage());
           }
       return mylist;
   //return usrs;
       
       
   }
   
   public ArrayList getData(User usr)
   {
       ArrayList <String> mylist;
          usr.setUsername(usr.getRequest().getSession().getAttribute("AUTHSESSION").toString());
          mylist =   this.getInfo(usr);
          
            //us.setMessage("lola");
          
 
       User usrs = new User()  ;
       return mylist;
       
   }
   
   public void UpdateUser(User usr)
   {
      if(this.UpdateProfile(usr))
      {
        usr.setMessage("User Updated Successfully");
      }
      else
      {
        usr.setMessage("Error while updating user!");
      }
   }
   private boolean UpdateProfile(User usr)
   {
       boolean flag;
       try {
           
           usr.setQuery("UPDATE `profile` set `about`='"+usr.getAbout()
                   +"',`handle`='"+usr.getHandle()+"',`favName`='"+usr.getFavname()
                   +"',`shortdescp`='"+usr.getSdescp()+"',`favquote`='"+usr.getFavquote()+
                   "' where `userid`='"+usr.getId()+"'");
           
           //System.out.println(usr.getCountry());
           usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
           usr.getPsmt().execute();
           //System.out.println(usr.getRequest().getSession().getAttribute("AUTHSESSION"));
           usr.setQuery("UPDATE `country` set `name`='"+usr.getCountry()+"' where `userid`='"+this.getUserid(usr)+"'");
           usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
           usr.getPsmt().execute();
           
           usr.setQuery("UPDATE `state` set `name`='"+usr.getState()+"' where `country`='"+this.getCountryId(usr)+"'");
           usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
           usr.getPsmt().execute();
           
           flag = true;
           
           
       } catch (SQLException ex) {
           flag = false;
           System.out.println(ex);
           }
      // System.out.println(">>>"+flag);
       return flag;
   }
   public   boolean changePassword(User usr)
   {
       usr.setOldPassword(encrypt.Md5(encrypt.Sha_1(encrypt.Md5(encrypt.Sha512(usr.getOldPassword())))));
     String password = "";
     boolean changed = false;
     usr.setQuery("SELECT `password` from `user` where `id`='"+getUserid(usr)+"'");
     try
     {
         usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
         usr.setRs(usr.getPsmt().executeQuery());
         while(usr.getRs().next())
         {
          password = usr.getRs().getString("password");
         }
            if(password.equals(usr.getOldPassword()))
         {
            usr.setPassword(encrypt.Md5(encrypt.Sha_1(encrypt.Md5(encrypt.Sha512(usr.getPassword())))));
            usr.setQuery("UPDATE `user` set `password`='"+usr.getPassword()+"' where `id` = '"+getUserid(usr)+"';");
            usr.setPsmt(this.con.prepareStatement(usr.getQuery()));
            
            usr.getPsmt().execute();
            changed = true;
                    return changed;
              
         }
         else
         {
           usr.setMessage("please write your old password correctly");
         }
         
     }
     catch(SQLException e)
     {
         changed = false;
         usr.setMessage(e.getMessage());
     }
     return changed;
   }
}
