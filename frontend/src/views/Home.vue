<template>
<!--    <header>-->
        <div>
            <HeaderComp/>
        </div>
<!--    </header>-->

    <main>
        <h1>Body page</h1>
        <DashboardComp/>
    </main>
</template>


<script>
import HeaderComp from "@/components/HeaderComponent.vue";
import DashboardComp from "@/components/DashboardComponent.vue";

export default {
    name: "Home",
    components: {DashboardComp, HeaderComp}
}
</script>

<style scoped>
main {
    padding: 10px;
}
</style>