package kody.com.trudigitalexample.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import kody.com.trudigitalexample.R;
import kody.com.trudigitalexample.utils.OnResponseListener;
import kody.com.trudigitalexample.utils.Utils;
import kody.com.trudigitalexample.views.SquareImageView;

/**
 * Created by kody on 2/6/16.
 * can be used by kody and people in [kody}]
 */
public class AppDetails extends AppCompatActivity {

    private File file;

    private boolean tryToInstallApk;
    private static Handler mHandler;

    private static final int DONE_LOADING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent recievedIntent = getIntent();
        int drawable = recievedIntent.getIntExtra("drawable", R.drawable.ic_launcher_bouncing_ball);
        int splash = recievedIntent.getIntExtra("splash", R.drawable.silver_rush);
        int apkId = recievedIntent.getIntExtra("apk_res", R.raw.twist);
        String title = recievedIntent.getStringExtra("title");
        String shortDescription = recievedIntent.getStringExtra("short");
        String longDescription = recievedIntent.getStringExtra("long");
        SquareImageView view = (SquareImageView) findViewById(R.id.app_image);
        view.setImageDrawable(getResources().getDrawable(drawable));
        ((ImageView) findViewById(R.id.splash)).setImageDrawable(getResources().getDrawable(splash));

        ((TextView) findViewById(R.id.app_title)).setText(title);
        ((TextView) findViewById(R.id.app_description_short)).setText(shortDescription);
        ((TextView) findViewById(R.id.app_description_long)).setText(longDescription);

        LoadApkActivity loadApkActivity = new LoadApkActivity(new OnResponseListener() {
            @Override
            public void onResponse(File tempFile) {
                final Handler mHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == DONE_LOADING) {
                            if (tryToInstallApk){
                                installAPK();
                            }
                        }
                        super.handleMessage(msg);
                    }
                };
                if (tempFile != null) {
                    file = tempFile;
                    mHandler.sendEmptyMessage(DONE_LOADING);
                }
            }
        }, apkId);
        loadApkActivity.execute();
    }

    public void install(View v) {
        installAPK();
    }

    private void installAPK() {
        if (file == null) {
            tryToInstallApk = true;
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            findViewById(R.id.progressBar).setVisibility(View.GONE);
        } catch (RuntimeException e) {

        }
        startActivity(intent);
    }

    private class LoadApkActivity extends AsyncTask<Void, Void, Boolean> {

        private OnResponseListener mOnResponseListener;
        private int id;

        public LoadApkActivity(OnResponseListener onResponseListener, int apkId) {
            this.mOnResponseListener = onResponseListener;
            this.id = apkId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            File tempFile = null;
            try {
                tempFile = Utils.openResourceFile(getBaseContext(), id, "yourapkname.apk");
                if (mOnResponseListener != null) {
                    mOnResponseListener.onResponse(tempFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (mOnResponseListener != null) {
                mOnResponseListener.onResponse(file);
            }
        }

        @Override
        protected void onCancelled() {
        }
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
                return "RIGHT";
            case KeyEvent.KEYCODE_DPAD_DOWN:
                return "DOWN";
            case KeyEvent.KEYCODE_DPAD_LEFT:
                return "LEFT";
            case KeyEvent.KEYCODE_DPAD_UP:
                return "UP";
            case KeyEvent.KEYCODE_BUTTON_B:
                finish();
                return "B";
            case KeyEvent.KEYCODE_BUTTON_A:
                installAPK();
                return "A";
            case KeyEvent.KEYCODE_BUTTON_SELECT:
                installAPK();
                return "OK";
            case KeyEvent.KEYCODE_R:
                return "RIGHT";
            case KeyEvent.KEYCODE_L:
                return "LEFT";
            case KeyEvent.KEYCODE_U:
                return "UP";
            case KeyEvent.KEYCODE_D:
                return "DOWN";
            case KeyEvent.KEYCODE_X: // A
                installAPK();
                return "A";
            case KeyEvent.KEYCODE_Y: // B
                return "B";
            case KeyEvent.KEYCODE_S: // OK
                installAPK();
                return "OK";
            case KeyEvent.KEYCODE_E:
                finish();
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
}
