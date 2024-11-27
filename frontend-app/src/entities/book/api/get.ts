import { apiRequest } from '@/shared'
import { Book, bookFromJson, BookResponseDTO } from '@/entities/book'

export async function getBook(id: number): Promise<Book> {
	return bookFromJson(await apiRequest<BookResponseDTO>(`books/${id}`, 'get'));
}
