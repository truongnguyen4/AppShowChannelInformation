package com.appmanager.showchannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppRecyclerAdapter extends RecyclerView.Adapter<AppRecyclerAdapter.AppViewHolder> {

    private final Context context;
    private final List<AppItem> appList;

    public AppRecyclerAdapter(Context context, List<AppItem> appList) {
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_channel_item, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppItem app = appList.get(position);
        
        holder.appName.setText(app.getName());
        holder.packageName.setText(app.getPackageName());

        // Set up nested RecyclerView for channels
        ChannelRecyclerAdapter channelAdapter = new ChannelRecyclerAdapter(context, app.getNotificationChannels());
        holder.channelRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.channelRecyclerView.setAdapter(channelAdapter);
        holder.channelRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {
        TextView appName;
        TextView packageName;
        RecyclerView channelRecyclerView;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.app_name);
            packageName = itemView.findViewById(R.id.package_name);
            channelRecyclerView = itemView.findViewById(R.id.list_channels_view);
        }
    }
}
