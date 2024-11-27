import { RouteRecordRaw } from 'vue-router'

export const routeName: string = 'Books';

export const route: RouteRecordRaw = {
	name: routeName,
	path: '/books',
	component: () => import('@/pages/BooksPage.vue'),
	children: [],
}
