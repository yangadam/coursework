/**
 * Created by xmuli on 2016/4/4.
 */
$(document).ready(function(){
    $("#addBtn").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "" || password == "")
            return;
        $.ajax({
            type: "POST",
            url: "/user/exist",
            data: {username:username},
            success: function(msg){
                // TODO whether it's existed
                if (msg == true){
                    $("#userExistedInfo").show();
                }
                else{
                    $.ajax({
                        type: "POST",
                        url: "/user/add",
                        data: {username:username, password:password},
                        success: function (msg) {
                            $("#addSuccessInfo").show();
                        }
                    });
                }
            }
        });
    });
});