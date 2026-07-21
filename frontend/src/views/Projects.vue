<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';
import GenericTable from "@/components/GenericTable.vue";
import type {Project} from "@/interfaces.ts";

const newProject = ref('');
const projectList = ref<Project[]>([]);
const errorMessage = ref('');

onMounted(() => {
  getProjects();
});

async function getProjects() {
  const response = await axios.get<Project[]>(`api/projects/all`);
  projectList.value = response.data;
}

async function addProject() {
  if (projectList.value.some((project) => project.name === newProject.value.trim())) {
    errorMessage.value = "Cannot create a project with the same name as another project.";
    setTimeout(() => { errorMessage.value = ""; }, 3000);
  } else if (newProject.value.trim() === '') {
    errorMessage.value = "Project name cannot be blank";
    setTimeout(() => { errorMessage.value = ""; }, 3000);
  } else {
    await axios.post(`api/projects/create`, {name: newProject.value}, {});
    await getProjects();
  }
  newProject.value = '';
}

async function deleteProject(project : Project) {
  await axios.delete(`api/projects/delete`, { data: project });
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
        <div v-if="errorMessage" class="error">
          <p>{{errorMessage}}</p>
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