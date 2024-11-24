import { apiRequest } from '@/shared'
import { BookResponseDTO } from '../model'

export async function listBooks() {
	return await apiRequest<BookResponseDTO[]>('books', 'get');
}
