<script setup lang="ts">
import { computed } from 'vue';

// Define generic types for flexibility
const props = defineProps<{
  items: Record<string, any>[];
  onDelete?: (item: any) => void;
}>();

// Automatically generate column headers from object keys
const headers = computed(() => {
  if (props.items.length === 0) return [];
  // shutup Vue this works
  return Object.keys(props.items[0]);
});

// Format header keys for better readability (e.g., "firstName" -> "First Name")
const formatHeader = (header: string) => {
  // Insert a space before any uppercase letter
  const spaced = header.replace(/([A-Z])/g, ' $1');
  // Capitalize the first letter and trim extra spaces
  return (spaced.charAt(0).toUpperCase() + spaced.slice(1)).trim();
};
</script>

<template>
  <table v-if="items.length > 0">
    <thead>
    <tr>
      <th v-for="header in headers" :key="header">
        {{ formatHeader(header) }}
      </th>
      <th v-if="onDelete">Delete</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item, index) in items" :key="item.id || index">
      <td v-for="header in headers" :key="header">
        {{ item[header] }}
      </td>
      <td v-if="onDelete" style="text-align: center;">
        <button type="button" @click="onDelete(item)">X</button>
      </td>
    </tr>
    </tbody>
  </table>
  <p v-else>No data available.</p>
</template>
<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  padding-right: 1rem;
}
</style>