<%@page import="Securities.CsrfImpl"%>
<%@include file="config.jsp" %>
<%
 if(request.getMethod().equals("POST"))
 {
   request.getParameter("token");
  if(CsrfImpl.checkToken(user, request.getParameter("token")))
  {
    out.print("NO CSRF ATTEMPT");
    
  }
  else
  {
    out.print("CSRF ATTEMPT");
    response.sendRedirect("login.jsp?csrf=true");
  }
 }
%>
