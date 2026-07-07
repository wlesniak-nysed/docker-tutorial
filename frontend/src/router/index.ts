import { createRouter, createWebHistory } from 'vue-router';
import Home from "@/views/Home.vue";

const router = createRouter({
  history: createWebHistory(globalThis.location.pathname.split('/')[1]),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    }
  ]
});

export default router;
