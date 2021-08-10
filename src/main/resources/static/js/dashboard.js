$.ajax({
    url: "/api/clockin",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        var clockout = $(result).filter(function (i, n) {
            return n.end_time != null;
        }).length;
        var clockin = $(result).length;
        console.log(clockin);
        console.log(clockout);
        document.getElementById('clockin').innerHTML(clockin.toString());
        document.getElementById('clockout').innerHTML(clockout.toString());
    },
    error: function () {
        location.href = "/";
    }
});
