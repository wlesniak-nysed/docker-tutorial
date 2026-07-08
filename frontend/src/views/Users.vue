<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';

const newUser = ref('');
const userList = ref<User[]>([]);

interface User {
  id: number;
  name: string;
}

onMounted(() => {
  getUsers();
});

async function getUsers() {
  const response = await axios.get<User[]>(`/api/users/all`);
  userList.value = response.data;
  // useful for testing page overflow
  // const longList = Array.from({ length: 10 }, () => response.data).flat();
  // userList.value = longList;
}

async function addUser() {
  await axios.post(`/api/users/create`, { name: newUser.value }, {});
  newUser.value = '';
  await getUsers();
}

async function deleteUser(user : User) {
  await axios.delete(`/api/users/delete`, { data: user });
  await getUsers();
}

</script>
<template>
  <div class="main">
    <div style="padding-top: 2rem;">
      <label for="addItem">Add User: </label>
      <input v-model="newUser" type="text" @keyup.enter="addUser"  />
      <button type="button" @click="addUser">+</button>
    </div>
    <div>
      <ul>
        <li v-for="user in userList" :key="user.id">
          <p style="display: inline-block">{{ user.name }}</p>
          <button style="float: right" type="button" @click="deleteUser(user)">X</button>
        </li>
      </ul>
    </div>
  </div>
</template>
<style scoped>
</style>