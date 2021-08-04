$("#submit").click(function () {
    var user = "{" +
        "\"nik\" : \"" + $("#nikKaryawan").val() + "\"," +
        "\"nama\" : \"" + $("#namaKaryawan").val() + "\"," +
        "\"email\" : \"" + $("#emailKaryawan").val() + "\"," +
        "\"tanggalLahir\" : \"" + $("#ttlKaryawan").val() + "\"," +
        "\"alamat\" : \"" + $("#alamatKaryawan").val() + "\"," +
        "\"role\": [\"" + $("#roleKaryawan").val() + "\"]," +
        "\"password\" : \"" + $("#nikKaryawan").val() + "\"" +
        "}";

    $.ajax({
        url: '/api/user/',
        type: 'post',
        data: user,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $('#target').html(data.msg);
        }
    });
});

