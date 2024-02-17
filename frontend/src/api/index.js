// src/api/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import Login from '@/views/Login.vue';
import Collection from "@/views/Collection.vue";
import NewDashboard from "@/views/NewDashboard.vue";

const routes = [
    {
        path: '/home',
        component: Home,
    },
    {
        path: '/',
        component: Login,
    },
    {
        path: '/collection/:id',
        name: 'Collection',
        component: Collection,
    },
    {
        path: '/new',
        component: NewDashboard,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
