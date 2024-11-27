import { apiRequest } from '@/shared'
import { Book, bookFromJson, BookResponseDTO, bookToJson } from '@/entities/book'

export async function updateBook(book: Book) {
	const response = await apiRequest<BookResponseDTO>(`books/${book.id}`, 'put', bookToJson(book));
	return bookFromJson(response);
}
