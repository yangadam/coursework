$(document).ready(function () {

    function refreshFiles(path){
        $.ajax({
            type : "GET",
            url : "/file/folder",
            global : false,
            data : {path : path},
            success : function(msg){
                showFiles(msg);
            },
            error : function (msg) {
                alert(msg);
            }
        });
    }

    function showFiles(files){
        $("#fileList").empty();
        for (var i = 0; i < files.length; i++){
            var file = files[i];
            var typeClass = "file";
            if ( file.folder )
                typeClass = "folder";

            var row = "<div class='row panel panel-default' style='padding:3px 0;'>"
                + "<div class='col-xs-1'><input type='checkbox'/></div>"

                + "<div class='col-xs-3'><span>";
            if (typeClass == "folder")
                row = row + "<a href='javascript:void(0)' class='" + typeClass + "'>" + file.name + "</a></span></div>";
            else
                row = row + "<span>" + file.name + "</span></span></div>";

            row = row + "<div class='col-xs-1'><span>" + (file.size/1024/1024).toFixed(1) + "MB</span></div>"
                + "<div class='col-xs-1'><span>" + "非公开" + "</span></div>"
                + "<div class='col-xs-2'><span>" + getLocalTime(file.createdDate) + "</span></div>"
                + "<div class='col-xs-3 col-xs-offset-1'>"
                + "<div class='row'>"
                + "<div class='col-xs-3'><a href='javascript:void(0)' class='btn btn-primary delete'>删除</a></div>";
            if (typeClass == "file")
                row = row + "<div class='col-xs-3'><a href='javascript:void(0)' class='btn btn-primary download'>下载</a></div>";
            else
                row = row + "<div class='col-xs-3'></div>";
            row = row + "<div class='col-xs-3'><a href='javascript:void(0)' class='btn btn-primary modify'>修改</a></div>"
                + "<div class='col-xs-3'><a href='javascript:void(0)' class='btn btn-primary get'>获取</a></div>"
                + "</div>"
                + "</div>"
                + "</div>";
            $("#fileList").append(row);
        }

    }

    function deleteFile(path, name){
        if (!confirm("确认要删除文件 " + name + " 吗？"))
            return;
        $.ajax({
            type : "GET",
            url : "/file/delete",
            global : false,
            data : {path : path, name : name},
            success : function(){
                refreshFiles(curPath);
            }
        });

    }

    function exists(path, name){
        var exist = false;
        $.ajax({
            type : "GET",
            url : "/file/exist",
            async : false,
            global : false,
            data : {path : path, name : name},
            success : function(msg){
                exist = msg;
            }
        });
        return exist;
    }

    function mkdir(path, name){
        if (exists(path, name)){
            alert("该文件夹已经存在！");
            return;
        }
        $.ajax({
            type : "GET",
            url : "/file/mkdir",
            global : false,
            data : {path : path, name : name},
            success : function(){
                refreshFiles(curPath);
            }
        });
    }

    function getFileName(obj){
        return $(obj).parent().parent().parent().parent().children().eq(1).children().eq(0).text();
    }

    $("#createFolder").click(function(){
        var folderName = $("#folderName").val();
        mkdir(curPath, folderName);
    });

    $("#fileList").on("click", ".delete", function(){
        var fileName = getFileName($(this));
        //if (confirm("确定删除 " + fileName + "吗？"))
        deleteFile(curPath, fileName);

    });

    $("#fileList").on("click",".download",function(){
        var fileName = getFileName($(this));
        window.location.target = "_blank";
        window.location.href = "/download?path=" + curPath + "&name=" + fileName;
    });

    $(".modify").click(function(){
        var fileName = getFileName($(this));
        alert("TODO modify:" + fileName);
    });

    $("#fileList").on("click",".folder",function(){
        var folderName = $(this).text();
        if (curPath == "/")
            curPath = curPath + folderName;
        else
            curPath = curPath + "/" + folderName;

        refreshFiles(curPath);
        refreshTreeView(curPath);
        refreshFileInput(curPath);
    });

    $("#fileList").on("click", ".modify", function(){
        var fileName = getFileName($(this));
        $("#oldName").val(fileName);
        $("#infoModal").modal("show");
    });

    $("#fileList").on("click", ".get", function(){
        var fileName = getFileName($(this));
        $("#shareLink").val(fileName);
        $("#shareModal").modal("show");
    });

    $("#modalSaveBtn").click(function(){
        var path = curPath;
        var oldName = $("#oldName").val();
        var newName = $("#newName").val();
        if (newName == oldName || newName == ""){
            $("#infoModal").modal("hide");
            return;
        }
        $.ajax({
            type : "GET",
            url : "/file/rename",
            global : false,
            data : {path : path, oldName : oldName, newName : newName},
            success : function(){
                refreshFiles(curPath);
            }
        });
        $("#infoModal").modal("hide");
    });

    $("#treeView").on("click",".subTree",function() {
        var path = $(this).next().val();
        curPath = path;
        refreshFiles(curPath);
        refreshTreeView(curPath);
        refreshFileInput(curPath);
    });

    function refreshTreeView(path){
        //update treeView
        var treeView = $("#treeView");
        $(treeView).empty();
        var tempTrees = path.split("/");
        var subTrees = new Array();
        $.each(tempTrees, function(index,obj){
            if (obj != "")
                subTrees.push(obj);
        });
        var length = subTrees.length;
        $(treeView).append("<li><a href='javascript:void(0)' class='subTree'>根目录</a><input type='hidden' value='/'></li>");
        var subPath = "";
        for (var i = 0; i < (length - 1); i++) {
            subPath = subPath + "/" + subTrees[i];
            $(treeView).append("<li><a href='javascript:void(0)' class='subTree'>" + subTrees[i] + "</a><input type='hidden' value='" + subPath + "'></li>");
        }
        if (length != 0) {
            $(treeView).append("<li class='active'>" + subTrees[length - 1] +"</li>");
        }
    }

    function refreshFileInput(path){
        $('#file-input').fileinput('refresh', {uploadExtraData: {path : path}});
    }

    <!-- Upload -->
    $("#uploadPageBtn").click(function () {
        if ($(this).hasClass('closed')) {
            $('.navbar-side.navbar-right').animate({right: '0px'});
            $(this).removeClass('closed');
        }
        else {
            $(this).addClass('closed');
            $('.navbar-side.navbar-right').animate({right: '-500px'});
        }
    });

    $('#file-input').fileinput({
        showPreview: true,
        language: 'zh',
        uploadUrl : "/upload",
        uploadExtraData : {path : "/"},
        uploadAsync : true,
        maxFileCount: 4
    });

    $('#file-input').on('fileuploaded', function(event, data, previewId, index) {
        refreshFiles(curPath);
    });
    $('#file-input').on('fileuploaderror', function(event, data, msg) {
        alert("文件上传出错！");
    });

    /*
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    */

    $("#deleteSelected").click(function(){
        if (!confirm("确认要删除文件选中的文件吗？"))
            return;
        $("#fileList > div").each(function () {
            var checked = $(this).children(":eq(0)").children(":eq(0)").is(":checked");
            if ( checked== true){
                $(this).children(":eq(5)").children(":eq(0)").children(":eq(0)").children(":eq(0)").trigger("click");
            }
        });
    });

    function getLocalTime(timestamp) {
        return new Date(parseInt(timestamp)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    }

    var curPath = "/";
    refreshFiles(curPath);
});
