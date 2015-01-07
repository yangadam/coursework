var FeeCharge = function () {

    return {
        init: function () {

            function ownerFormatResult(owner) {
                return "<option value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>";
            }

            function ownerFormatSelection(owner) {
                return "<option value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>";
            }

            $("#owner").on("change", function (e) {
                var ownerId = e.val;
                $.getJSON("/ownerBill.do?ownerId=" + ownerId, function (data) {
                    oTable.fnClearTable();
                    oTable.fnAddData(data["aaData"], true)

                });

            });

            $("#owner").select2({
                placeholder: "请指定业主",
                minimumInputLength: 2,
                ajax: {
                    url: "/ownerSearch.do",
                    dataType: 'json',
                    data: function (term, page) {
                        return {
                            term: term
                        };
                    },
                    results: function (data, page) {
                        return {
                            results: data["owners"]
                        };
                    }
                },
                formatResult: ownerFormatResult,
                formatSelection: ownerFormatSelection,
                dropdownCssClass: "bigdrop",
                escapeMarkup: function (m) {
                    return m;
                }
            });

            var oTable = $('#billItems').dataTable({
                "bPaginate": false,
                "bFilter": false,
                "bSort": false,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                /*"sAjaxSource": "/.do",*/
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
                    "sInfo": "显示 _START_ 到 _END_ 条　共计 _TOTAL_ 条",
                    "sInfoEmpty": "",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "sSearch": "搜索：",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    }
                },
                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
                ]
            });


        }
    };
}();

