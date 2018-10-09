package com.liangchao.asuper.chaocwmpandroid;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jiang on 18-10-9.
 */

public class ListViewDialog extends Dialog {
    private final Context mContext;
    private ListView mListView;
    Button btCancel,btConform;
    public ListViewDialog(Context context) {
        super(context);
        mContext = context;
        initView();
        initListView();
    }
    private void initView() {
        final View contentView = View.inflate(mContext, R.layout.content_dialog, null);
        mListView = (ListView) contentView.findViewById(R.id.lv);
        btCancel = (Button)contentView.findViewById(R.id.btcancel);
        btConform = (Button)contentView.findViewById(R.id.btconform);
        setContentView(contentView);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("this is cancel");
            }
        });
    }
    private void initListView() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_expandable_list_item_1);
        Map mapConf = null;
        try {
            mapConf = ConfFileControl.getConf("/sdcard/chaocwmp/cwmp.conf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Object key: mapConf.keySet()){
            stringArrayAdapter.add(key+":"+mapConf.get(key));
        }
        mListView.setAdapter(stringArrayAdapter);
    }
    @Override public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
    }
    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.6)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.6);
        }
        window.setAttributes(attributes);
    }
}

