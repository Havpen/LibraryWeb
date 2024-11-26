import { Reader, ReaderResponseDTO } from '@/entities/reader'
import { apiRequest } from '@/shared'
import { readerFromJson, readerToJson } from './mapper'

export async function createReader(reader: Reader) {
	const response = await apiRequest<ReaderResponseDTO>('readers', 'post', readerToJson(reader));
	return readerFromJson(response);
}
