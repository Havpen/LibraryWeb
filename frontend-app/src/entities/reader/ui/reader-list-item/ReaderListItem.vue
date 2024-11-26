<script setup lang="ts">
import { deleteReader, Reader } from '@/entities/reader'

const props = defineProps<{
	reader: Reader;
}>();

async function deleteSelf() {
	await deleteReader(props.reader.id);
}
</script>

<template>
	<div class="reader-list-item">
		{{ reader.id }}
		
		<router-link :to="`/readers/${reader.id}`">
			<a-button>Edit</a-button>
		</router-link>
		
		<a-button danger @click="deleteSelf">Delete</a-button>
	</div>
</template>

<style scoped>
.reader-list-item
{
	display: flex;
	flex-direction: row;
	gap: 10px;
}
</style>
