var data = null;
$(document).ready(function () {
    $('#sihapus').hide();
    $('#siubah').hide();
    var table = $('#dataUser').DataTable({
        dom: 'Bfrtip',
        buttons: [{
            text: "Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }],
        ajax: {
            url: "/api/user/",
            type: "GET",
            data: "data",
            headers: {Authorization: localStorage.getItem("token")},
            error: function (result) {
                if (result.status == 401) {
                    location.href = "/";
                }
            }
        },
        columnDefs: [{
            searchable: false,
            orderable: false,
            targets: 0
        }],
        ScrollX: true,
        order: [[1, 'asc']],
        columns: [
            {data: null},
            {data: "nik", class: "tbl-center"},
            {data: "nama", class: "tbl-center"},
            {data: "email", class: "tbl-center"},
            {data: "tanggal_lahir", class: "tbl-center"},
            {data: "alamat", class: "tbl-center"},
            {data: "divisi", class: "tbl-center"},
            {data: "nik_manager", class: "tbl-center"},
            {data: "role", class: "tbl-center"},
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
    table.on('draw.dt', function () {
        var PageInfo = $('#dataUser').DataTable().page.info();
        table.column(0, {page: 'current'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1 + PageInfo.start;
        });
    });
});

function deleteUser(obj) {
    var nik = $(obj).attr('id');
    var confir = confirm("Apakah anda yakin akan menghapus data user ?");
    if (confir == true) {
        $.ajax({
            url: "/api/user/nik/" + nik,
            type: "DELETE",
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                if (result.status == 200) {
                    window.scrollTo(0, 0);
                    $("#sihapus").show();
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                }
            },
            error: function (result) {
                if (result.status == 401) {
                    location.href = "/";
                }
            }
        });
    }
}
