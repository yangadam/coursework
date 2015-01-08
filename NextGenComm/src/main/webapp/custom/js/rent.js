var TableEditable = function () {
    return {
        init: function () {
            jQuery("#sample_editable_1").dataTable({
                "bPaginate": true,
                "sAjaxSource": "/vacantPark.do",
                "aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
                "iDisplayLength": 5,
                "sPaginationType": "bootstrap",
                "oLanguage": {"sLengthMenu": "_MENU_ 条记录每页", "oPaginate": {"sPrevious": "Prev", "sNext": "Next"}},
                "aoColumnDefs": [{
                    "bSortable": false, "aTargets": [3], "mRender": function (data) {
                        return '<a onclick="rent(' + data + ')">租用</a>'
                    }
                }]
            });

            function ownerFormatResult(owner) {
                return "<option onclick(do()) value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" + "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>"
            }

            function ownerFormatSelection(owner) {
                return "<option value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" + "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>"
            }

            $("#owner").select2({
                placeholder: "请指定业主",
                minimumInputLength: 1,
                ajax: {
                    url: "/ownerSearch.do", dataType: "json", data: function (term) {
                        return {term: term}
                    }, results: function (data) {
                        return {results: data["owners"]}
                    }
                },
                formatResult: ownerFormatResult,
                formatSelection: ownerFormatSelection,
                dropdownCssClass: "bigdrop",
                escapeMarkup: function (m) {
                    return m
                }
            }).on("change", function (e) {
                ownerId = e.val;
                if (ownerId == undefined) {
                    ownerId = -1
                }
                $("#ownerId").val(ownerId);
            });

        }
    }
}();

function rent(id) {
    $("#add-contract").removeClass("hide");
    $("#pp_id").val(id);
}