<template>
    <HeaderComp/>
    <div>
        <h1>Collection Page</h1>
        <p>ID: {{ $route.params.id }}</p>
        <!-- Použití hodnoty v JavaScriptu -->
        <button @click="doSomethingWithId">Do Something</button>
    </div>
</template>

<script>
import HeaderComp from "@/components/HeaderComponent.vue";

export default {
    name: "Collection",
    components: {HeaderComp},
    methods: {
        doSomethingWithId() {
            const id = this.$route.params.id;
            // Zde můžete provádět další operace s proměnnou "id"
            console.log("ID:", id);
        }
    }
};
</script>

<style scoped>
/* Styly pro komponentu */
</style>
