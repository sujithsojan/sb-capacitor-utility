export interface UtilityPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
