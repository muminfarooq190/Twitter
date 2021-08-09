<%@page import="Controller.encrypt"%>
<%@page import="Controller.Accounts"%>
<%@include file="config.jsp" %>

<%@page import="com.google.gson.Gson"%>
<%
   
  if(request.getMethod().equals("POST"))
  {
    
   User reg_user = (User)new Gson().fromJson(request.getParameter("DETAILS"),User.class);
   reg_user.setPassword(encrypt.Md5(encrypt.Sha_1(encrypt.Md5(encrypt.Sha512(reg_user.getPassword())))));
   reg_user.setRequest(request);
   reg_user.setResponse(response);

   ac.Signup(reg_user);
   out.print(reg_user.getMessage());
  }
 
%>