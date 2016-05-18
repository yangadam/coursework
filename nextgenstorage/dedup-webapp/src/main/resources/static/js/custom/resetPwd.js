$(document).ready(function(){
    $("#reset").click(function(){
        var username = $("#username").val();
        var email = $("#email").val();
        if (username == "" || email == "")
            return;
        $.ajax({
            type : "POST",
            url : "/reset",
            data : {username : username, email : email},
            success : function(){
                $("#successInfo").show();
            }
        });
    });
});