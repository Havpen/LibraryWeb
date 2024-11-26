import { routeName as home } from './routes/home'
import { routeName as bookEditor } from './routes/book-editor'
import { routeName as books } from './routes/books'
import { routeName as readers } from './routes/readers'
import { routeName as readerEditor } from './routes/reader-editor'

export const pages = {
	home,
	bookEditor,
	books,
	readers,
	readerEditor,
} as const;
