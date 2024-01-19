<%-- 
    Document   : ResetPW
    Created on : Aug 15, 2022, 8:38:56 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">    

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
    </head>
    <body>
        &nbsp;
        
         <form name="Mail" id="Mail" method="GET" action="./sendmail">
  
        <!--<form name="ChangePW" id="ChangePW" method="POST" form action="./ChangePW" >-->
                    <div class="form-group">
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">Username :</label>                   
                            <input maxlength="10" type="text" name="username" id="username" value="">
                        </div> 
        &nbsp;              
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn btn-lg btn-danger shadow" id="SendVerifyMail" name="SendVerifyMail"   value="SendVerifyMail"  >Reset password</button>

                        </div><br>
                        <br> 
                        <h6 id="error"  style="color: #0050E1" class="text-center">                           
                            <%
                              if(session.getAttribute("MSGError") != null){
                                out.println(session.getAttribute("MSGError"));
                                }
                                session.removeAttribute("MSGError");
                            %>
                          
                        </h6>          
                    </div>
                </form>
    </body>
</html>

<script>
    


    $('#username').on('input', function (evt) {
        $(this).val(function (_, val) {
            $("#error").hide();
            return val.toUpperCase();
        });
    });

   
    
    
</script>
    
    
    
    
    
