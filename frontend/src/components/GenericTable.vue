<script setup lang="ts">
import { computed, ref } from 'vue';

const props = withDefaults(
    defineProps<{
      items: Record<string, any>[];
      onDelete?: (item: any) => void;
      pageSize?: number;
    }>(),
    {
      pageSize: 5
    }
);

const currentPage = ref(1);

// compute total pages
const totalPages = computed(() => {
  return Math.ceil(props.items.length / props.pageSize);
});

// slice data for current page
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * props.pageSize;
  const end = start + props.pageSize;
  return props.items.slice(start, end);
});

// return key names from table data items to be used as headers
const headers = computed(() => {
  if (props.items.length === 0) return [];
  // shutup Vue this works
  return Object.keys(props.items[0]);
});

// format header keys for better readability (e.g., "firstName" -> "First Name")
const formatHeader = (header: string) => {
  const spaced = header.replace(/([A-Z])/g, ' $1');
  return (spaced.charAt(0).toUpperCase() + spaced.slice(1)).trim();
};
</script>
<template>
  <div v-if="items.length > 0" class="table-container">
    <table>
      <thead>
      <tr>
        <th v-for="header in headers" :key="header">
          {{ formatHeader(header) }}
        </th>
        <th v-if="onDelete">Delete</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item, index) in paginatedItems" :key="item.id || index">
        <td v-for="header in headers" :key="header">
          {{ item[header] }}
        </td>
        <td v-if="onDelete" style="text-align: center;">
          <button type="button" @click="onDelete(item)">X</button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="pagination-controls" v-if="totalPages > 1">
      <button v-if="totalPages > 2" :disabled="currentPage === 1" @click="currentPage = 1">
        First
      </button>
      <button :disabled="currentPage === 1" @click="currentPage--">
        Previous
      </button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button :disabled="currentPage === totalPages" @click="currentPage++">
        Next
      </button>
      <button v-if="totalPages > 2" :disabled="currentPage === totalPages" @click="currentPage = totalPages">
        Last
      </button>
    </div>
  </div>
  <p v-else>No data available.</p>
</template>
<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  padding-right: 1rem;
}
.table-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  align-items: center;
}
.pagination-controls {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}
</style>