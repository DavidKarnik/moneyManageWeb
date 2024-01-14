<template>
    <div>
        <h2>Transaction History</h2>
        <div>
            <canvas ref="chartCanvas"></canvas>
        </div>
    </div>
</template>

<script setup>
import Chart from 'chart.js/auto';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const chartCanvas = ref(null);

onMounted(async () => {
    try {
        const email = 'john.doe@example.com';
        const collectionId = 'f6681d5d-1ab3-4213-b45c';
        const response = await axios.get(
            `http://localhost:8080/api/transactions?email=${email}&collectionId=${collectionId}`
        );
        const transactions = response.data;

        // Získání referenčního objektu po vytvoření DOM
        const ctx = chartCanvas.value.getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: transactions.map((transaction) => transaction.date),
                datasets: [
                    {
                        label: 'Balance',
                        data: transactions.map((transaction) => transaction.balance),
                        borderColor: 'blue',
                        fill: false,
                    },
                ],
            },
        });
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
});
</script>
