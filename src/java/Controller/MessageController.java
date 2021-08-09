

package Controller;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MessageController extends Accounts {
    private boolean isValidMessage(User usr)
    {
      return (!usr.getMessage().equals(""));
    }
    private Integer getMessageId(User usr)
    {
     Integer id = null;
     try{
       usr.setQuery("SELECT `id` from `messages`  ORDER BY id DESC  LIMIT 1");
       usr.setPsmt(con.prepareStatement(usr.getQuery()));
       usr.setRs(usr.getPsmt().executeQuery());
       while(usr.getRs().next())
       {
          id = usr.getRs().getInt("id");
       }
       return id;
     }
     catch(SQLException e)
     {
       return null;
     }
    }
    
    private boolean postMessage(User usr)
    {
       usr.setQuery("INSERT INTO `messages` VALUES ('0','"+usr.getMessage()+"',CURRENT_TIMESTAMP)");
       try
       {
         usr.setPsmt(con.prepareStatement(usr.getQuery()));
         usr.getPsmt().executeUpdate();
         return true;
       }
       catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
    }
    
    private boolean postMessageinJunction(User usr)
    {
        usr.setUsername(usr.getRequest().getSession().getAttribute("AUTHSESSION").toString());
   
        String id = ProfileController.getUserid(usr);

        try
      {
         usr.setQuery("INSERT INTO `messagejunction` VALUES ('0','"+id+"','"+usr.getId()+"','"+this.getMessageId(usr)+"','0');");
         usr.setPsmt(con.prepareStatement(usr.getQuery()));
            
         usr.getPsmt().executeUpdate();
         return true;
      }
      catch(SQLException e){return false;}
     
    }
    
    public void chat(User usr)
    {
      if(isValidMessage(usr))
      {
         if(this.postMessage(usr))
         {
             postMessageinJunction(usr);
         }
         else
         {
             System.out.println("failed to deliever the message");
         }
      }
      else{usr.setMessage("nothing to senf");}
    }
    public String getMessages(User usr)
    {
        usr.setUsername(usr.getRequest().getSession().getAttribute("AUTHSESSION").toString());
   
        String id = ProfileController.getUserid(usr);
        String message = "";
        usr.setQuery("SELECT messages.*,messagejunction.*,user.* FROM messages,messagejunction,user WHERE (messagejunction.to='"+usr.getId()+"' AND messagejunction.from='"+id+"' OR messagejunction.from='"+usr.getId()+"' AND messagejunction.to='"+id+"')AND messagejunction.messageid=messages.id and user.id=messagejunction.from");
        
        try{
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            
            usr.setRs(usr.getPsmt().executeQuery());
            
            while(usr.getRs().next())
            {
                
               message += "<div id='messageDivv'>"+
                            "<small>"+
                            "<strong id='small'>"+usr.getRs().getString("username")+"</strong>:"+
                            "<small id='small'>"+usr.getRs().getString("text")+"</small>"+
                            "</small>"+"</div>" ;
                
            }
            usr.setQuery("SELECT * FROM messagejunction WHERE messagejunction.to='"+usr.getId()+"' AND messagejunction.from='"+id+"' OR messagejunction.from='"+usr.getId()+"' AND messagejunction.to='"+id+"' ORDER BY id DESC LIMIT 1 ;");
            System.out.println(usr.getQuery());
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            
            usr.setRs(usr.getPsmt().executeQuery());
            
           while(usr.getRs().next())
           {if(usr.getRs().getString("status").equals("1"))
                {message+="<small>seen</small>";

                }
           
           }
           
        }
        catch(SQLException e){System.out.println(e);}
      return  message;
    }
    public void textFriend(User usr)
    {
        
      if(this.isValidMessage(usr))
      {
         if(this.postMessage(usr)){
            
         usr.setId(ProfileController.getUserid(usr, usr.getUsername()));
             
         this.postMessageinJunction(usr);
         }
      }
    }
    
    public String getTexts(User usr)
    {
        usr.setId(ProfileController.getUserid(usr, usr.getUsername()));
        return this.getMessages(usr);
    }
    
    public String seen(User usr)
    {
        boolean changed = false;
        String seen = "";
        usr.setId(ProfileController.getUserid(usr,usr.getUsername()));
        String sessionid = ProfileController.getUserid(usr, usr.getRequest().getSession().getAttribute("AUTHSESSION").toString());
        usr.setQuery("SELECT  * from `messagejunction` where `to`='"+sessionid+"';");
         
        try{
            usr.setPsmt(con.prepareStatement(usr.getQuery()));
            usr.setRs(usr.getPsmt().executeQuery());
            String from = "";
            String status = "";
            while(usr.getRs().next())
            {
                 from =  usr.getRs().getString("from");
                 status =  usr.getRs().getString("status");
                 
                   if(usr.getRs().getString("to").equals(sessionid))
                   {String query = "UPDATE `messagejunction` SET `status`=1 where `to`='"+sessionid+"' and `status`=0 ;";
                     PreparedStatement smt = con.prepareStatement(query);
                     smt.execute();
                     changed = true;
                   }
                  
                   
            }
           // System.out.println(sessionid+from);
            if(!from.equals(sessionid) && status.equals("1"))
              {
              
               
              }
            
        }
        catch(SQLException e){System.out.println(e.getMessage());}
        
        return seen;
    }
    
}
