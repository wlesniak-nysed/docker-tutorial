<script setup lang="ts">
import { computed, ref, watch } from 'vue';

// 1. Define Props with defaults using withDefaults
const props = withDefaults(
    defineProps<{
      items: Record<string, any>[];
      onDelete?: (item: any) => void;
      pageSize?: number; // Optional prop with a default value
    }>(),
    {
      pageSize: 5 // Default page size is set to 5 rows
    }
);

// 2. Pagination State
const currentPage = ref(1);

// Reset page to 1 if the underlying data items list changes length
watch(() => props.items.length, () => {
  currentPage.value = 1;
});

// 3. Compute Total Pages
const totalPages = computed(() => {
  return Math.ceil(props.items.length / props.pageSize);
});

// 4. Slice Data for Current Page
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * props.pageSize;
  const end = start + props.pageSize;
  return props.items.slice(start, end);
});

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
  <div v-if="items.length > 0" class="table-container">
    <table>
      <thead>
      <tr>
        <th v-for="header in headers" :key="header">
          {{ formatHeader(header) }}
        </th>
        <th v-if="onDelete">Actions</th>
      </tr>
      </thead>
      <tbody>
      <!-- Loop over paginatedItems instead of raw items -->
      <tr v-for="(item, index) in paginatedItems" :key="item.id || index">
        <td v-for="header in headers" :key="header">
          {{ item[header] }}
        </td>
        <td v-if="onDelete" style="text-align: center;">
          <button type="button" @click="onDelete(item)">x</button>
        </td>
      </tr>
      </tbody>
    </table>

    <!-- Pagination Controls Navigation HTML -->
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