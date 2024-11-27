import { computed, ref, watch } from 'vue'
import { useRoute } from 'vue-router'
import { message } from 'ant-design-vue'

export function useEntityCRUDPage<EntityType>(
	createBlank: () => EntityType,
	createFunction: (entity: EntityType) => Promise<EntityType>,
	readFunction: (id: number) => Promise<EntityType>,
	updateFunction: (entity: EntityType) => Promise<EntityType>,
	deleteFunction: (id: number) => Promise<void>) {
	const route = useRoute();
	
	const entityId = computed(() => {
		const idRaw = route.params.id as string;
		if (idRaw != 'new') {
			return parseInt(idRaw);
		}
		
		return null;
	});
	
	const loadInProgress = ref(true);
	const saveInProgress = ref(false);
	const entity = ref<EntityType>(createBlank());
	
	async function reloadEntityData() {
		loadInProgress.value = true;
		
		if (entityId.value !== null) {
			try {
				entity.value = await readFunction(entityId.value);
			} catch (error) {
				message.error('Failed to fetch entity!');
			}
		}
		
		loadInProgress.value = false;
	}
	
	async function saveEntityData() {
		if (entityId.value === null) {
			entity.value = await createFunction(entity.value);
		} else {
			entity.value = await updateFunction(entity.value);
		}
	}
	
	async function deleteEntity() {
		if (entityId.value !== null) {
			await deleteFunction(entityId.value);
		}
	}
	
	watch(entityId, async () => {
		await reloadEntityData();
	});
	
	reloadEntityData();
	
	return {
		entityId,
		entity,
		loadInProgress,
		saveInProgress,
		saveEntityData,
		deleteEntity,
	}
}
