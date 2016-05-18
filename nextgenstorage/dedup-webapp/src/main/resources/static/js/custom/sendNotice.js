$(document).ready(function(){
    $("#submitBtn").on("click", function(){
        var title = $("#title").val();
        var content = $("#content").val();
        $.ajax({
            url: "/sendNotice",
            type: "POST",
            data: {title: title, concat: content},
            success: function () {
                $("#successInfo").show();
            }
        });
    });
});