<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';

const projectMembershipList = ref<ProjectMembership[]>([]);

interface ProjectMembership {
  userId: number;
  userName: string;
  projectId: number;
  projectName: string;
  role: string;  
}

onMounted(() => {
  getProjectMemberships();
});

async function getProjectMemberships() {
  const response = await axios.get<ProjectMembership[]>(`/api/project-membership/all`);
  projectMembershipList.value = response.data;
  // useful for testing page overflow
  // const longList = Array.from({ length: 10 }, () => response.data).flat();
  // projectMembershipList.value = longList;
}

</script>
<template>
  <div class="main">
    <div style="padding-top: 2rem;">
      <table>
        <thead>
          <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>Project ID</th>
            <th>Project Name</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="member in projectMembershipList" :key="member.userId + '-' + member.projectId">
            <td>{{ member.userId }}</td>
            <td>{{ member.userName }}</td>
            <td>{{ member.projectId }}</td>
            <td>{{ member.projectName }}</td>
            <td>{{ member.role }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  padding-right: 1rem;
}
</style>