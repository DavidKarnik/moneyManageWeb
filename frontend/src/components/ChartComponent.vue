<template>
    <div>
        <h2>Transaction History</h2>
        <canvas ref="chartCanvas"></canvas>
    </div>
</template>

<script setup>
import Chart from 'chart.js/auto';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const chartCanvas = ref(null);

// Přidejte prop pro předání ID
const props = defineProps(['collectionId']);

onMounted(async () => {
    try {
        const email = 'john.doe@example.com';
        // const collectionId = 'f6681d5d-1ab3-4213-b45c';
        // const collectionId = '3a268b02-881f-409b-b22e';
        const response = await axios.get(
            `http://localhost:8080/api/balance?email=${email}&collectionId=${props.collectionId}`
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
                        fill: true,
                    },
                ],
            },
        });
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
});
</script>

<style scoped>

canvas {
    width: 100vh !important;
    /*height: 25vh;*/
}
</style>