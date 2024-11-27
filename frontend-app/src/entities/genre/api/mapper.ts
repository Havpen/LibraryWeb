import { Genre, GenreResponseDTO } from '@/entities/genre'

export function genreFromJson(genre: GenreResponseDTO): Genre {
	return {
		id: genre.id,
		name: genre.name,
	};
}
