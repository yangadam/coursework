$(document).ready(function(){
    $.ajax({
        type : "GET",
        url : "/basicInfo/get",
        success : function(obj){
            $("#username").val(obj.username);
            $("#nickname").val(obj.nickname);
            $("#email").val(obj.email);
            $("#birthday").val(obj.birthday);
            $("#registerDate").val(obj.registerDate);
            if (obj.sex == "female"){
                $("#maleOption").attr("checked", false);
                $("#femaleOption").attr("checked", true);
            }
        }

    });

    $("#submitBtn").on("on", function(){
        var username = $("#username").val();
        var nickname = $("#nickname").val();
        var email = $("#email").val();
        var birthday = $("#birthday").val();
        var registerDate = $("#registerDate").val();
        var sex = "male";
        if ($("#maleOption").attr("checked") != true){
            sex = "female";
        }
        $.ajax({
            url: "/basicInfo/put",
            type: "POST",
            data: {username: username, nickname: nickname, email: email, birthday: birthday, registerDate: registerDate, sex: sex},
            success: function(){
                $("#successInfo").show();
            }
        });
    });

});