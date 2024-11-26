import { Reader, ReaderResponseDTO } from '@/entities/reader'
import { apiRequest } from '@/shared'
import { readerFromJson } from './mapper'

export async function listReaders(): Promise<Reader[]> {
	const response = await apiRequest<ReaderResponseDTO[]>('readers', 'get');
	return response.map(readerFromJson);
}
