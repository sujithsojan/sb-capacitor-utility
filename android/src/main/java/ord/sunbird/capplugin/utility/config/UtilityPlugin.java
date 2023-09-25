package ord.sunbird.capplugin.utility;


import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import org.sunbird.config.FileUtil;
import org.sunbird.config.ReflectionUtil;
import org.sunbird.storage.StorageUtil;
import org.sunbird.config.IntentUtil;
import org.sunbird.utm.InstallReferrerListener;
import org.sunbird.utm.PlayStoreInstallReferrer;

import org.sunbird.config.BuildConfigUtil;
import org.sunbird.config.DeviceSpecGenerator;
import org.sunbird.support.SunbirdFileHandler;

import com.getcapacitor.PluginResult;
import com.getcapacitor.annotation.PluginMethod;

import android.os.Environment;
import android.app.Activity;

import android.util.Log;
import android.os.Build;
import android.text.TextUtils;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.content.pm.PackageManager;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import com.getcapacitor.Logger;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.pm.ApplicationInfo;
import java.io.File;
import java.util.ArrayList; 
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "Utility")
public class UtilityPlugin extends Plugin {
    private Utility implementation = new Utility();
    private static final String SHARED_PREFERENCES_NAME = "org.ekstep.genieservices.preference_file";
    private PluginCall onActivityResultCallbackContext = null;
    public AppUpdateManager appUpdateManager;

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void isGoogleServicesAvailable(PluginCall call) {
    try {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getActivity());
        boolean isAvailable = result == ConnectionResult.SUCCESS;

        JSObject ret = new JSObject();
        ret.put("result", isAvailable);
        call.resolve(ret);
    } catch (Exception e) {
        call.reject(e.getMessage());
    }
}

