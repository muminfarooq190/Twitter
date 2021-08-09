
$(document).ready(function(){
     $('#Registerbutton').prop('disabled', true); 
});

$("#Firstname").blur(function(e){
  var messageLabel = $('#messageLabel');
  var fname = e.target.value;
  if(!fname.match(/^([A-Za-z]{3,20}$)/i)){
       messageLabel.html("First name should be valid !");
       $("#Firstname").focus();
       
    }
    else{
        messageLabel.html("");
  
         $('#Firstname').css("border-color","silver");
    }
});
$("#Lastname").blur(function(e){
  var messageLabel = $('#messageLabel');
  var lname = e.target.value;
  if(!lname.match(/^([A-Za-z]{3,20}$)/i)){
       messageLabel.html("Last name should be valid !");
       $("#Lastname").focus();
       
       
    }
    else{
        messageLabel.html("");
      
        $('#Lastname').css("border-color","silver");
    }
});
$("#Username").blur(function(e){
  var messageLabel = $('#messageLabel');
  var uname = e.target.value;
  if(!uname.match(/^([A-Za-z]{3,20}$)/i)){
       messageLabel.html("user name should be valid !");
       $("#Username").focus();
   
       
    }
    else{
        messageLabel.html("");
       
         $("#Username").css("border-color","silver");
    }
});
$('#Contact').blur(function(e){
   var messageLabel = $('#messageLabel');
  
     if($('#emailInstead').hasClass('phone'))
   {
          var contact = e.target.value;
            
          if(!contact.match(/^([A-Za-z0-9._-]+@[A-Za-z0-9._-]+\.[A-Za-z._-]{3,20}$)/i)){
            messageLabel.html("Email required !");
            $('#Contact').focus();
           
          } 
        else{
             messageLabel.html("");
            }
    }
    else
    {
        var phone =  e.target.value;
        if(!phone.match( /^[0-9-+]+$/))
        {
         messageLabel.html("Phone no should be valid  !");
        }
        else{
             messageLabel.html("");
            }
        
    }      
   
});
$("#Password").blur(function(e){
      var messageLabel = $('#messageLabel');
      var password = e.target.value;
      if(password <7)
        {
          $("#Password").focus();
          
           messageLabel.html("password too short");
        }
        else
        {
            $('#Registerbutton').prop('disabled', false); 
            messageLabel.html(""); 
         
            $("#Password").css("border-color","silver");
          
        }
          
});
$('#handle').blur(function(e){
  var messageLabel = $('#Info');
  var handle = e.target.value;
  if(!handle.match(/^([A-Za-z]{3,20}$)/i)){
       messageLabel.html("user name should be valid !");
       $("#handle").focus();
   
       
    }
    else{
        messageLabel.html("");
       
         $("#handle").css("border-color","silver");
    }
})
$('#favname').blur(function(e){
  var messageLabel = $('#Info');
  var handle = e.target.value;
  if(!handle.match(/^([A-Za-z]{3,20}$)/i)){
       messageLabel.html("Nick name should be valid !");
       $("#favname").focus();
   
       
    }
    else{
        messageLabel.html("");
       
         $("#favname").css("border-color","silver");
    }
})

