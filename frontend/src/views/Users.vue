<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';

const newUser = ref('');
const userList = ref<User[]>([]);

interface User {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
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
  <div class="container-fluid col-md-9">
    <div class="card">
      <div class="card-header bg-primary">
        <strong>Users</strong>
      </div>
      <div id="userContent" class="m-4">
        <div>
          <label for="addItem">Add User: </label>
          <input v-model="newUser" type="text" @keyup.enter="addUser"  />
          <button type="button" @click="addUser">+</button>
        </div>
        <div>
          <table>
            <thead>
            <tr>
              <th>User ID</th>
              <th>User Name</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Delete User</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="user in userList" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.userName }}</td>
              <td>{{ user.firstName }}</td>
              <td>{{ user.lastName }}</td>
              <td style="text-align: center;"><button type="button" @click="deleteUser(user)">X</button></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  padding-right: 1rem;
}
#userContent {
  display: flex;
  flex-direction: column;
  align-items: center;

}
</style>