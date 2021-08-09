<%@page import="Controller.ResetPasswordController"%>
<%@include file="config.jsp" %>
<%
      user.setUsername(
      request.getParameter("Name"));
     user.setFavquote(request.getParameter("Quote"));
     user.setPassword(request.getParameter("Pass"));
     ResetPasswordController rcp = new ResetPasswordController();
     rcp.resetPassword(user);
     out.print(user.getMessage());
%>