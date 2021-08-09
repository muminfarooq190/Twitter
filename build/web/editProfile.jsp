<%@page import="Controller.AuthoriseUser"%>
<%@include file="views/header.jsp" %>

</br>
</br>
<div class="row">
    <div class="col lg-8">
            <div class="d-flex justify-content-center">
                <h3 ><span class="usertitle">Welcome <%=AuthoriseUser.getUser(user)%></span></h3>
            </div>
        
    </div>
</div>
<div class="container ">
    </br>
    <div class="row">
        <div class="d-flex justify-content-center">
            <h4><span id="Info"></span></h4>
        </div>
    </div>
    <div style="float:right" class="change">
    
        <small id="changePassword" style="font-size: 16px"  id="PasswordChange">Change Password</small>
    
    </div>
    <div id="PasswordDiv">
        <form>
            <div class="mb-3">
    <label for="OldPassword" class="form-label usertitle">Enter your Old Password</label>
    <input type="text" class="form-control" id="OldPassword" >
  </div>
    <div class="mb-3">
    <label for="NewPassword" class="form-label usertitle">Enter your New Password</label>
     <input type="text" class="form-control" id="NewPassword" >
  </div>
            <div class="row">
        <button style="height:40px; font-size: 22px;" type="submit" id="btnUpdatePassword" class="btn btn-primary  ">Update</button>
    </div>
        </form>
    </div>
    <form id="UpdateForm">
    <div class="mb-3">
    
    <input type="hidden" class="form-control" id="id" >
     </div>
  <div class="mb-3">
    <label for="About" class="form-label usertitle">About Yourself</label>
     <textarea class="form-control" id="About" rows="3"></textarea>
  </div>
    <div class="mb-3">
    <label for="Shortdesc" class="form-label usertitle">Short description</label>
     <textarea class="form-control" id="Shortdesc" rows="3"></textarea>
  </div>
    <div class="mb-3">
    <label for="favquote" class="form-label usertitle">quote</label>
     <textarea class="form-control" id="favquote" rows="3"></textarea>
  </div>
  <div class="mb-3">
    <label for="favname" class="form-label usertitle">Nick Name</label>
    <input type="text" class="form-control" id="favname" >
     </div>
    <div class="mb-3">
    <label for="handle" class="form-label usertitle">User Name</label>
    <input type="text" class="form-control" id="handle" >
     </div>
    <div class="mb-3">
    <label for="country" class="form-label usertitle">Country</label>
    <input type="text" class="form-control" id="country" >
     </div>
    <div class="mb-3">
    <label for="state" class="form-label usertitle">State</label>
    <input type="text" class="form-control" id="state" >
     </div>
    <div class="row">
        <button style="height:50px; font-size: 22px;" type="submit" id="btnUpdate" class="btn btn-primary  ">Update</button>
    </div>
</form>
    
    </div>
 <script src="js/getdata.js"></script> 
 <script>getData();</script>
<%@include file="views/footer.jsp" %>