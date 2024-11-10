import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Home';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/',
	component: () => import('@/pages/HomePage.vue'),
	children: [],
}
