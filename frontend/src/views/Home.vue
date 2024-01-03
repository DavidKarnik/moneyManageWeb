<template>
<!--    <header>-->
        <div>
            <HeaderComp/>
        </div>
<!--    </header>-->

    <main>
        <p>Body page</p>
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

</style>