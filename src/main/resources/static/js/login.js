$(document).ready(function () {
    $("#sigagal1").hide();
    $("#sigagal2").hide();
    if(localStorage.getItem("token") != null) {
        location.href = "/hcms/";
    }
});

$("#login").click(function () {
    var user = "{" +
        "\"username\" : \"" + $("#username").val() + "\"," +
        "\"password\" : \"" + $("#password").val() + "\"" +
        "}";

    $.ajax({
        url: '/api/login/',
        type: 'POST',
        data: user,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            if (result.status == 200) {
                if (result.data.role == 'HCM') {
                    localStorage.setItem("token", result.data.token);
                    location.href = "/hcms/";
                } else {
                    $("#sigagal2").show();
                    setTimeout(function () {
                        location.reload();
                    }, 2000)
                }
            } else {
                $("#sigagal1").show();
                setTimeout(function () {
                    location.reload();
                }, 2000)
            }
        },
        error: function (result) {
            if (result.status == 401) {
                location.href = "/";
            }
        }
    });
});
