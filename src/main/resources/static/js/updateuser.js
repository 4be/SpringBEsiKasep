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
            "\"tanggal_lahir\" : \"" + $("#ttlKaryawan").val() + "\"," +
            "\"alamat\" : \"" + $("#alamatKaryawan").val() + "\"," +
            "\"divisi\" : \"" + $("#divisiKaryawan").val() + "\"," +
            "\"nik_manager\" : \"" + $("#nikManager").val() + "\"," +
            "\"role\": \"" + $("#roleKaryawan").val() + "\"," +
            "\"password\" : \"" + $("#nikKaryawan").val() + "\"" +
            "}";

        $.ajax({
            url: '/api/user/' + $("#nikKaryawan").val(),
            type: 'PATCH',
            data: user,
            dataType: 'json',
            contentType: 'application/json',
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                if (result.status == 200) {
                    location.href = "/hcms/datauser";
                } else if (result.status == 401) {
                    location.href = "/";
                } else {
                    window.location = window.location;
                }
            },
        });
    }
});

