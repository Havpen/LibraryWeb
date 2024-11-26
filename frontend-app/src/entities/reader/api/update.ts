import { Reader, ReaderResponseDTO } from '@/entities/reader'
import { apiRequest } from '@/shared'
import { readerFromJson, readerToJson } from './mapper'

export async function updateReader(id: number, reader: Reader) {
	const response = await apiRequest<ReaderResponseDTO>(`readers/${id}`, 'put', readerToJson(reader));
	return readerFromJson(response);
}
