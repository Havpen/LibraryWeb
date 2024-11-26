import { Reader, ReaderRequestDTO, ReaderResponseDTO } from '@/entities/reader'
import moment from 'moment'

export function readerToJson(reader: Reader): ReaderRequestDTO {
	return {
		id: reader.id,
		name: reader.name,
		birthday: reader.birthday.toISOString(),
		email: reader.email,
		address: reader.address,
		registrationDate: reader.registrationDate.toISOString(),
		phone: reader.phone,
		cardNumber: reader.cardNumber,
	};
}

export function readerFromJson(reader: ReaderResponseDTO): Reader {
	return {
		id: reader.id,
		name: reader.name,
		birthday: moment(reader.birthday),
		email: reader.email,
		address: reader.address,
		registrationDate: moment(reader.registrationDate),
		phone: reader.phone,
		cardNumber: reader.cardNumber,
	};
}
