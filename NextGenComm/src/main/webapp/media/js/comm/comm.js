$(document).ready(function () {
    $('#comm').dataTable({
        "bPaginate": false,
        "bProcessing": false,
        "sAjaxSource": "/admin/listComm.do",
        "aoColumnDefs": [{
            'bSortable': false,
            "aTargets": [1],
            "mRender": function (data, type, full) {
                return '<a href="delComm.do?commId=' + data + '">删除</a>&nbsp;&nbsp;' +
                    '<a href="building.do?community.id=' + data + '">详细信息</a>';
            }
        }]
    });
});