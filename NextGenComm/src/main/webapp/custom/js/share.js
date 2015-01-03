var TableEditable = function () {
    return {
        init: function () {
            var oTable = jQuery('#sample_editable_1').dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listOwner.do",
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ 条记录每页",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        "aTargets": [4],
                        "mRender": function (data, type, full) {
                            return '<a  href="/">详细信息</a>';
                        }
                    },
                    {
                        'sDefaultContent': '',
                        "aTargets": ['_all']
                    }
                ]
            });

        }
    };
}();