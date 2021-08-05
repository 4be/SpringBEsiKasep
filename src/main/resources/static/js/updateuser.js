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
            url: '/api/user/' + $("#nikKaryawan").val(),
            type: 'PATCH',
            data: user,
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                if (result.status == 200) {
                    location.href = "/hcms/datauser";
                } else {
                    window.location = window.location;
                }
            }
        });
    }
});

