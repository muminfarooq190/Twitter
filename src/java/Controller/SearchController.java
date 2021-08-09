

package Controller;

import Model.User;
import Persistence.dbContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchController {
    private static Connection con=null;
    public SearchController()
    {
      SearchController.con = dbContext.Connect();
    }
    
    private boolean isValidSearch(User usr)
    {
        System.out.println(">>"+usr.getUsername());
       return (!usr.getUsername().equals("") );
    }
    
    private ResultSet Search(User usr)
    {
        try
        {
            usr.setQuery("Select `fname`, `lname` ,`username` from `user` where `username` LIKE '"+usr.getUsername()+"%' ");
            usr.setSmt(SearchController.con.createStatement());
            usr.setRs(usr.getSmt().executeQuery(usr.getQuery()));
            if(usr.getRs().next()){
                return usr.getRs();
            }
            return null;
        }
        catch(SQLException e){return null;}
        }
    public   ResultSet  getSearch(User usr)
    {
      if(this.isValidSearch(usr))
      {
          return   Search(usr);
          
      }
      else
      {
        usr.setMessage("enter valid search paramateres");
          System.out.println(usr.getMessage());
          return null;
      }
    
    }
   
}
