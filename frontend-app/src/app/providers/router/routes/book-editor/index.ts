import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Book Editor';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/book/:id',
	component: () => import('@/pages/BookEditor.vue'),
	children: [],
}
