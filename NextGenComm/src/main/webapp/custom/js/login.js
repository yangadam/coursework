var Login = function () {
    return {
        init: function () {
            $(".login-form").validate({
                errorElement: "label",
                errorClass: "help-inline",
                focusInvalid: false,
                rules: {username: {required: true}, password: {required: true}, remember: {required: false}},
                messages: {username: {required: "请输入您的用户名"}, password: {required: "请输入您的密码"}},
                invalidHandler: function (event, validator) {
                    $(".alert-error", $(".login-form")).show()
                },
                highlight: function (element) {
                    $(element).closest(".control-group").addClass("error")
                },
                success: function (label) {
                    label.closest(".control-group").removeClass("error");
                    label.remove()
                },
                errorPlacement: function (error, element) {
                    error.addClass("help-small no-left-padding").insertAfter(element.closest(".input-icon"))
                }
            });
            $(".forget-form").validate({
                errorElement: "label",
                errorClass: "help-inline",
                focusInvalid: false,
                ignore: "",
                rules: {email: {required: true, email: true}},
                messages: {email: {required: "请输入您的邮箱"}},
                invalidHandler: function (event, validator) {
                },
                highlight: function (element) {
                    $(element).closest(".control-group").addClass("error")
                },
                success: function (label) {
                    label.closest(".control-group").removeClass("error");
                    label.remove()
                },
                errorPlacement: function (error, element) {
                    error.addClass("help-small no-left-padding").insertAfter(element.closest(".input-icon"))
                }
            });
            jQuery("#forget-password").click(function () {
                jQuery(".login-form").hide();
                jQuery(".forget-form").show()
            });
            jQuery("#back-btn").click(function () {
                jQuery(".login-form").show();
                jQuery(".forget-form").hide()
            })
        }
    }
};