import { WebPlugin } from '@capacitor/core';

import type { UtilityPlugin } from './definitions';

export class UtilityWeb extends WebPlugin implements UtilityPlugin {

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;

  }

  async test(options: { value: string }): Promise<{ value: string }> {
    console.log('test', options);
    return options;
  }

  async sample(options: { value: string }): Promise<{ value: string }> {
    console.log('sample', options);
    return options;
  }

  async getBuildConfigParam(options: { package: string, property: string }): Promise<{ value: string }> {
    console.log('getBuildConfigParam', options);
    const result = {
        value: "some value" 
    };
    return result;
}

  async getBuildConfigValues(options: { package: string }): Promise<{ values: Record<string, string> }> {
  console.log('getBuildConfigValues', options);
  
  const values: Record<string, string> = {
      key1: "value1",
      key2: "value2",
  }; 
  const result = {
      values: values
  }; 
  return result;
}

async openPlayStore(options: { appId: string }): Promise<{ success: boolean }> {
  console.log('openPlayStore', options);
  return { success: true };
}

async getDeviceAPILevel(): Promise<{ apiLevel: number }> {
  const apiLevel = 0; 
  return { apiLevel };
}

async checkAppAvailability(options: { packageName: string }): Promise<{ available: boolean }> {
  console.log('checkAppAvailability', options);
  const available = false; 

  return { available };
}

async getDownloadDirectoryPath(): Promise<{ path: string }> {
  const defaultDownloadPath = '/downloads';
  return { path: defaultDownloadPath };
}

async exportApk(options: { destination: string }): Promise<{success: boolean}> {
  console.log('exportApk', options);
  return { success: true };  
}

async getDeviceSpec(): Promise<{ spec: any }> {
  const browserInfo = "sample";
  return { spec: browserInfo };
}

async createDirectories(options: { parentDirectory: string, identifiers: string[] }): Promise<{ success: boolean }> {
  console.log('createDirectories', options);
  return { success: true };  
}

async writeFile(options: { fileMapList: any[] }): Promise<{ success: boolean }> {
  console.log('writeFile', options);
  return { success: true };
}

async getMetaData(options: { fileMapList: any[] }): Promise<{ metadata: any }> {
  console.log('getMetaData', options);  
  return { metadata: {} };
}

async getAvailableInternalMemorySize(): Promise<{ size: number }> {
  const availableMemorySize = 1024; // Replace this with the actual value
  return { size: availableMemorySize };
}

async getUtmInfo(): Promise<{ utmInfo: any }> {
  const utmInfo = {
    source: 'example-source',
  };

  return { utmInfo };
}

async clearUtmInfo(): Promise<{ success: boolean }> {
  return { success: true };  
}

async getStorageVolumes(): Promise<{ volumes: any[] }> {
  const storageVolumes = [
    {
      name: 'Internal Storage'
    }
  ];

  return { volumes: storageVolumes };
}

async copyDirectory(options: { sourceDirectory: string, destinationDirectory: string }): Promise<{ success: boolean }> {
  console.log('copyDirectory', options);
  return { success: true };
}

async renameDirectory(options: { sourceDirectory: string, toDirectoryName: string }): Promise<{ success: boolean }> {
  console.log('renameDirectory', options);
  return { success: true };
}

async canWrite(options: { directory: string }): Promise<{ canWrite: boolean }> {
  console.log('canWrite', options);
  return { canWrite: true };
}

async getFreeUsableSpace(options: { directory: string }): Promise<{ space: number }> {
  console.log('getFreeUsableSpace', options);
  const freeUsableSpace = 1024; 
  return { space: freeUsableSpace };
}

async readFromAssets(options: { filePath: string }): Promise<{ content: string }> {
  console.log('readFromAssets', options);
  const contentString = 'sample'; 
  return { content: contentString };
}

async copyFile(options: { sourceDirectory: string, destinationDirectory: string, fileName: string }): Promise<{ success: boolean }> {
  console.log('copyFile', options);
  return {success: true};
}

async getApkSize(): Promise<{ size: number }> {
  const apkSize = 65; 
  return { size: apkSize };
}

async verifyCaptcha(options: { apiKey: string }): Promise<{ success: boolean }> {
  console.log('verifyCaptcha', options);
  return {success: true};
}

async startActivityForResult(options: {sample: string, sample2: string}): Promise<any>  {
  console.log('startActivityForResult', options);
  return {success: true};
}

async getAppAvailabilityStatus(options: { appList: string[] }): Promise<{ availability: Record<string, boolean> }> {
  console.log('getAppAvailabilityStatus', options);
  const appsToCheck = ['App1', 'App2', 'App3'];
  const availability: Record<string, boolean> = {};
  for (const appName of appsToCheck) {
    availability[appName] = true;
  }
  return { availability };
}

async openFileManager(): Promise<{ success: boolean }> {
  return {success: true};
}






}

