$(document).ready(function () {
    function parseDateValue(rawDate) {
        if (rawDate == '') {
            return "";
        } else {
            const dateArray = rawDate.split("-");
            const parsedDate = new Date(parseInt(dateArray[0]), parseInt(dateArray[1]) - 1, parseInt(dateArray[2]));  // -1 because months are from 0 to 11
            return parsedDate;
        }
    }

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
            } else {
                flag = false;
            }
            console.log(data[3] + " " + current);
            return flag;
        }
    );
    $('#min').datepicker({
        uiLibrary: 'bootstrap',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'id',
        immediateUpdates: true,
        todayHighlight: true
    });
    $('#max').datepicker({
        uiLibrary: 'bootstrap',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'id',
        immediateUpdates: true,
        todayHighlight: true
    });
    // datatable implementation
    var t = $('#dataClockTable').DataTable({
        dom: 'Bfrtip',
        buttons: [{
            text: "Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }],
        "ajax": {
            "url": '/api/clock/desc',
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            "dataSrc": function (result) {
                for(var i=0;i<result.length;i++) {
                    if(result[i].working == true) {
                        result[i].working = 'clock in';
                    } else {
                        result[i].working = 'clock out';
                    }
                }
                return result;
            }
            //  "beforeSend": function (xhr) {
            //      xhr.setRequestHeader("Access-Control-Allow-Origin", "http://35.209.242.226/");
            //      xhr.setRequestHeader("Authorization", "Bearer " + token);
            //  },
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
                            status = 'Clock In';
                        } else if(data == 'clock out') {
                            btnColor = 'danger';
                            status = 'Clock Out';
                        }
                        data = '<span class="badge badge-pill badge-' + btnColor + '">' + status + '</span>';
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
                    if (type == 'display') {
                        let datetime = data.split("T");
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
                        // id = id.replace("/", ":8080/");
                        data = '<a id="' + id + '" href="#" class="btn btn-primary finger-pointer" data-toggle="modal" data-target="#imageClockModal" data-link="'+id+'"><i class="fas fa-eye"></i></a>';

                    }
                    return data;
                }
            }
        ],
        "order": [[3, 'desc']]
        // "initComplete": function () {
        //     this.api().columns('.tbl-dropdown-filter').every(function () {
        //         var column = this;
        //         var select = $('<select><option value="">Semua</option></select>')
        //             .appendTo($(column.header()))
        //             .on('change', function (e) {
        //                 e.preventDefault();
        //                 var val = $.fn.dataTable.util.escapeRegex($(this).val());
        //                 column.search(val ? '^' + val + '$' : '', true, false).draw();
        //             });
        //         column.data().unique().sort().each(function (d, j) {
        //             select.append('<option value="' + d + '">' + d + '</option>');
        //         });
        //     });
        // }
    });
    t.on('draw.dt', function () {
        var PageInfo = $('#dataClockTable').DataTable().page.info();
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
    $('#imageClockModal').on('shown.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('link');
        $('#imageClock').attr('src', 'http://' + id);
        $('#downloadImageClock').attr('download', 'http://' + id);
        $('#downloadImageClock').attr('target', '_blank');
        $('#downloadImageClock').attr('href', 'http://' + id);
    })
});
