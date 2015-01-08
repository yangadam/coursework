var TableEditable = function () {
    return {
        init: function () {
            var oTable = jQuery("#sample_editable_1").dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listBuild.do",
                "aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {"sLengthMenu": "_MENU_ 条记录每页", "oPaginate": {"sPrevious": "Prev", "sNext": "Next"}},
                "aoColumnDefs": [{
                    "bSortable": false, "aTargets": [2], "mRender": function (data, type, full) {
                        return '<a  href="/">编辑</a>'
                    }
                }, {
                    "bSortable": false, "aTargets": [3], "mRender": function (data, type, full) {
                        return '<a  href="#">删除</a>'
                    }
                }]
            });
            $("#sample_editable_1_new").click(function (e) {
                $("#add-build").removeClass("hide")
            })
        }
    }
}();