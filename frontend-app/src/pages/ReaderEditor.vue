<script setup lang="ts">
import { createReader, getReader, Reader, updateReader, deleteReader } from '@/entities/reader'
import { useRouter } from 'vue-router'
import moment from 'moment'
import { AppRoutes } from '@/app/providers'
import { useEntityCRUDPage } from '@/shared/useEntityEditorPage'

const router = useRouter();

function createBlank(): Reader {
	return {
		id: 0,
		name: '',
		birthday: moment(),
		address: '',
		cardNumber: 0,
		email: '',
		phone: '',
		registrationDate: moment(),
	};
}

const {
	entity: reader,
	saveEntityData: save,
} = useEntityCRUDPage<Reader>(
	createBlank,
	async (reader: Reader) => {
		const result = await createReader(reader);
		await router.push(AppRoutes.getReader(reader.id));
		return result;
	},
	getReader,
	async (reader: Reader) => { return await updateReader(reader.id, reader); },
	deleteReader);
</script>

<template>
	<div class="reader-editor">
		<h1>Reader Profile</h1>
		
		<div class="reader-grid" v-if="reader">
			<h3>Name</h3>
			<a-input v-model:value="reader.name" />
			
			<h3>Birthday</h3>
			<a-date-picker v-model:value="reader.birthday" />
			
			<h3>Address</h3>
			<a-input v-model:value="reader.address" />
			
			<h3>Card Number</h3>
			<a-input-number v-model:value="reader.cardNumber" />
			
			<h3>Email</h3>
			<a-input v-model:value="reader.email" />
			
			<h3>Phone</h3>
			<a-input v-model:value="reader.phone" />
			
			<h3>Registration Date</h3>
			<a-date-picker v-model:value="reader.registrationDate" />
		</div>
		
		<a-button type="primary" @click="save">Save</a-button>
	</div>
</template>

<style scoped>
.reader-grid
{
	display: grid;
	grid-template-columns: auto 1fr;
	column-gap: 25px;
	row-gap: 5px;
	align-items: center;
	text-align: left;
}
</style>
