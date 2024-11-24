import axios, { Method } from 'axios'

const BACKEND_URL = import.meta.env.VITE_BACKEND_SERVER_URL + 'api/v1/';

export async function apiRequest<ResponseT>(url: string, method: Method) {
	const response = await axios(BACKEND_URL + url, {
		method: method,
	});
	
	return response.data as ResponseT;
}
