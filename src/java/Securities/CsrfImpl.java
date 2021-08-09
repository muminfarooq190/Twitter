

package Securities;

import Model.User;

public class CsrfImpl {
    
    public static void generateToken(User usr)
    {
      
        if(usr.getRequest().getSession().getAttribute("CSRFTOKEN")==null)
        {
          usr.getRequest().getSession().setAttribute("CSRFTOKEN",Math.random());
        }
 
    }
    
    public static boolean checkToken(User usr,String token)
    {
        boolean falsetoken = true;
      if(token.equals(usr.getRequest().getSession().getAttribute("CSRFTOKEN")))
      {
         falsetoken = false;
      }
      return falsetoken;
    }
    
    public static String getTokenField(User usr)
    {
      return "<input type='hidden' id='token' name='token' value = '"+usr.getRequest().getSession().getAttribute("CSRFTOKEN")+"'>";
    }
    public static void destroyToken(User usr)
    {
      usr.getRequest().getSession().removeAttribute("CSRFTOKEN");
    }
    
}
