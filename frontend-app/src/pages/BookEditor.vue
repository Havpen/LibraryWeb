<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useEntityCRUDPage } from '@/shared/useEntityEditorPage'
import { AppRoutes } from '@/app/providers'
import { Book, deleteBook, getBook, updateBook, createBook } from '@/entities/book'
import GenreSelect from '@/widgets/genre-select/GenreSelect.vue';
import { Genre } from '@/entities/genre';
import PublisherSelect from '@/widgets/publisher-select/PublisherSelect.vue';

const router = useRouter();

function createBlank(): Book {
	return {
		id: 0,
		title: '',
		year: '',
		language: '',
		genre: null,
		publisher: null,
	};
}

const {
	entity: book,
	saveEntityData: save,
} = useEntityCRUDPage<Book>(
	createBlank,
	async (book: Book) => {
		const result = await createBook(book);
		await router.push(AppRoutes.getBookEditor(book.id));
		return result;
	},
	getBook,
	updateBook,
	deleteBook);
</script>

<template>
	<div class="book-editor">
		<h1>Book Editor</h1>
		
		<div class="book-grid">
			<h3>Title</h3>
			<a-input v-model:value="book.title" />
			
			<h3>Year</h3>
			<a-input v-model:value="book.year" />
			
			<h3>Language</h3>
			<a-input v-model:value="book.language" />
			
			<h3>Publisher</h3>
			<PublisherSelect v-model:value="book.publisher" />
			
			<h3>Genre</h3>
			<GenreSelect v-model:value="book.genre" />
		</div>
		
		<a-button type="primary" @click="save">Save</a-button>
	</div>
</template>

<style scoped>
.book-editor
{
	.book-grid
	{
		display: grid;
		grid-template-columns: auto 1fr;
		column-gap: 25px;
		row-gap: 5px;
		align-items: center;
		text-align: left;
	}
}
</style>
