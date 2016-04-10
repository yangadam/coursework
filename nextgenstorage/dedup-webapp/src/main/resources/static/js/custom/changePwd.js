/**
 * Created by xmuli on 2016/4/5.
 */
$(document).ready(function(){
    $("#updateBtn").click(function(){
        var old = $("#password").val();
        var new1 = $("#newPassword1").val();
        var new2 = $("#newPassword2").val();
        if (new1 != new2 || new1 == ""){
            $("#newPwdWrongInfo").show();
        }
        $.ajax({
            type : "POST",
            data: {newPassword:new1, password:old},
            url : "",
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