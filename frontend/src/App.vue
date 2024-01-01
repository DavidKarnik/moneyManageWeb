<script setup>
import HeaderComp from './components/HeaderComponent.vue'
import DashboardComp from './components/DashboardComponent.vue'
</script>

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

<!-- scoped -> styl je omezený jen na tuto konkrétní komponentu-->
<style scoped>

</style>
