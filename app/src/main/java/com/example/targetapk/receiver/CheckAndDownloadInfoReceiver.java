package com.example.targetapk.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.example.targetapk.MainActivity;

public class CheckAndDownloadInfoReceiver extends BroadcastReceiver {
    private static final String TAG = "CheckAndDownloadInfoReceiver";
    private MainActivity mainActivity;
    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive(Context context, Intent intent)");
        int state = intent.getIntExtra("state",0);
        switch (state) {
            case 0:
                int resultState = intent.getIntExtra("resultState", 0);
                if (resultState == 1000) {
                    String versionName = intent.getStringExtra("versionName");
                    String content = intent.getStringExtra("content");
                    setText(content);
                    Log.d(TAG, "resultState:" + resultState + "versionName:" + versionName + "content:" + content);
                } else {
                    String error = intent.getStringExtra("error");
                    Log.d(TAG, "resultState:" + resultState + "error:" + error);
                }
                break;
            case 1:
                int progress =intent.getIntExtra("progress",0);
                setProgress(progress);
                Log.d(TAG, "progress:" + progress);
                break;
            case 3:
                String policyException = intent.getStringExtra("exception");
                setText(policyException);
                Log.d(TAG, "exception:" + policyException);
        }
    }
    private void setText(String content){
        if(mainActivity == null) return;
        mainActivity.setTextView(content);
    }

    private void setProgress(int progress){
        if(mainActivity == null) return;
        mainActivity.setProgressBar(progress);
    }
    public void setView(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
