import { Reader, ReaderResponseDTO } from '@/entities/reader'
import { apiRequest } from '@/shared'
import { readerFromJson } from './mapper'

export async function getReader(id: number): Promise<Reader> {
	return readerFromJson(await apiRequest<ReaderResponseDTO>(`readers/${id}`, 'get'));
}
