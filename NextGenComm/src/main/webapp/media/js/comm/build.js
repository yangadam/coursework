$(document).ready(function () {
    $('#build').dataTable({
        "bPaginate": false,
        "bProcessing": false,
        "sAjaxSource": "/admin/listBuild.do",
        "aoColumnDefs": [{
            'bSortable': false,
            "aTargets": [1],
            "mRender": function (data, type, full) {
                return '<a href="delBuild.do?buildId=' + data + '">删除</a>&nbsp;&nbsp' +
                    '<a href="commEdit.do?comm.id=' + data + '">详细信息</a>';
            }
        }]
    });
});