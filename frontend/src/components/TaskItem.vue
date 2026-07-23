<script setup lang="ts">
// Vue automatically allows components to reference themselves by their file name
defineProps({
  task: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <li class="task-item">
    <div class="task-content">
      <span class="project-title" v-if="!task.parentTaskId">{{task.projectName}}<br /></span>
      <span class="task-title">{{ task.title }}</span>
      <p v-if="task.description" class="task-desc">{{ task.description }}</p>
    </div>

    <!-- if this task has subtasks, render this component again -->
    <ul v-if="task.subTasks && task.subTasks.length > 0" class="subtask-list">
      <TaskItem
          v-for="subTask in task.subTasks"
          :key="subTask.id"
          :task="subTask"
      />
    </ul>
  </li>
</template>
<style scoped>
  .task-item {
    list-style-type: none;
    margin: 8px 0;
  }
  .task-content {
    padding: 8px;
    background: #f9f9f9;
    border-left: 4px solid #42b983;
  }
  .task-title {
    font-weight: bold;
  }
  .task-desc {
    margin: 4px 0 0 0;
    font-size: 0.9em;
    color: #666;
  }
  .subtask-list {
    padding-left: 20px;
    border-left: 1px dashed #ccc;
  }
  .project-title {
    color: blue;
    font-weight: bold;
  }
</style>
