<template>
    <p>ID: {{ collectionId }}</p>
    <p id="balance">Balance: 0</p>
    <p id="highest">Highest: 0</p>
    <p id="lowest">Lowest: 0</p>
</template>

<script setup>
import {onMounted} from 'vue';
import axios from "axios";

const props = defineProps(['collectionId']);

let currentBalance = 0;

onMounted(async () => {
    try {
        const email = 'john.doe@example.com';
        // const collectionId = 'f6681d5d-1ab3-4213-b45c';
        // const collectionId = '3a268b02-881f-409b-b22e';
        const responseBalance = await axios.get(
            `http://localhost:8080/api/collectionInfos?email=${email}&collectionId=${props.collectionId}`
        );
        currentBalance = responseBalance.data;

        const paragraph = document.getElementById("balance");
        paragraph.textContent = "Balance: " + currentBalance;
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
});

function getCurrentBalance() {

}
</script>

<style scoped>
/* Your scoped styles */
</style>
