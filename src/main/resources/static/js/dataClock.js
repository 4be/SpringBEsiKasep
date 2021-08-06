//var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";
var data = null;
$(document).ready(function () {
    $.ajax({
        url: "/api/clockin",
        type: "GET",
        headers: {Authorization: localStorage.getItem("token")},
        success: function (result) {
            data = {data: result};
        },
        error: function () {
            location.href = "/";
        }
    });

    $('#dataClockTable thead tr').clone(true).appendTo('#dataClockTable thead');
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
    var table = $('#dataClockTable').DataTable({
        "ajax": {
            "url": '/api/clockin',
            "dataSrc": data,
            "type": "GET",
            "headers": {Authorization: localStorage.getItem("token")},
            //  "beforeSend": function (xhr) {
            //      xhr.setRequestHeader("Access-Control-Allow-Origin", "http://35.209.242.226/");
            //      xhr.setRequestHeader("Authorization", "Bearer " + token);
            //  },
        },
        "columns": [
            {"data": 'id'},
            {"data": 'user_id'},
            {"data": 'start_time'},
            {"data": 'start_time'},
            {"data": 'location_clockin'},
            {"data": 'end_time'},
            {"data": 'location_clockout'},
            {"data": 'level_kesehatan_fisik_id'},
            {"data": 'level_kesehatan_mental_Id'},
        ],
        //Select box mode
        "initComplete": function () {
            this.api().columns().every(function () {
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
            });
        }
    });
});
