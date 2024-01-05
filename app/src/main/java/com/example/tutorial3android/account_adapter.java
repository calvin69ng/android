package com.example.tutorial3android;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class account_adapter extends ArrayAdapter<String> {

    public account_adapter(Context context, int resource, List<String> usernames) {
        super(context, resource, usernames);
    }
}