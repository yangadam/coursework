var TableEditable = function () {
    return {
        init: function () {
            var oTable = jQuery('#sample_editable_1').dataTable({
                "bPaginate": true,
                "sAjaxSource": "/vacantPark.do",
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
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
                        "aTargets": [3],
                        "mRender": function (data, type, full) {
                            return '<a  href="" onclick="rent(' + data + ')">租用</a>';
                        }
                    },
                ]
            });
        }
    };
}();

function rent(id) {
    $("#add-contract").removeClass("hide");
}