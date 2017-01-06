package com.example.harsh.myapplication1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Harsh on 15-Apr-16.
 */
public class AlarmReciever extends BroadcastReceiver {
TodoActivity todo=new TodoActivity();
    Notificationmaps note=new Notificationmaps();
double mLatitude,mLongitude,distance,raduis;

    @Override
    public void onReceive(Context context, Intent intent) {
        String ns1=Context.LOCATION_SERVICE;
        String ns = Context.NOTIFICATION_SERVICE;
        LocationManager locationManager = (LocationManager) context.getSystemService(ns1);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        double raduis2=1000;
        Location selected_location=new Location("");
        selected_location.setLatitude(mLatitude);
        selected_location.setLongitude(mLongitude);
        Location near_locations=new Location("");
        double latit=near_locations.getLatitude();
        double longi=near_locations.getLongitude();

        near_locations.setLatitude(note.lati[0]);
        near_locations.setLongitude(note.longi[0]);
        distance=selected_location.distanceTo(near_locations);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(ns);
        @SuppressWarnings("deprecation")
        Notification notification = new Notification(R.drawable.marker, "New Message", System.currentTimeMillis());
        Intent notificationIntent = new Intent(context, NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        if(distance<=raduis2) {

            notification.setLatestEventInfo(context, "Reminder","The destination is in the proximity", pendingIntent);
            notification.defaults = Notification.DEFAULT_ALL;
            notificationManager.notify(9999, notification);
        }
        else {
            notification.setLatestEventInfo(context, "Reminder", "The destination is not in the proximity", pendingIntent);
            notification.defaults = Notification.DEFAULT_ALL;
            notificationManager.notify(9999, notification);

        }

    }
}
