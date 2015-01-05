/**
 * Created by Roger on 2015/1/3 0003.
 */

var UnfinishedParkBillTale = function () {

    return {

        //main function to initiate the module
        init: function () {

            var oTable = $('#sample_editable_1').dataTable({
                "aLengthMenu": [
                    [5, 10, 15, -1],
                    [5, 10, 15, "全部"]
                ],
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                /*"bServerSide" : true,*/
                "bProcessing": true,
                "sAjaxSource": "/unchargedParkBill.do",
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
                        "aTargets": [3],
                        "mRender": function (data, type,
                                             full) {
                            return '<a href="tempParkingPaying.do?parkBillId='
                                + data + '">离开</a>';
                        }
                    }
                ]

            });
        }

    };

}();
