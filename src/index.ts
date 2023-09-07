import { registerPlugin } from '@capacitor/core';

import type { UtilityPlugin } from './definitions';

const Utility = registerPlugin<UtilityPlugin>('Utility', {
  web: () => import('./web').then(m => new m.UtilityWeb()),
});

export * from './definitions';
export { Utility };
