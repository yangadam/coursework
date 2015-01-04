var Change = function () {

    return {
        init: function () {
            var buildId = -1;
            var floorId = -1;
            var roomId = -1;
            var ownerId = -1;
            var curOwnerId = -1;

            getBuild();

            $("#build").change(function () {
                buildId = $(this).children('option:selected').val();
                getFloor();
            });

            $("#floor").change(function () {
                floorId = $(this).children('option:selected').val();
                getRoom();
            });

            $("#room").change(function () {
                roomId = $(this).children('option:selected').val();
                getRoomInfo();
            });

            $("#owner").on("change", function (e) {
                ownerId = e.val;
                if (ownerId == undefined) {
                    ownerId = -1;
                }
            });

            $("#change").click(function () {
                var message = "";
                if (roomId == undefined || roomId == -1) {
                    message += "请选择房间。";
                }
                if (ownerId == undefined || ownerId == -1) {
                    message += "\n请选择业主";
                }
                if (message == "") {
                    $.post("doCheckIn.do?roomId=" + roomId + "&ownerId=" + ownerId);
                } else {
                    alert(message);
                }
            });

            function getBuild() {
                $("#build").empty();
                $.getJSON("/buildNo.do", function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                        + "</option>").appendTo('#build');
                    }
                    buildId = $("#build").children('option:selected').val();
                    if (buildId == undefined) {
                        buildId = -1;
                    }
                    getFloor();
                });
            }

            function getFloor() {
                $("#floor").empty();
                $.getJSON("/floorNo.do?buildId=" + buildId, function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                        + "</option>").appendTo('#floor');
                    }
                    floorId = $("#floor").children('option:selected').val();
                    if (floorId == undefined) {
                        floorId = -1;
                    }
                    getRoom();
                });
            }

            function getRoom() {
                $("#room").empty();
                $.getJSON("/nonVacantRoomNo.do?floorId=" + floorId, function (data) {
                    var i;
                    for (i = 0; i < data["no"].length; i++) {
                        $("<option value='" + data["id"][i] + "'>" + data["no"][i]
                        + "</option>").appendTo('#room');
                    }
                    roomId = $("#room").children('option:selected').val();
                    if (roomId == undefined) {
                        roomId = -1;
                    }
                    getRoomInfo();
                })
            }

            function getRoomInfo() {
                $.getJSON("/roomInfo.do?roomId=" + roomId, function (data) {
                    $("#area").val(data["area"]);
                    if (data["owner.name"] != undefined) {
                        $("#curOwner").val(data["owner.name"] + "    <" + data["owner.username"] + ">");
                    } else {
                        $("#curOwner").val("");
                    }
                    curOwnerId = data["owner.id"];
                })
            }

            function ownerFormatResult(owner) {
                return "<option onclick(do()) value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>";
            }

            function ownerFormatSelection(owner) {
                return "<option value='" + owner["id"] + "'>" + owner["name"] + "&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&lt;" + owner["username"] + "&gt;" + "</option>";
            }

            $("#owner").select2({
                placeholder: "请指定业主",
                minimumInputLength: 1,
                ajax: {
                    url: "/ownerSearch.do",
                    dataType: 'json',
                    data: function (term, page) {
                        return {
                            term: term
                        };
                    },
                    results: function (data, page) {
                        return {
                            results: data["owners"]
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