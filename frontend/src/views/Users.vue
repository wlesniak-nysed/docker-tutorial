<script setup lang="ts">
import { onMounted, ref } from 'vue';
import axios from 'axios';

const createEmptyUser = () => ({
  // we don't use this field until it has been saved, and the save operation returns its actual id
  // setting it to -1 helps clarify it doesn't exist/hasn't been saved
  id: -1,
  userName: '',
  firstName: '',
  lastName: ''
});

const newUser = ref<User>(createEmptyUser());
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
  // the user's first and last name will be given an appropriate number on the back end
  // i.e. an existing "aavery1" will cause "aavery2" to be assigned
  newUser.value.userName = newUser.value.firstName.substring(0, 1) + newUser.value.lastName.substring(0, 10)
  await axios.post(`/api/users/create`, newUser.value);
  newUser.value = createEmptyUser();
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
          <p>Add User: </p>
          <label for="firstName">First Name</label>
          <input id="firstName" v-model="newUser.firstName" type="text" />
          <label for="lastName">Last Name</label>
          <input id="lastName" v-model="newUser.lastName" type="text" />
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