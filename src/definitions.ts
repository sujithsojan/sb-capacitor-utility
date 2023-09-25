export interface UtilityPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  test(options: { value: string }): Promise<{ value: string }>;
  sample(options: { value: string }): Promise<{ value: string }>;
  execute(action: string, args: any[]): Promise<any>;
  isGoogleServicesAvailable(options: {}): Promise<{ result: boolean }>;
  getBuildConfigParam(options: { package: string, property: string }): Promise<{ value: string }>;
  getBuildConfigValues(options: { package: string }): Promise<{ values: Record<string, string> }>;
  rm(options: { directoryPath: string, directoryToBeSkipped: string }): Promise<{ success: boolean }>;
  openPlayStore(options: { appId: string }): Promise<{ success: boolean }>;
  getDeviceAPILevel(): Promise<{ apiLevel: number }>;
  checkAppAvailability(options: { packageName: string }): Promise<{ available: boolean }>;
  getDownloadDirectoryPath(): Promise<{ path: string }>;
  getIdOfResource(options: {name: string, resourceType: string}): Promise<{resourceId: number}>;
  exportApk(options: { destination: string }): Promise<{ success: boolean }>;
  createDirectories(options: { parentDirectory: string, identifiers: string[] }): Promise<{ success: boolean }>;
  writeFile(options: { fileMapList: any[] }): Promise<{ success: boolean }>;
  getMetaData(options: { fileMapList: any[] }): Promise<{ metadata: any }>;
  getAvailableInternalMemorySize(): Promise<{ size: number }>;
  getUtmInfo(): Promise<{ utmInfo: any }>;
  clearUtmInfo(): Promise<{ success: boolean }>;
  getStorageVolumes(): Promise<{ volumes: any[] }>;
  copyDirectory(options: { sourceDirectory: string, destinationDirectory: string }): Promise<{ success: boolean }>;
  renameDirectory(options: { sourceDirectory: string, toDirectoryName: string }): Promise<{ success: boolean }>;
  canWrite(options: { directory: string }): Promise<{ canWrite: boolean }>;
  getUsableSpace(options: { directory: string }): Promise<{ space: number }>;
  readFromAssets(options: { filePath: string }): Promise<{ content: string }>;
  copyFile(options: { sourceDirectory: string, destinationDirectory: string, fileName: string }): Promise<{ success: boolean }>;
  getApkSize(): Promise<{ size: number }>;
  verifyCaptcha(options: { apiKey: string }): Promise<{ success: boolean }>;
  startActivityForResult(options: any): Promise<any>; 
  getAppAvailabilityStatus(options: { appList: string[] }): Promise<{ availability: Record<string, boolean> }>;
  openFileManager(): Promise<{ success: boolean }>; 
}

