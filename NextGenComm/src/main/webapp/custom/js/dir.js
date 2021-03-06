var TableEditable = function () {
    return {
        init: function () {
            var oTable = $("#sample_editable_1").dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listDir.do",
                "aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]],
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {"sLengthMenu": "_MENU_ 条记录每页", "oPaginate": {"sPrevious": "Prev", "sNext": "Next"}},
                "aoColumnDefs": [{
                    "bSortable": false, "aTargets": [4], "mRender": function (data, type, full) {
                        return '<a  href="#">编辑</a>'
                    }
                }, {
                    "bSortable": false, "aTargets": [5], "mRender": function (data, type, full) {
                        return '<a  href="#">删除</a>'
                    }
                }]
            });
            $("#sample_editable_1_new").click(function (e) {
                $("#add-mng").removeClass("hide");
                $("#select").empty();
                $.ajax({
                    url: "/commNames.do", type: "GET", dataType: "json", success: function (data) {
                        var i;
                        var json = eval(data);
                        for (i = 0; i < data["names"].length; i++) {
                            $("<option value='" + data["names"][i] + "'>" + data["names"][i] + "</option>").appendTo("#select")
                        }
                    }
                })
            });
            var ele = jQuery("#sample_editable_1_wrapper");
            ele.find(".dataTables_filter input").addClass("m-wrap medium");
            ele.find(".dataTables_length select").addClass("m-wrap small");
            ele.find(".dataTables_length select").select2({showSearchInput: false});
            var nEditing = null;
            $("#sample_editable_1").find("a.delete").live("click", function (e) {
                e.preventDefault();
                if (confirm("Are you sure to delete this row ?") == false) {
                    return
                }
                var nRow = $(this).parents("tr")[0];
                oTable.fnDeleteRow(nRow);
                alert("Deleted! Do not forget to do some ajax to sync with backend :)")
            });
            $("#sample_editable_1").find("a.cancel").live("click", function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents("tr")[0];
                    oTable.fnDeleteRow(nRow)
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null
                }
            });
            $("#sample_editable_1").find("a.edit").live("click", function (e) {
                e.preventDefault();
                var nRow = $(this).parents("tr")[0];
                if (nEditing !== null && nEditing != nRow) {
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow
                } else {
                    if (nEditing == nRow && this.innerHTML == "Save") {
                        saveRow(oTable, nEditing);
                        nEditing = null;
                        alert("Updated! Do not forget to do some ajax to sync with backend :)")
                    } else {
                        editRow(oTable, nRow);
                        nEditing = nRow
                    }
                }
            })
        }
    }
}();