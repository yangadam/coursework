/**
 * Created by Roger on 2015/1/7 0007.
 */
var TableEditable2 = function () {
    return {
        init: function () {
            var oTable = jQuery("#sample_editable_2").dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listPark.do?type=TEMP",
                "aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap",
                "oLanguage": {"sLengthMenu": "_MENU_ 条记录每页", "oPaginate": {"sPrevious": "Prev", "sNext": "Next"}}
            })
        }
    }
}();