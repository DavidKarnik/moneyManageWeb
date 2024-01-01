<!-- TileComponent.vue -->

<template>
    <div class="tile">
        <h3>{{ nameOfAccount }}</h3>
        <p>ID: {{ id }}</p>
        <p>Balance: {{ balance }}</p>
    </div>
</template>

<script setup>
defineProps(['id', 'nameOfAccount', 'balance']);
</script>

<style scoped>
.tile {
    border: 1px solid #ccc;
    padding: 10px;
    margin: 10px;
    width: 200px;
}
</style>
