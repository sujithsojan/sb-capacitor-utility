package org.sunbird.support;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import org.sunbird.config.DeviceSpecGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SunbirdFileHandler {

    private static final String SUPPORT_FILE = "_support.txt";
    private static final String CONFIG_FILE = ".txt";
    private static final String SUPPORT_DIRECTORY = "support";
    private static final String DIRECTORY_NAME_SEPERATOR = "-";
    private static final String SEPERATOR = "~";

    public static String makeEntryInSunbirdSupportFile(String packageName, Context context, String versionName, String appName,
                                                       String appFlavor) throws IOException {
        File supportDirectory = SunbirdFileHandler.getRequiredDirectory(context,
                appName + DIRECTORY_NAME_SEPERATOR + appFlavor + DIRECTORY_NAME_SEPERATOR + SUPPORT_DIRECTORY);
        String filePath = supportDirectory + "/" + appName + DIRECTORY_NAME_SEPERATOR + appFlavor + SUPPORT_FILE;
        // for the first time when file does not exists
        if (!SunbirdFileHandler.checkIfFileExists(filePath)) {
            SunbirdFileHandler.createFileInTheDirectory(filePath);
            String firstEntry = versionName + SEPERATOR + System.currentTimeMillis() + SEPERATOR + "1";
            SunbirdFileHandler.saveToFile(filePath, firstEntry);
        } else {
            String lastLineOfFile = SunbirdFileHandler.readLastLineFromFile(filePath);
            if (!isNullOrEmpty(lastLineOfFile)) {
                String[] partsOfLastLine = lastLineOfFile.split(SEPERATOR);

                if (versionName.equalsIgnoreCase(partsOfLastLine[0])) {
                    // just remove the last line from the file and update it their
                    SunbirdFileHandler.removeLastLineFromFile(filePath);

                    String previousOpenCount = partsOfLastLine[2];
                    int count = Integer.parseInt(previousOpenCount);
                    count++;
                    String updateEntry = versionName + SEPERATOR + partsOfLastLine[1] + SEPERATOR + count;
                    SunbirdFileHandler.saveToFile(filePath, updateEntry);
                } else {
                    // make a new entry to the file
                    String newEntry = versionName + SEPERATOR + System.currentTimeMillis() + SEPERATOR + "1";
                    SunbirdFileHandler.saveToFile(filePath, newEntry);
                }
            } else {
                // make a new entry to the file
                String newEntry = versionName + SEPERATOR + System.currentTimeMillis() + SEPERATOR + "1";
                SunbirdFileHandler.saveToFile(filePath, newEntry);
            }
        }

        return filePath;
    }

    public static String shareSunbirdConfigurations(String packageName, String versionName, String appName,
                                                    String appFlavor, Context context, String getUserCount, String getLocalContentCount) throws IOException {
        File sunbirdSupportDirectory = SunbirdFileHandler.getRequiredDirectory(context,
                appName + DIRECTORY_NAME_SEPERATOR + appFlavor + DIRECTORY_NAME_SEPERATOR + SUPPORT_DIRECTORY);
        String filePath = sunbirdSupportDirectory + "/" + "Details_" + getDeviceID(context) + "_" + System.currentTimeMillis() + CONFIG_FILE;

        SunbirdFileHandler.createFileInTheDirectory(filePath);
        String firstEntry = versionName + SEPERATOR + System.currentTimeMillis() + SEPERATOR + "1";
        String sharedData = fetchDeviceData(context, appName, appFlavor, getUserCount , getLocalContentCount) + firstEntry;
        SunbirdFileHandler.saveToFile(filePath, sharedData);

        return filePath;
    }

    private static String getDeviceID(Context context) {
        String android_id = null;
        try {
            android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            return SunbirdFileHandler.checksum(android_id);
        } catch (Exception e) {
            return android_id;
        }
    }

    private static String getCrossWalkVersion(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo("org.xwalk.core", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo == null ? "na" : pInfo.versionName;
    }

    private static String checksum(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sha1hash.length; i++) {
            sb.append(Integer.toString((sha1hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private static String fetchDeviceData(Context context, String appName, String appFlavor, String getUserCount, String getLocalContentCount) {
        StringBuilder configString = new StringBuilder();

        // add DeviceId
        configString.append("did:");
        configString.append(getDeviceID(context));
        configString.append("||");
        // add Device Model
        configString.append("mdl:");
        configString.append(DeviceSpecGenerator.getDeviceModel());
        configString.append("||");

        // add device make
        configString.append("mak:");
        configString.append(DeviceSpecGenerator.getDeviceMaker());
        configString.append("||");

        // add Crosswalk version
        configString.append("cwv:");
        configString.append(getCrossWalkVersion(context));
        configString.append("||");

        //add total user on device
        configString.append("uno:");
        configString.append(getUserCount);
        configString.append("||");

        // add count of content of device
        configString.append("cno:");
        configString.append(getLocalContentCount);
        configString.append("||");

        // add Android OS version
        configString.append("dos:");
        configString.append(DeviceSpecGenerator.getOSVersion());
        configString.append("||");

         // add Webview version
         configString.append("wv:");
         configString.append(DeviceSpecGenerator.getCurrentWebViewVersionName(context));
         configString.append("||");

        // add Screen Resolution
        configString.append("res:");
        configString.append(DeviceSpecGenerator.getScreenWidth(context) + "x" + DeviceSpecGenerator.getScreenHeight(context));
        configString.append("||");

        // add Screen DPI
        configString.append("dpi:");
        configString.append(DeviceSpecGenerator.getDeviceDensityInDpi(context));
        configString.append("||");

        // add Total disk space
        configString.append("tsp:");
        configString.append(DeviceSpecGenerator.getTotalExternalMemorySize() + DeviceSpecGenerator.getTotalInternalMemorySize());
        configString.append("||");

        // add free space
        configString.append("fsp:");
        configString.append(DeviceSpecGenerator.getAvailableExternalMemorySize(context) + DeviceSpecGenerator.getAvailableInternalMemorySize());
        configString.append("||");

        // add current timestamp
        configString.append("ts:");
        configString.append(System.currentTimeMillis());
        configString.append("||");

        //calculate checksum before adding pipes
        String checksum = encodeToBase64Uri(org.sunbird.config.CryptoUtil.generateHMAC(configString.toString().trim(),
                getDeviceID(context).getBytes(), JWTokenType.HS256.getAlgorithmName()));

        //add HMAC
        configString.append("csm:");
        configString.append(checksum);
        configString.append("||");

        File sunbirdSupportDirectory = SunbirdFileHandler.getRequiredDirectory(context,
                appName + DIRECTORY_NAME_SEPERATOR + appFlavor +
                        DIRECTORY_NAME_SEPERATOR + SUPPORT_DIRECTORY);
        String fileVersion = sunbirdSupportDirectory + "/" + appName + DIRECTORY_NAME_SEPERATOR + appFlavor
                + SUPPORT_FILE;
        String versionHistory = SunbirdFileHandler.readFile(fileVersion);
        configString.append("sv: ");
        configString.append(versionHistory);

        return configString.toString();
    }

    private static String encodeToBase64Uri(byte[] data) {
        //The 11 magic number indicates that it should be
        // base64Uri and without wrap and with the = at the end
        return Base64Util.encodeToString(data, 11);
    }

    public static String readFile(String filePath) {
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + ",");
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }

        if (isNullOrEmpty(line)) {
            line = line.substring(0, line.length() - 1);
            return line;
        } else {
            return line;
        }
    }

    private static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static File getRequiredDirectory(Context context, String directoryName) {
        File file = context.getExternalFilesDir(null);
        File directory = null;
        if (file!= null) {
            directory = new File(context.getExternalFilesDir(null).getAbsolutePath() + '/' + directoryName);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }
        }
        return directory;
    }

    public static void removeLastLineFromFile(final String filePath) throws IOException {
        final List<String> lines = new LinkedList<String>();
        final Scanner reader = new Scanner(new FileInputStream(filePath), "UTF-8");
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine().concat(System.getProperty("line.separator")));
        }
        reader.close();
        lines.remove(lines.size() - 1);
        final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        for (final String line : lines)
            writer.write(line);
        writer.flush();
        writer.close();
    }

    public static boolean checkIfFileExists(String filePath) {
        File fileToCheck = new File(filePath);
        if (fileToCheck.exists()) {
            return true;
        }

        return false;
    }

    public static void createFileInTheDirectory(String filePath) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileOutputStream out = new FileOutputStream(f);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the data to the file
     *
     * @param filePath
     * @param data
     */
    public static void saveToFile(String filePath, String data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
            fileOutputStream.write((data.concat(System.getProperty("line.separator"))).getBytes());
        } catch (FileNotFoundException ex) {
            System.out.print(ex.getMessage());
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
     * Read last line from a given file
     *
     * @param filePath
     * @return
     */
    public static String readLastLineFromFile(String filePath) {
        String currentLine = null;
        String lastLine = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                lastLine = currentLine;
            }
            fileInputStream.close();

            bufferedReader.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        return lastLine;
    }

    public static void removeFile(Context context, String appName, String appFlavor) {
        File supportDirectory = SunbirdFileHandler.getRequiredDirectory(context,
                appName + DIRECTORY_NAME_SEPERATOR + appFlavor + DIRECTORY_NAME_SEPERATOR + SUPPORT_DIRECTORY);
        for (File fileToDelete : supportDirectory.listFiles()) {
            if (fileToDelete.getName().startsWith("Details_")) {
                fileToDelete.delete();
            }
        }
    }
}