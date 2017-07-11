package app.uninstall.udmatech.com.uninstall;

/**
 * Created by User2 on 26-10-2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


public class IncomingSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Log.e("DDD", key + "=" + bundle.get(key));
        }
    }
}