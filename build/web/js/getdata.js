function Search()
{
$.ajax({
        type: 'POST',
        url:'includes/getsearchResults.jsp',
        data: {SEARCH: $('#search').val()},
        success: function(res) {
            $('#SearchResultsDiv').show()
            $('#SearchResultsDiv').html(res);
        }
});
return false;
}

function SearchFriends()
{
  
$.ajax({
        type: 'POST',
        url:'includes/getsearchResults.jsp',
        data: {FRIENDSSEARCH: $('#searchInput').val()},
        success: function(res) {
            
             $('#resultshai').show();
           $('#resultshai').html(res);
        }
});
return false;
}
$('#search').keypress(function(){
  $('#closeid').show();
})

$('#followbutton').click(function(){
    
    $.ajax({
        type: 'POST',
        url: "includes/follow.jsp",
        data:{REQUESTTO:$('#useridbyname').val()},
        success:function()
        {
            $('#unfollowbutton').show();
            $('#followbutton').attr('value', 'Following');
            $("#followbutton").removeClass("btn-primary");
            $("#followbutton").addClass("btn-secondary");
           
        }
    })
})
$('#unfollowbutton').click(function(){
     $.ajax({
        type: 'POST',
        url: "includes/unfollow.jsp",
        data:{REQUESTTO:$('#useridbyname').val()},
        success:function()
        {
            $('#unfollowbutton').hide();
            $('#followbutton').attr('value', 'Follow');
            $("#followbutton").removeClass("btn-secondary");
            $("#followbutton").addClass("btn-primary");
        }
    })
})

function close1(){
      $('#closeid').hide();
      $('#SearchResultsDiv').hide();
    
}
$('#btn-tweet').click(function(){
    
    
    $.ajax({
        type: 'POST',
        url: "includes/tweetManagement.jsp",
        data:{TWEETEDBY:$('#sessionuseridbyname').val(),TWEET:$('#tweetTextArea').val()},
        success:function(res)
        {
            
            $('#tweetTextArea').val(" ");
           
        }
    });
    });
      

  
function getTweets()
{
  
     
        $.ajax({
        type: 'GET',
        url: "includes/tweetManagement.jsp",
        data:{IMG:$('#useridbyname').val(),TWEETOF:$('#useridbyname').val()},
         success:function(res)
        {  $('.composition').html(res);
        }
    });
}



function getData(){
$.ajax({
      type: 'GET', 
      dataType: 'json',
      url:'includes/UpdateUserProfile.jsp',
      data: {},
      success: function(result){
          
         $('#About').text(result[1]);
         $('#Shortdesc').text(result[4]);
         $('#favquote').text(result[4]);
         $('#favname').val(result[3]);
         $('#handle').val(result[2]);
         $('#country').val("Unknown");
         $('#state').val("unknown");
      }
   });   
}
$('#btnUpdate').click(function(){   
   
    var detail = {
        'id':       $('#id').val(),
        'about':    $('#About').val(),
        'Sdescp':   $('#Shortdesc').val(),
        'favName':    $('#favname').val(),
        'favquote'  :$('#favquote').val(),
        'handle'    :$('#handle').val(),
        'country'    :$('#country').val(),
        'state'    :$('#state').val()  
    };
$.ajax({
      type: 'POST', 
      //dataType: 'json',
      url:'includes/UpdateUserProfile.jsp',
      data: {DETAILS:JSON.stringify(detail)},
      success: function(result){
          result = $.trim(result);
          getData();
          $('#Info').val("Updated Successfully");
          document.location = "userprofile.jsp?user="+$('#handle').val();
      } 
   }); 
   return false;
});
$('.profilecard').click(function()
{
    $('#profileForm').show();
})
$('.profilecard').dblclick(function()
{
    $('#profileForm').hide();
})

