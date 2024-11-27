import { apiRequest } from '@/shared'

export async function deleteBook(id: number) {
	await apiRequest(`books/${id}`, 'delete');
}
