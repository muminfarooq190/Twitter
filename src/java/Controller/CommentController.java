

package Controller;

import Model.User;
import Persistence.dbContext;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mumin
 */
public class CommentController extends Accounts {

   public CommentController() {
        
    }
    
    public static boolean isValidComment(User usr)
    {
      return(!usr.getMessage().equals(""));
    }
    private static void doComment(User usr)
    {
        Connection  conno = dbContext.Connect();
        
        
        usr.setQuery("INSERT INTO `comments` VALUES('0',CURRENT_TIMESTAMP,'"+usr.getMessage()+"','"+ProfileController.getUserid(usr, AuthoriseUser.getUser(usr))+"')");
        System.out.println(usr.getQuery());
        try
        {
           usr.setPsmt(conno.prepareStatement(usr.getQuery()));
           usr.getPsmt().execute();
           getCommentId(usr,conno);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public  static boolean comment(User usr)
    {
      if(isValidComment(usr))
      {
         doComment(usr);
         return true;
      }
      else
      {
         usr.setMessage("Comment cannot be empty");
         return false;
       }
    }
    
    private static void getCommentId(User usr,Connection con)
    {
        int id = 0;
       usr.setQuery("SELECT * from `comments` ORDER BY id DESC ");
       try
       {
           usr.setPsmt(con.prepareStatement(usr.getQuery()));
           usr.setRs(usr.getPsmt().executeQuery());
           while(usr.getRs().next())
           {
              id = usr.getRs().getInt("id");
              insertIntoJunctionTable(usr,con,id);
              break;
           }
           
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());
       }
    }
    private static void insertIntoJunctionTable(User usr,Connection con,int id)
    {
      usr.setQuery("INSERT INTO `tweetcomment` VALUES ('0','"+usr.getId()+"','"+id+"')");
      try
      {
        usr.setPsmt(con.prepareStatement(usr.getQuery()));
        usr.getPsmt().execute();
      }
      catch(SQLException e)
      {
          System.out.println(e.getMessage());
      }
    }
    public  void deleteComment(User usr)
    {
        if(this.deleteCommentsFromCommentsTable(usr))
        {
           deleteFromJunctionTable(usr);
        }
     
    }
    private void deleteFromJunctionTable(User usr)
    {
      boolean flag = false;
      usr.setQuery("DELETE FROM `tweetcomment` where `commentid`="+usr.getId()+"");
      try
      {
        usr.setPsmt(con.prepareStatement(usr.getQuery()));
        if(usr.getPsmt().executeUpdate()>0)
        {
            flag =  true;
        }
        
      }
      catch(SQLException e)
      {
          System.out.println(e);
        flag =  false;
      }
     
    }
   
    private  boolean deleteCommentsFromCommentsTable(User usr)
    {
       boolean flag = false;
      usr.setQuery("DELETE FROM `comments` where `id`="+usr.getId()+"");
        System.out.println(usr.getQuery());
      try
      {
        usr.setPsmt(con.prepareStatement(usr.getQuery()));
        if(usr.getPsmt().executeUpdate()>0)
        {
            flag =  true;
        }
        
      }
      catch(SQLException e)
      {
          System.out.println(e);
        flag =  false;
      }
      return flag;
    }
}
