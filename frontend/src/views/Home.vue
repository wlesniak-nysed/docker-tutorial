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

async function addName() {
  const response = await axios.post<Name>(`/api/items/create`, { name: newName.value }, {});
  newName.value = '';
  console.log(response.data);
  await getNames();
}

async function getNames() {
  const response = await axios.get<Name[]>(`/api/items/all`);
  nameList.value = response.data;
  console.log(response.data);
}
</script>
<template>
  <div>
    <label for="addItem">Add Name</label>
    <input v-model="newName" type="text" @keyup.enter="addName"  />
    <button type="button" @click="addName">+</button>
  </div>
  <div>
    <ul>
      <li v-for="name in nameList" :key="name.id">{{ name.name }}</li>
    </ul>
  </div>
</template>