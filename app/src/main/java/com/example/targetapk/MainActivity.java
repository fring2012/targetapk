package com.example.targetapk;


import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.targetapk.receiver.CheckAndDownloadInfoReceiver;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.resultInfo)
    TextView textView;
    private CheckAndDownloadInfoReceiver mReceiver;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver();

    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.check,R.id.update,R.id.download,R.id.cleanCache})
    public void onClick(View view){
        int requestCode = 100;
        switch (view.getId()){
            case R.id.check:
                requestCode = 0;
                break;
            case R.id.download:
                requestCode = 1;
                break;
            case R.id.update:
                requestCode = 2;
                break;
            case R.id.cleanCache:
                requestCode = 3;
                break;
        }
        sendBroadcast(requestCode);

    }

    @Override
    public void init() {

    }

    public void startActivity(String action){
        Intent intent = new Intent(action);
        startActivity(intent);
    }
    public void sendBroadcast(int requestCode){

        Intent intent = new Intent();
        intent.putExtra("requestCode",requestCode);
        setParams(intent);
        intent.setComponent(new ComponentName("com.abupdate.apk_up_receiver",
                "com.abupdate.apk_up_receiver.broadcast.ApkUpInfoReceiver"));
        intent.setAction("broadcast.ApkUpInfoReceiver");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        Log.d(TAG,"checkVersion() end");
    }
    public void setParams(Intent intent){
        String packageName = getApplicationContext().getPackageName();

        intent.putExtra("packageName",packageName);
        intent.putExtra("receiverName", "CheckAndDownloadInfoReceiver");
        //intent.putExtra("receiverClass","com.example.targetapk.receiver.CheckAndDownloadInfoReceiver");
        intent.putExtra("mid","4325");
        intent.putExtra("deviceType","phone");
        intent.putExtra("device","MI3C");
        intent.putExtra("oem","212");
        intent.putExtra("platform","BRCM23550");
        intent.putExtra("productId","1525594277");
        intent.putExtra("productSecret","c380eaa48f0248f9878a14857844d133");
    }




    public void setTextView(String str){
        textView.setText(str);
    }
    public void setProgressBar(int progress){
        if(progress >= 0 && progress <= 100)
            progressBar.setProgress(progress);
    }

    private void registerReceiver(){
        mReceiver = new CheckAndDownloadInfoReceiver();
        mReceiver.setView(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CheckAndDownloadInfoReceiver");
        registerReceiver(mReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
