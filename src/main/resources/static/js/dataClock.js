var token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2OSIsImlhdCI6MTYyODA0ODA4OCwiZXhwIjoxNjI4MTM0NDg4fQ.xmosnnHJGUWrts0trjbsR5P4YTuja1-z3PrMdGdG9OirFy7oUKqJoQMtQERIxCi3TggvDDAJj9tz0dgR1xcOPw";

$(document).ready(function () {
    $.ajax({
        url: "localhost:8080/api/role/",
        type: "GET",
        success: function (result) {
            console.log(result);
        },
        error: function (result) {
            console.log(result);
        }
    })
});
