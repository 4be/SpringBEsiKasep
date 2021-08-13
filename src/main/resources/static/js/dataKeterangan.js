$(document).ready(function () {
    $.fn.dataTable.ext.search.push(
        function (settings, data, index, rowData, counter) {
            var min = parseDateValue($('#min').val());
            var max = parseDateValue($('#max').val());
            var current_start = parseDateValue(data[2]);
            var current_end = parseDateValue(data[3]);
            var flag = false;
            if ((min == '' && max == '') ||
                (min <= current_start && max == '') ||
                (min == '' && current_end <= max) ||
                (min <= current_start && current_end <= max)) {
                flag = true;
            } else {
                flag = false;
            }
            return flag;
        }
    );

    // datatable implementation
    var t = $('#dataKeteranganTable').DataTable({
        dom: 'Blfrtip',
        buttons: [{
            text: "Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4]
            }
        }],
        lengthMenu: [
            [10, 25, 50, -1],
            ['10 rows', '25 rows', '50 rows', 'Show all']
        ],
        "ajax": {
            "url": '/api/keterangan/list',
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            "dataSrc": function (result) {
                return result;
            },
            "error": function (result) {
                if (result.status == 401) {
                    location.href = "/";
                }
            }
        },
        "columns": [
            {"data": null, "class": "tbl-center"},
            {"data": 'user_id.nama', "class": "tbl-center"},
            {
                "data": 'start_date',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        let indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
                        let date = data.split("-")[2];
                        let month = data.split("-")[1];
                        let year = data.split("-")[0];
                        data = date + " " + indoMonth[parseInt(month)] + " " + year;
                    }
                    // data = datetime[0];
                    return data;
                }
            },
            {
                "data": 'end_date',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        var indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
                        let date = data.split("-")[2];
                        let month = data.split("-")[1];
                        let year = data.split("-")[0];
                        data = date + " " + indoMonth[parseInt(month)] + " " + year;
                    }
                    // data = datetime[0];
                    return data;
                }
            },
            {"data": 'description', "class": "tbl-center"},
            {
                "data": 'files',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        let link = data;
                        link = link.replace("/", ":8080/");
                        data = '<a href="http:\/\/' + link + '" target="_blank"><button class="btn btn-success"><i class="fas fa-download"></i></button></a>';
                    }
                    return data;
                }
            },
        ],
        "columnDefs": [{
            "targets": [0, 5],
            "orderable": false
        }]
    });
    t.on('draw.dt', function () {
        var PageInfo = $('#dataKeteranganTable').DataTable().page.info();
        t.column(0, {page: 'current'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1 + PageInfo.start;
        });
    });
    $('#min, #max').change(function () {
        t.draw();
    });
    $('#btnClear').on('click', () => {
        $('#min').val("");
        $('#max').val("");
        t.draw();
    });
    $('#btnToday').on('click', function () {
        var now = new Date();
        var dateNow = now.toISOString().split('T')[0];
        $('#min').val(dateNow);
        $('#max').val(dateNow);
        t.draw();
    });
});
