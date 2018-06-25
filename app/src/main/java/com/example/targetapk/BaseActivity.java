package com.example.targetapk;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;


import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        init();

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        Log.d(TAG,"ButterKnife.bind(this)");
    }

    public abstract int getContentView();


    public abstract void init();
}
