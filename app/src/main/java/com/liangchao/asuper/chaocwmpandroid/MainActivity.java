package com.liangchao.asuper.chaocwmpandroid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    Button bt1,bt2;
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = new String[]{"创建文件","退出"};
        builder.setIcon(R.drawable.ic_launcher_background).setTitle("未发现配置文件")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                File dir = new File("/sdcard/chaocwmp");
                                dir.mkdir();
                                break;
                            case 1:
                                finish();
                                break;
                        }
                    }
                });
        if(!ConfFileControl.checkFile("/sdcard/chaocwmp/cwmpd.log")) builder.create().show();

        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println( NativeCwmpMethod.getStringHello());
                    }
                }).start();

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("chaocwmp is:"+ ConfFileControl.checkFile("/sdcard/chaocwmp"));
                System.out.println("chaocwmp/cwmpd.log is:"+ ConfFileControl.checkFile("/sdcard/chaocwmp/cwmpd.log"));
                /*try {
                    ConfFileControl.setConf("/sdcard/chaocwmp/cwmp.conf","enable","1");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                ListViewDialog listViewDialog = new ListViewDialog(MainActivity.this);
                listViewDialog.show();
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
