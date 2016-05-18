$(document).ready(function(){
    $.ajax({
        url: "/notification",
        type: "GET",
        success: function (obj) {
            var size = obj.length;
            for(var i = 0; i < size; i++){
                var message = obj[i];
                var line = "";
                line =  line + "<tr>"
                    + "<td>" + message.title + "</td>"
                    + "<td>" + message.date + "</td>"
                    + "<td>" + message.type + "</td>"
                    + "<td>" + message.status + "</td>";
                if ( message.status == "unread" ){
                    line = line + "<td><input type='button' class='btn btn-primary' value='标为已读' /></td>"
                }
                else{
                    line = line + "<td><input type='button' class='btn btn-primary' value='删除' /></td>"
                }
                line = line +  "</tr>";

                $("#messageTable tbody").append(line);
            }
        }
    });
    
    $("#messageTable .delete").on("click", function () {
        
    });
    
    $("#messageTable .toRead").on("click", function () {
        $(this).val("删除");
    });
});