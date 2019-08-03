/*
        @Author : Palash Sarkar
        @CreatedON : 21st JUL, 2019, 5:45:55 PM
        @FILEName :  usrlogin.js
 */

$(document).ready(function(){


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

    $("form#login_form").on('submit', function(e) {
        e.preventDefault();
        var cnt = 0;
        if ((/^[A-Z]{2}[0-9]+$/g).test($("input#uid").val())) {
            fieldValidationVisStat($("input#uid"), true);
            cnt++;
        } else {
            fieldValidationVisStat($("input#uid"), false);
        }
        if ((/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,12}$/g).test($("input#upass").val())) {
            fieldValidationVisStat($("input#upass"), true);
            cnt++;
        } else {
            fieldValidationVisStat($("input#upass"), false);
        }
        if (cnt === 2){
            $(this).css("display","none");
            $("div#loader").css("display","block");
            $(this).unbind('submit').submit();
        }
    });

});