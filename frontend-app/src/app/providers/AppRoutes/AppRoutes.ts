import { RouteLocationNamedRaw, RouteParamsRaw, RouteQueryAndHash, RouteRecordName } from 'vue-router'
import { AppPages } from '../router'

export class AppRoutes {
	private constructor() {}

	static getHome() {
		return getRoute(AppPages.home);
	}
	
	static getBookEditor(id: string) {
		return getRoute(AppPages.bookEditor, { id });
	}
	
	static getBooks() {
		return getRoute(AppPages.books);
	}
}

function getRoute(name: RouteRecordName, params: RouteParamsRaw = {}, queryParams: RouteQueryAndHash = {}): RouteLocationNamedRaw {
	return {
		name,
		params,
		query: queryParams.query,
		hash: queryParams.hash,
	}
}
