package com.appmanager.showchannel;

import android.app.NotificationChannel;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.datalogic.device.app.AppManager;
import static com.datalogic.device.app.AppManagerException.SUCCESS;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        AppRecyclerAdapter adapter = new AppRecyclerAdapter(this, getAllAppList());
        RecyclerView listAppsView = findViewById(R.id.list_apps_view);
        listAppsView.setLayoutManager(new LinearLayoutManager(this));
        listAppsView.setAdapter(adapter);
    }

    private List<AppItem> getAllAppList() {
        List<AppItem> appList = new ArrayList<>();

        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);

        for (ResolveInfo info : apps) {
            String appName = info.loadLabel(pm).toString();
            String packageName = info.activityInfo.packageName;
            List<NotificationChannel> channels = getNotificationChannels(packageName);
            appList.add(new AppItem(appName, packageName, channels));
        }
        return appList;
    }

    private List<NotificationChannel> getNotificationChannels(String packageName) {
        List<NotificationChannel> list = new ArrayList<>();
        AppManager am = new AppManager(this);
        int result = am.getNotificationChannels(packageName, list);
        if (result != SUCCESS) {
            Log.d("TAG", "getNotificationChannels() return FAIL, packageName: " + packageName);
        }
        return list;
    }
}
