import { apiRequest } from '@/shared'
import { Book, bookFromJson, BookResponseDTO, bookToJson } from '@/entities/book'

export async function createBook(book: Book) {
	const response = await apiRequest<BookResponseDTO>('books', 'post', bookToJson(book));
	return bookFromJson(response);
}
