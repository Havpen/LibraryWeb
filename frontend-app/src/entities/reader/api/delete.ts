import { ReaderResponseDTO } from '@/entities/reader'
import { apiRequest } from '@/shared'

export async function deleteReader(id: number) {
	await apiRequest<ReaderResponseDTO>(`readers/${id}`, 'delete');
}
