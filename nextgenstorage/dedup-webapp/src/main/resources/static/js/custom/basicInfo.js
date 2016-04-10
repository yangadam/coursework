/**
 * Created by xmuli on 2016/4/5.
 */
$(document).ready(function(){
    $.ajax({
        type : "GET",
        url : "/basicInfo/get",
        success : function(){

        }

    });

    $("#updateBtn").click(function(){
        var name = $("#name").val();
        if (name == "") return;
        $.ajax({
            type: "GET",
            url: "/basicInfo/update",
            success: function(){

            }
        });
    });
});