/**
 * Created by xmuli on 2016/4/4.
 */
$(document).ready(function(){
    $("#addBtn").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        var nickname = $("#nickname").val();
        var email = $("#email").val();
        var birthday = $("#birthday").val();
        var sex = "male";
        if ($("#femaleOption").attr("checked") == true) {
            sex = "female";
        }
        if (username == "" || password == "" || nickname == "" || email == "")
            return;
        $.ajax({
            type: "POST",
            url: "/user/exist",
            data: {username:username},
            success: function(msg){
                if (msg == true){
                    $("#errorInfo").show();
                }
                else{
                    $.ajax({
                        type: "POST",
                        url: "/user/add",
                        data: {username:username, password:password, nickname: nickname, email: email, sex: sex, birthday: birthday},
                        success: function (msg) {
                            $("#addSuccessInfo").show();
                        }
                    });
                }
            }
        });
    });
});