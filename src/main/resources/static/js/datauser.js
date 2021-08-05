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
        },
        "columnDefs": [{
            "searchable": false,
            "orderable": false,
            "targets": 0
        }],
        "ScrollX": true,
        "order": [[1, 'asc']],
        "columns": [
            {data: null},
            {data: "nik"},
            {data: "nama"},
            {data: "email"},
            {data: "tanggalLahir"},
            {data: "alamat"},
            {data: "divisi"},
            {data: "role"},
            {
                data: "nik",
                render: function (data) {
                    return '<a href="/hcms/update/' + data + '"><button id="' + data + '" class="btn btn-info">Ubah</button></a>'
                }
            },
            {
                data: "nik",
                render: function (data) {
                    return '<button id="' + data + '" onclick="deleteUser(this)" class="btn btn-danger">Hapus</button>'
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
            url: "/api/user/" + nik,
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
