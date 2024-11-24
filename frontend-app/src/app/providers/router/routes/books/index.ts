import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Books';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/',
	component: () => import('@/pages/BooksPage.vue'),
	children: [],
}
