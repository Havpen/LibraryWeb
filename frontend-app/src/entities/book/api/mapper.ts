import { Book, BookRequestDTO, BookResponseDTO } from '@/entities/book'
import { genreFromJson } from '@/entities/genre'
import { publisherFromJson } from '@/entities/publisher'

export function bookToJson(book: Book): BookRequestDTO {
	return {
		id: book.id,
		title: book.title,
		year: book.year,
		language: book.language,
		genre: { id: book.genre.id },
		publisher: { id: book.publisher.id },
	};
}

export function bookFromJson(book: BookResponseDTO): Book {
	return {
		id: book.id,
		title: book.title,
		year: book.year,
		language: book.language,
		genre: genreFromJson(book.genre),
		publisher: publisherFromJson(book.publisher),
	};
}
