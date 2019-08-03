/* 
        @Author : Palash Sarkar
        @CreatedON : 21st JUL, 2019, 5:45:55 PM
        @FILEName :  usrreg.js
 */

$(document).ready(function(){
       
       $.ajaxSetup({
            "timeout" : 0,
            "beforeSend" : function (xhr)
                           {
                                xhr.overrideMimeType('application/json');
                           }
       });

       function fieldValidationVisStat(obj, flag){
           obj.parent().parent().removeClass("has-success has-error has-feedback");
           obj.parent().children("div.msg").remove();
           obj.parent().children("span.glyphicon").remove();
           var span = document.createElement("span");
           var div = document.createElement("div");
           div.className = "col-lg-12 msg";
           if (flag){
               span.className = "glyphicon glyphicon-ok form-control-feedback"; div.innerText = "Proper value !!"; div.style.color = "darkgreen"; div.style.textAlign = "center";
               obj.parent().parent().addClass("has-success has-feedback");
               (obj.parent()).append(span);
               (obj.parent()).append(div);
           }
           else{
               span.className = "glyphicon glyphicon-remove form-control-feedback"; div.innerText = "Invalid value !!"; div.style.color = "darkred"; div.style.textAlign = "center";
               obj.parent().parent().addClass("has-error has-feedback");
               (obj.parent()).append(span);
               (obj.parent()).append(div);
           }
       }
    
       $("button#submit").click(function() {
           var cnt = 0, tot = 7;
           if ((/[A-Z,a-z]+/g).test($("input#fname").val())) {
               fieldValidationVisStat($("input#fname"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#fname"), false);
           }
           if ((/[A-Z,a-z]+/g).test($("input#lname").val())) {
               fieldValidationVisStat($("input#lname"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#lname"), false);
           }
           if ((/(DEVELOPER|MANAGER|ADMIN)/g).test($("select#role").val())) {
               fieldValidationVisStat($("select#role"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("select#role"), false);
           }
           if ((/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/g).test($("input#email").val())) {
               fieldValidationVisStat($("input#email"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#email"), false);
           }
           if ((/^\+[1-9]{1}[0-9]{3,14}$/g).test($("input#mobno").val())) {
               fieldValidationVisStat($("input#mobno"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#mobno"), false);
           }
           if ((/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$/g).test($("input#pwd").val())) {
               fieldValidationVisStat($("input#pwd"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#pwd"), false);
           }
           if (($("input#pwd").val() === $("input#pwd2").val()) && ($("input#pwd2").val() !== "")) {
               fieldValidationVisStat($("input#pwd2"), true);
               cnt++;
           } else {
               fieldValidationVisStat($("input#pwd2"), false);
           }
           if ( $("input#key").parent().parent().css("display") === "block") {
               tot = tot + 1;
               if ((/^[0-9]{6}$/g).test($("input#key").val())) {
                   fieldValidationVisStat($("input#key"), true);
                   cnt++;
               } else {
                   fieldValidationVisStat($("input#key"), false);
               }
           }
           if (cnt === tot){
               var reg_dets = {
                   "USER_INPUT_FIRSTNAME": $("input#fname").val(),
                   "USER_INPUT_LASTNAME": $("input#lname").val(),
                   "USER_INPUT_ROLE": $("select#role").val(),
                   "USER_INPUT_EMAILID": $("input#email").val(),
                   "USER_INPUT_MOBNO": $("input#mobno").val(),
                   "USER_INPUT_PASSWORD": $("input#pwd").val(),
                   "USER_INPUT_SECKEY": $("input#key").val()
               };
               $.ajax({
                   url: "UserDetailsServlet",
                   async: true,
                   cache: true,
                   contentType: "text/json",
                   processData: true,
                   scriptCharset: "UTF-8",
                   type: 'PUT',
                   traditional: true,
                   timeout: 0,
                   dataType: "json",
                   data: JSON.stringify(reg_dets),
                   beforeSend: function (xhr) {
                       xhr.overrideMimeType('text/json');
                       $("div#dummy_form").css("display", "none");
                       $("div#loader").css("display", "block");
                   },
                   dataFilter: function (respdata, type) {
                       var jso = JSON.parse(respdata);
                       var ob = {};
                       if (jso.STATUS_CODE === "1") {
                           $("div#myDiv").children("h2").text("Registration Successful").css("color", "green");
                           ob.resp = "Your USER-ID is : " + jso.RESPONSE.USER_ID;
                           return (JSON.stringify(ob));
                       }
                       else {
                           $("div#myDiv").children("h2").text("Registration Failed").css("color", "red");
                           ob.resp = jso.RESPONSE;
                           return (JSON.stringify(ob));
                       }
                   },
                   success: function (result, status, xhr) {
                       $("div#myDiv").children("h3").text("AJAX Call Successful").css("color", "darkblue");
                       $("div#myDiv").children("h4").text(result.resp);
                   },
                   error: function (xhr, status, error) {
                       $("div#myDiv").children("h2").text("Registration Failed").css("color", "red");
                       $("div#myDiv").children("h3").text("AJAX Call Failed").css("color", "darkblue");
                       $("div#myDiv").children("h4").text("XHR : " + xhr + " STATUS : " + status + "ERROR : " + error).css("color", "darkblue");
                   },
                   complete: function (xhr, status) {
                       $("div#loader").css("display", "none");
                       $("div#myDiv").css("display", "block");
                   },
                   headers: {
                       'Accept': 'json',
                       'Content-Type': 'text/json',
                       "X-HTTP-Method-Override": "PUT"
                   }
               });
           }
       });

       $("button#back").click(function(){
           $("input").val("");
           $("select").val("NULL");
           $("select#role").trigger("change");
           $("div.form-group").removeClass("has-success has-error has-feedback");
           $("div.msg").remove();
           $("span.glyphicon").remove();
           $("div#myDiv").css("display","none");
           $("div#dummy_form").css("display","block");
       });

       $("select#role").change(function(){
            $("input#key").parent().parent().removeClass("has-success has-error has-feedback");
            $("input#key").parent().children("div.msg").remove();
            $("input#key").parent().children("span.glyphicon").remove();
            $("input#key").val("");
            if ($(this).val() === "MANAGER"){
                $("input#key").parent().parent().css("display","block");
            } else { $("input#key").parent().parent().css("display","none"); }
       });

});
