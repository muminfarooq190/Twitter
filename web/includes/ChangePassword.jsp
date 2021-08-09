<%@include file="config.jsp" %>

<%
 user.setPassword(request.getParameter("CURRENT"));
 user.setOldPassword(request.getParameter("PREVIOUS"));
 
 if(ac.changePassword(user))
 {
   user.setMessage("Passwordchanged");
    
 }
 out.print(user.getMessage());
%>
