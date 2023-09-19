package ord.sunbird.capplugin.utility;

import android.util.Log;
import android.os.Build;

import com.getcapacitor.Logger;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.pm.ApplicationInfo;
import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "Utility")
public class UtilityPlugin extends Plugin {
    private Utility implementation = new Utility();

    

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
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

// @PluginMethod
// public void getBuildConfigValue(PluginCall call) {
//     try {
//         String packageName = call.getString("packageName");
//         String property = call.getString("property");

//         Class<?> clazz = getBuildConfigClass(packageName);

//         if (clazz == null) {
//             call.reject("packageName can not be null or empty.");
//             return;
//         }

//         Object value = ReflectionUtil.getStaticFieldValue(clazz, property);

//         if (value != null) {
//             call.resolve(value.toString());
//         } else {
//             call.reject("Value Not found");
//         }
//     } catch (Exception e) {
//         call.reject(e.getMessage(), e);
//     }
// }

    @PluginMethod
    public void getBuildConfigParam(PluginCall call) {
        String packageName = call.getString("package");
        String propertyName = call.getString("property");

            JSObject ret = new JSObject();
            ret.put("value", "value");
            call.resolve(ret);
    }

    // @PluginMethod
    // public void getBuildConfigValues(PluginCall call) {
    //     try {
    //         String packageName = call.getString("packageName");
    //         Class<?> clazz = getBuildConfigClass(packageName);
    //         if (clazz == null) {
    //             call.reject("packageName can not be null or empty.");
    //             return;
    //         }
    //         HashMap<String, Object> values = ReflectionUtil.getBuildConfigValues(clazz);
    //         JSObject jsonObject = new JSObject(values);
    //         jsonObject.put("value", packageName);
    //         call.resolve(jsonObject);
    //     } catch (Exception e) {
    //         call.reject(e.getMessage(), e);
    //     }
    // }

    @PluginMethod
    public void openPlayStore(PluginCall call) {
        String appId = call.getString("appId");
        JSObject ret = new JSObject();
            ret.put("values", "value");
            call.resolve(ret);
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
        String urlToCheck = call.getString("url");
            JSObject ret = new JSObject();
            ret.put("available", "available");
            call.resolve(ret);
}

 @PluginMethod
    public void getDownloadDirectoryPath(PluginCall call) {
        String defaultDownloadPath = "/downloads";

        JSObject ret = new JSObject();
        ret.put("path", "defaultDownloadPath");
        call.resolve(ret);
    }
 
   @PluginMethod
    public void exportApk(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("exportApk", "exportApk");
        call.resolve(ret);
    }

    @PluginMethod
    public void getDeviceSpec(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getDeviceSpec", "getDeviceSpec");
        call.resolve(ret);
    }

    @PluginMethod
    public void createDirectories(PluginCall call) {
       JSObject ret = new JSObject();
        ret.put("createDirectories", "createDirectories");
        call.resolve(ret); 
    }

    @PluginMethod
    public void writeFile(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("writeFile", "writeFile");
        call.resolve(ret);
    }

    @PluginMethod
    public void getMetaData(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("writeFile", "writeFile");
        call.resolve(ret);
    }

    @PluginMethod
    public void getAvailableInternalMemorySize(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getAvailableInternalMemorySize", "getAvailableInternalMemorySize");
        call.resolve(ret);
    }
    
    @PluginMethod
    public void getUtmInfo(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getUtmInfo", "getUtmInfo");
        call.resolve(ret);
    }

    @PluginMethod
    public void clearUtmInfo(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("clearUtmInfo", "clearUtmInfo");
        call.resolve(ret);
    }

    @PluginMethod
    public void getStorageVolumes(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getStorageVolumes", "getStorageVolumes");
        call.resolve(ret);
    }

    @PluginMethod
    public void copyDirectory(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("copyDirectory", "copyDirectory");
        call.resolve(ret);
    }

     @PluginMethod
    public void renameDirectory(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("renameDirectory", "renameDirectory");
        call.resolve(ret);
    }

     @PluginMethod
    public void canWrite(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("canWrite", "canWrite");
        call.resolve(ret);
    }

     @PluginMethod
    public void getFreeUsableSpace(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getFreeUsableSpace", "getFreeUsableSpace");
        call.resolve(ret);
    }

     @PluginMethod
    public void readFromAssets(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("readFromAssets", "readFromAssets");
        call.resolve(ret);
    }

     @PluginMethod
    public void copyFile(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("copyFile", "copyFile");
        call.resolve(ret);
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
                    String apiKey = call.getString("apiKey");
        JSObject ret = new JSObject();
        ret.put("verifyCaptcha", "verifyCaptcha");
        call.resolve(ret);
    }

    @PluginMethod
    public void startActivityForResult(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("startActivityForResult", "startActivityForResult");
        call.resolve(ret);
    }

    @PluginMethod
    public void getAppAvailabilityStatus(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("getAppAvailabilityStatus", "getAppAvailabilityStatus");
        call.resolve(ret);
    }

    @PluginMethod
    public void openFileManager(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("openFileManager", "openFileManager");
        call.resolve(ret);
    }
}
