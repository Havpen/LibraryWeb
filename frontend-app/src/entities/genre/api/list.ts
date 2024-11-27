import { Genre, genreFromJson, GenreResponseDTO } from '@/entities/genre'
import { apiRequest } from '@/shared'

export async function listGenres(): Promise<Genre[]> {
	return (await apiRequest<GenreResponseDTO[]>('genres', 'get')).map(genreFromJson);
}
