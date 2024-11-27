import { apiRequest } from '@/shared'
import { Book, BookResponseDTO } from '../model'
import { bookFromJson } from '../api'

export async function listBooks(): Promise<Book[]> {
	return (await apiRequest<BookResponseDTO[]>('books', 'get')).map(bookFromJson);
}
