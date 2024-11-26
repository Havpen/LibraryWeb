<script setup lang="ts">
import { createReader, getReader, Reader, updateReader } from '@/entities/reader'
import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import moment from 'moment'
import { AppRoutes } from '@/app/providers'

const reader = ref<Reader | null>(null);
const router = useRouter();

const readerId = computed(() => {
	const idRaw = useRoute().params.id as string;
	if (idRaw != 'new') {
		return parseInt(idRaw);
	}
	
	return null;
});

if (readerId.value !== null) {
	getReader(readerId.value)
		.then((_reader) => reader.value = _reader);
} else {
	reader.value = {
		id: 0,
		name: '',
		birthday: moment(),
		address: '',
		cardNumber: 0,
		email: '',
		phone: '',
		registrationDate: moment(),
	}
}

async function save() {
	if (reader.value) {
		if (readerId.value === null) {
			reader.value = await createReader(reader.value);
			await router.push(AppRoutes.getReader(reader.value.id));
		} else {
			reader.value = await updateReader(readerId.value, reader.value);
		}
	}
}
</script>

<template>
	<div>
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
