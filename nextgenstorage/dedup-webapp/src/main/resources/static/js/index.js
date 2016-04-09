$(document).ready(function () {
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

    $(".file-input").fileinput({
        'showPreview': false,
        'uploadUrl': 'http://localhost:8080/nextgenstorage/json/fileUploadAction.action'
    });

});
