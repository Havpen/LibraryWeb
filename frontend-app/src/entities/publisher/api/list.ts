import { apiRequest } from '@/shared'
import { Publisher, publisherFromJson, PublisherResponseDTO } from '@/entities/publisher'

export async function listPublishers(): Promise<Publisher[]> {
	return (await apiRequest<PublisherResponseDTO[]>('publishers', 'get')).map(publisherFromJson);
}
