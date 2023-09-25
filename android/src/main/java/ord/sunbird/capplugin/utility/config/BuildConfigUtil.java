package org.sunbird.config;


/**
 * Created on 4/18/2017.
 *
 * @author anil
 */
public class BuildConfigUtil {

    public static Class<?> getBuildConfigClass(String packageName) {
        return ReflectionUtil.getClass(packageName + ".BuildConfig");
    }

    public static <T> T getBuildConfigValue(String packageName, String property) {
        Class<?> clazz = getBuildConfigClass(packageName);
        if (clazz == null) {
            throw new IllegalStateException("packageName, can not be null or empty.");
        }

        Object value = ReflectionUtil.getStaticFieldValue(clazz, property);
        return (T) value;
    }
}