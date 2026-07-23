<script setup lang="ts">
import { onMounted, ref, computed } from 'vue';
import axios from 'axios';
import { type Project, type Task} from "@/interfaces.ts";
import TaskItem from "@/components/TaskItem.vue";

const createEmptyTask = () => ({
  id: -1,
  title: '',
  description: '',
  projectId: -1,
  projectName: '',
  parentTaskId: -1,
  subTasks: []
});

const taskList = ref<Task[]>([]);
const rootTaskList = ref<Task[]>([]);
const projectList = ref<Project[]>([]);
const newTask = ref<Task>(createEmptyTask());
const errorMessage = ref('');

const selectedProject = ref<Project | null>(null);
const selectedTask = ref<Task | null>(null);

const filteredTasks = computed(() => {
  if (!selectedProject.value?.id) {
    return []; // Return empty if no project is selected yet
  }
  return taskList.value.filter(task => task.projectId === selectedProject.value?.id);
});

onMounted(() => {
  getProjects();
  getTasks();
  getRootTasks();
});

async function getProjects() {
  const response = await axios.get<Project[]>(`api/projects/all`);
  projectList.value = response.data;
}

async function getTasks() {
  const response = await axios.get<Task[]>(`api/tasks/all`);
  taskList.value = response.data;
}

async function getRootTasks() {
  const response = await axios.get<Task[]>(`api/tasks/all-root`);
  rootTaskList.value = response.data;
}

async function createTask() {
  if (selectedProject.value === null) {
    errorMessage.value = 'Select a project';
    setTimeout(() => { errorMessage.value = ''; }, 3000);
  } else if (newTask.value.title === '') {
    errorMessage.value = 'Task must have a title';
    setTimeout(() => { errorMessage.value = ''; }, 3000);
  } else {
    newTask.value.projectId = selectedProject.value.id;
    newTask.value.projectName = selectedProject.value.name;
    newTask.value.parentTaskId = selectedTask.value?.id;
    await axios.post(`api/tasks/create`, newTask.value);
    await getTasks();
    await getRootTasks();
    selectedProject.value = null;
    newTask.value = createEmptyTask();
  }
}

</script>
<template>
  <div class="container-fluid col-md-9">
    <div class="card">
      <div class="card-header bg-primary">
        <strong>Tasks</strong>
      </div>
      <div id="userContent" class="m-4">
        <div class="simple-flex">
          <label for="project-select">Project*:</label>
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
          <label for="task-title">Title*:</label>
          <input id="task-title" v-model="newTask.title" type="text" />
          <label for="task-description">Description:</label>
          <input id="task-description" v-model="newTask.description" type="text" />
          <label for="parent-task-select">Parent Task:</label>
          <select id="parent-task-select" v-model="selectedTask"
                  :disabled="filteredTasks.length === 0">
            <option :value="null" disabled>Select a parent task</option>
            <option
                v-for="task in filteredTasks"
                :key="task.id"
                :value="task"
            >
              {{ task.projectName }}: {{ task.title }}
            </option>
          </select>
          <button type="button" @click="createTask">Create Task</button>
        </div>
        <div v-if="errorMessage" class="error">
          <p>{{errorMessage}}</p>
        </div>
        <div>
          <ul class="root-task-list">
            <TaskItem v-for="task in rootTaskList" :key="task.id" :task="task" />
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.simple-flex div {
  margin: 0 1rem;
}
</style>