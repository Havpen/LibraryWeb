import { inject } from 'vue'
import { EAppProviders } from '@/app/providers/types'
import { AppRoutes } from '@/app/providers'

export function useAppRoutes(): AppRoutes {
	const appRoutes = inject(EAppProviders.AppRoutes);

	if (!appRoutes) {
		throw 'Failed to inject AppRoutes!';
	}

	return appRoutes;
}