var data = null;
$(document).ready(function () {
    // $.ajax({
    //     url: "http://35.209.242.226/api/stories/list",
    //     type: "GET",
    //     headers: {Authorization: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNMDAwMDMiLCJpYXQiOjE2Mjg1Nzg4NzcsImV4cCI6MTYyODY2NTI3N30.Eeg7192C_iguAjbaHa8q2Ee-54mLHqxY6t6Lq-kse-SVHMys6JJCB82fqVQIflmjorlC6Y1oLWrQowHesHC05Q"},
    //     success: function (result) {
    //         data = {data: result};
    //     },
    //     // error: function () {
    //     //     location.href = "/";
    //     // }
    // });
    // $('#dataStories thead tr').clone(true).appendTo('#dataStories thead');

    var table = $('#dataStories').DataTable({
        dom: 'Bfrtip',
        buttons: [{
            text: "Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3, 4, 5, 6, 7]
            }
        }],
        ajax: {
            url: "http://35.209.242.226/api/stories/list",
            type: "GET",
            data: "data",
            headers: {Authorization: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNMDAwMDMiLCJpYXQiOjE2Mjg1Nzg4NzcsImV4cCI6MTYyODY2NTI3N30.Eeg7192C_iguAjbaHa8q2Ee-54mLHqxY6t6Lq-kse-SVHMys6JJCB82fqVQIflmjorlC6Y1oLWrQowHesHC05Q"},
            // error: function () {
            //     location.href = "/";
            // }
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
            {data: "url_foto_stories"},
            {data: "description"},
            {data: "date_published"},
            {data: "user_id.nama"}
        ],
    });
    table.on('draw.dt', function () {
        var PageInfo = $('#dataStories').DataTable().page.info();
        table.column(0, {page: 'current'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1 + PageInfo.start;
        });
    });
});
