var App = function () {
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;
    var sidebarWidth = 225;
    var sidebarCollapsedWidth = 35;
    var responsiveHandlers = [];
    var layoutColorCodes = {
        "blue": "#4b8df8",
        "red": "#e02222",
        "green": "#35aa47",
        "purple": "#852b99",
        "grey": "#555555",
        "light-grey": "#fafafa",
        "yellow": "#ffb848"
    };
    var handleInit = function () {
        if ($("body").css("direction") === "rtl") {
            isRTL = true
        }
        isIE8 = !!navigator.userAgent.match(/MSIE 8.0/);
        isIE9 = !!navigator.userAgent.match(/MSIE 9.0/);
        isIE10 = !!navigator.userAgent.match(/MSIE 10/);
        if (isIE10) {
            jQuery("html").addClass("ie10")
        }
    };
    var handleDesktopTabletContents = function () {
        if ($(window).width() <= 1280 || $("body").hasClass("page-boxed")) {
            $(".responsive").each(function () {
                var forTablet = $(this).attr("data-tablet");
                var forDesktop = $(this).attr("data-desktop");
                if (forTablet) {
                    $(this).removeClass(forDesktop);
                    $(this).addClass(forTablet)
                }
            })
        }
        if ($(window).width() > 1280 && $("body").hasClass("page-boxed") === false) {
            $(".responsive").each(function () {
                var forTablet = $(this).attr("data-tablet");
                var forDesktop = $(this).attr("data-desktop");
                if (forTablet) {
                    $(this).removeClass(forTablet);
                    $(this).addClass(forDesktop)
                }
            })
        }
    };
    var handleSidebarState = function () {
        if ($(window).width() < 980) {
            $("body").removeClass("page-sidebar-closed")
        }
    };
    var runResponsiveHandlers = function () {
        for (var i in responsiveHandlers) {
            var each = responsiveHandlers[i];
            each.call()
        }
    };
    var handleResponsive = function () {
        handleTooltips();
        handleSidebarState();
        handleDesktopTabletContents();
        handleSidebarAndContentHeight();
        handleChoosenSelect();
        handleFixedSidebar();
        runResponsiveHandlers()
    };
    var handleResponsiveOnInit = function () {
        handleSidebarState();
        handleDesktopTabletContents();
        handleSidebarAndContentHeight()
    };
    var handleResponsiveOnResize = function () {
        var resize;
        if (isIE8) {
            var currheight;
            $(window).resize(function () {
                if (currheight == document.documentElement.clientHeight) {
                    return
                }
                if (resize) {
                    clearTimeout(resize)
                }
                resize = setTimeout(function () {
                    handleResponsive()
                }, 50);
                currheight = document.documentElement.clientHeight
            })
        } else {
            $(window).resize(function () {
                if (resize) {
                    clearTimeout(resize)
                }
                resize = setTimeout(function () {
                    handleResponsive()
                }, 50)
            })
        }
    };
    var handleSidebarAndContentHeight = function () {
        var content = $(".page-content");
        var sidebar = $(".page-sidebar");
        var body = $("body");
        var height;
        if (body.hasClass("page-footer-fixed") === true && body.hasClass("page-sidebar-fixed") === false) {
            var available_height = $(window).height() - $(".footer").height();
            if (content.height() < available_height) {
                content.attr("style", "min-height:" + available_height + "px !important")
            }
        } else {
            if (body.hasClass("page-sidebar-fixed")) {
                height = _calculateFixedSidebarViewportHeight()
            } else {
                height = sidebar.height() + 20
            }
            if (height >= content.height()) {
                content.attr("style", "min-height:" + height + "px !important")
            }
        }
    };
    var handleSidebarMenu = function () {
        jQuery(".page-sidebar").on("click", "li > a", function (e) {
            if ($(this).next().hasClass("sub-menu") == false) {
                if ($(".btn-navbar").hasClass("collapsed") == false) {
                    $(".btn-navbar").click()
                }
                return
            }
            var parent = $(this).parent().parent();
            parent.children("li.open").children("a").children(".arrow").removeClass("open");
            parent.children("li.open").children(".sub-menu").slideUp(200);
            parent.children("li.open").removeClass("open");
            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery(".arrow", jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(200, function () {
                    handleSidebarAndContentHeight()
                })
            } else {
                jQuery(".arrow", jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(200, function () {
                    handleSidebarAndContentHeight()
                })
            }
            e.preventDefault()
        });
        jQuery(".page-sidebar").on("click", " li > a.ajaxify", function (e) {
            e.preventDefault();
            App.scrollTop();
            var url = $(this).attr("href");
            var menuContainer = jQuery(".page-sidebar ul");
            var pageContent = $(".page-content");
            var pageContentBody = $(".page-content .page-content-body");
            menuContainer.children("li.active").removeClass("active");
            menuContainer.children("arrow.open").removeClass("open");
            $(this).parents("li").each(function () {
                $(this).addClass("active");
                $(this).children("a > span.arrow").addClass("open")
            });
            $(this).parents("li").addClass("active");
            App.blockUI(pageContent, false);
            $.post(url, {}, function (res) {
                App.unblockUI(pageContent);
                pageContentBody.html(res);
                App.fixContentHeight();
                App.initUniform()
            })
        })
    };
    var _calculateFixedSidebarViewportHeight = function () {
        var sidebarHeight = $(window).height() - $(".header").height() + 1;
        if ($("body").hasClass("page-footer-fixed")) {
            sidebarHeight = sidebarHeight - $(".footer").height()
        }
        return sidebarHeight
    };
    var handleFixedSidebar = function () {
        var menu = $(".page-sidebar-menu");
        if (menu.parent(".slimScrollDiv").size() === 1) {
            menu.slimScroll({destroy: true});
            menu.removeAttr("style");
            $(".page-sidebar").removeAttr("style")
        }
        if ($(".page-sidebar-fixed").size() === 0) {
            handleSidebarAndContentHeight();
            return
        }
        if ($(window).width() >= 980) {
            var sidebarHeight = _calculateFixedSidebarViewportHeight();
            menu.slimScroll({
                size: "7px",
                color: "#a1b2bd",
                opacity: 0.3,
                position: isRTL ? "left" : ($(".page-sidebar-on-right").size() === 1 ? "left" : "right"),
                height: sidebarHeight,
                allowPageScroll: false,
                disableFadeOut: false
            });
            handleSidebarAndContentHeight()
        }
    };
    var handleFixedSidebarHoverable = function () {
        if ($("body").hasClass("page-sidebar-fixed") === false) {
            return
        }
        $(".page-sidebar").off("mouseenter").on("mouseenter", function () {
            var body = $("body");
            if ((body.hasClass("page-sidebar-closed") === false || body.hasClass("page-sidebar-fixed") === false) || $(this).hasClass("page-sidebar-hovering")) {
                return
            }
            body.removeClass("page-sidebar-closed").addClass("page-sidebar-hover-on");
            $(this).addClass("page-sidebar-hovering");
            $(this).animate({width: sidebarWidth}, 400, "", function () {
                $(this).removeClass("page-sidebar-hovering")
            })
        });
        $(".page-sidebar").off("mouseleave").on("mouseleave", function () {
            var body = $("body");
            if ((body.hasClass("page-sidebar-hover-on") === false || body.hasClass("page-sidebar-fixed") === false) || $(this).hasClass("page-sidebar-hovering")) {
                return
            }
            $(this).addClass("page-sidebar-hovering");
            $(this).animate({width: sidebarCollapsedWidth}, 400, "", function () {
                $("body").addClass("page-sidebar-closed").removeClass("page-sidebar-hover-on");
                $(this).removeClass("page-sidebar-hovering")
            })
        })
    };
    var handleSidebarToggler = function () {
        $(".page-sidebar").on("click", ".sidebar-toggler", function (e) {
            var body = $("body");
            var sidebar = $(".page-sidebar");
            if ((body.hasClass("page-sidebar-hover-on") && body.hasClass("page-sidebar-fixed")) || sidebar.hasClass("page-sidebar-hovering")) {
                body.removeClass("page-sidebar-hover-on");
                sidebar.css("width", "").hide().show();
                e.stopPropagation();
                runResponsiveHandlers();
                return
            }
            $(".sidebar-search", sidebar).removeClass("open");
            if (body.hasClass("page-sidebar-closed")) {
                body.removeClass("page-sidebar-closed");
                if (body.hasClass("page-sidebar-fixed")) {
                    sidebar.css("width", "")
                }
            } else {
                body.addClass("page-sidebar-closed")
            }
            runResponsiveHandlers()
        });
        $(".page-sidebar").on("click", ".sidebar-search .remove", function (e) {
            e.preventDefault();
            $(".sidebar-search").removeClass("open")
        });
        $(".page-sidebar").on("keypress", ".sidebar-search input", function (e) {
            if (e.which == 13) {
                window.location.href = "extra_search.html";
                return false
            }
        });
        $(".sidebar-search .submit").on("click", function (e) {
            e.preventDefault();
            if ($("body").hasClass("page-sidebar-closed")) {
                if ($(".sidebar-search").hasClass("open") == false) {
                    if ($(".page-sidebar-fixed").size() === 1) {
                        $(".page-sidebar .sidebar-toggler").click()
                    }
                    $(".sidebar-search").addClass("open")
                } else {
                    window.location.href = "extra_search.html"
                }
            } else {
                window.location.href = "extra_search.html"
            }
        })
    };
    var handleHorizontalMenu = function () {
        $(".header").on("click", ".hor-menu .hor-menu-search-form-toggler", function (e) {
            if ($(this).hasClass("hide")) {
                $(this).removeClass("hide");
                $(".header .hor-menu .search-form").hide()
            } else {
                $(this).addClass("hide");
                $(".header .hor-menu .search-form").show()
            }
            e.preventDefault()
        });
        $(".header").on("click", ".hor-menu .search-form .btn", function (e) {
            window.location.href = "extra_search.html";
            e.preventDefault()
        });
        $(".header").on("keypress", ".hor-menu .search-form input", function (e) {
            if (e.which == 13) {
                window.location.href = "extra_search.html";
                return false
            }
        })
    };
    var handleGoTop = function () {
        jQuery(".footer").on("click", ".go-top", function (e) {
            App.scrollTo();
            e.preventDefault()
        })
    };
    var handlePortletTools = function () {
        jQuery("body").on("click", ".portlet .tools a.remove", function (e) {
            e.preventDefault();
            var removable = jQuery(this).parents(".portlet");
            if (removable.next().hasClass("portlet") || removable.prev().hasClass("portlet")) {
                jQuery(this).parents(".portlet").remove()
            } else {
                jQuery(this).parents(".portlet").parent().remove()
            }
        });
        jQuery("body").on("click", ".portlet .tools a.reload", function (e) {
            e.preventDefault();
            var el = jQuery(this).parents(".portlet");
            App.blockUI(el);
            window.setTimeout(function () {
                App.unblockUI(el)
            }, 1000)
        });
        jQuery("body").on("click", ".portlet .tools .collapse, .portlet .tools .expand", function (e) {
            e.preventDefault();
            var el = jQuery(this).closest(".portlet").children(".portlet-body");
            if (jQuery(this).hasClass("collapse")) {
                jQuery(this).removeClass("collapse").addClass("expand");
                el.slideUp(200)
            } else {
                jQuery(this).removeClass("expand").addClass("collapse");
                el.slideDown(200)
            }
        })
    };
    var handleUniform = function () {
        if (!jQuery().uniform) {
            return
        }
        var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
        if (test.size() > 0) {
            test.each(function () {
                if ($(this).parents(".checker").size() == 0) {
                    $(this).show();
                    $(this).uniform()
                }
            })
        }
    };
    var handleAccordions = function () {
        $(".accordion").collapse().height("auto");
        var lastClicked;
        jQuery("body").on("click", ".accordion.scrollable .accordion-toggle", function () {
            lastClicked = jQuery(this)
        });
        jQuery("body").on("shown", ".accordion.scrollable", function () {
            jQuery("html,body").animate({scrollTop: lastClicked.offset().top - 150}, "slow")
        })
    };
    var handleTabs = function () {
        var fixTabHeight = function (tab) {
            $(tab).each(function () {
                var content = $($($(this).attr("href")));
                var tab = $(this).parent().parent();
                if (tab.height() > content.height()) {
                    content.css("min-height", tab.height())
                }
            })
        };
        $("body").on("shown", '.nav.nav-tabs.tabs-left a[data-toggle="tab"], .nav.nav-tabs.tabs-right a[data-toggle="tab"]', function () {
            fixTabHeight($(this))
        });
        $("body").on("shown", ".nav.nav-tabs", function () {
            handleSidebarAndContentHeight()
        });
        fixTabHeight('.nav.nav-tabs.tabs-left > li.active > a[data-toggle="tab"], .nav.nav-tabs.tabs-right > li.active > a[data-toggle="tab"]');
        if (location.hash) {
            var tabid = location.hash.substr(1);
            $('a[href="#' + tabid + '"]').click()
        }
    };
    var handleScrollers = function () {
        $(".scroller").each(function () {
            $(this).slimScroll({
                size: "7px",
                color: "#a1b2bd",
                position: isRTL ? "left" : "right",
                height: $(this).attr("data-height"),
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                disableFadeOut: true
            })
        })
    };
    var handleTooltips = function () {
        if (App.isTouchDevice()) {
            jQuery(".tooltips:not(.no-tooltip-on-touch-device)").tooltip()
        } else {
            jQuery(".tooltips").tooltip()
        }
    };
    var handleDropdowns = function () {
        $("body").on("click", ".dropdown-menu.hold-on-click", function (e) {
            e.stopPropagation()
        })
    };
    var handlePopovers = function () {
        jQuery(".popovers").popover()
    };
    var handleChoosenSelect = function () {
        if (!jQuery().chosen) {
            return
        }
        $(".chosen").each(function () {
            $(this).chosen({allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false})
        })
    };
    var handleFancybox = function () {
        if (!jQuery.fancybox) {
            return
        }
        if (jQuery(".fancybox-button").size() > 0) {
            jQuery(".fancybox-button").fancybox({
                groupAttr: "data-rel",
                prevEffect: "none",
                nextEffect: "none",
                closeBtn: true,
                helpers: {title: {type: "inside"}}
            })
        }
    };
    var handleTheme = function () {
        var panel = $(".color-panel");
        if ($("body").hasClass("page-boxed") == false) {
            $(".layout-option", panel).val("fluid")
        }
        $(".sidebar-option", panel).val("default");
        $(".header-option", panel).val("fixed");
        $(".footer-option", panel).val("default");
        var resetLayout = function () {
            $("body").removeClass("page-boxed").removeClass("page-footer-fixed").removeClass("page-sidebar-fixed").removeClass("page-header-fixed");
            $(".header > .navbar-inner > .container").removeClass("container").addClass("container-fluid");
            if ($(".page-container").parent(".container").size() === 1) {
                $(".page-container").insertAfter(".header")
            }
            if ($(".footer > .container").size() === 1) {
                $(".footer").html($(".footer > .container").html())
            } else {
                if ($(".footer").parent(".container").size() === 1) {
                    $(".footer").insertAfter(".page-container")
                }
            }
            $("body > .container").remove()
        };
        var lastSelectedLayout = "";
        var setLayout = function () {
            var layoutOption = $(".layout-option", panel).val();
            var sidebarOption = $(".sidebar-option", panel).val();
            var headerOption = $(".header-option", panel).val();
            var footerOption = $(".footer-option", panel).val();
            if (sidebarOption == "fixed" && headerOption == "default") {
                alert("固定侧边栏选择默认的标题是不支持。继续用默认工具栏默认标题。");
                $(".sidebar-option", panel).val("default");
                sidebarOption = "default"
            }
            resetLayout();
            if (layoutOption === "boxed") {
                $("body").addClass("page-boxed");
                $(".header > .navbar-inner > .container-fluid").removeClass("container-fluid").addClass("container");
                var cont = $(".header").after('<div class="container"></div>');
                $(".page-container").appendTo("body > .container");
                if (footerOption === "fixed" || sidebarOption === "default") {
                    $(".footer").html('<div class="container">' + $(".footer").html() + "</div>")
                } else {
                    $(".footer").appendTo("body > .container")
                }
            }
            if (lastSelectedLayout != layoutOption) {
                runResponsiveHandlers()
            }
            lastSelectedLayout = layoutOption;
            if (headerOption === "fixed") {
                $("body").addClass("page-header-fixed");
                $(".header").removeClass("navbar-static-top").addClass("navbar-fixed-top")
            } else {
                $("body").removeClass("page-header-fixed");
                $(".header").removeClass("navbar-fixed-top").addClass("navbar-static-top")
            }
            if (sidebarOption === "fixed") {
                $("body").addClass("page-sidebar-fixed")
            } else {
                $("body").removeClass("page-sidebar-fixed")
            }
            if (footerOption === "fixed") {
                $("body").addClass("page-footer-fixed")
            } else {
                $("body").removeClass("page-footer-fixed")
            }
            handleSidebarAndContentHeight();
            handleFixedSidebar();
            handleFixedSidebarHoverable()
        };
        var setColor = function (color) {
            $("#style_color").attr("href", "media/css/" + color + ".css");
            $.cookie("style_color", color)
        };
        $(".icon-color", panel).click(function () {
            $(".color-mode").show();
            $(".icon-color-close").show()
        });
        $(".icon-color-close", panel).click(function () {
            $(".color-mode").hide();
            $(".icon-color-close").hide()
        });
        $("li", panel).click(function () {
            var color = $(this).attr("data-style");
            setColor(color);
            $(".inline li", panel).removeClass("current");
            $(this).addClass("current")
        });
        $(".layout-option, .header-option, .sidebar-option, .footer-option", panel).change(setLayout)
    };
    var handleFixInputPlaceholderForIE = function () {
        if (isIE8 || isIE9) {
            jQuery("input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)").each(function () {
                var input = jQuery(this);
                if (input.val() == "" && input.attr("placeholder") != "") {
                    input.addClass("placeholder").val(input.attr("placeholder"))
                }
                input.focus(function () {
                    if (input.val() == input.attr("placeholder")) {
                        input.val("")
                    }
                });
                input.blur(function () {
                    if (input.val() == "" || input.val() == input.attr("placeholder")) {
                        input.val(input.attr("placeholder"))
                    }
                })
            })
        }
    };
    return {
        init: function () {
            handleInit();
            handleResponsiveOnResize();
            handleUniform();
            handleScrollers();
            handleResponsiveOnInit();
            handleFixedSidebar();
            handleFixedSidebarHoverable();
            handleSidebarMenu();
            handleHorizontalMenu();
            handleSidebarToggler();
            handleFixInputPlaceholderForIE();
            handleGoTop();
            handleTheme();
            handlePortletTools();
            handleDropdowns();
            handleTabs();
            handleTooltips();
            handlePopovers();
            handleAccordions();
            handleChoosenSelect();
            App.addResponsiveHandler(handleChoosenSelect)
        }, fixContentHeight: function () {
            handleSidebarAndContentHeight()
        }, addResponsiveHandler: function (func) {
            responsiveHandlers.push(func)
        }, setEqualHeight: function (els) {
            var tallestEl = 0;
            els = jQuery(els);
            els.each(function () {
                var currentHeight = $(this).height();
                if (currentHeight > tallestEl) {
                    tallestColumn = currentHeight
                }
            });
            els.height(tallestEl)
        }, scrollTo: function (el, offeset) {
            pos = el ? el.offset().top : 0;
            jQuery("html,body").animate({scrollTop: pos + (offeset ? offeset : 0)}, "slow")
        }, scrollTop: function () {
            App.scrollTo()
        }, blockUI: function (el, centerY) {
            var el = jQuery(el);
            el.block({
                message: '<img src="./media/image/ajax-loading.gif" align="">',
                centerY: centerY != undefined ? centerY : true,
                css: {top: "10%", border: "none", padding: "2px", backgroundColor: "none"},
                overlayCSS: {backgroundColor: "#000", opacity: 0.05, cursor: "wait"}
            })
        }, unblockUI: function (el) {
            jQuery(el).unblock({
                onUnblock: function () {
                    jQuery(el).removeAttr("style")
                }
            })
        }, initUniform: function (els) {
            if (els) {
                jQuery(els).each(function () {
                    if ($(this).parents(".checker").size() == 0) {
                        $(this).show();
                        $(this).uniform()
                    }
                })
            } else {
                handleUniform()
            }
        }, initChosenSelect: function (els) {
            $(els).chosen({allow_single_deselect: true})
        }, initFancybox: function () {
            handleFancybox()
        }, getActualVal: function (el) {
            var el = jQuery(el);
            if (el.val() === el.attr("placeholder")) {
                return ""
            }
            return el.val()
        }, getURLParameter: function (paramName) {
            var searchString = window.location.search.substring(1), i, val, params = searchString.split("&");
            for (i = 0; i < params.length; i++) {
                val = params[i].split("=");
                if (val[0] == paramName) {
                    return unescape(val[1])
                }
            }
            return null
        }, isTouchDevice: function () {
            try {
                document.createEvent("TouchEvent");
                return true
            } catch (e) {
                return false
            }
        }, isIE8: function () {
            return isIE8
        }, isRTL: function () {
            return isRTL
        }, getLayoutColorCode: function (name) {
            if (layoutColorCodes[name]) {
                return layoutColorCodes[name]
            } else {
                return ""
            }
        }
    }
}();