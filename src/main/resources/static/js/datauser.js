var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";

$(document).ready(function () {
    $('#sihapus').hide();
    $('#siubah').hide();
    awal();
});

function awal() {
    var t = $('#dataUser').DataTable({
        "ajax": {
            "url": "/api/user/",
            "type": "GET",
            "data": "data",
            // "columns": [
            //     {data: 'nik'},
            //     {data: 'nama'},
            //     {data: 'email'},
            //     {data: 'tanggalLahir'},
            //     {data: 'alamat'},
            //     // {"data": 'roles.rolename'},
            // ],
            // columnDefs: [{
            //     "defaultContent": "-",
            //     "targets": "_all"
            // }]
            // success: function (result) {
            //     var data = result.data;
            //     var hasil = '';
            //     $.each(data, function (i, item) {
            //         var j = i + 1;
            //         hasil += "<tr align=\"center\">\n" +
            //             "<td>" + j + "</td>\n" +
            //             "<td>" + data[i].nik + "</td>\n" +
            //             "<td>" + data[i].nama + "</td>\n" +
            //             "<td>" + data[i].email + "</td>\n" +
            //             "<td>" + data[i].tanggalLahir + "</td>\n" +
            //             "<td>" + data[i].alamat + "</td>\n" +
            //             "<td>" + data[i].roles[0].rolename + "</td>\n" +
            //             "<td>\n" +
            //             "<button onclick=\"myFunction()\" type=\"button\" class=\"btn btn-info\">Ubah</button>\n" +
            //             "<br><br>\n" +
            //             "<button onclick=\"deleteUser(" + data[i].id + ")\" type=\"button\" class=\"btn btn-danger\">Hapus</button>\n" +
            //             "</td>\n" +
            //             "</tr>";
            //     });
            //     $("#bodyDataUser").html(hasil);
            // },
            // error: function (result) {
            //     console.log(result);
            // }
        },
        "columnDefs": [{
            "searchable": false,
            "orderable": false,
            "targets": 0
        }],
        "order": [[1, 'asc']],
        "columns": [
            {data: null},
            {data: "nik"},
            {data: "nama"},
            {data: "email"},
            {data: "tanggalLahir"},
            {data: "alamat"},
            {data: "roles[0].rolename"},
            {
                data: "nik",
                render: function (data) {
                    return '' +
                        '<button id="' + data + '" onclick="editClick(this)" type="button" class="btn btn-info">Edit</button>' +
                        '&ensp;' +
                        '<button id="' + data + '" onclick="deleteUser(this)" type="button" class="btn btn-danger">Delete</button>'
                }
            }
        ],
    });
    t.on('draw.dt', function () {
        var PageInfo = $('#dataUser').DataTable().page.info();
        t.column(0, {page: 'current'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1 + PageInfo.start;
        });
    });
}

function deleteUser(obj) {
    var nik = $(obj).attr('id');
    var confir = confirm("Apakah anda yakin akan menghapus data user ?");
    if (confir == true) {
        $.ajax({
            url: "/api/user/nik/" + nik,
            type: "DELETE",
            success: function () {
                window.scrollTo(0, 0);
                $("#sihapus").show();
                setTimeout(function () {
                    location.reload();
                }, 1500);
            },
            error: function (result) {
                console.log(result);
            }
        });
    }
}

function editClick(obj) {
    var nik = $(obj).attr('id');
    $.ajax({
        url: "/hcms/update/" + nik,
        type: "GET"
    })
}
