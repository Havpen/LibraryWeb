import axios, { Method } from 'axios'

const BACKEND_URL = import.meta.env.VITE_BACKEND_SERVER_URL + 'api/v1/';

export async function apiRequest<ResponseT>(url: string, method: Method, data?: any, params?: any) {
	const response = await axios(BACKEND_URL + url, {
		method: method,
		data: data,
		params: params,
	});
	
	return response.data as ResponseT;
}
