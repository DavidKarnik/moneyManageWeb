<!-- DashboardComponent.vue -->

<template>
    <div>
        <h2>Account Tiles</h2>
        <TileComponent
                v-for="account in accounts"
                :id="account.id"
                :nameOfAccount="account.nameOfAccount"
                :balance="account.balance"
        />
    </div>
</template>

<script setup>
import axios from 'axios';
import TileComponent from './TileComponent.vue';
import {onMounted, ref} from "vue";

const accounts = ref([]);

onMounted(async () => {
    try {
        // Načtení dat z backendu (předpokládejme, že backend poskytuje API na /api/accounts)
        const response = await axios.get('http://localhost:8080/api/collections/john.doe@example.com');
        accounts.value = response.data;
    } catch (error) {
        console.error('Error loading accounts:', error);
    }
});
</script>
