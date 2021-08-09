<%@page import="Controller.CommentController"%>
<%@include file="config.jsp" %>
<%
if(request.getMethod().equals("POST"))
{
  user.setId(request.getParameter("COMMENTID"));
  CommentController cc = new CommentController();
  cc.deleteComment(user);
}
%>