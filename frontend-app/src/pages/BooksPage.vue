<script setup lang="ts">
import { ref } from 'vue'
import { listBooks, Book } from '@/entities/book'
import BookListItem from '@/entities/book/ui/book-list-item'

const books = ref<Book[]>([]);

listBooks()
	.then((_books) => {
		books.value = _books;
	});

function onDeleteBook(id: number) {
	const idx = books.value.findIndex((_book) => _book.id === id);
	if (idx != -1) {
		books.value.splice(idx, 1);
	}
}
</script>

<template>
	<div class="books-page">
		<h1>Books</h1>
		
		<BookListItem v-for="book in books" :book="book" @delete="onDeleteBook(book.id)" />
		
		<a-button type="primary">Add Book</a-button>
	</div>
</template>

<style scoped>
.books-page
{
	width: 1200px;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	gap: 5px;
	padding: 10px 20px;
	background: #f6f6f6;
	border-radius: 5px;
	border: 1px solid #ddd;
}
</style>
