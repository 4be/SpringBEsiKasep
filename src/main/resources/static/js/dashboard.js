// clock in count
$.ajax({
    url: "/api/status/result/1",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        $('#clockInCount').html(result.data);
    },
    error: function (result) {
        if (result.status == 401) {
            alert(result.responseJSON.message);
            localStorage.removeItem("token");
            location.href = "/";
        }
    }
});
// clock out count
$.ajax({
    url: "/api/status/result/0",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        $('#clockOutCount').html(result.data);
    },
    error: function (result) {
        if (result.status == 401) {
            console.log(result);
        }
    }
});

//chart

function generateRGBA(r, g, b, a) {
    return 'rgba(' + r.toString() + ',' + g.toString() + ',' + b.toString() + ',' + a.toString() + ')';
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function cvs_pie(cvsid, xlabel, val) {
    var ctx = $(cvsid);
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: xlabel,
            datasets: [{
                data: val,
                backgroundColor: [generateRGBA(49, 247, 66, 0.7), generateRGBA(230, 42, 9, 0.7)],
                hoverBackgroundColor: [generateRGBA(49, 247, 66, 0.9), generateRGBA(230, 42, 9, 0.9)],
                hoverBorderColor: "rgba(234, 236, 244, 1)",
            }],
        },
        radius:  0.1,
        options: {
            maintainAspectRatio: false,
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
            },
            legend: {
                display: true
            }
        },
    });
}

function cvs_bar(cvsid, data) {
    var ctx = $(cvsid);
    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"],
            datasets: data,
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    position: "left",
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1
                    },
                    scaleLabel: {
                        display: true,
                        labelString: 'Total Clock'
                    },

                }],
                xAxes: [{
                    position: "bottom",
                    ticks: {
                        beginAtZero: true
                    },
                    scaleLabel: {
                        display: true,
                        labelString: 'Bulan'
                    },
                }]
            },
            responsive: true,
            legend: {
                display: true
            },
            tooltips: {
                mode: 'index',
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
            },
        }
    });
}

// data clock last month
$.ajax({
    url: "/api/clock/totalbylastmonth/",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        let key = ['Clock In', 'Clock Out'];
        let val = [parseInt(result[0].split(",")[0]), parseInt(result[0].split(",")[1])];
        cvs_pie('#clockLastMonth', key, val);
    },
    error: function (result) {
        if (result.status == 401) {
            console.log(result);
        }
    }
});

// data clock this month
$.ajax({
    url: "/api/clock/totalbymonth/",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        let key = ['Clock In', 'Clock Out'];
        let val = [parseInt(result[0].split(",")[0]), parseInt(result[0].split(",")[1])];
        cvs_pie('#clockThisMonth', key, val);
    },
    error: function (result) {
        if (result.status == 401) {
            console.log(result);
        }
    }
});

// data clock this year
function clockThisYear() {
    let valClockIn = [], valClockOut = [];
    for (let i = 1; i <= 12; i++) {
        $.ajax({
            url: "/api/clock/total/month/" + i.toString(),
            type: "GET",
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                valClockIn[i] = parseInt(result[0].split(",")[0]);
                valClockOut[i] = parseInt(result[0].split(",")[1]);
            },
            error: function (result) {
                if (result.status == 401) {
                    console.log(result);
                }
            }
        });
    }
    let output = [{
        label: "Clock In",
        backgroundColor: generateRGBA(49, 247, 66, 0.7),
        hoverBackgroundColor: generateRGBA(49, 247, 66, 0.9),
        borderColor: generateRGBA(49, 247, 66, 0.9),
        data: valClockIn,
    }, {
        label: "Clock Out",
        backgroundColor: generateRGBA(230, 42, 9, 0.7),
        hoverBackgroundColor: generateRGBA(230, 42, 9, 0.9),
        borderColor: generateRGBA(230, 42, 9, 0.9),
        data: valClockOut,
    }];
    setTimeout(() => {
        cvs_bar('#clockThisYear', output);
    }, 1000);
    let ctx = $("#clockThisYear").get(0).getContext("2d");
    ctx.width = 10;
    ctx.height = 10;
}

clockThisYear();

// data clock this year
function keteranganThisYear() {
    let keterangan = [];
    for (let i = 1; i <= 12; i++) {
        $.ajax({
            url: "/api/keterangan/total/month/" + i.toString(),
            type: "GET",
            headers: {Authorization: localStorage.getItem("token")},
            success: function (result) {
                keterangan[i] = parseInt(result[0]);
                ;
            },
            error: function (result) {
                if (result.status == 401) {
                    console.log(result);
                }
            }
        });
    }
    let output = [{
        label: "Keterangan Sakit",
        backgroundColor: generateRGBA(242, 27, 12, 0.7),
        hoverBackgroundColor: generateRGBA(242, 27, 12, 0.9),
        borderColor: generateRGBA(242, 27, 12, 0.9),
        data: keterangan,
        categoryPercentage: 0.25,
        barPercentage: 0.5
    }];
    setTimeout(() => {
        cvs_bar('#keteranganThisYear', output);
    }, 1000);
}

keteranganThisYear();


