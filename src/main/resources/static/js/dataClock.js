$(document).ready(function () {
    $.fn.dataTable.ext.search.push(
        function (settings, data, index, rowData, counter) {
            var min = parseDateValue($('#min').val());
            var max = parseDateValue($('#max').val());
            var current = parseDateValue(data[3].split("T")[0]);
            var flag = false;
            if ((min == '' && max == '') ||
                (min <= current && max == '') ||
                (min == '' && current <= max) ||
                (min <= current && current <= max)) {
                flag = true;
            }
            return flag;
        }
    );
    $.fn.dataTable.ext.search.push(
        function (settings, data, index, rowData, counter) {
            var status = $('#statusClock').val();
            var flag = false;
            console.log(data[1]);
            if (status == 'all' || status == data[1]) {
                flag = true;
            }
            return flag;
        }
    );
    // datatable implementation
    var t = $('#dataClockTable').DataTable({
        dom: "<'row'<'col-md-3'l><'col-md-5 text-left'B><'col-md-4'f>>" +
            "<'row'<'col-md-12'tr>>" +
            "<'row'<'col-md-5'i><'col-md-7'p>>",
        buttons: [{
            text: "<i class=\"fas fa-download\"></i> Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }, {
            text: "<i class=\"fas fa-download\"></i> Export Excel",
            extend: 'excel',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }],
        lengthMenu: [
            [10, 25, 50, -1],
            ['10 rows', '25 rows', '50 rows', 'Show all']
        ],
        "ajax": {
            "url": '/api/clock/desc/',
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            "dataSrc": function (result) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].working == true) {
                        result[i].working = 'clock in';
                    } else {
                        result[i].working = 'clock out';
                    }
                }
                return result;
            },
            error: function (result) {
                if (result.status == 401) {
                    alert(result.responseJSON.message);
                    localStorage.removeItem("token");
                    location.href = "/";
                }
            }
        },
        "columns": [
            {"data": null, "class": "tbl-center"},
            {
                "data": 'working',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        let btnColor;
                        let status;
                        if (data == 'clock in') {
                            btnColor = 'success';
                            status = 'clock in';
                        } else if (data == 'clock out') {
                            btnColor = 'danger';
                            status = 'clock out';
                        }
                        data = '<span class="badge badge-pill badge-' + btnColor + '"><i class="far fa-clock"></i> ' + status + '</span>';
                    }
                    return data;
                }
            },
            {"data": 'user_id.nama', "class": "tbl-center"},
            {
                "data": 'times',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    let datetime = data.split("T");
                    if (type == 'display') {
                        let indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
                        let date = datetime[0].split("-")[2];
                        let month = datetime[0].split("-")[1];
                        let year = datetime[0].split("-")[0];
                        data = date + " " + indoMonth[parseInt(month)] + " " + year;
                    }
                    // data = datetime[0];
                    return data;
                }
            },
            {
                "data": 'times',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    let datetime = data.split("T");
                    if (type == 'display') {
                        data = datetime[1].substr(0, 8);
                    }
                    return data;
                }
            },
            {"data": 'location_clock', "class": "tbl-center"},
            {"data": 'level_kesehatan_fisik_id', "class": "tbl-center"},
            {"data": 'level_kesehatan_mental_Id', "class": "tbl-center"},
            {
                "data": 'coordinate',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        data = data.replace(" ", "");
                        data = '<a href="https://maps.google.com/maps?q=' + data + '" target="_blank" class="btn btn-primary"><i class="fas fa-map-marked-alt"></i></a>';
                    }
                    return data;
                }
            },
            {
                "data": 'url_foto_clock',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        let id = data;
//                        id = id.replace("/", ":8080/");
                        data = '<a id="' + id + '" href="#" class="btn btn-primary finger-pointer" data-toggle="modal" data-target="#imageClockModal" data-link="' + id + '"><i class="fas fa-eye"></i></a>';

                    }
                    return data;
                }
            }
        ],
        "order": [[3, 'desc']],
        "columnDefs": [{
            "targets": [0, 8, 9],
            "orderable": false
        }]
    });
    t.on('order.dt search.dt', function () {
        t.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
            t.cell(cell).invalidate('dom');
        });
    });
    $('#min, #max, #statusClock').change(function () {
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
