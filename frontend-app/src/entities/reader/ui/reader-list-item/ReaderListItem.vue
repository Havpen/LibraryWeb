<script setup lang="ts">
import { deleteReader, Reader } from '@/entities/reader'

const props = defineProps<{
	reader: Reader;
}>();

const emit = defineEmits<{
	(e: 'delete'): void;
}>();

async function deleteSelf() {
	await deleteReader(props.reader.id);
	emit('delete');
}
</script>

<template>
	<div class="reader-list-item">
		<div class="primary-info">
			<div>{{ reader.cardNumber }}</div>
			<div>{{ reader.name }}</div>
			<div>{{ reader.phone }}</div>
			<div>{{ reader.email }}</div>
		</div>
		
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
	align-items: center;
	gap: 10px;
	
	.primary-info
	{
		flex-grow: 1;
		display: grid;
		grid-template-columns: max-content 1fr 1fr 1fr 1fr;
		column-gap: 10px;
	}
}
</style>
