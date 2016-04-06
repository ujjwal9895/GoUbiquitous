package com.example.android.sunshine.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;

public class WatchListenerService extends WearableListenerService {

    public static final String LOG_TAG = WatchListenerService.class.getSimpleName();
    public static final String WEATHER = "/weather";
    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                String path = dataEvent.getDataItem().getUri().getPath();
                if (path.equals(WEATHER)) {
                    SunshineSyncAdapter.syncImmediately(this);
                }
            }
        }
    }
}
