/**
 * Created by xmuli on 2016/4/5.
 */
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

});