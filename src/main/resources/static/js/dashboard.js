// clock in count
$.ajax({
    url: "/api/status/result/1",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        // var clockin = $(result).filter(function (i, n) {
        //     return n.working == true;
        // }).length;
        // var clockout = $(result).filter(function (i, n) {
        //     return n.working == false;
        // }).length;
        $('#clockInCount').html(result.data);
    },
    error: function (result) {
        if (result.status == 401) {
            console.log(result);
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
function int_random(min, max) {
    var res = Math.random() * (max - min) + min;
    return parseInt(res);
}

let r4, r5, r6;

function rgb_random() {
    var r1 = int_random(0, 255);
    var r2 = int_random(0, 255);
    var r3 = int_random(0, 255);
    if (r1 >= 20) {
        r4 = r1 - 20;
    } else {
        r4 = r1 + 20;
    }
    if (r2 >= 20) {
        r5 = r2 - 20;
    } else {
        r5 = r2 + 20;
    }
    if (r3 >= 20) {
        r6 = r3 - 20;
    } else {
        r6 = r3 + 20;
    }
    var rgb1 = 'rgb(' + r1.toString() + ',' + r2.toString() + ',' + r3.toString() + ')';
    return rgb1;
}

function rgb_random2() {
    var rgb2 = 'rgb(' + r4.toString() + ',' + r5.toString() + ',' + r6.toString() + ')';
    return rgb2;
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
function cvs_pie(cvsid, xlabel, val, bc, hbc) {
    var ctx = $(cvsid);
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: xlabel,
            datasets: [{
                data: val,
                backgroundColor: bc,
                hoverBackgroundColor: hbc,
                hoverBorderColor: "rgba(234, 236, 244, 1)",
            }],
        },
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

function cvs_bar(cvsid, xlabel, val, bc, hbc) {
    var ctx = $(cvsid);
    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["January", "February", "March", "April", "May", "June"],
            datasets: [{
                label: "Revenue",
                backgroundColor: "#4e73df",
                hoverBackgroundColor: "#2e59d9",
                borderColor: "#4e73df",
                data: [4215, 5312, 6251, 7841, 9821, 14984],
            }],
        },
        options: {
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    time: {
                        unit: 'month'
                    },
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    ticks: {
                        maxTicksLimit: 6
                    },
                    maxBarThickness: 25,
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: 15000,
                        maxTicksLimit: 5,
                        padding: 10,
                        // Include a dollar sign in the ticks
                        callback: function(value, index, values) {
                            return '$' + number_format(value);
                        }
                    },
                    gridLines: {
                        color: "rgb(234, 236, 244)",
                        zeroLineColor: "rgb(234, 236, 244)",
                        drawBorder: false,
                        borderDash: [2],
                        zeroLineBorderDash: [2]
                    }
                }],
            },
            legend: {
                display: false
            },
            tooltips: {
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
                callbacks: {
                    label: function(tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                    }
                }
            },
        }
    });
}

// data clock last month
$.ajax({
    url: "/json/clockLastMonth.json",
    type: "GET",
    headers: {Authorization: localStorage.getItem("token")},
    success: function (result) {
        let key = ['Clock In','Clock Out'];
        let val = [result.data.clockin,result.data.clockout];
        let background = [],hoverBackground = [];
        for(let i=0;i<2;i++) {
            background[i] = rgb_random();
            hoverBackground[i] = rgb_random2();
        }
        cvs_pie('#clockLastMonth', key, val, background, hoverBackground);
    },
    error: function (result) {
        if (result.status == 401) {
            console.log(result);
        }
    }
});
