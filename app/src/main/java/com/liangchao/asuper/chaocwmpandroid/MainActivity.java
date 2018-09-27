package com.liangchao.asuper.chaocwmpandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button bt1;
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        检查是否有相应的权限
        boolean isPermission = checkSelfPermissionAll(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE});
        if (isPermission) {
            Toast.makeText(MainActivity.this, "正在查看!", Toast.LENGTH_SHORT).show();
            return;
        }
//        请求权限
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_CODE);

        bt1 = (Button)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println( NativeCwmpMethod.getStringHello());
            }
        });
    }

    //    检查是否拥有指定的所有权限
    private boolean checkSelfPermissionAll(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
