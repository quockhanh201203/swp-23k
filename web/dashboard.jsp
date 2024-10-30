<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="css/styles.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
            font-family: Arial, sans-serif;
            margin: 20px;
            }

            h1, h2 {
            color: #333;
            }

            canvas {
            max-width: 800px;
            margin: 20px auto;
            }
        </style>
    </head>
    <body>
        <h1>Dashboard</h1>

        <h2>Doanh thu theo thời gian</h2>
        <canvas id="revenueChart"></canvas>

        <h2>Doanh thu theo loại sản phẩm</h2>
        <canvas id="productRevenueChart"></canvas>

        <h2>Số lượng đơn hàng theo trạng thái</h2>
        <canvas id="orderStatusChart"></canvas>

        <h2>Lịch sử lương</h2>
        <canvas id="salaryChart"></canvas>

        <script>
            // Dữ liệu doanh thu theo thời gian
            const revenueDates = ${revenueDates};
            const revenueValues = ${revenueValues};
            const revenueChart = new Chart(document.getElementById('revenueChart'), {
            type: 'line',
                    data: {
                    labels: revenueDates,
                            datasets: [{
                            label: 'Doanh thu',
                                    data: revenueValues,
                                    borderColor: 'rgba(75, 192, 192, 1)',
                                    fill: false,
                            }]
                    }
            });
            // Dữ liệu doanh thu theo loại sản phẩm
            const productTypes = ${productTypes};
            const productTotals = ${productTotals};
            const productRevenueChart = new Chart(document.getElementById('productRevenueChart'), {
            type: 'bar',
                    data: {
                    labels: productTypes,
                            datasets: [{
                            label: 'Doanh thu theo loại sản phẩm',
                                    data: productTotals,
                                    backgroundColor: 'rgba(255, 99, 132, 1)',
                            }]
                    }
            });
            // Dữ liệu số lượng đơn hàng theo trạng thái
            const orderStatuses = ${orderStatuses};
            const orderCounts = ${orderCounts};
            const orderStatusChart = new Chart(document.getElementById('orderStatusChart'), {
            type: 'bar',
                    data: {
                    labels: orderStatuses,
                            datasets: [{
                            label: 'Số lượng đơn hàng',
                                    data: orderCounts,
                                    backgroundColor: 'rgba(54, 162, 235, 1)',
                            }]
                    }
            });
            // Dữ liệu lịch sử lương
            const salaryDates = ${salaryDates};
            const salaryValues = ${salaryValues};
            const salaryChart = new Chart(document.getElementById('salaryChart'), {
            type: 'line',
                    data: {
                    labels: salaryDates,
                            datasets: [{
                            label: 'Lịch sử lương',
                                    data: salaryValues,
                                    borderColor: 'rgba(255, 206, 86, 1)',
                                    fill: false,
                            }]
                    }
            });
        </script>
    </body>
</html>