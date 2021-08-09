<%@page import="Controller.FriendsController"%>
<%@include file="config.jsp" %>
<%
if(request.getMethod().equals("GET"))
{
   
    
        user.setUsername(request.getParameter("FRIENDOF"));
       
        FriendsController fc = new FriendsController();
        out.print(fc.getFriends(user));
        
}
%>