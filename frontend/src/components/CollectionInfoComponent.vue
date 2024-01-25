<template>
    <h2>Collection Info</h2>
    <div>
        <p>ID: {{ props.collectionId }}</p>
        <p id="balance">Balance: {{ currentBalance }}</p>
        <p>Highest Balance: {{ highestBalance.balance }} (on {{ highestBalance.date }})</p>
        <p>Lowest Balance: {{ lowestBalance.balance }} (on {{ lowestBalance.date }})</p>
    </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';

const props = defineProps(['collectionId']);

let currentBalance = ref(0);
let highestBalance = ref({});
let lowestBalance = ref({});

onMounted(async () => {
    try {
        const email = 'john.doe@example.com';

        const responseBalance = await axios.get(
            `http://localhost:8080/api/currentBalance?email=${email}&collectionId=${props.collectionId}`
        );
        currentBalance.value = responseBalance.data;

        const responseHighest = await axios.get(
            `http://localhost:8080/api/highestBalance?email=${email}&collectionId=${props.collectionId}`
        );
        highestBalance.value = responseHighest.data;

        const responseLowest = await axios.get(
            `http://localhost:8080/api/lowestBalance?email=${email}&collectionId=${props.collectionId}`
        );
        lowestBalance.value = responseLowest.data;

        // Aktualizace HTML obsahu pomocí JavaScriptu nebo dalších metod, jak vám vyhovuje
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});
</script>
