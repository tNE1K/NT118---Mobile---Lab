package com.example.lab3_12;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int layout;
    List<Contact> list;

    public ContactAdapter(Activity context, int layout, List<Contact> list){
        super(context, layout, list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacts, null, false);
        }
        Contact contact = list.get(position);
        TextView tvContact = (TextView) convertView.findViewById(R.id.item_contact);

        if(contact.getName() != null && contact.getPhoneNumber() != null)
        {
            tvContact.setText("ID: " + contact.getId() + ", name: " + contact.getName() + ", phone number: " + contact.getPhoneNumber());
        }
        else tvContact.setText("");
        return convertView;
    }
}
