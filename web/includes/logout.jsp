<%@include file="../views/header.jsp" %>
 
<%
    if(lg_user.getRequest().getMethod().equals("POST")){
    if(lg_user.getRequest().getSession().getAttribute("AUTHSESSION")!=null){
   
     lg_user.getRequest().getSession().removeAttribute("AUTHSESSION"); 
     lg_user.getResponse().sendRedirect("login.jsp?ID=99");
    }
    }
    
%>
<%@include file="../views/footer.jsp" %>

