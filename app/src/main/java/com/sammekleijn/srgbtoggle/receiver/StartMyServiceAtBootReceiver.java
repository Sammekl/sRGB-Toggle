package com.sammekleijn.srgbtoggle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sammekleijn.srgbtoggle.SRGBService;

public class StartMyServiceAtBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, SRGBService.class);
            context.startService(serviceIntent);
        }
    }
}