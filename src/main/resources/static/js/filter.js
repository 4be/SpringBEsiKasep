function parseDateValue(rawDate) {
    if (rawDate == '') {
        return "";
    } else {
        const dateArray = rawDate.split("-");
        const parsedDate = new Date(parseInt(dateArray[0]), parseInt(dateArray[1]) - 1, parseInt(dateArray[2]));  // -1 because months are from 0 to 11
        return parsedDate;
    }
}

$(document).ready(function () {
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
    $('#imageClockModal').on('shown.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('link');
        $('#imageClock').attr('src', 'http://' + id);
        $('#downloadImageClock').attr('download', id.split("/")[2]);
        $('#downloadImageClock').attr('target', '_blank');
        $('#downloadImageClock').attr('href', 'http://' + id);
    });
});
