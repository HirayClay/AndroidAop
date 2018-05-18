package com.hiray.mvvm.androidaop;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class AopPermissionActivity extends Activity {
    private static final String TAG = "AopPermissionActivity";
    private static final int REQUEST_CAMERA = 1234;
    private File file;

    AppCompatImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_permission);
        imageView = findViewById(R.id.camera_bitmap);
    }


    @AspectPermission(Manifest.permission.CAMERA)
    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        Uri uri = FileProvider.getUriForFile(this, "com.hiray.mvvm.androidaop", file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Log.i(TAG, "takePhoto: ");
        startActivityForResult(intent, REQUEST_CAMERA);
//        Toast.makeText(this, "Permission Requested OK!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }
    }
}
