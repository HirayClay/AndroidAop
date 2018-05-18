package com.hiray.mvvm.androidaop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionRequestActivity extends Activity {


    private static PermissionCallBack permissionCallBack;

    private static final String PERMISSION_ARRAY = "PERMISSION_ARRAY";
    private static final String REQUEST_CODE = "REQUEST_CODE";
    private int mRequestCode;

    public interface PermissionCallBack {

        void onPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        Intent intent = getIntent();
        String[] permissions = intent.getStringArrayExtra(PERMISSION_ARRAY);
        mRequestCode = intent.getIntExtra(REQUEST_CODE, 0);
        ActivityCompat.requestPermissions(this, permissions, mRequestCode);
    }

    public static void requestPermissions(Context context, @NonNull String[] permissions, int requestCode, PermissionCallBack permissionCb) {
        permissionCallBack = permissionCb;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.putExtra(PERMISSION_ARRAY, permissions);
        intent.putExtra(REQUEST_CODE, requestCode);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionCallBack.onPermissionResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ActivityCompat.finishAfterTransition(this);
    }
}
