import { Moment } from 'moment'

export type Reader = {
	id: number;
	name: string;
	birthday: Moment;
	address: string;
	phone: string;
	email: string;
	registrationDate: Moment;
	cardNumber: number;
}

export type ReaderRequestDTO = {
	id: number;
	name: string;
	birthday: string;
	address: string;
	phone: string;
	email: string;
	registrationDate: string;
	cardNumber: number;
}

export type ReaderResponseDTO = {
	id: number;
	name: string;
	birthday: string;
	address: string;
	phone: string;
	email: string;
	registrationDate: string;
	cardNumber: number;
}
