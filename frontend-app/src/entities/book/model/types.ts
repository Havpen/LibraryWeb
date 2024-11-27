import { Genre, GenreIdDTO, GenreResponseDTO } from '@/entities/genre'
import { Publisher, PublisherIdDTO, PublisherResponseDTO } from '@/entities/publisher'

export type Book = {
	id: number;
	title: string;
	year: number;
	language: string;
	genre: Genre | null;
	publisher: Publisher | null;
}

export type BookRequestDTO = {
	id: number;
	title: string;
	year: number;
	language: string;
	genre: GenreIdDTO;
	publisher: PublisherIdDTO;
}

export type BookResponseDTO = {
	id: number;
	title: string;
	year: number;
	language: string;
	genre: GenreResponseDTO;
	publisher: PublisherResponseDTO;
}
