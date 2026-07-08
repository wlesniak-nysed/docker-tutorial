import { createRouter, createWebHistory } from 'vue-router';
import Home from "@/views/Home.vue";
import Users from "@/views/Users.vue";
import ProjectMemberships from "@/views/ProjectMemberships.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/users',
      name: 'Users',
      component: Users,
    },
    {
      path: '/project-memberships',
      name: 'ProjectMemberships',
      component: ProjectMemberships,
    }
  ]
});

export default router;
