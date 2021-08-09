var count=0;
$('#btn_login').click(function(){
   
  
    $.ajax({
       type:'POST',
       url:'includes/csrf.jsp',
       
        data: {
      "token":$('#token').val() 
      },
       success: function(result){}
        
    });
   
    var mydata = {
       
           username:$('#username').val(),
           password:$('#password').val(),
           checked:$('#rememberMe').is(':checked'),
       
    }
    mydata = JSON.stringify(mydata);

    $.ajax({
       type:'POST',
       url:'includes/validator.jsp',
       
        data: {
      "mydata":mydata 
      },
       //dataType: "json",
       success: function(result){
          
           result = $.trim(result);
          
            if(result ==='bora')
            {
                document.location ='userprofile.jsp?user=' +$('#username').val();
            }
            else if(result === 'ATTEMPTS EXCEEDED')
            {
                count = count +1;
              
                 alert("You have exceeded the limit of retries please login after some time >>`");
                 $('#btn_login').attr('disabled', 'disabled'); 
                 if(count === 1)
                 {
                    setTimeout(function(){
                         $('#btn_login').removeAttr('disabled'); 
                     },3000);
                 }
                 else if(count === 2)
                 {
                    setTimeout(function(){
                         $('#btn_login').removeAttr('disabled'); 
                     },6000);
                 }
                 else if(count === 3)
                 {
                    setTimeout(function(){
                         $('#btn_login').removeAttr('disabled'); 
                     },9000);
                 }
                
                 
            }
            
            $('#message').html(result);
           
       }
        
    });
   return false; 
});

$('#signupanchor').click(function(){
    
   $('#signupDiv').hide();
   $('#signupDiv').show();
   $('#rememberMe').fadeOut();
  // document.getElementById("#rememberMe").style.display=none;
});
$('#next').click(function (){
    $('#signupDiv').hide();
})
$('#emailInstead').click(function(){
     if(!$('#emailInstead').hasClass('phone'))
     {   
      $("#Contact").attr("placeholder","Email");
      $('#emailInstead').addClass('phone');
      $('#emailInstead').text('Use Contact Instead');
     }
  else
  {
        $("#Contact").attr("placeholder","Phone");
        $('#emailInstead').text('Use Email Instead');
        $('#emailInstead').removeClass('phone');
   }
    });

 $('#Registerbutton').click(function(){
   
    var Details = {
        "fname":$('#Firstname').val(),
        "lname":$('#Lastname').val(),
        "contact":$('#Contact').val(),
        "month":$('#months_select').val(),
        "day":$('#days_select').val(),
        "year":$('#years_select').val(),
        "username":$('#Username').val(),
        "password":$('#Password').val()
    }
  
    
   Details = JSON.stringify(Details);
   //alert(Details);
  $.ajax({
       type:'POST',
       url:'includes/registerUser.jsp',
       
        data: {
            "DETAILS":Details
      
      },
       //dataType: "json",
       success: function(result){
          
           result = $.trim(result);
           if(result === "User addded successfully")
             {
             document.location = "editProfile.jsp?ID=200"
            }
             var messageLabel = $('#messageLabel');
             messageLabel.html(result);
             
       },
       error: function(result){
           result = $.trim(result);
          
             var messageLabel = $('#messageLabel');
             messageLabel.html(result);
       }
        
    });
   return false; 
});

$('#forgot').click(function(){
  $('#forgotpasswordDiv').show();
  $('#rememberMe').fadeOut();
  
  
});
function forgot(){
  
  $.ajax({
        type: 'POST',
        url: "includes/resetPassword.jsp",
        data:{"Name":$('#Name').val(),"Quote":$('#Identity').val(),"Pass":$('#NewPass').val()},
        success:function(res){
          alert(res);
          
        }
  });
  
  return false;
}
$('#messagebutton').click(function (){
   $('#randomMessageDiv').hide();
   $('#randomMessageDiv').slideDown();
    getMessages();
});
$('#closeimage').click(function (){
    $('#randomMessageDiv').hide();
    $('#randomMessageDiv').slideUp();
    
});
$('#closechat').click(function (){
    $('#chatdiv').removeClass('shown');
     $('#chatdiv').hide();
     showSeenstatus();
     
})
$('#send').click(function (){
    $.ajax({
        type: 'POST',
        url: "includes/messageService.jsp",
        data:{
            "MESSAGE":$('#textMessage').val(),"TO":$('#useridbyname').val(),
       
        },
        success:function(res){
         
         $('#textMessage').val("");
            getMessages()
        }
  });
  
  return false;
});

function getMessages()
{
    $.ajax({
        type: 'GET',
        url: "includes/messageService.jsp",
        data:{
             "TO":$('#useridbyname').val() },
        success:function(res){
         
         $('#displayChat').html(res+"</br>");
           
        }
  });
  
  return false;
    
}