$('.coverDiv').click(function()
{
    $('#coverForm').show();
})
$('.coverDiv').dblclick(function()
{
    $('#coverForm').hide();
})
$('#EditProfilebutton').click(function(){
    document.location = "editProfile.jsp?user="+$('#sessionuseridbyname').val();
});
function toggleComments(id)
{
       $('#mainCommentDiv'+id).slideToggle();

}
function addComment(tweetId)
{
    
    $.ajax({
      type: 'POST', 
      url:'includes/tweetManagement.jsp',
      data: {ONTWEET:tweetId, ACTUALCOMMENT:$('#commenttext'+tweetId).val(),COMMENTBY:$('#sessionuseridbyname').val()},
      success: function(result){
           $('#mainCommentDiv'+tweetId).hide();
      }
   });     
    
}
function getFriends()
{
   
        $.ajax({
        type: 'GET',
        url: "includes/Friends.jsp",
        data:{FRIENDOF:$('#useridbyname').val()},
         success:function(res)
        {
           
           $('#FriendDiv').html(res);
        }
    });
}
$("#Logout").each(function(){

  $(this).hover(
    function() {
      $(this).text("Logout");
    },
    function() {
      $(this).html("<i class='fa fa-sign-out' aria-hidden='true'></i>");
    }

 );
});
$("#loggedInUserprofile").each(function(){

  $(this).hover(
    function() {
      $(this).text("profile");
    },
    function() {
      $(this).html("<i class='fa fa-user' aria-hidden='true'></i>");
    }

 );
});
$("#Messagref").each(function(){

  $(this).hover(
    function() {
      $(this).text("Message");
    },
    function() {
      $(this).html("<i class='fa fa-envelope' aria-hidden='true'></i>");
    }

 );
});
$("#friends").each(function(){

  $(this).hover(
    function() {
      $(this).text("friends");
    },
    function() {
      $(this).html("<i class='fa fa-users' aria-hidden='true'></i>");
    }

 );
});
$("#homeref").each(function(){

  $(this).hover(
    function() {
      $(this).text("Home");
    },
    function() {
      $(this).html("<i class='fa fa-home' aria-hidden='true'></i>");
    }

 );
});
$("#loggedInUserprofile").click(function(){
    
   $("#loggedInUserprofile").attr('href','userprofile.jsp?user='+$('#sessionuseridbyname').val())
});
$("#Logout").click(function(){
  
   if(confirm("WANT TO LOG OUT?")){ $.ajax({
      type: 'POST', 
      //dataType: 'json',
      url:"includes/logout.jsp"
     
    });
}
});
function deleteComment(id)
{
    if(confirm("You wanna delete this comment?")){
        $.ajax({
                type: 'POST',
                url: "includes/DeleteComment.jsp",
                data:{COMMENTID:id},
                success:function()
                {
                   
                }
    })
}
}
$('#changePassword').click(function(){
   $('#PasswordDiv').show();
   $('#UpdateForm').hide();
   
})
$('#changePassword').dblclick(function(){
   $('#PasswordDiv').hide();
   $('#UpdateForm').show();
   
})
$('#btnUpdatePassword').click(function(){
     var previous = $('#OldPassword').val();
     var current = $('#NewPassword').val();
     
     $.ajax({
                type: 'POST',
                url: "includes/ChangePassword.jsp",
                data:{PREVIOUS:previous,CURRENT:current},
                success:function(res)
                {
                   
                      alert(res);
                      $('#PasswordDiv').hide();
                      $('#UpdateForm').show();
                   
                 
                }
    });
    return false;
});
$('#Messagref').click(function (){
 $("#Messagref").attr('href','messenger.jsp');

});
function displaychatNow(){
    
    $('#chatdiv').show();
    $('#chatdiv').addClass('shown');
    showSeenstatus()
   $('#searchInput').val("");
    $('#resultshai').hide();
    $('#displayMessages').html("<b style='margin-left:25px; '>"+$("#resultshai").find("b").html()+"</b>");
    getChats();
};

function chatStarted()
{
    
     $.ajax({
        type: 'POST',
        url: "includes/messageService.jsp",
        data:{
            "REALMESSAGE":$('#txtSearchChat').val(),"TO":$("#resultshai").find("b").html(),
       
        },
        success:function(res){
     
         $('#txtSearchChat').val("");
            getChats()
        }
  });
  
    return false;
}
$('#closechat').click(function (){
    $('#chatdiv').removeClass('shown');
     $('#chatdiv').hide();
     
})
function getChats()
{
    $.ajax({
        type: 'GET',
        url: "includes/messageService.jsp",
        data:{
             "REALTO":$("#resultshai").find("b").html() },
        success:function(res){
         
         $('#displayMessages').html(res+"</br>");
           
        }
  });
  
  return false;
    
}

function showSeenstatus()
{
    if($('#chatdiv').hasClass('shown'))
    {
        
        $.ajax({
            type: 'GET',
            url: "includes/showSeenStatus.jsp",
            data: {ANOTHER:$("#resultshai").find("b").html()},
            success:function (res)
            {
                 $('#spanseen').text("seen");
                
            }
            
        });
    }
    
}