$(document).ready(function(){
    $.ajax({
        url: "/complaintList",
        type: "GET",
        success: function(obj){
            var size = obj.length;
            for(var i = 0; i < size; i++){
                var item = obj[i];
                var line = "";
                if (item.status == "unhandled"){
                    line = line + "<tr>"
                        + "<td>" + item.username + "</td>"
                        + "<td>" + item.title + "</td>"
                        + "<td style='display: none;'>" + item.content + "</td>"
                        + "<td>" + item.date + "</td>"
                        + "<td>" + item.status + "</td>"
                        + "<td><input class='btn btn-primary modal-response' value='回复'/></td>"
                        + "</tr>";
                }
                else{
                    line = line + "<tr>"
                        + "<td>" + item.username + "</td>"
                        + "<td>" + item.title + "</td>"
                        + "<td style='display: none;'>" + item.content + "</td>"
                        + "<td>" + item.date + "</td>"
                        + "<td>" + item.status + "</td>"
                        + "<td><input class='btn btn-primary modal-check' value='查看'/></td>"
                        + "<td style='display: none;'>item.response</td>"
                        + "</tr>";
                }
                $("#complaintTable tbody").append(line);
            }

        }
    });

    $("#complaintTable .modal-response").on("click", function(){
        $("#titleModal").html($(this).parent().prev().prev().prev().prev().html());
        $("#contentModal").html(($(this).parent().prev().prev().prev().html()));
        $("#responseModal").html("");
        $("#responseBtn").show();
    });

    $("#complaintTable .modal-check").on("click", function(){
        $("#titleModal").html($(this).parent().prev().prev().prev().prev().html());
        $("#contentModal").html(($(this).parent().prev().prev().prev().html()));
        $("#responseModal").html($(this).parent().next().html());
        $("#responseBtn").hide();
    });
});
