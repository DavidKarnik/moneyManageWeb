<template>
    <HeaderComp/>
    <main>
        <h1>Collection Page</h1>
        <div class="collection-page">
            <div class="collection-info">
                <CollectionInfoComponent :collectionId="$route.params.id"></CollectionInfoComponent>
                <!-- Použití hodnoty v JavaScriptu -->
                <button @click="doSomethingWithId">Do Something</button>
            </div>
            <ChartComponent :collectionId="$route.params.id"></ChartComponent>
        </div>
        <TransactionPostComponent></TransactionPostComponent>
    </main>
</template>

<script>
import HeaderComp from "@/components/HeaderComponent.vue";
import ChartComponent from "@/components/ChartComponent.vue";
import CollectionInfoComponent from "@/components/CollectionInfoComponent.vue";
import TransactionPostComponent from "@/components/TransactionPostComponent.vue";

export default {
    name: "Collection",
    components: {CollectionInfoComponent, ChartComponent, HeaderComp, TransactionPostComponent},
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

main {
    padding: 10px;
}

/* Styly pro komponentu */
.collection-page {
    display: flex; /* Použijeme flexbox layout model */
}

.collection-info {
    /*flex: 1; !* Rovnoměrné rozdělení místa s potomky *!*/
    margin-right: 20px; /* Přidáme odstup mezi prvky, aby nebyly příliš blízko */
}
</style>
