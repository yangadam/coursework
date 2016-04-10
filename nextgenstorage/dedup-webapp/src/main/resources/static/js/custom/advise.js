/**
 * Created by xmuli on 2016/4/5.
 */
$(document).ready(function(){
    $("#submitBtn").click(function(){
        var title = $("#title").val();
        var content = $("#content").val();
        var type = $("#type").val();
        if (title == "" || content == "")
            return;
        $.ajax({
            type: "POST",
            url: "/advice/add",
            data: {title:title,content:content,type:type},
            success: function(msg){
                if (msg == null){
                    //fail
                    $("#failInfo").show();
                }
                else{
                    //success
                    $("#successInfo").show();
                }
            }
        });
    });
});