@PluginMethod
    public void removeFile(PluginCall call) {
        final String appFlavor = BuildConfigUtil.getBuildConfigValue("org.sunbird.app", "FLAVOR");

        // Use getActivity() to obtain the Activity context if you are within an Activity or Fragment
        Activity activity = getActivity();

        if (activity != null) {
            String appName = activity.getString(UtilityPlugin.getIdOfResource("_app_name", "string"));

            // You can add error handling here if needed
            try {
                SunbirdFileHandler.removeFile(activity, appName, appFlavor);
                call.success(); // Call success if the operation was successful
            } catch (PackageManager.NameNotFoundException | IOException e) {
                call.reject("Error: " + e.getMessage()); // Call reject if there's an error
            }
        } else {
            call.reject("No activity found"); // Handle the case where getActivity() returns null
        }
    }

    @PluginMethod
    public void shareSunbirdConfigurations(PluginCall call) {
        String getUserCount = call.getString("getUserCount", "getUserCount");
        String getLocalContentCount = call.getString("getLocalContentCount", "getLocalContentCount");
        String filePath = null;

        try {
            final String packageName = getContext().getPackageName();
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(packageName, 0);
            final String versionName = packageInfo.versionName;
            final String appFlavor = BuildConfigUtil.getBuildConfigValue("org.sunbird.app", "FLAVOR");
            String appName = getContext().getString(UtilityPlugin.getIdOfResource("_app_name", "string"));

            filePath = SunbirdFileHandler.shareSunbirdConfigurations(
                packageName,
                versionName,
                appName,
                appFlavor,
                getContext(),
                getUserCount,
                getLocalContentCount
            );

            call.resolve(filePath); // Resolve with the file path on success
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
            call.reject("Error: " + e.getMessage()); // Reject with an error message on failure
        }
    }

        @PluginMethod
    public void makeEntryInSunbirdSupportFile(PluginCall call) {
        String filePath = null;

        try {
            final String packageName = getContext().getPackageName();
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(packageName, 0);
            final String versionName = packageInfo.versionName;
            final String appFlavor = BuildConfigUtil.getBuildConfigValue("org.sunbird.app", "FLAVOR");
            String appName = getContext().getString(UtilityPlugin.getIdOfResource("_app_name", "string"));

            filePath = SunbirdFileHandler.makeEntryInSunbirdSupportFile(
                packageName,
                getContext(),
                versionName,
                appName,
                appFlavor
            );

            call.resolve(filePath); // Resolve with the file path on success
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
            call.reject("Error: " + e.getMessage()); // Reject with an error message on failure
        }
    }

    @PluginMethod
    public void removeDirectory(PluginCall call) {
        try {
            final String directoryPath = call.getString("directoryPath");
            final String directoryName = call.getString("directoryName");

            File directory = new File(directoryPath).replace("file://", "");
            if (directory.exists() && directory.isDirectory()) {
                boolean success = FileUtil.rm(directory, directoryName);
                if (success) {
                    call.resolve();
                } else {
                    call.reject("Error while deleting");
                }
            } else {
                call.reject("Directory does not exist");
            }
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }


    @PluginMethod
    public void test(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.test(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void sample(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.test(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void getBuildConfigValue(PluginCall call) {
        Class<?> clazz = BuildConfigUtil.getBuildConfigClass(packageName);
        if (clazz == null) {
            call.reject("packageName, can not be null or empty.");
        }

        Object value = ReflectionUtil.getStaticFieldValue(clazz, property);
        if (value != null) {
            call.resolve(value.toString());
        } else {
            call.reject("Value Not found");
        }

    }

    @PluginMethod
    public void getBuildConfigValues(PluginCall call) {
        String packageName = call.getString("packageName");
        Class<?> clazz = BuildConfigUtil.getBuildConfigClass(packageName);
        if(clazz == null) {
            call.resolve("packageName, can not be null or empty.");
        }
        HashMap values = ReflectionUtil.getBuildConfigValues(clazz);
        JSONObject jsonObject = new JSONObject(values);
        call.reject(jsonObject.toString());
    }

    @PluginMethod
    public void openPlayStore(PluginCall call) {
        try {
            String appId = call.getString("appId");
            openGooglePlay(appId);  
            call.resolve(appId);
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }


     @PluginMethod
    public void getDeviceAPILevel(PluginCall call) {
     int apiLevel = Build.VERSION.SDK_INT;
       JSObject ret = new JSObject();
       ret.put("apiLevel", apiLevel);
       call.resolve(ret);
    }

     @PluginMethod
    public void checkAppAvailability(PluginCall call) {
         try {
            String packageName = call.getString("packageName");
            getActivity().getApplicationContext().getPackageManager().getApplicationInfo(packageName, 0);
            call.resolve(new JSObject().put("success", true));
        } catch (PackageManager.NameNotFoundException e) {
            call.resolve(new JSObject().put("success", false));

        }
}

 @PluginMethod
    public void getDownloadDirectoryPath(PluginCall call) {
     JSObject result = new JSObject();
     result.put("filePath", "file://" + String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/");
     call.resolve(result);
    }

 
   @PluginMethod
    public void exportApk(PluginCall call) {
        try {
            String destination = call.getString("destination").replace("file://", "");
            ApplicationInfo app = getActivity().getApplicationInfo();
            String filePath = app.sourceDir;

            // Append file
            File originalApk = new File(filePath);

            File tempFile;
            if (!TextUtils.isEmpty(destination)) {
                tempFile = new File(destination);
            } else {
                tempFile = new File(getActivity().getExternalCacheDir() + "/ExtractedApk");
            }
             // If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            // Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/"
                    + getActivity().getString(getIdOfResource("_app_name", "string")) + "_"
                    + BuildConfigUtil.getBuildConfigValue("org.sunbird.app", "REAL_VERSION_NAME").toString().replace(".","_") + ".apk");
            // If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            // Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            JSObject result = new JSObject();
            result.put("path", tempFile.getPath()); 
            call.resolve(result);  
        } catch (Exception ex) {
            call.reject(ex.getMessage());
        }
    }

    public static int getIdOfResource(String name, String resourceType) {
        int resourceId = getActivity().getResources().getIdentifier(
        name,
        resourceType,
        getActivity().getApplicationInfo().packageName
    );
    return resourceId;
    }

    @PluginMethod
    public void getDeviceSpec(PluginCall call) {
         try {
               call.resolve(new DeviceSpecGenerator().getDeviceSpec(getActivity()));
            } catch (Exception e) {
                call.reject(e.getMessage());
            }
    }

    @PluginMethod
public void getMetaData(PluginCall call) {
    try {
        JSONArray inputArray = call.getArray("inputArray");
        JSObject jsObject = new JSObject();
        for (int i = 0; i < inputArray.length(); i++) {
            JSObject eachItem = inputArray.getJSObject(i);
            File f = new File(eachItem.getString("path"));

            JSObject output = new JSObject();
            output.put("size", FileUtil.getFileSize(f));
            output.put("lastModifiedTime", f.lastModified());
            jsObject.put(eachItem.getString("identifier"), output);
        }
        call.resolve(jsObject);
    } catch (Exception e) {
        call.reject(e.getMessage());
    }
}


    @PluginMethod
    public void createDirectories(PluginCall call) {
       try {
            String parentDirectory = call.getString("parentDirectory");
            JSONArray identifiers = call.getArray("identifiers");
            JSONObject jsonObject = new JSONObject();
            for (int i=0;i<identifiers.length;i++){
                File f = new File(parentDirectory, identifiers[i]);
                if (!f.isDirectory()) {
                    f.mkdirs();
                }
                JSONObject output = new JSONObject();
                output.put("path","file://"+f.getPath()+ "/");
                jsonObject.put(identifiers[i], output);
            }
            call.resolve(jsonObject);
        } catch (Exception e) {
            call.reject("false");
        }
    }

    @PluginMethod
    public void writeFile(PluginCall call) {
        try {
            JSONArray mapList = call.getArray("mapList");
            for (int i=0;i<mapList.length();i++){
                JSONObject jsonObject = mapList.getJSONObject(i);
                String destinationPath = jsonObject.getString("path");
                String fileName = jsonObject.getString("fileName");
                String data = jsonObject.getString("data");
                FileUtil.write(destinationPath, fileName, data);
            }
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void getMetaData(PluginCall call) {
         try {
            JSONArray inputArray = call.getArray("inputArray");
            JSONObject jsonObject = new JSONObject();
            for (int i=0;i<inputArray.length();i++){
                JSONObject eachItem = inputArray.getJSONObject(i);
                File f = new File(eachItem.getString("path"));

                JSONObject output = new JSONObject();
                output.put("size",FileUtil.getFileSize(f));
                output.put("lastModifiedTime",f.lastModified());
                jsonObject.put(eachItem.getString("identifier"), output);
            }
            call.resolve(jsonObject);
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void getAvailableInternalMemorySize(PluginCall call) {
        try {
            JSObject result = new JSObject();
        result.put("availableInternalMemorySize", String.valueOf(DeviceSpecGenerator.getAvailableInternalMemorySize()));
        call.resolve(result);
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }
    
// @PluginMethod
// public void getUtmInfo(PluginCall call) {
//                     try {
//                         SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UtilityPlugin.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
//                         SharedPreferences.Editor editor = sharedPreferences.edit();
//                         editor.putString("campaign_parameters", String.valueOf(new JSONObject(properties)));

//                         if (isFirstTime) {
//                 PlayStoreInstallReferrer playStoreInstallreferrer = new PlayStoreInstallReferrer();
//                 playStoreInstallreferrer.start(getActivity(), new InstallReferrerListener() {
//                     public void onHandlerReferrer(Map<String, String> properties) {
//                         SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UtilityPlugin.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
//                         SharedPreferences.Editor editor = sharedPreferences.edit();
//                         editor.putString("campaign_parameters", String.valueOf((new JSONObject(properties))));

//                         call.resolve(new JSONObject(properties));
//                         splashSharedPreferences.edit().putBoolean("installed_referrer_api", false).apply();
//                         editor.commit();
//                     }
//                 });
//             } 
//         else {
//             JSObject emptyObject = new JSObject();
//             call.resolve(emptyObject);
//         }
//     } catch (Exception e) {
//         call.reject(e.getMessage());
//     }
// }


    @PluginMethod
    public void clearUtmInfo(PluginCall call) {
          try {
        Activity activity = this.getActivity();
        if (activity != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences(UtilityPlugin.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("campaign_parameters");
            editor.apply();
            call.resolve();
        } else {
            call.reject("No activity found");
        }
    } catch (Exception e) {
        call.reject("Error: " + e.getMessage());
    }
    }

     @PluginMethod
    public void getStorageVolumes(PluginCall call) {
        try {
             JSONArray storageVolumes = new JSONArray(StorageUtil.getStorageVolumes(getContext()));
        JSObject result = new JSObject();
        result.put("storageVolumes", storageVolumes.toString());
        call.resolve(result);
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }

    @PluginMethod
    public void copyDirectory(PluginCall call) {
        try {
            String sourceDirectory = call.getString("sourceDirectory").replace("file://", "");
            String destinationDirectory = call.getString("destinationDirectory").replace("file://", "");
            FileUtil.copyFolder(new File(sourceDirectory), new File(destinationDirectory));
            call.resolve();
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }

    @PluginMethod
    public void renameDirectory(PluginCall call) {
        try {
            String sourceDirectory = call.getString("sourceDirectory").replace("file://", "");
            String toDirectoryName = call.getString("toDirectoryName");
             File sourceFile = new File(sourceDirectory);
        File toDirectory = new File(toDirectoryName);

        if (sourceFile.renameTo(toDirectory)) {
            call.resolve();
        } else {
            call.reject("Failed to rename directory");
        }
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }

     @PluginMethod
    public void canWrite(PluginCall call) {
        try {
            String directory = call.getString("directory").replace("file://", "");
            boolean canWrite = new File(directory).canWrite();
            if(canWrite){
                call.resolve();
            }else{
                call.reject("Can't write to the folder");
            }
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void getUsableSpace(PluginCall call) {
        try {
            String directory = call.getString("directory").replace("file://", "");
            long freeUsableSpace = FileUtil.getFreeUsableSpace(new File(directory));
            JSObject result = new JSObject();
            result.put("freeUsableSpace", String.valueOf(freeUsableSpace));
            call.resolve(result);
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }

     @PluginMethod
    public void readFromAssets(PluginCall call)  {
                try {
                    String fileName = call.getString("fileName").replace("file:///android_asset/","");
                    String output = FileUtil.readFileFromAssets(getActivity().getAssets().open(fileName));
                    JSObject result = new JSObject();
                    result.put("output", output);
                     if (output != null) {
                        call.resolve(result); 
                    } else {
                        call.reject("File contents are null");
                    }
                } catch (Exception e) {
                    e.getMessage();
                    call.reject(e.getMessage());
                }
    }

     @PluginMethod
        public  void copyFile(PluginCall call)  {
                try {
            final String sourcePath = call.getString("sourcePath").replace("file://", "");
            final String destinationPath = call.getString("destinationPath").replace("file://", "");  
            final String fileName = call.getString("fileName");
                    File source = new File(sourcePath, fileName);
                    if (source.exists()) {
                        File dest = new File(destinationPath, fileName);
                        dest.getParentFile().mkdirs();
                        FileUtil.cp(source, dest);
                    }
                    call.resolve();
                } catch (Exception e) {
                    call.reject(e.getMessage());
                }
    }


     @PluginMethod
    public void getApkSize(PluginCall call) {
         try {
            ApplicationInfo app = getActivity().getApplicationInfo();
            String filePath = app.sourceDir;
            File originalApk = new File(filePath);
            JSObject result = new JSObject();
            result.put("fileSize", String.valueOf(originalApk.length()));
            call.resolve(result);
        } catch (Exception ex) {
            call.reject(ex.getMessage());
        }
    }


    @PluginMethod
    public void verifyCaptcha(PluginCall call) {
        try {
            String apiKey = call.getString("apiKey");
            verify(apiKey, call);
            JSObject ret = new JSObject();
            ret.put(apiKey, "verifyCaptcha");
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Error: " + e.getMessage());
        }
    }


    private void verify(String apiKey, PluginCall call) {
        if (apiKey.length() > 0) {
            SafetyNet.getClient(getContext())
                    .verifyWithRecaptcha(apiKey)
                    .addOnSuccessListener(response -> {
                        String userResponseToken = response.getTokenResult();
                        if (!userResponseToken.isEmpty()) {
                            JSObject result = new JSObject();
                            result.put("token", userResponseToken);
                            call.resolve(result);
                        } else {
                            call.reject("Response token was empty.");
                        }
                    })
                    .addOnFailureListener(e -> {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            String message = CommonStatusCodes.getStatusCodeString(statusCode);
                            call.reject(message);
                        } else {
                            call.reject(e.getMessage());
                        }
                    });
        } else {
            call.reject("Verify called without providing a Site Key");
        }
    }


    @PluginMethod
public void startActivityForResult(PluginCall call) {
    try {
        JSObject args = call.getObject("args"); // Use getObject to get a JSON object
        if (!args.has("requestCode")) {
            call.reject("Missing 'requestCode' parameter");
            return;
        }

        int requestCode = args.getInteger("requestCode");
        Intent intent = IntentUtil.populateIntent(args);
        
        // Store the callback context for later use in onActivityResult
        this.onActivityResultCallbackContext = call;

        // Start the activity for result
        startActivityForResult(call, intent, requestCode);
    } catch (JSONException e) {
        call.reject("Error: " + e.getMessage());
    }
}




    @PluginMethod
    public void getAppAvailabilityStatus(PluginCall call) {
        JSONArray appList = call.getArray("appList");
        final PackageManager packageManager = getContext().getPackageManager();
        List<ApplicationInfo> packagesList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> packageNameList = new ArrayList();
        for(ApplicationInfo p: packagesList) {
            packageNameList.add(p.packageName);
        }
        JSONObject availableAppsMap = new JSONObject();
        try {
            for (int i = 0; i < appList.length(); i++) {
                availableAppsMap.put(appList.getString(i), packageNameList.contains(appList.getString(i)));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void startActivity(Intent intent, int requestCode) {
        if (intent.resolveActivityInfo(this.getActivity().getPackageManager(), 0) != null) {
            this.getActivity().startActivityForResult(intent, requestCode);
        }
    }



   @PluginMethod
public void openFileManager(PluginCall call) {
    try {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString());
        intent.setDataAndType(uri, "*/*");
        startActivityForResult(call, intent, 0);
    } catch (Exception e) {
        call.reject("Error: " + e.getMessage());
    }
}
}
