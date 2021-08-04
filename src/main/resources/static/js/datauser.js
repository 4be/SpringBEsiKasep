var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";

$(document).ready(function () {
    $.ajax({
        url: "/api/user/",
        type: "GET",
        success: function (result) {
            var data = result.data;
            var hasil = '';
            $.each(data, function (i, item) {
                var j = i + 1;
                hasil += "<tr align=\"center\">\n" +
                    "<td>" + j + "</td>\n" +
                    "<td>" + result.data[i].nik + "</td>\n" +
                    "<td>" + result.data[i].nama + "</td>\n" +
                    "<td>" + result.data[i].email + "</td>\n" +
                    "<td>" + result.data[i].tanggalLahir + "</td>\n" +
                    "<td>" + result.data[i].alamat + "</td>\n" +
                    "<td>" + result.data[i].roles[0].rolename + "</td>\n" +
                    "<td>\n" +
                    "<a th:href=\"@{/user/ubahKaryawan(idKaryawan)}\">\n" +
                    "<button type=\"button\" class=\"btn btn-info\">Ubah</button>\n" +
                    "</a>\n" +
                    "<a th:href=\"@{/user/deleteKaryawan(idKaryawan)}\">\n" +
                    "<button type=\"button\" class=\"btn btn-danger\">Hapus</button>\n" +
                    "</a>\n" +
                    "</td>\n" +
                    "</tr>";
            });
            $("#bodyDataUser").html(hasil);
        },
        error: function (result) {
            console.log(result);
        }
    })
});
