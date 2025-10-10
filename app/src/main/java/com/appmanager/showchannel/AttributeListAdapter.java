package com.appmanager.showchannel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class AttributeListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> attributes;
    public AttributeListAdapter(android.content.Context context, List<String> attributes) {
        super(context, 0, attributes);
        this.context = context;
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = android.view.LayoutInflater.from(getContext()).inflate(R.layout.attribute_item, parent, false);
        }

        String attribute = attributes.get(position);

        TextView attributeView = convertView.findViewById(R.id.attribute_text);
        attributeView.setText(attribute);
        attributeView.setMaxLines(Integer.MAX_VALUE);

        return convertView;
    }
}
