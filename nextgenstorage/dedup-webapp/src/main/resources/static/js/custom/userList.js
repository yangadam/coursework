
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/user/getAllUser",
        success: function (msg) {
            var size = msg.length;
            for(var i = 0; i < size; i++){
                var user = msg[i];
                var row = "<tr>" + "<td>" + user.username + "</td>"
                    + "<td>" + user.nickname + "</td>"
                    + "<td>" + user.date + "</td>"
                    + "<td>" + user.size + "</td>"
                    + "<td><a href='javascript:void(0)' class='btn btn-danger delete'>删除</a></td>"
                    + "</tr>";
                $("#userTable  tbody").append(row);
            }
        }
    });
    
    $("#userTable .delete").click(function () {
        $(this).parent().parent().remove();
        //var username = $(this).parent().prev().prev().text();
        // TODO use this username to delete the user.
    });
});