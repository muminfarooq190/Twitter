<%@page import="Controller.MessageController"%>
<%@include  file="config.jsp" %>

<%
 if(request.getMethod().equals("GET"))
 {
    MessageController mc = new MessageController();
    user.setUsername(request.getParameter("ANOTHER"));
    String message = mc.seen(user);
    out.print(message);
 }
%>