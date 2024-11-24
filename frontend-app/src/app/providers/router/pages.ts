import { routeName as home } from './routes/home'
import { routeName as bookEditor } from './routes/book-editor'
import { routeName as books } from './routes/books'

export const pages = {
	home,
	bookEditor,
	books,
} as const;
