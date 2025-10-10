package com.appmanager.showchannel;

import android.app.NotificationChannel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChannelRecyclerAdapter extends RecyclerView.Adapter<ChannelRecyclerAdapter.ChannelViewHolder> {

    private final Context context;
    private final List<NotificationChannel> channels;

    public ChannelRecyclerAdapter(Context context, List<NotificationChannel> channels) {
        this.context = context;
        this.channels = channels;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.channel_item, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        NotificationChannel channel = channels.get(position);
        holder.channelName.setText(channel.getName());

        // Create attributes list
        List<String> attributes = new ArrayList<>(Arrays.asList(
                context.getString(R.string.id) + ": " + channel.getId(),
                context.getString(R.string.description) + ": " + channel.getDescription()
        ));

        // Set up nested RecyclerView for attributes
        AttributeRecyclerAdapter attributeAdapter = new AttributeRecyclerAdapter(context, attributes);
        holder.attributeRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.attributeRecyclerView.setAdapter(attributeAdapter);
        holder.attributeRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    static class ChannelViewHolder extends RecyclerView.ViewHolder {
        TextView channelName;
        RecyclerView attributeRecyclerView;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.channel_name);
            attributeRecyclerView = itemView.findViewById(R.id.list_attribute_view);
        }
    }
}
