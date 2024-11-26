import type { RouteRecordRaw } from 'vue-router'
import { route as home } from './home'
import { route as bookEditor } from './book-editor'
import { route as books } from './books'
import { route as readers } from './readers'
import { route as readerEditor } from './reader-editor'

export const routes: RouteRecordRaw[] = [
	home,
	bookEditor,
	books,
	readers,
	readerEditor,
]
