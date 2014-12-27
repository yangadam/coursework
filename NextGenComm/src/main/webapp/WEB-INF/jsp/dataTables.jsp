<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data Tables</title>

    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.css"/>
    <script type="text/javascript" charset="utf8" src="../../pumpking/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="../../pumpking/js/jquery-2.1.3.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#example').dataTable({
                "ajax": "../..//pumpking/data.txt",
                "columns": [
                    {"data": "name"},
                    {"data": "position"},
                    {"data": "office"},
                    {"data": "extn"},
                    {"data": "start_date"},
                    {"data": "salary"}
                ]
            });
        });
    </script>
</head>
<body>
<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </tfoot>
</table>
</body>
</html>
