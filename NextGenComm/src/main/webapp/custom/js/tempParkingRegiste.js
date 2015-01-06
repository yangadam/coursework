var TempParkingRegiste = function () {
    return {
        init: function () {
            var buildId = -1;
            var floorId = -1;
            var roomId = -1;
            var ownerId = -1;
            getBuild();
            $("#build").change(function () {
                buildId = $(this).children("option:selected").val();
                getFloor()
            });
            $("#floor").change(function () {
                floorId = $(this).children("option:selected").val();
                getRoom()
            });
            $("#room").change(function () {
                roomId = $(this).children("option:selected").val();
                getRoomInfo()
            });
            $("#owner").change(function () {
                ownerId = $(this).children("option:selected").val()
            });
            $("#query_room").click(function () {
                $.getJSON("ownerByRoom.do?roomId=" + roomId, function (data) {
                    ownerId = data["owner_id"];
                    $("#owner_name").text(data["owner_name"]);
                    $("#owner_community").text(data["community_name"]);
                    $("#owner_building").text(data["building_num"]);
                    $("#owner_room").text(data["room_num"]);
                    $("#owner_phone").text(data["phone_num"])
                })
            });
            $("#addTempParkingBill").click(function () {
                if (ownerId == -1) {
                    return
                }
                license_u = $("#license").text();
                license_u = encodeURI(encodeURI(license_u));
                $.getJSON("tempParkBillAdd.do?license=" + license_u + "&ownerId=" + ownerId, function (data) {
                    if (data["hasFreeParkPlace"] == "false") {
                        alert("没有剩余车位");
                        window.location.href = "tempParkingList.do"
                    } else {
                        if (data["hasOwner"] == "false") {
                            alert("没有该业主")
                        } else {
                            if (data["parkBillId"] != "") {
                                alert("请放行");
                                window.location.href = "tempParkingList.do"
                            }
                        }
                    }
                })
            });
            function getBuild() {
                $("#build").empty();
                $.getJSON("/buildNo.do", function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i] + "</option>").appendTo("#build")
                    }
                    buildId = $("#build").children("option:selected").val();
                    if (buildId == undefined) {
                        buildId = -1
                    }
                    getFloor()
                })
            }

            function getFloor() {
                $("#floor").empty();
                $.getJSON("/floorNo.do?buildId=" + buildId, function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i] + "</option>").appendTo("#floor")
                    }
                    floorId = $("#floor").children("option:selected").val();
                    if (floorId == undefined) {
                        floorId = -1
                    }
                    getRoom()
                })
            }

            function getRoom() {
                $("#room").empty();
                $.getJSON("/nonVacantRoomNo.do?floorId=" + floorId, function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i] + "</option>").appendTo("#room")
                    }
                    roomId = $("#room").children("option:selected").val();
                    if (roomId == undefined) {
                        roomId = -1
                    }
                    getRoomInfo()
                })
            }

            function getRoomInfo() {
                $.getJSON("/roomInfo.do?roomId=" + roomId, function (data) {
                    $("#area").val(data["area"]);
                    getOwner()
                })
            }

            function getOwner() {
                $.getJSON("/ownerSearch.do?term=owner", function (data) {
                    var i;
                    for (i = 0; i < data["id"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["name"][i] + "&nbsp;&nbsp;" + "&nbsp;&nbsp;&lt;" + data["username"][i] + "&gt;" + "</option>").appendTo("#owner")
                    }
                    ownerId = $("#owner").children("option:selected").val()
                })
            }
        }
    }
}();
$.extend({
    StandardPost: function (url, args) {
        var form = $("<form method='post'></form>"), input;
        form.attr({"action": url});
        $.each(args, function (key, value) {
            input = $("<input type='hidden'>");
            input.attr({"name": key});
            input.val(value);
            form.append(input)
        });
        form.submit()
    }
});