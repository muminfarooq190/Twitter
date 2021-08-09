<%@page import="Securities.BruteForce"%>
<%@page import="Controller.VerifyLoginUser"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="Model.login"%>
<%@page import="Controller.Accounts"%>
<%@page import="Model.User"%>
<%
     
 
    login lg = (login) new Gson().fromJson(request.getParameter("mydata"), login.class);
 
    lg.setRequest(request);
    lg.setResponse(response);
      
    VerifyLoginUser vf = new VerifyLoginUser();
      if(vf.loginUser(lg))
    {
      lg.setMessage("bora");
    }
    else
    {
      Integer hitsCount = (Integer)lg.getRequest().getSession().getAttribute("hitCounter");
         if( hitsCount ==null || hitsCount == 0 ) {
             hitsCount = 1;
         } else {
             hitsCount += 1;
         }
         lg.getRequest().getSession().setAttribute("hitCounter", hitsCount);
         BruteForce.preventAttack(lg);
         if(lg.getRequest().getSession().getAttribute("BRUTEATTACK")!=null)
         {
             lg.setMessage("ATTEMPTS EXCEEDED");
             lg.getRequest().getSession().removeAttribute("BRUTEATTACK");
         }
         
   
    }
    
    out.print(lg.getMessage());

%>