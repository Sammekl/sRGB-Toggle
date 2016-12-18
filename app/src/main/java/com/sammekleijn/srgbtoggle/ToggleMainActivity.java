package com.sammekleijn.srgbtoggle;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sammekleijn.srgbtoggle.util.SharedPreferenceHelper;

public class ToggleMainActivity extends AppCompatActivity {

    Switch switchToggle;
    SRGBService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_main);

        switchToggle = (Switch) findViewById(R.id.srgb_toggle);
        final SharedPreferenceHelper sharedPreferenceHelper = SharedPreferenceHelper.getInstance(this);

        service = new SRGBService();


        switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked && !sharedPreferenceHelper.getBoolean(SharedPreferenceHelper.SRGB_ON)) {
                    boolean successful = service.enableSRGB();
                    showSnackbar(successful);
                } else if (!isChecked) {
                    boolean successful = service.disableSRGB();
                    showSnackbar(successful);
                }
            }
        });

        getCurrentSRGBState();

    }

    private void getCurrentSRGBState() {
        boolean toggled = service.getCurrentSRGBState();

        if (toggled) {
            switchToggle.setChecked(true);
        } else {
            switchToggle.setChecked(false);
        }
    }

    private void showSnackbar(boolean successful) {
        String txt = successful && service.getCurrentSRGBState() ? "sRGB Enabled" : successful && !service.getCurrentSRGBState() ? "sRGB Disabled" : "Failed to get root access";
        Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
    }
}