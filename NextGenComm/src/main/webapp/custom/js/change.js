var CheckIn = function () {

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

            $("#owner").change(function () {
                ownerId = $(this).children('option:selected').val();
            });

            $("#checkin").click(function () {
                var message = "";
                if (roomId == undefined || roomId == -1) {
                    message.append("请选择房间。");
                }
                if (ownerId == undefined || ownerId == -1) {
                    message.append("\n请选择业主");
                }
                $.post("doCheckIn.do?roomId=" + roomId + "&ownerId=" + ownerId);
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
                    getOwner();
                })
            }

            function getOwner() {
                $.getJSON("/ownerSearch.do?term=owner", function (data) {
                    var i;
                    for (i = 0; i < data["id"].length; i++) {
                        if (curOwnerId != data["id"][i]) {
                            $("<option value='" + data["id"][i] + "'>" + data["name"][i] + "&nbsp;&nbsp;" +
                            "&nbsp;&nbsp;&lt;" + data["username"][i] + "&gt;" + "</option>").appendTo('#owner');
                        }
                    }
                    ownerId = $("#owner").children('option:selected').val();
                })
            }

        }
    };

}();

var handleSelec2 = function () {

    $("#owner").select2({
        placeholder: "Search for a movie",
        minimumInputLength: 1,
        ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
            url: "http://api.rottentomatoes.com/api/public/v1.0/movies.json",
            dataType: 'jsonp',
            data: function (term, page) {
                return {
                    q: term, // search term
                    page_limit: 10,
                    apikey: "ju6z9mjyajq2djue3gbvv26t" // please do not use so this example keeps working
                };
            },
            results: function (data, page) { // parse the results into the format expected by Select2.
                // since we are using custom formatting functions we do not need to alter remote JSON data
                return {
                    results: data.movies
                };
            }
        },
        initSelection: function (element, callback) {
            // the input tag has a value attribute preloaded that points to a preselected movie's id
            // this function resolves that id attribute to an object that select2 can render
            // using its formatResult renderer - that way the movie name is shown preselected
            var id = $(element).val();
            if (id !== "") {
                $.ajax("http://api.rottentomatoes.com/api/public/v1.0/movies/" + id + ".json", {
                    data: {
                        apikey: "ju6z9mjyajq2djue3gbvv26t"
                    },
                    dataType: "jsonp"
                }).done(function (data) {
                    callback(data);
                });
            }
        },
        formatResult: movieFormatResult, // omitted for brevity, see the source of this page
        formatSelection: movieFormatSelection, // omitted for brevity, see the source of this page
        dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
        escapeMarkup: function (m) {
            return m;
        } // we do not want to escape markup since we are displaying html in results
    });
};