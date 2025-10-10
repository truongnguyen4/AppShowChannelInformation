package com.appmanager.showchannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttributeRecyclerAdapter extends RecyclerView.Adapter<AttributeRecyclerAdapter.AttributeViewHolder> {

    private final Context context;
    private final List<String> attributes;

    public AttributeRecyclerAdapter(Context context, List<String> attributes) {
        this.context = context;
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.attribute_item, parent, false);
        return new AttributeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttributeViewHolder holder, int position) {
        String attribute = attributes.get(position);
        holder.attributeText.setText(attribute);
        holder.attributeText.setMaxLines(Integer.MAX_VALUE); // Expand
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    static class AttributeViewHolder extends RecyclerView.ViewHolder {
        TextView attributeText;

        public AttributeViewHolder(@NonNull View itemView) {
            super(itemView);
            attributeText = itemView.findViewById(R.id.attribute_text);
        }
    }
}
