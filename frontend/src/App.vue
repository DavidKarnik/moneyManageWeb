<script setup>
import HeaderComp from './components/HeaderComponent.vue'
import DashboardComp from './components/DashboardComponent.vue'
</script>

<!--<template>-->
<!--&lt;!&ndash;    <header>&ndash;&gt;-->
<!--        <div>-->
<!--            <HeaderComp/>-->
<!--        </div>-->
<!--&lt;!&ndash;    </header>&ndash;&gt;-->

<!--    <main>-->
<!--        <p>Body page</p>-->
<!--        <DashboardComp/>-->
<!--    </main>-->
<!--</template>-->

<template>
    <div>
        <router-view></router-view>
    </div>
</template>


<!-- scoped -> styl je omezený jen na tuto konkrétní komponentu-->
<style scoped>

</style>
