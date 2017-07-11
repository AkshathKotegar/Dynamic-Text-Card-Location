package app.uninstall.udmatech.com.uninstall;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by User2 on 04-04-2017.
 */

class CustomBroadcastReceiver extends BroadcastReceiver {
    ActivityManager mActivityManager =null;
    /**
     * This method captures the event when a package has been removed
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {

        System.out.println("Hello from CustomBroadcastReceiver");
        if (intent != null) {
            String action = intent.getAction();
            System.out.println("L1123 : "+action);

            ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;
            String packageName = topActivity.getPackageName();
            String className = topActivity.getClassName();
            Log.v("exe", "packageName" + packageName);
            Log.v("exe", "className" + className);

            if ("com.android.packageinstaller".equals(packageName)
                    && "com.android.packageinstaller.UninstallerActivity".equals(className)) {
//Do anything you want here
                if (action.equals(intent.ACTION_PACKAGE_REMOVED))   {
                    //Log the event capture in the log file ...
                    System.out.println("The package has been removed");
                }
            }

        }
    }
}
