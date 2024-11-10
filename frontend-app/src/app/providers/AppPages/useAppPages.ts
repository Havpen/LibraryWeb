import { inject } from 'vue'
import { EAppProviders } from '@/app/providers/types'

export function useAppPages() {
	const appPages = inject(EAppProviders.AppPages);

	if (!appPages) {
		throw 'Failed to inject AppPages!';
	}

	return appPages;
}
