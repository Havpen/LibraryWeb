<script setup lang="ts">
import { listReaders, Reader } from '@/entities/reader'
import { ref } from 'vue'
import ReaderListItem from '@/entities/reader/ui/reader-list-item/ReaderListItem.vue';

const loadingReaders = ref(true);
const readers = ref<Reader[]>([]);

listReaders().then(
	(_readers) => {
		readers.value = _readers;
		loadingReaders.value = false;
	});

function deleteItem(id: number) {
	const idx = readers.value.findIndex((item) => item.id === id);
	if (idx != -1) {
		readers.value.splice(idx, 1);
	}
}
</script>

<template>
	<div>
		<h1>Readers</h1>
		
		<a-skeleton :loading="loadingReaders" active avatar></a-skeleton>
		
		<ReaderListItem v-for="reader in readers" :reader="reader" @delete="deleteItem(reader.id)" />
		
		<router-link to="/readers/new">
			<a-button type="primary">Create Reader</a-button>
		</router-link>
	</div>
</template>

<style scoped>

</style>
