package com.example.targetapk;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import butterknife.OnClick;

public class LastActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_last;
    }
    @OnClick(R.id.br)
    public void OnClick(View view){
        Uri uri = Uri.parse("smsto:13200100001");
        Intent  intent  = new Intent();
        intent.setAction(Intent.  ACTION_SENDTO );
        intent.setData(uri);
        intent.putExtra("sms_body", "信息内容...");
        startActivity( intent );
    }
    @Override
    public void init() {

    }
}
