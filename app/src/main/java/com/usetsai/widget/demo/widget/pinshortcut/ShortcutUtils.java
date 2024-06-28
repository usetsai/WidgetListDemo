package com.usetsai.widget.demo.widget.pinshortcut;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.usetsai.widget.demo.R;
import com.usetsai.widget.demo.WidgetListActivity;

public class ShortcutUtils {
    public static void testAddShortcut(Context context) {
        //其次，通过为该Intent添加Extra属性来设置快捷方式的标题、图标及快捷方式对应的启动程序（分别对应下面的代码）；
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_android_round);
        //用于点击快捷方式要启动的程序，这里就启动本程序了
        Intent startIntent = new Intent(context, WidgetListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //向桌面添加快捷方式的广播
        Intent addShortCutIntent = new Intent("com.meizu.flyme.launcher.action.INSTALL_SHORTCUT");
        addShortCutIntent.setPackage("com.meizu.flyme.launcher");
        //快捷方式的名称
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "testAddShortcut");
        //快捷方式的图标
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //将快捷方式与要启动的程序关联起来
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, startIntent);
        //最后，就是调用sendBroadcast()方法发送广播即可添加快捷方式
        context.sendBroadcast(addShortCutIntent);
    }
}
