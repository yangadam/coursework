var TableEditable = function () {

    return {

        //main function to initiate the module
        init: function () {

            var oTable = $('#sample_editable_1').dataTable({
                "aLengthMenu": [
                    [5, 10, 15, -1],
                    [5, 10, 15, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                /*"bServerSide" : true,*/
                "bPaginate": true,
                "bProcessing": false,
                "sAjaxSource": "/json/commListData.do",
                "oLanguage": {
                    "sSearch": "搜索",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "末页"
                    }
                },
                "aoColumnDefs": [
                    {
                        'bSortable': false,
                        "aTargets": [2],
                        "mRender": function (data, type,
                                             full) {
                            return '<a href="commEdit.do?comm.id='
                                + data + '">编辑</a>';
                        }
                    },
                    {
                        'bSortable': false,
                        "aTargets": [3],
                        "mRender": function (data, type,
                                             full) {
                            return '<a href="#static" data-toggle="modal" id="' + data + '"onclick="deleteUser('
                                + data + ')">删除</a>';
                        }
                    },
                    {
                        'bSortable': false,
                        "aTargets": [4],
                        "mRender": function (data, type,
                                             full) {
                            return '<a href="buildingList.do?comm.id='
                                + data + '">详细信息</a>';
                        }
                    }
                ]

            });

            jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
                showSearchInput: false //hide search box with special css class
            });

            $('#sample_editable_1_new').click(function (e) {

            });
        }

    };

}();

