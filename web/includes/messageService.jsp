<%@page import="Controller.MessageController"%>
<%@include file="config.jsp" %>
<%
MessageController mc = new MessageController();
if(request.getMethod().equals("POST"))
{
    if(request.getParameter("REALMESSAGE")!=null)
    {
         user.setMessage(request.getParameter("REALMESSAGE"));
         user.setUsername(request.getParameter("TO"));
         mc.textFriend(user);
         out.print("yess");
    }
    else{
    user.setMessage(request.getParameter("MESSAGE"));
    user.setId(request.getParameter("TO"));
 
     
     mc.chat(user);
    }}
else
{
    if(request.getParameter("REALTO")!=null)
    {
      user.setUsername(request.getParameter("REALTO"));
      String message = mc.getTexts(user);
      out.print(message);
    }
    else{
  user.setId(request.getParameter("TO"));
  String message = mc.getMessages(user);
  out.print(message);
    }
}
%>