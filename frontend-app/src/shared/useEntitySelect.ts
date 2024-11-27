import { computed, ref } from 'vue'

export type EntitySelectProps<T> = {
	value: T | null;
}

export type EntitySelectEmits<T> = {
	(e: 'update:value', newVal: T | null): void;
}

export function useEntitySelect<T extends { id: number }>(
	fetchItems: () => Promise<T[]>,
	labelExtractor: (item: T) => string,
	emit: EntitySelectEmits<T>) {
	const items = ref<T[]>([]);
	fetchItems()
		.then((_items) => {
			items.value = _items;
		});
	
	function updateValue(id: number | null) {
		const entity = items.value.find((item) => item.id === id) as T | undefined;
		emit('update:value', entity ?? null);
	}
	
	const options = computed<{ value: number; label: string; }[]>(() => items.value.map((item) => {
		return {
			value: item.id,
			label: labelExtractor(item as T),
		}
	}));
	
	function filterOption(searchQuery: string, option: { value: number; label: string }) {
		return option.label.toLowerCase().includes(searchQuery.toLowerCase());
	}
	
	return {
		options,
		updateValue,
		filterOption,
	};
}
