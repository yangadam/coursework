$(document).ready(function(){
    $("#submitBtn").click(function(){
        var type = $("#type").val();
        var title = $("#title").val();
        var content = $("#content").val();
        if (title == "" || content == "")
            return;
        else{
            $.ajax({
                type : "GET",
                url : "/message/advise",
                data : {type : type, title : title, content : content},
                success : function(msg){
                    $("#successInfo").show();
                }
            });
        }
    });

});