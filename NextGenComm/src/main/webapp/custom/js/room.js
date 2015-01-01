var TableEditable = function () {
    return {
        init: function () {
            var buildId;
            var floorId;
            var oTable;
            $("#build").empty();
            $.ajax({
                url: "/buildNo.do",
                dataType: "json",
                success: function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                        + "</option>").appendTo('#build');
                    }
                    buildId = $("#build").children('option:selected').val();
                    $("#floor").empty();
                    $.ajax({
                        url: "/floorNo.do?buildId=" + buildId,
                        dataType: "json",
                        success: function (data) {
                            var i;
                            for (i = 0; i < data["no"].length; i++) {
                                $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                                + "</option>").appendTo('#floor');
                            }
                            floorId = $("#floor").children('option:selected').val();
                            $("#floorId").val(floorId);
                            oTable = jQuery('#sample_editable_1').dataTable({
                                "bPaginate": true,
                                "sAjaxSource": "/listRoom.do?floorId=" + floorId,
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
                                        "aTargets": [3],
                                        "mRender": function (data, type, full) {
                                            return '<a  href="/">编辑</a>';
                                        }
                                    },
                                    {
                                        'bSortable': false,
                                        "aTargets": [4],
                                        "mRender": function (data, type, full) {
                                            return '<a  href="#">删除</a>';
                                        }
                                    },
                                    {
                                        sDefaultContent: '',
                                        aTargets: ['_all']
                                    }
                                ]
                            });
                        }
                    })
                }
            });

            $("#build").change(function () {
                buildId = $(this).children('option:selected').val();
                $("#floor").empty();
                $.ajax({
                    url: "/floorNo.do?buildId=" + buildId,
                    dataType: "json",
                    success: function (data) {
                        var i;
                        for (i = 0; i < data["no"].length; i++) {
                            $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                            + "</option>").appendTo('#floor');
                        }
                        floorId = $("#floor").children('option:selected').val();
                        $("#floorId").val(floorId);
                        $.ajax({
                            url: "/listRoom.do?floorId=" + floorId,
                            dataType: "json",
                            success: function (data) {
                                oTable.fnClearTable();
                                oTable.fnAddData(data["aaData"], true);
                            }
                        });
                    }
                })
            });

            $("#floor").change(function () {
                floorId = $(this).children('option:selected').val();
                $("#floorId").val(floorId);
                $.ajax({
                    url: "/listRoom.do?floorId=" + floorId,
                    dataType: "json",
                    success: function (data) {
                        oTable.fnClearTable();
                        oTable.fnAddData(data["aaData"], true);
                    }
                });
            });

            $('#sample_editable_1_new').click(function (e) {
                $("#add-room").removeClass("hide");
            });

        }
    };
}();