$(document).ready(function () {
    $("#downloadBtn").on("click", function () {
        $.ajax({
            url: "/download",
            type: "POST",
            data: {},
            success: function(){

            }
        });
    });
});
