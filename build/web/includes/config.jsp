<%@page import="Model.login"%>
<%@page import="Model.User"%>
<%@page import="Controller.Accounts"%>
<%
Accounts ac = new Accounts();
User user = new User();
user.setRequest(request);
user.setResponse(response);
login lg_user = new login();
lg_user.setRequest(request);
lg_user.setResponse(response);
String loginUrl = request.getServerName()+":"+request.getServerPort()+request.getContextPath()+request.getServletPath();
String DefaultUrl = request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/Profile.jsp";

%>
