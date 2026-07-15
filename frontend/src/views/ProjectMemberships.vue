<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';
import GenericTable from "@/components/GenericTable.vue";

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
  <div class="container-fluid col-md-9">
    <div class="card">
      <div class="card-header bg-primary">
        <strong>Projects</strong>
      </div>
      <div id="userContent" class="m-4">
        <div>
          <GenericTable :items="projectMembershipList" />
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
#userContent {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>