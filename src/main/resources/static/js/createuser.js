$(document).ready(function () {
    $("#sisukses").hide();
    $("#sigagal").hide();
});

$("#submit").click(function () {
    var confir = confirm("Apakah anda yakin akan melakukan submit ?");
    if (confir == true) {
        var user = "{" +
            "\"nik\" : \"" + $("#nikKaryawan").val() + "\"," +
            "\"nama\" : \"" + $("#namaKaryawan").val() + "\"," +
            "\"email\" : \"" + $("#emailKaryawan").val() + "\"," +
            "\"tanggalLahir\" : \"" + $("#ttlKaryawan").val() + "\"," +
            "\"alamat\" : \"" + $("#alamatKaryawan").val() + "\"," +
            "\"divisi\" : \"" + $("#divisiKaryawan").val() + "\"," +
            "\"role\": \"" + $("#roleKaryawan").val() + "\"," +
            "\"password\" : \"" + $("#nikKaryawan").val() + "\"" +
            "}";

        $.ajax({
            url: '/api/user/',
            type: 'POST',
            data: user,
            dataType: 'json',
            contentType: 'application/json',
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                if (result.status == 200) {
                    $("#sisukses").show();
                    setTimeout(function () {
                        location.href = "/hcms/datauser";
                    }, 1000);
                } else {
                    $("#sigagal").show();
                    setTimeout(function () {
                        location.reload();
                    }, 1000)
                }
            },
            error: function () {
                location.href = "/";
            }
        });
    }
});

