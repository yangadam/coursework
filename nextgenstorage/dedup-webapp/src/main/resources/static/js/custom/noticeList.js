$(document).ready(function(){
    $.ajax({
        url: "/notices/get",
        type: "GET",
        success: function (obj) {
            var size = obj.length;
            for(var i = 0; i < size; i++){
                var notice = obj[i];
                var line = "";
                line =  line + "<tr>"
                    + "<td>" + notice.title + "</td>"
                    + "<td>" + notice.date + "</td>"
                    + "<td><input type='button' class='btn btn-primary delete' value='删除' /></td>"
                    + "</tr>";

                $("#noticeTable tbody").append(line);
            }
        }
    });

    $("#noticeTable .delete").on("click", function () {
        $(this).parent().remove();
    });
});