package com.sammekleijn.srgbtoggle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.sammekleijn.srgbtoggle.util.RootCommandExecutor;
import com.sammekleijn.srgbtoggle.util.SharedPreferenceHelper;

/**
 * Created by sammekleijn on 18/12/2016.
 */
public class SRGBService extends Service {

    private static final String sRGBCommandDisabler = "echo 0 > /sys/class/graphics/fb0/srgb";
    private static final String sRGBCommandEnabler = "echo 1 > /sys/class/graphics/fb0/srgb";

    private static SharedPreferenceHelper sharedPreferenceHelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (getCurrentSRGBState()) {
            enableSRGB();
        } else {
            disableSRGB();
        }
    }

    public boolean enableSRGB() {
        sharedPreferenceHelper = SharedPreferenceHelper.getInstance(this);
        sharedPreferenceHelper.setSRGBToggle(true);
        return RootCommandExecutor.runAsRoot(sRGBCommandEnabler);
    }

    public boolean disableSRGB() {
        sharedPreferenceHelper = SharedPreferenceHelper.getInstance(this);
        sharedPreferenceHelper.setSRGBToggle(false);
        return RootCommandExecutor.runAsRoot(sRGBCommandDisabler);
    }

    public boolean getCurrentSRGBState() {
        return SharedPreferenceHelper.getInstance(this).getBoolean(SharedPreferenceHelper.SRGB_ON);
    }
}
