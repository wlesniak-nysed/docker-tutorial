<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';
import GenericTable from "@/components/GenericTable.vue";

const newProject = ref('');
const projectList = ref<Project[]>([]);

interface Project {
  id: number;
  name: string;
}

onMounted(() => {
  getProjects();
});

async function getProjects() {
  const response = await axios.get<Project[]>(`/api/projects/all`);
  projectList.value = response.data;
  // useful for testing page overflow
  // const longList = Array.from({ length: 10 }, () => response.data).flat();
  // projectList.value = longList;
}

async function addProject() {
  await axios.post(`/api/projects/create`, { name: newProject.value }, {});
  newProject.value = '';
  await getProjects();
}

async function deleteProject(project : Project) {
  await axios.delete(`/api/projects/delete`, { data: project });
  await getProjects();
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
          <label for="addItem">Add Project: </label>
          <input v-model="newProject" type="text" @keyup.enter="addProject" />
          <button type="button" @click="addProject">+</button>
        </div>
        <div>
          <GenericTable :items="projectList" :on-delete="deleteProject" />
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