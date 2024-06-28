package com.usetsai.widget.demo.widget.pinshortcut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.usetsai.widget.demo.R;
import com.usetsai.widget.demo.WidgetListActivity;

public class AddShortcutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createShortCut();
    }

    public void createShortCut() {
        //推断是否须要加入快捷方式
        if (Intent.ACTION_CREATE_SHORTCUT.equals(getIntent().getAction())) {
            Intent addShortCut = new Intent();
            //快捷方式的名称
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "我的快捷方式");
            //显示的图片
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_android_round);
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            //快捷方式激活的activity，须要运行的intent，自定义
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(this, WidgetListActivity.class));
            //OK，生成
            setResult(RESULT_OK, addShortCut);
            finish();
        } else {
            //取消
            setResult(RESULT_CANCELED);
        }
    }
}