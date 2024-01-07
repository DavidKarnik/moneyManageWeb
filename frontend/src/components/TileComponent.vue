<!-- TileComponent.vue -->

<template>
    <div class="tile" @click="redirectToTile">
        <h3>{{ nameOfAccount }}</h3>
        <p>ID: {{ id }}</p>
        <p>Balance: {{ balance }}</p>
    </div>
</template>

<script setup>
const props = defineProps(['id', 'nameOfAccount', 'balance']);

import { useRouter } from 'vue-router';

const router = useRouter();

// const redirectToTile = () => {
//     // router.push(`/api/tile/${props.id}`);
//     router.push(`/collection/${props.id}`);
// };

const redirectToTile = () => {
    router.push(`/collection/${props.id}`);
};

</script>

<style scoped>
@import '@/assets/dashboardTiles.css';
</style>