/**
 * Created by xmuli on 2016/4/5.
 */
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/advice",
        success: function (msg) {
            var size = msg.length;
            for(var i = 0; i < size; i++){
                var advice = msg[i];
                var row = "<tr>" + "<td>" + advice.username + "</td>"
                    + "<td>" + advice.title + "</td>"
                    + "<td>" + advice.content + "</td>"
                    + "<td><a href='javascript:void(0)' class='btn btn-danger response'>回复</a></td>"
                    + "</tr>";
                $("#adviceTable  tbody").append(row);
            }
        }
    });

    $(".delete").click(function () {
        var username = $(this).parent().prev().prev().prev().text();
        var title = $(this).parent().prev().prev().text();
        // TODO use this username to delete the user.
    });
});
