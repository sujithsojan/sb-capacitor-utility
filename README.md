# capacitor-plugin-utility

utility plugin

## Install

```bash
npm install capacitor-plugin-utility
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`test(...)`](#test)
* [`sample(...)`](#sample)
* [`execute(...)`](#execute)
* [`getBuildConfigParam(...)`](#getbuildconfigparam)
* [`getBuildConfigValues(...)`](#getbuildconfigvalues)
* [`openPlayStore(...)`](#openplaystore)
* [`getDeviceAPILevel()`](#getdeviceapilevel)
* [`checkAppAvailability(...)`](#checkappavailability)
* [`getDownloadDirectoryPath()`](#getdownloaddirectorypath)
* [`exportApk(...)`](#exportapk)
* [`createDirectories(...)`](#createdirectories)
* [`writeFile(...)`](#writefile)
* [`getMetaData(...)`](#getmetadata)
* [`getAvailableInternalMemorySize()`](#getavailableinternalmemorysize)
* [`getUtmInfo()`](#getutminfo)
* [`clearUtmInfo()`](#clearutminfo)
* [`getStorageVolumes()`](#getstoragevolumes)
* [`copyDirectory(...)`](#copydirectory)
* [`renameDirectory(...)`](#renamedirectory)
* [`canWrite(...)`](#canwrite)
* [`getFreeUsableSpace(...)`](#getfreeusablespace)
* [`readFromAssets(...)`](#readfromassets)
* [`copyFile(...)`](#copyfile)
* [`getApkSize()`](#getapksize)
* [`verifyCaptcha(...)`](#verifycaptcha)
* [`startActivityForResult(...)`](#startactivityforresult)
* [`getAppAvailabilityStatus(...)`](#getappavailabilitystatus)
* [`openFileManager()`](#openfilemanager)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### test(...)

```typescript
test(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### sample(...)

```typescript
sample(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### execute(...)

```typescript
execute(action: string, args: any[]) => Promise<any>
```

| Param        | Type                |
| ------------ | ------------------- |
| **`action`** | <code>string</code> |
| **`args`**   | <code>any[]</code>  |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### getBuildConfigParam(...)

```typescript
getBuildConfigParam(options: { package: string; property: string; }) => Promise<{ value: string; }>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code>{ package: string; property: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getBuildConfigValues(...)

```typescript
getBuildConfigValues(options: { package: string; }) => Promise<{ values: Record<string, string>; }>
```

| Param         | Type                              |
| ------------- | --------------------------------- |
| **`options`** | <code>{ package: string; }</code> |

**Returns:** <code>Promise&lt;{ values: <a href="#record">Record</a>&lt;string, string&gt;; }&gt;</code>

--------------------


### openPlayStore(...)

```typescript
openPlayStore(options: { appId: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ appId: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### getDeviceAPILevel()

```typescript
getDeviceAPILevel() => Promise<{ apiLevel: number; }>
```

**Returns:** <code>Promise&lt;{ apiLevel: number; }&gt;</code>

--------------------


### checkAppAvailability(...)

```typescript
checkAppAvailability(options: { packageName: string; }) => Promise<{ available: boolean; }>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ packageName: string; }</code> |

**Returns:** <code>Promise&lt;{ available: boolean; }&gt;</code>

--------------------


### getDownloadDirectoryPath()

```typescript
getDownloadDirectoryPath() => Promise<{ path: string; }>
```

**Returns:** <code>Promise&lt;{ path: string; }&gt;</code>

--------------------


### exportApk(...)

```typescript
exportApk(options: { destination: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ destination: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### createDirectories(...)

```typescript
createDirectories(options: { parentDirectory: string; identifiers: string[]; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                             |
| ------------- | ---------------------------------------------------------------- |
| **`options`** | <code>{ parentDirectory: string; identifiers: string[]; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### writeFile(...)

```typescript
writeFile(options: { fileMapList: any[]; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                 |
| ------------- | ------------------------------------ |
| **`options`** | <code>{ fileMapList: any[]; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### getMetaData(...)

```typescript
getMetaData(options: { fileMapList: any[]; }) => Promise<{ metadata: any; }>
```

| Param         | Type                                 |
| ------------- | ------------------------------------ |
| **`options`** | <code>{ fileMapList: any[]; }</code> |

**Returns:** <code>Promise&lt;{ metadata: any; }&gt;</code>

--------------------


### getAvailableInternalMemorySize()

```typescript
getAvailableInternalMemorySize() => Promise<{ size: number; }>
```

**Returns:** <code>Promise&lt;{ size: number; }&gt;</code>

--------------------


### getUtmInfo()

```typescript
getUtmInfo() => Promise<{ utmInfo: any; }>
```

**Returns:** <code>Promise&lt;{ utmInfo: any; }&gt;</code>

--------------------


### clearUtmInfo()

```typescript
clearUtmInfo() => Promise<{ success: boolean; }>
```

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### getStorageVolumes()

```typescript
getStorageVolumes() => Promise<{ volumes: any[]; }>
```

**Returns:** <code>Promise&lt;{ volumes: any[]; }&gt;</code>

--------------------


### copyDirectory(...)

```typescript
copyDirectory(options: { sourceDirectory: string; destinationDirectory: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                                    |
| ------------- | ----------------------------------------------------------------------- |
| **`options`** | <code>{ sourceDirectory: string; destinationDirectory: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### renameDirectory(...)

```typescript
renameDirectory(options: { sourceDirectory: string; toDirectoryName: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                               |
| ------------- | ------------------------------------------------------------------ |
| **`options`** | <code>{ sourceDirectory: string; toDirectoryName: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### canWrite(...)

```typescript
canWrite(options: { directory: string; }) => Promise<{ canWrite: boolean; }>
```

| Param         | Type                                |
| ------------- | ----------------------------------- |
| **`options`** | <code>{ directory: string; }</code> |

**Returns:** <code>Promise&lt;{ canWrite: boolean; }&gt;</code>

--------------------


### getFreeUsableSpace(...)

```typescript
getFreeUsableSpace(options: { directory: string; }) => Promise<{ space: number; }>
```

| Param         | Type                                |
| ------------- | ----------------------------------- |
| **`options`** | <code>{ directory: string; }</code> |

**Returns:** <code>Promise&lt;{ space: number; }&gt;</code>

--------------------


### readFromAssets(...)

```typescript
readFromAssets(options: { filePath: string; }) => Promise<{ content: string; }>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ filePath: string; }</code> |

**Returns:** <code>Promise&lt;{ content: string; }&gt;</code>

--------------------


### copyFile(...)

```typescript
copyFile(options: { sourceDirectory: string; destinationDirectory: string; fileName: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                                                      |
| ------------- | ----------------------------------------------------------------------------------------- |
| **`options`** | <code>{ sourceDirectory: string; destinationDirectory: string; fileName: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### getApkSize()

```typescript
getApkSize() => Promise<{ size: number; }>
```

**Returns:** <code>Promise&lt;{ size: number; }&gt;</code>

--------------------


### verifyCaptcha(...)

```typescript
verifyCaptcha(options: { apiKey: string; }) => Promise<{ success: boolean; }>
```

| Param         | Type                             |
| ------------- | -------------------------------- |
| **`options`** | <code>{ apiKey: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### startActivityForResult(...)

```typescript
startActivityForResult(options: any) => Promise<any>
```

| Param         | Type             |
| ------------- | ---------------- |
| **`options`** | <code>any</code> |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### getAppAvailabilityStatus(...)

```typescript
getAppAvailabilityStatus(options: { appList: string[]; }) => Promise<{ availability: Record<string, boolean>; }>
```

| Param         | Type                                |
| ------------- | ----------------------------------- |
| **`options`** | <code>{ appList: string[]; }</code> |

**Returns:** <code>Promise&lt;{ availability: <a href="#record">Record</a>&lt;string, boolean&gt;; }&gt;</code>

--------------------


### openFileManager()

```typescript
openFileManager() => Promise<{ success: boolean; }>
```

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### Type Aliases


#### Record

Construct a type with a set of properties K of type T

<code>{ [P in K]: T; }</code>

</docgen-api>
