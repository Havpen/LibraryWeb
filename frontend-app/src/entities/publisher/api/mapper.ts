import { Publisher, PublisherResponseDTO } from '@/entities/publisher'

export function publisherFromJson(publisher: PublisherResponseDTO): Publisher {
	return {
		id: publisher.id,
		name: publisher.name,
	};
}
