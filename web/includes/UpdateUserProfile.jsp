<%@page import="Model.Profile"%>
<%@page import="java.util.ArrayList"%>
<%@include file="config.jsp" %>
<%@page import="com.google.gson.Gson"%>
<%
 if(request.getMethod().equals("GET")){
    ArrayList mylist = ac.getData(user);
    String temp_json =  new Gson().toJson(mylist);
    out.print(temp_json);
 }
 else
 {
     User pf= (User)new Gson().fromJson(request.getParameter("DETAILS"), User.class);
     pf.setRequest(request);
     pf.setResponse(response);
     //out.print(pf.getSdescp());
     ac.UpdateUser(pf);
     out.print(pf.getMessage());
 }
  
%>