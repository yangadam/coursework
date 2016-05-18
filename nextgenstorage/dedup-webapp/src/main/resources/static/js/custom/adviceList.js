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
                    + "<td><a href='javascript:void(0)' class='adviceContent'>" + advice.title + "</a></td>"
                    + "<td style='display: none'>" + advice.content + "</td>"
                    + "<td>" + advice.date + "</td>"
                    + "</tr>";
                $("#adviceTable  tbody").append(row);
            }
        }
    });

    $(".adviceContent").on('click', function(){
        var content = $(this).html();
        var title = $(this).parent().prev().html();
        $("#contentModal").html(content);
        $("#titleModal").html(title);
        $("#infoModal").show();
    });
});
