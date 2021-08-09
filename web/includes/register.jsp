<div class="container-md modal " id="signupDiv">
     <div class="row">
      
      <div class="col-md-8 ">
           <div class="css-1dbjc4n r-1awozwy r-1pz39u2 r-6koalj r-16y2uox r-1777fci r-18qmn74">
          <svg viewBox="0 0 24 24" aria-label="Twitter" class="r-13gxpu9 r-4qtqp9 r-yyyyoo r-16y2uox r-lwhw9o r-dnmrzs r-bnwqim r-1plcrui r-lrvibr"><g><path d="M23.643 4.937c-.835.37-1.732.62-2.675.733.962-.576 1.7-1.49 2.048-2.578-.9.534-1.897.922-2.958 1.13-.85-.904-2.06-1.47-3.4-1.47-2.572 0-4.658 2.086-4.658 4.66 0 .364.042.718.12 1.06-3.873-.195-7.304-2.05-9.602-4.868-.4.69-.63 1.49-.63 2.342 0 1.616.823 3.043 2.072 3.878-.764-.025-1.482-.234-2.11-.583v.06c0 2.257 1.605 4.14 3.737 4.568-.392.106-.803.162-1.227.162-.3 0-.593-.028-.877-.082.593 1.85 2.313 3.198 4.352 3.234-1.595 1.25-3.604 1.995-5.786 1.995-.376 0-.747-.022-1.112-.065 2.062 1.323 4.51 2.093 7.14 2.093 8.57 0 13.255-7.098 13.255-13.254 0-.2-.005-.402-.014-.602.91-.658 1.7-1.477 2.323-2.41z"></path></g></svg>
      </div>
      </div>
         <div  class="col-md-4 next">
             <div id="next" style="cursor:pointer;">
                <a id="closeSignup"> close</a>
             </div>
         </div>
    </div>
    <div class="row">
      
        <div class="col-md-6">
            <h3 class="accountheading">Create your Account</h3>
        </div>
        
 
    </div>
    <div class="row">
      
        <div class="col-md-6">
            <h5 style="color:rgba(29,161,242,1.00);" id="messageLabel"></h5>
        </div>
        
 
    </div>
    <form id="register_form">
   <div class="mb-3">
    
    <input placeholder="FirstName" type="text" class="form-control" id="Firstname"    >
   </div>
       
  <div  class="mb-3">
  
    <input placeholder="LastName" type="text" class="form-control" id="Lastname" >
  </div>
   <div class="mb-3">
  
       <input id="Contact" placeholder="Phone" type="text" class="form-control"  >
  </div>
         <div class="mb-3">
 
    <input placeholder="Choose username" type="text" class="form-control" id="Username">
  </div>
  <div class="mb-3">
 
    <input placeholder="Password" type="password" class="form-control" id="Password">
  </div>
       <div class="row">
           <a style=" " id="emailInstead" class="col-md-4">Use email instead.</a>
       </div>
  
       <div class="row">
           <strong style="margin-left:-45px;" class="col-md-4">Date of birth</strong></br>
           <p  style="color:#a2a9b0;">This will not be shown publicly. Confirm your own age,
    even if this account is for a business, a pet</p>
       </div>
      
       <select id="months_select" class="months_select col-md-4" >
  <option selected>Month</option>
  <option value="1">Jan</option>
  <option value="2">Feb</option>
  <option value="3">March</option>
   <option value="4">Apr</option>
  <option value="5">May</option>
  <option value="6">Jun</option>
   <option value="7">Jul</option>
  <option value="8">Aug</option>
  <option value="9">Sept</option>
   <option value="10">Oct</option>
  <option value="11">Nov</option>
  <option value="12">Dec</option>
</select>
       <select id="days_select" class="col-md-2 days_selected">
           <option selected>Day</option>
           <%
               for(int i=1;i<=31;i++)
               {
                out.print("<option value="+i+">"+i+"</option>");
               }
               %>
       </select>
       <select id="years_select" class="col-md-2 years_selected">
            <option selected>Year</option>
           <%
               for(int i=1901;i<2021;i++)
               {
                out.print("<option value="+i+">"+i+"</option>");
               }
               %>
       </select>  
       </br>
       </br>
       <div class="row">
          <input type="submit" class="btn btn-primary  col-md-4" value="Register" id="Registerbutton"/>
        
       </div>
            
</form>
</div>
