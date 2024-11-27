<script setup lang="ts">
import { deleteBook, Book } from '@/entities/book'
import { AppRoutes } from '@/app/providers'

const props = defineProps<{
	book: Book;
}>();

const emit = defineEmits<{
	(e: 'delete'): void;
}>();

async function deleteSelf() {
	await deleteBook(props.book.id);
	emit('delete');
}
</script>

<template>
	<div class="book-list-item">
		<div class="primary-info">
			<div>{{ book.title }}</div>
			<div>{{ book.genre.name }}</div>
			<div>{{ book.publisher.name }}</div>
			<div>{{ book.year }}</div>
			<div>{{ book.language }}</div>
		</div>
		
		<router-link :to="AppRoutes.getBookEditor(book.id)">
			<a-button>Edit</a-button>
		</router-link>
		
		<a-button danger @click="deleteSelf">Delete</a-button>
	</div>
</template>

<style scoped>
.book-list-item
{
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 10px;
	
	.primary-info
	{
		flex-grow: 1;
		display: grid;
		grid-template-columns: 1fr 1fr 1fr auto auto;
		column-gap: 10px;
	}
}
</style>
