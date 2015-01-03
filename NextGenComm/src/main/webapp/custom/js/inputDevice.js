var TableEditable = function () {
    return {
        init: function () {
            var oTable = jQuery('#sample_editable_1').dataTable({
                "bPaginate": true,
                "sAjaxSource": "/listBuild.do",
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
                        "aTargets": [2],
                        "mRender": function (data, type, full) {
                            return '<a  href="/">编辑</a>';
                        }
                    },
                    {
                        'bSortable': false,
                        "aTargets": [3],
                        "mRender": function (data, type, full) {
                            return '<a  href="#">删除</a>';
                        }
                    }
                ]
            });

        }
    };
}();
function check(obj) {
    $.getJSON("/deviceValue.do?deviceNo=" + $(obj).val(), function (data) {
        if (data["lastValue"] != undefined) {
            $("#lastValue").val(data["lastValue"]);
            alert(data["type"]);
            if (data["type"] == "电表") {
                $(".unit").text("度");
            } else if (data["type"] == "水表") {
                $(".unit").text("m³");
            }
            $("#record").enable();
        } else {
            $("#lastValue").val("");
            $(".unit").text("");
            $("#record").disable();
        }
    })
}