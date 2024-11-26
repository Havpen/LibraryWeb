import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Reader Editor';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/readers/:id',
	component: () => import('@/pages/ReaderEditor.vue'),
	children: [],
}
