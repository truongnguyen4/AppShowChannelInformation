package com.appmanager.showchannel;

import android.app.NotificationChannel;

import java.util.List;

public class AppItem {
    private final String name;
    private final String packageName;
    private final List<NotificationChannel> notificationChannels;

    public AppItem(String name, String packageName, List<NotificationChannel> notificationChannels) {
        this.name = name;
        this.packageName = packageName;
        this.notificationChannels = notificationChannels;
    }

    public String getName() { return name; }
    public String getPackageName() { return packageName; }
    public List<NotificationChannel> getNotificationChannels() { return notificationChannels; }

}
