/*
        @Author : Palash Sarkar
        @CreatedON : 21st JUL, 2019, 5:45:55 PM
        @FILEName :  res.js
 */

$(document).ready(function(){

    $.ajaxSetup({
        "beforeSend" : function (xhr)
        {
            xhr.overrideMimeType('application/json');
        }
    });

    $("button.apv").click(function() {
            var del_det = {
                "PEN_USR_SLNO": (($(this).parent().parent().children("td"))[0]).innerText,
                "PEN_USR_EML": (($(this).parent().parent().children("td"))[4]).innerText
            };
            var t = $(this);
            $.ajax({
                url: "UserDetailsServlet",
                async: true,
                cache: true,
                contentType: "text/json",
                processData: true,
                scriptCharset: "UTF-8",
                type: 'DELETE',
                traditional: true,
                timeout: 20000,
                dataType: "json",
                data: JSON.stringify(del_det),
                beforeSend: function (xhr) {
                    xhr.overrideMimeType('text/json');
                },
                success: function (result, status, xhr) {
                    if ( result.STATUS_CODE === "1" ) { t.parent().parent().remove(); }
                    alert(result.RESPONSE);
                },
                error: function (xhr, status, error) {
                    alert("XHR : " + xhr + " STATUS : " + status + "ERROR : " + error);
                },
                headers: {
                    'Accept': 'json',
                    'Content-Type': 'text/json',
                    "X-HTTP-Method-Override": "DELETE"
                }
            });
    });

    $("button.pat").click(function () { alert("Patching Failure!! Host Unreachable !!"); });

    $("button#submit").click(function () {
            var dat = {
                "hname" : $("input#hname").val(),
                "pname" : $("input#pname").val(),
                "htyp" : $("select#htyp").val(),
                "hloc" : $("select#hloc").val()
            };
            $.post("HostDetailsServlet", dat, function (data,status,xhr){
                if (status === "success"){
                        alert(data.STATUS + "\n" + JSON.stringify(data.RESPONSE));
                } else { alert("Failed"); }
                $("input#hname").val("");
                $("input#pname").val("");
                $("select#htyp").val("DEV");
                $("select#hloc").val("NAM");
            }, "json");
    });

    $("button.del").click(function() {
        var del_det = {
            "HOST_SLNO": (($(this).parent().parent().children("td"))[0]).innerText
        };
        var t = $(this);
        $.ajax({
            url: "HostDetailsServlet",
            async: true,
            cache: true,
            contentType: "text/json",
            processData: true,
            scriptCharset: "UTF-8",
            type: 'DELETE',
            traditional: true,
            timeout: 20000,
            dataType: "json",
            data: JSON.stringify(del_det),
            beforeSend: function (xhr) {
                xhr.overrideMimeType('text/json');
            },
            success: function (result, status, xhr) {
                if ( result.STATUS_CODE === "1" ) { t.parent().parent().remove(); }
                alert(result.RESPONSE);
            },
            error: function (xhr, status, error) {
                alert("XHR : " + xhr + " STATUS : " + status + "ERROR : " + error);
            },
            headers: {
                'Accept': 'json',
                'Content-Type': 'text/json',
                "X-HTTP-Method-Override": "DELETE"
            }
        });
    });

});