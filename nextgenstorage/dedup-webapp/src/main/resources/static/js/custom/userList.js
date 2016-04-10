/**
 * Created by xmuli on 2016/4/4.
 */
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/user/getAllUser",
        success: function (msg) {
            var size = msg.length;
            for(var i = 0; i < size; i++){
                var user = msg[i];
                var row = "<tr>" + "<td>" + user.username + "</td>"
                    + "<td>" + user.name + "</td>"
                    + "<td>" + "...." + "</td>"
                    + "<td><a href='javascript:void(0)' class='btn btn-danger delete'>删除</a></td>"
                    + "</tr>";
                $("#userTable  tbody").append(row);
            }
        }
    });
    
    $(".delete").click(function () {
        var username = $(this).parent().prev().prev().text();
        // TODO use this username to delete the user.
    });
});