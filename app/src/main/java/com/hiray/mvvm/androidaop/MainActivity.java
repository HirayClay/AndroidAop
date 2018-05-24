package com.hiray.mvvm.androidaop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    LinearLayout contentLayoutRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentLayoutRoot = findViewById(R.id.content_layout_root);
    }

    @AspectSingleClick
    @AspectLog
    public void addWidget(View view) {
        TextView textView = new TextView(this);
        textView.setAllCaps(false);
        textView.setText("Android AspectJ Test");
        MyLinearLayout myLinearLayout = new MyLinearLayout(this);
        myLinearLayout.addView(textView);
        contentLayoutRoot.addView(myLinearLayout);
    }

    public void aopNetWork(View view) {
        startActivity(new Intent(this, AopNetWorkActivity.class));

    }

    public void aopPermission(View view) {
        startActivity(new Intent(this, AopPermissionActivity.class));
    }
}
