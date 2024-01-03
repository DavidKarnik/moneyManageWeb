// src/main.js
import { createApp } from 'vue';
import App from './App.vue';
import router from './api';

import './assets/global.css';

createApp(App).use(router).mount('#app');
