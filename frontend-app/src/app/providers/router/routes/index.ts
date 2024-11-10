import type { RouteRecordRaw } from 'vue-router'
import { route as home } from './home'
import { route as bookEditor } from './book-editor'
import { route as books } from './books'

export const routes: RouteRecordRaw[] = [
	home,
	bookEditor,
	books,
]
