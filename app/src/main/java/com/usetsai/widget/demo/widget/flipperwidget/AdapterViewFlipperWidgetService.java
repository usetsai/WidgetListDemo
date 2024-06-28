package com.usetsai.widget.demo.widget.flipperwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.usetsai.widget.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterViewFlipperWidgetService extends RemoteViewsService {
    private static final String TAG = "AdapterViewFlipperWidgetService";

    private final static int[] mLayoutIds = { R.layout.widget_view_flipper_page1,
            R.layout.widget_view_flipper_page2, R.layout.widget_view_flipper_page3};
    private final static String[] mTexts = { "第 1 页", "第 2 页", "第 3 页"};

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.i(TAG, "onGetViewFactory()");
        return new ViewFactory(intent);
    }

    private class ViewFactory implements RemoteViewsFactory {
        private Date mUpdateDate = new Date();

        public ViewFactory(Intent intent) {
            int mInstanceId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            Log.i(TAG, "onCreate()");
        }

        @Override
        public void onDataSetChanged() {
            Log.i(TAG, "onDataSetChanged()");
            mUpdateDate = new Date();
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "onDestroy()");
        }

        @Override
        public int getCount() {
            Log.i(TAG, "getCount() " + mLayoutIds.length);
            return mLayoutIds.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews page = new RemoteViews(getPackageName(), mLayoutIds[position]);
            SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
                    .getDateTimeInstance();
            page.setTextViewText(R.id.update_date, sdf.format(mUpdateDate));
            String text = mTexts[position];
            Log.i(TAG, "getViewAt position:" + position + " update text:" + text);
            page.setTextViewText(R.id.page, text);
          return page;
        }

        @Override
        public RemoteViews getLoadingView() {
            Log.i(TAG, "getLoadingView()");
            return new RemoteViews(getPackageName(), R.layout.widget_view_flipper_loading);
        }

        @Override
        public int getViewTypeCount() {
            Log.i(TAG, "getViewTypeCount()");
            return mLayoutIds.length;
        }

        @Override
        public long getItemId(int position) {
            Log.i(TAG, "getItemId()");
            return position;
        }

        @Override
        public boolean hasStableIds() {
            Log.i(TAG, "hasStableIds()");
            return true;
        }
    }
}