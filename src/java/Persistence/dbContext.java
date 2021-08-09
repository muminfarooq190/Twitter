
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbContext {
    private static Connection con;
    public static Connection Connect()
    {
         try{
       Class.forName("com.mysql.cj.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
        //System.out.println("CONNECTION ESTABLISHED");
    
        }
        catch(ClassNotFoundException| SQLException e)
            {
                
            }
        
        return con;
    }
}
