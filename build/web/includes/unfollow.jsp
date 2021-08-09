<%@page import="Controller.ProfileController"%>
<%@include file="config.jsp" %>

<%
user.setId(request.getParameter("REQUESTTO"));
ProfileController.unFollower(user);
%>
