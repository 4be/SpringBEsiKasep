//var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";
var data = null;
$(document).ready(function () {
    $.ajax({
        url: "/api/keterangan/list",
        type: "GET",
        headers: {Authorization: localStorage.getItem("token")},
        success: function (result) {
            data = {data: result};
        },
        error: function () {
            location.href = "/";
        }
    });

    $('#dataKeteranganTable thead tr').clone(true).appendTo('#dataKeteranganTable thead');
    // Search mode
//    $('#dataClockTable thead tr:eq(1) th').each( function (i) {
//        var title = $(this).text();
//        var placeholder = "Cari "+title+"...";
//        var width = placeholder.length;
//        $(this).html('<input type="text" placeholder="'+placeholder+'" size="'+width+'" />');
//        $( 'input', this ).on( 'keyup change', function () {
//            if ( table.column(i).search() !== this.value ) {
//                table
//                    .column(i)
//                    .search( this.value )
//                    .draw();
//            }
//        });
//    });
    // datatable implementation
    var table = $('#dataKeteranganTable').DataTable({
        dom: 'Bfrtip',
        buttons: [{
            text: "Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }],
        "ajax": {
            "url": '/api/keterangan/list',
            "dataSrc": data,
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            error: function (result) {
                if (result.status == 401) {
                    location.href = "/";
                }
            }
//            "beforeSend": function (xhr) {
//                xhr.setRequestHeader("Access-Control-Allow-Origin", "http://35.209.242.226/");
//                xhr.setRequestHeader("Authorization", token);
//            },
        },
        "columns": [
            {"data": 'id'},
            {"data": 'user_id'},
            {"data": 'start_date'},
            {"data": 'end_date'},
            {"data": 'description'},
            {"data": 'files'},
        ],
        "columnDefs": [{
            "targets": 1,
            "render": function (data, type, full, meta) {
                var res = '-';
                if (type === 'display') {
                    if (data != null) {
                        res = data;
                    }
                }
                return res;
            }
        }, {
            "targets": 5,
            "render": function (data, type, full, meta) {
                if (type === 'display') {
                    data = '<a href="' + data + '"><button class="btn btn-success"><i class="fas fa-download"></i></button></a>';
                }
                return data;
            }
        }],
        //Select box mode
        "initComplete": function () {
            this.api().columns().every(function (index) {
                if (index < 5) {
                    var column = this;
                    var select = $('<select><option value="">Semua</option></select>')
                        .appendTo($(column.header()).empty())
                        .on('change', function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            column
                                .search(val ? '^' + val + '$' : '', true, false)
                                .draw();
                        });
                    column.data().unique().sort().each(function (d, j) {
                        select.append('<option value="' + d + '">' + d + '</option>');
                    });
                } else {
                    $('Download')
                        .appendTo($(column.header()).empty());
                }
            });
        }
    });
});
