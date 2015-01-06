var TableEditable = function () {
    return {
        init: function () {
            var ownerId = -1;
            var oTable = jQuery("#sample_editable_1").dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listCar.do",
                "aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {"sLengthMenu": "_MENU_ 条记录每页", "oPaginate": {"sPrevious": "Prev", "sNext": "Next"}},
                "aoColumnDefs": [{
                    "bSortable": false, "aTargets": [3], "mRender": function (data, type, full) {
                        return '<a  href="#">编辑</a>'
                    }
                }, {
                    "bSortable": false, "aTargets": [4], "mRender": function (data, type, full) {
                        return '<a  href="#">删除</a>'
                    }
                }, {"sDefaultContent": "", "aTargets": ["_all"]}]
            });
            $("#sample_editable_1_new").click(function (e) {
                $("#add-owner").removeClass("hide")
            });
            $("#owner").on("change", function (e) {
                ownerId = e.val
            });
            $("#owner").select2({
                placeholder: "请指定业主",
                minimumInputLength: 2,
                ajax: {
                    url: "/ownerSearch.do", dataType: "json", data: function (term, page) {
                        return {term: term}
                    }, results: function (data, page) {
                        return {results: data["owners"]}
                    }
                },
                formatResult: ownerFormatResult,
                formatSelection: ownerFormatSelection,
                dropdownCssClass: "bigdrop",
                escapeMarkup: function (m) {
                    return m
                }
            })
        }
    }
}();