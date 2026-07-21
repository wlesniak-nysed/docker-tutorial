<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';
import GenericTable from "@/components/GenericTable.vue";
import {type ProjectMembership, type Project, type User, ProjectRole, ProjectRoleLabels}
  from "@/interfaces.ts";

const userList = ref<User[]>([]);
const projectList = ref<Project[]>([]);
const projectMembershipList = ref<ProjectMembership[]>([]);
const errorMessage = ref('');
const selectedUser = ref<User | null>(null);
const selectedProject = ref<Project | null>(null);
const selectedRole = ref<ProjectRole | null>(null);

onMounted(() => {
  getUsers();
  getProjects();
  getProjectMemberships();
});


async function getUsers() {
  const response = await axios.get<User[]>(`api/users/all`);
  userList.value = response.data;
}

async function getProjects() {
  const response = await axios.get<Project[]>(`api/projects/all`);
  projectList.value = response.data;
}

async function getProjectMemberships() {
  const response = await axios.get<ProjectMembership[]>(`api/project-membership/all`);
  projectMembershipList.value = response.data;
}

async function assignProjectMembership() {
  if (selectedUser.value === null) {
    errorMessage.value = "Select a user to assign";
    setTimeout(() => { errorMessage.value = ""; }, 3000);
  } else if (selectedProject.value === null) {
    errorMessage.value = "Select a project to assign";
    setTimeout(() => { errorMessage.value = ""; }, 3000);
  } else if (selectedRole.value === null) {
    errorMessage.value = "Select a role to assign";
    setTimeout(() => { errorMessage.value = ""; }, 3000);
  } else {
    await axios.post(`api/project-membership/create`, {
      userDto: selectedUser.value,
      projectDto: selectedProject.value,
      projectRole: selectedRole.value
    });
    await getProjectMemberships();
    selectedUser.value = null;
    selectedProject.value = null;
    selectedRole.value = null;
  }
}

</script>
<template>
  <div class="container-fluid col-md-9">
    <div class="card">
      <div class="card-header bg-primary">
        <strong>Projects</strong>
      </div>
      <div id="userContent" class="m-4">
        <div class="dropdown-container">
          <div>
            <label for="user-select">User:</label>
            <select id="user-select" v-model="selectedUser">
              <option :value="null" disabled>Select a user</option>
              <option
                  v-for="user in userList"
                  :key="user.id"
                  :value="user"
              >
                {{ user.firstName + ' ' + user.lastName }}
              </option>
            </select>
          </div>
          <div>
            <label for="project-select">Project:</label>
            <select id="project-select" v-model="selectedProject">
              <option :value="null" disabled>Select a project</option>
              <option
                  v-for="project in projectList"
                  :key="project.id"
                  :value="project"
              >
                {{ project.name }}
              </option>
            </select>
          </div>
          <div>
            <label for="role-select">Project:</label>
            <select id="role-select" v-model="selectedRole">
              <option :value="null" disabled>Select a role</option>
              <option v-for="role in Object.values(ProjectRole)" :key="role" :value="role">
                {{ ProjectRoleLabels[role] }}
              </option>
            </select>
          </div>
          <button type="button" @click="assignProjectMembership">Assign</button>
        </div>
        <div v-if="errorMessage" class="error">
          <p>{{errorMessage}}</p>
        </div>
        <br />
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
.dropdown-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.dropdown-container div {
  margin: 0 1rem;
}
</style>