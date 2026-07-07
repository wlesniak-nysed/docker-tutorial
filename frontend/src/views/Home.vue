<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';

const newName = ref('');
const nameList = ref<Name[]>([]);

interface Name {
  id: number;
  name: string;
}

onMounted(() => {
  getNames();
});

async function getNames() {
  const response = await axios.get<Name[]>(`/api/items/all`);
  nameList.value = response.data;
}

async function addName() {
  await axios.post(`/api/items/create`, { name: newName.value }, {});
  newName.value = '';
  await getNames();
}

async function deleteName(name : Name) {
  await axios.delete(`/api/items/delete`, { data: name });
  await getNames();
}

</script>
<template>
  <div class="main">
    <div style="padding-top: 2rem;">
      <label for="addItem">Add Name: </label>
      <input v-model="newName" type="text" @keyup.enter="addName"  />
      <button type="button" @click="addName">+</button>
    </div>
    <div>
      <ul>
        <li v-for="name in nameList" :key="name.id">
          <p style="display: inline-block">{{ name.name }}</p>
          <button style="float: right" type="button" @click="deleteName(name)">X</button>
        </li>
      </ul>
    </div>
  </div>
</template>
<style scoped>
</style>