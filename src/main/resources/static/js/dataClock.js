$(document).ready(function () {
    function parseDateValue (rawDate) {
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
            var current = parseDateValue(data[2]);
            var flag = false;
            if( (min == '' && max == '') ||
                (min <= current && max == '') ||
                (min == '' && current <= max) ||
                (min <= current && current <= max)) {
                flag = true;
            } else {
                flag = false;
            }
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
        "ajax": {
            "url": '/api/clockin',
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            "dataSrc": function (result) {
                var datetime, time, date, month, year;
                var indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
                for (var i = 0; i < result.length; i++) {
                    // orderable desc time
                    result[i].time = result[i].start_time;
                    // date
                    datetime = result[i].start_time.split("T");
                    result[i].date = datetime[0];
                    // start_time
                    // time = datetime[1];
                    // result[i].start_time = time.substr(0, 8);
                    // end_time
                    if (result[i].end_time == null) {
                        result[i].end_time = '-';
                    }
                    // location_clockout
                    if (result[i].location_clockout == null) {
                        result[i].location_clockout = '-';
                    }
                }
                console.log(result);
                return result;
            }
            //  "beforeSend": function (xhr) {
            //      xhr.setRequestHeader("Access-Control-Allow-Origin", "http://35.209.242.226/");
            //      xhr.setRequestHeader("Authorization", "Bearer " + token);
            //  },
        },
        "columns": [
            {"data": null, "class": "tbl-center"},
            {"data": 'user_id.nama', "class": "tbl-center"},
            {
                "data": 'date',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        var datetime = data.split("T");
                        var indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
                        var date = datetime[0].split("-")[2];
                        var month = datetime[0].split("-")[1];
                        var year = datetime[0].split("-")[0];
                        data = date + " " + indoMonth[parseInt(month)] + " " + year;
                    }
                    return data;
                }
            },
            {
                "data": 'start_time',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    if (type == 'display') {
                        var datetime = data.split("T");
                        var time = datetime[1];
                        data = time.substr(0, 8);
                    }
                    return data;
                }
            },
            {"data": 'location_clockin', "class": "tbl-center"},
            {
                "data": 'end_time',
                "class": "tbl-center",
                "render": function(data, type, row, meta) {
                    if (type == 'display') {
                        if (data != null) {
                            var time = data.split("T")[1];
                            data = time.substr(0, 8);
                        } else {
                            data = '-';
                        }
                    }
                    return data;

                }
            },
            {"data": 'location_clockout', "class": "tbl-center"},
            {"data": 'level_kesehatan_fisik_id', "class": "tbl-center"},
            {"data": 'level_kesehatan_mental_Id', "class": "tbl-center"},
            {"data": 'time', "visible": false}
        ],
        "order": [[9, 'desc']]
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
    t.on('order.dt search.dt', function () {
        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
            t.cell(cell).invalidate('dom');
        });
    }).draw();
    $('#min, #max').change(function () {
        t.draw();
    });
    $('#btnClear').on('click',() => {
        $('#min').val("");
        $('#max').val("");
        t.draw();
    });
    $('#btnToday').on('click',function () {
        var now = new Date();
        var dateNow = now.toISOString().split('T')[0];
        $('#min').val(dateNow);
        $('#max').val(dateNow);
        t.draw();
    });
});
