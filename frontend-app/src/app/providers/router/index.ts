import { createRouter, createWebHistory } from 'vue-router'
import { routes } from './routes'

export { pages as AppPages } from './pages'

export const router = createRouter({
	routes,
	history: createWebHistory(),
	scrollBehavior(to, from, savedPosition) {
		if (savedPosition) {
			return savedPosition;
		} else if (to.hash) {
			return { el: to.hash };
		} else {
			return { top: 0 };
		}
	},
});
