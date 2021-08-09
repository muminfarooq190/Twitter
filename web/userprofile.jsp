
<%@page import="Controller.ProfileController"%>
<%@page import="Controller.AuthoriseUser"%>
<%@page import="Controller.FileController"%>

<%@include file="includes/config.jsp" %>
<html>
    <head>
        <title>Welcome <%=AuthoriseUser.getUser(user)%></title>
     <link rel="stylesheet" href="css/default.css"/>
         <link rel="stylesheet" href="css/bootstrap.css"/>
 <script src="js/jquery.min.js"></script>
  <link rel="icon" type="image/png" href="imgs/assassins.png">
       
 <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css">
 <script src="https://kit.fontawesome.com/ebfafc2eb8.js" crossorigin="anonymous"></script>

    </head>
    
    <body>
 <%
 
   if(request.getParameter("user")!=null){
       user.setUsername(request.getParameter("user"));
   FileController fc = new FileController();
   fc.uploadPics(user);
   if(user.getMessage()!=null){
   out.print(user.getMessage());
   }
   }
   else{}
 
 %>

        <div  class="container-fluid">
            <input id="useridbyname" type="hidden" value=<%=ProfileController.getUserid(user)%>>
            <input id="sessionuseridbyname" type="hidden" value=<%=AuthoriseUser.getUser(user)%>>
             
            <div class="container-sm">
              
                <div class="row navv">
                   
                    <div class="col-md-6">
                        <div class="searchbox animated zoomIn">
                            <form id="searchForm" method="Post" onsubmit=" return Search()">
                               <input oninput="triggercross(this.value)" type="text" placeholder="search" id="search" name="search"
                                   class="search"><i class="fas fa-search"></i>
                               <button onclick="close1()" id="closeid" type="reset" class="close">
                                   <i class="fas fa-times"></i>
                                   <p id="demo"></p>
                               </button>
                           </form>

                       </div>
                    </div>
                    <div class="col-md-6">
                        <div class="tab">
                                <div class="button">
                                  <div class="svg"></div>
                                  <div class="text home"><a id="homeref" style="text-decoration: none; color:#ccc;"  href=""><i style="width:30px; height: 30px;"class="fa fa-home" aria-hidden="true"></i></a></div>
                                </div>
                                <div class="button">
                                  <div class="svg"></div>
                                  <div class="text find"><a id="friends" style="text-decoration: none; color:#ccc;"  href=""><i class="fa fa-users" aria-hidden="true"></i></a></div>
                                </div>
                                <div class="button">
                                  <div class="svg"></div>
                                  <div class="text chat"><a id="Messagref" style="text-decoration: none; color:#ccc;"  href=""><i class="fa fa-envelope" aria-hidden="true"></i></a></div>
                                </div>
                                <div class="button">
                                  <div class="svg"></div>
                                  <div  class="text chat"><a id="loggedInUserprofile" style="text-decoration: none; color:#ccc;"  href=""><i class="fa fa-user" aria-hidden="true"></i></a></div>
                                </div>
                                <div class="button">
                                  <div class="svg"></div>
                                  <div class="text me"><a id="Logout" style="text-decoration: none; color:#ccc;" href=""><i class="fa fa-sign-out" aria-hidden="true"></i></a></div>
                                </div>
                     </div>
                    </div>
                  
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div id="SearchResultsDiv">
                           
                        </div>
                    </div>
                </div>
                
            </div>    
            <div class="container-sm">
                
                <div class="coverDiv">
                        <%=FileController.getCoverPic(user)%> 
                          <div class="formdiv">

                                <%
                                if(user.getUsername().equals(AuthoriseUser.getUser(user)))
                                {
                                  %>
                                    <%=FileController.getCoverUploadForm()%>
                                <% }
                                %>  
                          </div>
                    
                </div>
                <div class="row">
                    <div class="col-md-4 firstrow">
                          <div class="profilecard">   
                                <%=FileController.getProfilePic(user)%> 
                          </div>
                          <%
                                if(user.getUsername().equals(AuthoriseUser.getUser(user)))
                                {
                                  %>
                                  <div id="formUploadDiv" >
                                      <%=FileController.getProfileUploadForm()%></div>
                                <% }
                                %>
                    
                    </div>
                    <div class="col-md-8 firstrow" >
                       <%=ProfileController.getFollowandMessageButtons(user)%>
                       <div id='quotediv' >
                         

                       </div>
                    </div>
                       
                    <%
                      if(ProfileController.checkFollower(user))
                      {
                        
                    
                        %>
                        <script  src="js/checkfollower.js"></script>
                        <%
                      }
                    %>
                    
                 </div>
                    <div id="randomMessageDiv">
                        <img id="closeimage" style="width:30px;height: 30px; float:right;" src="imgs/close.jpeg">
                        
                        <div id="displayChat">
                            </div>
                        <div id="sendChat">
                            <form>
                            <input type="text" name="textMessage" id="textMessage"/>
                            <input type="submit" id="send"value="send" style="width: 40px;height: 30px;border:none; background-color: #fff"/>
                            </form>
                        </div>
                    </div>
             
               
                    <div class="row">
                        <div style="margin-top:-20px" class="col-md-4 secondrow">
                            <h5><i class="fa fa-user-o" aria-hidden="true"></i> <span class="userheadin"><%=user.getUsername()%></span> </h5>
                        </div>
                         
                        
                   </div>
                   <div class="row">
                        <div  class="col-md-4 secondrow">
                            <h5><i class="fa fa-map-marker" aria-hidden="true"></i> <span class="userheadin"><%=ProfileController.locate(user)%></div>
                   </div> 
                         <div class="row">
                        <div class="col-md-4 secondrow">
                            <h6><i class="fa fa-birthday-cake" aria-hidden="true"></i> <span class="userheadin"><%=ProfileController.Birthday(user)%></span> </h6>
                        </div>
                   </div> 
                         
           <section class="section-about">
            <div class="roww">
                <div class="col-1-of-2">
                    <br><br>
                  <h3 style="color:#a2a9b0;" class="heading-tertiary u-margin-bottom-small">Friend List</h3>
                 <%
                 if(request.getParameter("user").equals(AuthoriseUser.getUser(user)))
                 {
                 %>
                  <div id="FriendDiv">
                    
                  </div>
                  <%}%>
                  <%
                 if (!request.getParameter("user").equals(AuthoriseUser.getUser(user))&&ProfileController.isFriend(user,ProfileController.getUserid(user),AuthoriseUser.getUser(user)))
                 {
                   
                  %>
                  <div id="FriendDiv">
                    
                  </div>
                   <%}%>
                  
                </div>
    
                <div class="col-1-of-2">
                     <br><br>
                     
                     <textarea class="form-control" id="tweetTextArea" placeholder="Whats happening?" rows="2" ></textarea>
                      <input type="button" id="btn-tweet" value="tweet">  
                      <div class="composition">
                        
                      </div>

                </div>
            </div>
    </section>
                               
            
        </div>
  <script src="js/getdata.js"></script>
  <script src="js/_js.js"></script>
  <script>getTweets();</script>
    <script>getFriends();</script>
    <script>getMessages();</script>
    
    </body>
</html>
