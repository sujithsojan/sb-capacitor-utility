import { WebPlugin } from '@capacitor/core';

import type { UtilityPlugin } from './definitions';

export class UtilityWeb extends WebPlugin implements UtilityPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
