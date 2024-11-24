<script setup lang="ts">
import { ref } from 'vue'
import { listBooks, BookResponseDTO } from '@/entities/book'

const books = ref<BookResponseDTO[]>([]);

listBooks()
	.then((_books) => {
		books.value = _books
	});
</script>

<template>
	<div>
		<h1>Books Page</h1>
		
		<div class="book-entry" v-for="book in books">
			{{ book }}
		</div>
		
		<a-button type="primary">Add Book</a-button>
	</div>
</template>

<style scoped>

</style>
