<script setup lang="ts">
import { EntitySelectEmits, EntitySelectProps, useEntitySelect } from '@/shared/useEntitySelect'
import { Publisher, listPublishers } from '@/entities/publisher'

defineProps<EntitySelectProps<Publisher>>();
const emit = defineEmits<EntitySelectEmits<Publisher>>();

const {
	options,
	updateValue,
	filterOption,
} = useEntitySelect<Publisher>(listPublishers, (publisher) => publisher.name, emit);
</script>

<template>
	<a-select :value="value?.id ?? null"
			  @update:value="updateValue"
			  :options="options"
			  show-search
			  :filter-option="filterOption">
	</a-select>
</template>
