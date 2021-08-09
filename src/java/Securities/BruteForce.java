/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Securities;


import Model.login;
public class BruteForce  {
 
    public static boolean preventAttack(login usr)
    {
        
        
        
         if(usr.getRequest().getSession().getAttribute("hitCounter")!=null)
      {
         int count =  Integer.parseInt(usr.getRequest().getSession().getAttribute("hitCounter").toString());
         
          if(count==3 || count >3)
          {
              usr.getRequest().getSession().setAttribute("BRUTEATTACK", "COWARDATTACKER");
              usr.getRequest().getSession().removeAttribute("hitCounter");
          } else {
                   
                 }
     
      }
      return false;
      
    
    }
  
    
   
    
}
