package kody.com.trudigitalexample.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kody.com.trudigitalexample.R;
import kody.com.trudigitalexample.model.App;
import kody.com.trudigitalexample.utils.Strings;
import kody.com.trudigitalexample.views.AppAdapter;
import kody.com.trudigitalexample.views.AppItem;

public class MainActivity extends AppCompatActivity {

    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appAdapter = new AppAdapter((LinearLayout) findViewById(R.id.container),
                (HorizontalScrollView) findViewById(R.id.app_scroll_view),
                getBaseContext(),
                getAppsArrayList());
    }

    private ArrayList<App> getAppsArrayList() {
        ArrayList<App> appArrayList = new ArrayList<>();

        appArrayList.add(new App("Twist", Strings.twistShortDescription, Strings.twistLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_twist),
                R.drawable.ic_launcher_twist, R.drawable.twist, R.raw.twist));

        appArrayList.add(new App("Bouncing Ball", Strings.bouncingBallShortDescription, Strings.bouncingBallLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_bouncing_ball),
                R.drawable.ic_launcher_bouncing_ball, R.drawable.bouncing_ball, R.raw.bouncing_ball));

        appArrayList.add(new App("Crack the Code", Strings.crackTheCodeShortDescription, Strings.crackTheCodeLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_crack_the_code),
                R.drawable.ic_launcher_crack_the_code, R.drawable.ctc, R.raw.crack_the_code));

        appArrayList.add(new App("Touch Particles", Strings.lwpShortDescription, Strings.lwpLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_finger_particles_lwp),
                R.drawable.ic_launcher_finger_particles_lwp, R.drawable.lwp, R.raw.finger_particles_lwp));

        appArrayList.add(new App("MinerMan", Strings.minermanShortDescription, Strings.minermanLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_minerman),
                R.drawable.ic_launcher_minerman, R.drawable.minerman, R.raw.minerman));

        appArrayList.add(new App("Pebble", Strings.pebbleShortDescription, Strings.pebbleLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_pebble),
                R.drawable.ic_launcher_pebble, R.drawable.pebble, R.raw.pebble));

        appArrayList.add(new App("Silver Rush", Strings.silverRushShortDescription, Strings.silverRushLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_silver_rush),
                R.drawable.ic_launcher_silver_rush, R.drawable.silver_rush, R.raw.silver_rush));

        appArrayList.add(new App("Twist", Strings.twistShortDescription, Strings.twistLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_twist),
                R.drawable.ic_launcher_twist, R.drawable.twist, R.raw.twist));

        appArrayList.add(new App("Bouncing Ball", Strings.bouncingBallShortDescription, Strings.bouncingBallLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_bouncing_ball),
                R.drawable.ic_launcher_bouncing_ball, R.drawable.bouncing_ball, R.raw.bouncing_ball));

        appArrayList.add(new App("Crack the Code", Strings.crackTheCodeShortDescription, Strings.crackTheCodeLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_crack_the_code),
                R.drawable.ic_launcher_crack_the_code, R.drawable.ctc, R.raw.crack_the_code));

        appArrayList.add(new App("Touch Particles", Strings.lwpShortDescription, Strings.lwpLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_finger_particles_lwp),
                R.drawable.ic_launcher_finger_particles_lwp, R.drawable.lwp, R.raw.finger_particles_lwp));

        appArrayList.add(new App("MinerMan", Strings.minermanShortDescription, Strings.minermanLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_minerman),
                R.drawable.ic_launcher_minerman, R.drawable.minerman, R.raw.minerman));

        appArrayList.add(new App("Pebble", Strings.pebbleShortDescription, Strings.pebbleLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_pebble),
                R.drawable.ic_launcher_pebble, R.drawable.pebble, R.raw.pebble));

        appArrayList.add(new App("Silver Rush", Strings.silverRushShortDescription, Strings.silverRushLongDescription,
                getResources().getDrawable(R.drawable.ic_launcher_silver_rush),
                R.drawable.ic_launcher_silver_rush, R.drawable.silver_rush, R.raw.silver_rush));

        return appArrayList;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getSource() == InputDevice.SOURCE_GAMEPAD) {
            getDirectionText(event.getKeyCode());
        } else if (event.getSource() == InputDevice.SOURCE_KEYBOARD && event.getAction() == KeyEvent.ACTION_UP) {
            getDirectionText(event.getKeyCode());
        }
        return true;
    }

    private String getDirectionText(int keycode) {
        switch (keycode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                appAdapter.selectRight();
                return "RIGHT";
            case KeyEvent.KEYCODE_DPAD_DOWN:
                appAdapter.selectDown();
                return "DOWN";
            case KeyEvent.KEYCODE_DPAD_LEFT:
                appAdapter.selectLeft();
                return "LEFT";
            case KeyEvent.KEYCODE_DPAD_UP:
                appAdapter.selectUp();
                return "UP";
            case KeyEvent.KEYCODE_BUTTON_B:
                return "B";
            case KeyEvent.KEYCODE_BUTTON_A:
                return "A";
            case KeyEvent.KEYCODE_BUTTON_SELECT:
                selectApp();
                return "OK";
            case KeyEvent.KEYCODE_R:
                appAdapter.selectRight();
                return "RIGHT";
            case KeyEvent.KEYCODE_L:
                appAdapter.selectLeft();
                return "LEFT";
            case KeyEvent.KEYCODE_U:
                appAdapter.selectUp();
                return "UP";
            case KeyEvent.KEYCODE_D:
                appAdapter.selectDown();
                return "DOWN";
            case KeyEvent.KEYCODE_X: // A
                return "A";
            case KeyEvent.KEYCODE_Y: // B
                return "B";
            case KeyEvent.KEYCODE_S: // OK
                selectApp();
                return "OK";
            case KeyEvent.KEYCODE_E:
                return "BACK";
            case KeyEvent.KEYCODE_4:
                return "HOME";
            case KeyEvent.KEYCODE_6:
                return "RESTART";
            case KeyEvent.KEYCODE_3:
                return "STAR";
            case KeyEvent.KEYCODE_C:
                return "REWIND";
            case KeyEvent.KEYCODE_8:
                return "FAST FORWARD";
            case KeyEvent.KEYCODE_A:
                return "PLAY/PAUSE";
        }
        return "";
    }

    public ArrayList getGameControllerIds() {
        ArrayList gameControllerDeviceIds = new ArrayList();
        int[] deviceIds = InputDevice.getDeviceIds();
        for (int deviceId : deviceIds) {
            System.out.println(deviceId);
            InputDevice dev = InputDevice.getDevice(deviceId);
            int sources = dev.getSources();

            // Verify that the device has gamepad buttons, control sticks, or both.
            if (((sources & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD)
                    || ((sources & InputDevice.SOURCE_JOYSTICK)
                    == InputDevice.SOURCE_JOYSTICK)) {
                // This device is a game controller. Store its device ID.
                if (!gameControllerDeviceIds.contains(deviceId)) {
                    gameControllerDeviceIds.add(deviceId);
                }
            }
        }
        return gameControllerDeviceIds;
    }

    private void selectApp() {
        Intent otherActivity = new Intent(getBaseContext(), AppDetails.class);
        otherActivity.putExtra("drawable", appAdapter.getSelectedAppItem().getApp().getIconId());
        otherActivity.putExtra("splash", appAdapter.getSelectedAppItem().getApp().getSplashId());
        otherActivity.putExtra("apk_res", appAdapter.getSelectedAppItem().getApp().getApkId());
        otherActivity.putExtra("title", appAdapter.getSelectedAppItem().getApp().getName());
        otherActivity.putExtra("short", appAdapter.getSelectedAppItem().getApp().getShortDescription());
        otherActivity.putExtra("long", appAdapter.getSelectedAppItem().getApp().getLongDescription());
        startActivity(otherActivity);
    }

    public void onClick(View view) {
        AppItem appItem = appAdapter.getSelectedAppItem();
        appAdapter.selectApp(view);
        if (appItem.equals(appAdapter.getSelectedAppItem())) {
            selectApp();
        }
    }
}
