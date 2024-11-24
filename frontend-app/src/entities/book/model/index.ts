export type BookResponseDTO = {
	id: number;
	title: string;
	year: number;
	language: string;
	genre: {
		id: number;
		name: string;
	},
	publisher: {
		id: number;
		name: string;
	},
}
