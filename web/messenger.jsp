<%@page import="Controller.ProfileController"%>
<%@page import="Controller.AuthoriseUser"%>
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
        <div class="container-md" id="messenger">
            <div class="heading">
                <h5>Chat with your friends <%=AuthoriseUser.getUser(user)%></h5>
            </div>
            <div class='containerr'>
                <form method="Post" onsubmit=" return SearchFriends()">
                        <input type="text" id="searchInput" placeholder="Search..">
                        <div id='submitsearch'
                            style="">
                            <span>Search</span>
                </form>
                         </div>
            </div>
            <div id="resultshai">
                
            </div>
            
            
            <div id="chatdiv">
                
                 <img id="closechat"  style="width:30px;height: 30px; float:right;" src="imgs/close.jpeg">
                 <div id="displayMessages">
                   
                 </div>
                 <form method="POST" onsubmit="return chatStarted();">
                     <input type="text" class="realMessage"id="txtSearchChat" onkeypress="return searchKeyPress(event);" placeholder="Message" spellcheck="false"/>
                    <input type="submit" class="sendMessage" id="btnSearch" Value="enter" onclick="doSomething();" />
                 </form>  
            </div>
            
            
            <div id="textlogs">
                
            </div>
        </div>
            <script src="js/getdata.js"></script>    
            <script src="js/_js.js"></script>  
            <script>getChats();</script>  
</body>
</html>   