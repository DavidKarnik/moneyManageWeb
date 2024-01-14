<template>
    <div>
        <h2>Transaction History</h2>
        <div>
            <canvas ref="chart"></canvas>
        </div>
    </div>
</template>

<script setup>
import Chart from 'chart.js/auto';
import axios from "axios";
import {onMounted} from "vue";

onMounted(async () => {
    try {
        const response = await axios.get(`http://localhost:8080/api/transactions/${accountId}`);
        const transactions = response.data;

        // Zde použijte knihovny pro grafy (Chart.js) k vykreslení grafu
        const ctx = this.$refs.chart.getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: transactions.map(transaction => transaction.date),
                datasets: [{
                    label: 'Balance',
                    data: transactions.map(transaction => transaction.balance),
                    borderColor: 'blue',
                    fill: false,
                }],
            },
        });
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
});
</script>
