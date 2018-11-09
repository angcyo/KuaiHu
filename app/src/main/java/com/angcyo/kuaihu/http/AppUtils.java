package com.angcyo.kuaihu.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.angcyo.uiview.less.utils.utilcode.utils.StringUtils;

import java.util.UUID;

public class AppUtils {
    private static Context mContext;
    private static Thread mUiThread;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void init(Context context) {
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isUIThread() {
        return Thread.currentThread() == mUiThread;
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunnable(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }

    public static String getSDKVersion() {
        return String.valueOf(VERSION.SDK_INT);
    }

    public static String getOSVersion() {
        return VERSION.RELEASE;
    }

    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    public static String getAppVersionName(Context context, String packageName) {
        if (StringUtils.isSpace(packageName)) {
            return null;
        }
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            if (pi != null) {
                return pi.versionName;
            }
            return null;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context.getPackageName());
    }

    public static int getAppVersionCode(Context context, String packageName) {
        if (StringUtils.isSpace(packageName)) {
            return 1;
        }
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            if (pi != null) {
                return pi.versionCode;
            }
            return 1;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            return model.trim().replaceAll("\\s*", "");
        }
        return "";
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID(Context context) {
//        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
//        return TextUtils.isEmpty(deviceId) ? Secure.getString(context.getContentResolver(), "android_id") : deviceId;
        return UUID.randomUUID().toString();
    }

    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }
}