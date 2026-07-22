import { createRouter, createWebHistory } from 'vue-router';
import Home from "@/views/Home.vue";
import Users from "@/views/Users.vue";
import Projects from "@/views/Projects.vue";
import ProjectMemberships from "@/views/ProjectMemberships.vue";
import Tasks from "@/views/Tasks.vue";

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
      path: '/projects',
      name: 'Projects',
      component: Projects,
    },
    {
      path: '/project-memberships',
      name: 'ProjectMemberships',
      component: ProjectMemberships,
    },
    {
      path: '/tasks',
      name: 'Tasks',
      component: Tasks,
    }
  ]
});

export default router;
