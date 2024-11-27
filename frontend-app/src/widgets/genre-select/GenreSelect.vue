<script setup lang="ts">
import { EntitySelectEmits, EntitySelectProps, useEntitySelect } from '@/shared/useEntitySelect'
import { Genre, listGenres } from '@/entities/genre'

defineProps<EntitySelectProps<Genre>>();
const emit = defineEmits<EntitySelectEmits<Genre>>();

const {
	options,
	updateValue,
	filterOption,
} = useEntitySelect<Genre>(listGenres, (genre) => genre.name, emit);
</script>

<template>
	<a-select :value="value?.id ?? null"
			  @update:value="updateValue"
			  :options="options"
			  show-search
			  :filter-option="filterOption">
	</a-select>
</template>
