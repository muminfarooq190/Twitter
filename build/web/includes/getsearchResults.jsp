<%@page import="Controller.FileController"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Controller.SearchController"%>

<%@include file="../views/header.jsp" %>
<%
  if(request.getMethod().equals("POST"))
  {
       SearchController sc = new SearchController();
        ResultSet  rs;
     if(request.getParameter("FRIENDSSEARCH")==null){
    
   String search =  request.getParameter("SEARCH");
   user.setUsername(search);
  
   rs = sc.getSearch(user);
  
   user.setUsername(rs.getString("username"));
   out.print(
           "<table id = 'searchTable' cellspacing = 5 cellpadding = 5>"+
             "<tr style='border-bottom:1px solid #ccc'>"+      
             "<td id='firsttd'><div id='searchprofilediv'>"+FileController.getProfilePic(user)+"</div></td>"+      
               
          
          "<td style='margin-left:-300px;'><a style='text-decoration:none; color:black' href='userprofile.jsp?user="+user.getUsername()+"'><b>"+rs.getString("username")+"</b></a></td>"+
                 
            "</tr>"+  
                   
          ""+
          ""+
          ""+
          "</table>"
   
   );
 
     }
     else
     {
         String search =  request.getParameter("FRIENDSSEARCH");
          user.setUsername(search);
          rs = sc.getSearch(user);
          user.setUsername(rs.getString("username"));
   out.print(
           "<table id = 'searchTable' cellspacing = 5 cellpadding = 5>"+
             "<tr style='border-bottom:1px solid #ccc'>"+      
             "<td id='firsttd'><div id='searchprofilediv'>"+FileController.getProfilePic(user)+"</div></td>"+      
               
          
          "<td style='margin-left:-300px;'><a id='chatNow' style='text-decoration:none; color:black; cursor:pointer' onclick='displaychatNow()'  ><b>"+rs.getString("username")+"</b></a></td>"+
                 
            "</tr>"+  
                   
          ""+
          ""+
          ""+
          "</table>"
   );
   
     }
  }

%>
