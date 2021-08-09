
package Controller;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TweetController extends ProfileController {
    
    private boolean isValidTweet(User usr)
    {
      return(!usr.getTweet().equals(""));
    }
    private boolean postTweet(User usr)
    {
        try{
              usr.setQuery("INSERT INTO `tweet` values('0','"+getUserid(usr)+"','"+usr.getTweet()+"',CURRENT_TIMESTAMP);");
              System.out.println(usr.getQuery());
              usr.setPsmt(con.prepareStatement(usr.getQuery()));
              usr.getPsmt().execute();
              return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void tweet(User usr)
    {
      if(this.isValidTweet(usr))
      {
        this.postTweet(usr);
        usr.setMessage("Post tweeted");
      } 
      else
      {
        usr.setMessage("Please write something...");
      }
    }
    public String getTweet(User usr) 
    {
        String returned = "";
      try{
          String name = ProfileController.getUserName(usr, usr.getUsername());
        
           Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String innit = sdf.format(cal.getTime()) ;
          Date date = sdf.parse(innit);
     
        usr.setQuery("SELECT tweet.id,tweet.tweet,handle,date from tweet,profile where tweet.userid = "+usr.getId()+" and profile.userid=tweet.userid and tweet.userid=profile.userid  ORDER BY tweet.id DESC");
         
        usr.setPsmt(con.prepareStatement(usr.getQuery()));
        usr.setRs(usr.getPsmt().executeQuery());
        while(usr.getRs().next())
        {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
               String [] replace = usr.getRs().getString("date").split(" ");
                Date date2 = dateFormat.parse( replace[1]);
              String timeago = TweettimeController.timeAgo(date, date2);
              returned += 
                     "<div id='PostDiv'>" +FileController.getProfilePic(usr,name)+
                             "<a style='text-decoration:none; color:#a2a9b0;' href='userprofile.jsp?user="+usr.getRs().getString("handle")+"'><small style=' margin:20px;'>@"+usr.getRs().getString("handle")+"</small></a>"+
                              "<small style='color:#a2a9b0;'>"+timeago+"</small>"+ 
                              "<p class='textpara'>"+usr.getRs().getString("tweet")+"</p>"
                              +"<div class='maincomments' id=\"mainCommentDiv"+usr.getRs().getInt("id")+"\">"
                              
                                 +"<div id='comment'>"
                                     +"<textarea class='commenttext' id=\"commenttext"+usr.getRs().getInt("id")+"\" placeholder='Add Reply' ></textarea>"
                                 +"</div>"
                                     +"<input style=' color:#a2a9b0; width:70px; margin-top:1rem;margin-left:20rem; background:#f1f3f4;\n" +
                                       "border-radius: 25px;\n" +
                                        "border: none;\n" +
                                        "border: 2px solid #f1f3f4;' type='button' id='commentbutton' value='Add' onclick='addComment("+usr.getRs().getInt("id")+")'>"
                              +"</div>"
                             
                              +"<div style='color:#a2a9b0;cursor:pointer; margin-top:10px; font-size:14px;  id='toggleComments' onclick='toggleComments("+usr.getRs().getInt("id")+")'>AddComments</div>"
                      +"<div style='min-width:350px; margin-top:10px; margin-left:20px;padding:10px; border-raduis:10px;'>"          
                      +getComment(usr,usr.getRs().getInt("id"))
                      +"</div>"
                      +"</div>"
                    
                  ;
        }
      
      }
      catch(SQLException e)
      {
        usr.setMessage(e.getMessage());
      } catch (ParseException ex) { 
            Logger.getLogger(TweetController.class.getName()).log(Level.SEVERE, null, ex);
        } 
      return returned;
    }
    
    public String getComment(User usr,int id)
    {
        String returned = "";
       usr.setQuery("SELECT `commentid` from `tweetcomment` where `tweetid`='"+id+"'ORDER BY commentid DESC");
       try
       {
         PreparedStatement st  = (con.prepareStatement(usr.getQuery()));
         ResultSet rs = (st.executeQuery());
         while(rs.next())
         {
           int commentid = rs.getInt("commentid");
           returned +=  getCommentandUserId(usr,commentid);
         }
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());
           return "";
       }
       return returned;
    }
    private String getCommentandUserId(User usr,int id)
    {
        String returned = "";
        String comment = "";
        String usrid = ""; 
        usr.setQuery("SELECT `comment`,`userid` from `comments` where `id`='"+id+"'ORDER BY id DESC");
       try
       {
           PreparedStatement st = (con.prepareStatement(usr.getQuery()));
           ResultSet rs = (st.executeQuery());
         while(rs.next())
         {
           comment =  rs.getString("comment");
           usrid =  rs.getString("userid");
           String username  =  ProfileController.getUserName(usr, usrid);
           returned =  "<p><strong>"+username+"</strong></p>";
               
                     if(username.equals(AuthoriseUser.getUser(usr)))
                     {
                        returned += 
                                "<p id='\"commentpara"+id+"\"'>"+comment+"&nbsp&nbsp<small style='color:#a2a9b0; cursor:pointer;' onclick=\"javascript:deleteComment('"+id+"')\">Delete</small></p>"; 
                     }
                     else{
                        returned+=  "<p>"+comment+"</p>";
                     }
                                      
        
         }
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());
       }
       return returned;
    }
    
    
}
