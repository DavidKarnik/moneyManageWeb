<template>
    <HeaderComp/>
    <div class="collection-page">
        <div class="collection-info">
            <h1>Collection Page</h1>
            <p>ID: {{ $route.params.id }}</p>
            <!-- Použití hodnoty v JavaScriptu -->
            <button @click="doSomethingWithId">Do Something</button>
        </div>
        <ChartComponent></ChartComponent>
    </div>
</template>

<script>
import HeaderComp from "@/components/HeaderComponent.vue";
import ChartComponent from "@/components/ChartComponent.vue";

export default {
    name: "Collection",
    components: {ChartComponent, HeaderComp},
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
.collection-page {
    display: flex; /* Použijeme flexbox layout model */
}

.collection-info {
    /*flex: 1; !* Rovnoměrné rozdělení místa s potomky *!*/
    margin-right: 20px; /* Přidáme odstup mezi prvky, aby nebyly příliš blízko */
}
</style>
