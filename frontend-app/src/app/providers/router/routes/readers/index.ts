import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Readers';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/readers',
	component: () => import('@/pages/ReadersPage.vue'),
	children: [],
}
