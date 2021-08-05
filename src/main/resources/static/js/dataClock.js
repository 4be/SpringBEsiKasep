var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";

$(document).ready(function() {
    $('#dataClockTable').DataTable({
        "scrollX": true,
        "ajax": {
            "url": '35.209.242.226/api/clockin',
            "dataType": "json",
            "type": "GET",
            "processing": true,
            "serverSide": true,
            "beforeSend": function (xhr) {
                xhr.setRequestHeader("Access-Control-Allow-Origin", "http://35.209.242.226/");
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            columns: [
                { data: 'id' },
                { data: 'user_id' },
                { data: 'price' }
            ],
        }
    });
});
