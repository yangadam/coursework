$(document).ready(function(){
    $("#submitBtn").click(function(){
        var old = $("#password").val();
        var new1 = $("#newPassword1").val();
        var new2 = $("#newPassword2").val();
        if (new1 != new2 || new1 == ""){
            $("#newPwdWrongInfo").show();
        }
        $.ajax({
            type : "POST",
            data: {newPassword:new1, password:old},
            url : "/modifyPassword",
            success : function (msg) {
                if (msg == null){
                    $("#passwordWrongInfo").show();
                }
                else{
                    $("#successInfo").show();
                }
            }
        });
    });
});