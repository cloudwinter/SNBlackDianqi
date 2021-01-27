package com.sn.blackdianqi;

import android.app.Application;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class RunningContext {

    public static boolean mock = false;
    public static boolean debug = false;



    /**
     * 动态申请权限request_code
     */
    public static final int PERMISSIONS_REQUEST_CODE = 300;


    /**
     * 全局线程池
     */
    private static ThreadPoolExecutor sThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    /**
     * 登录用户锁
     */
    private static byte[] sLoginInfoLock = new byte[0];

    /**
     * 全局静态context
     */
    public static Context sAppContext = null;


    public static JobScheduler jobScheduler;


    public static void init(Application app) {
        sAppContext = app.getApplicationContext();
    }



    /**
     * 全局线程池
     *
     * @return
     */
    public static ThreadPoolExecutor threadPool() {
        return sThreadPool;
    }


    /**
     * 校验网络连接
     *
     * @return
     */
    public static boolean checkNetWork() {
        return isNetworkAvailable(sAppContext);
    }


    /**
     * 检查网络权限
     *
     * @param context
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接　　　　　　
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 获取版本号Name
     */
    public static String getVersionName() {
        try {
            PackageManager manager = sAppContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(sAppContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    /**
     * 获取版本号Code
     */
    public static long getVersionCode() {
        try {
            PackageManager manager = sAppContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(sAppContext.getPackageName(), 0);
            long version = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                version = info.getLongVersionCode();
            } else {
                version = info.versionCode;
            }
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
