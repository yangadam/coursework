var TableEditable = function () {
    return {
        init: function () {
            var oTable = jQuery('#sample_editable_1').dataTable({
                "bPaginate": true,
                "sAjaxSource": "/deviceList.do",
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
                            return '<a  href="#static" data-toggle="modal" onclick="modify(' + data + ')">修改</a>';
                        }
                    }
                ]
            });

            var devices;

            function ownerFormatResult(device) {
                return "<option value='" + device["id"] + "'>" + device["no"] + "&nbsp&nbsp&nbsp&nbsp" + device["type"] + "</option>";
            }

            function ownerFormatSelection(device) {
                return "<option value='" + device["id"] + "'>" + device["no"] + "&nbsp&nbsp&nbsp&nbsp" + device["type"] + "</option>";
            }

            $("#device").on("change", function (e) {
                var i;
                for (i = 0; i < devices.length; i++) {
                    if (devices[i]["id"] = e.val) {
                        $("#lastValue").val(devices[i]["currentValue"]);
                        $("#deviceId").val(e.val);
                        break;
                    }
                }
            });

            $("#device").select2({
                placeholder: "请输入设备号",
                minimumInputLength: 2,
                ajax: {
                    url: "/deviceSearch.do",
                    dataType: 'json',
                    data: function (term, page) {
                        return {
                            term: term
                        };
                    },
                    results: function (data, page) {
                        devices = data["devices"];
                        return {
                            results: data["devices"]
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

        }
    };
}();

function modify(deviceId) {
    $("#modifyId").val(deviceId);
}

function calculate() {
    $.post("calculate.do");
}