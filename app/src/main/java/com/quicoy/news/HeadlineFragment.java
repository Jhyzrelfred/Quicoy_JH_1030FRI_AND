package com.quicoy.news;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HeadlineFragment extends Fragment {

    OnHeadlineSelectedListener callback;

    // Sample headlines
    String[] headlines = {
            "Breaking News: Tech Giants Merge",
            "Sports Update: Local Team Wins Championship",
            "Weather Alert: Storm Approaching",
            "Health: Tips for a Balanced Diet",
            "Entertainment: New Movie Breaks Records"
    };

    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);

        // Set up the ListView to display headlines
        ListView listView = view.findViewById(R.id.headlineList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, headlines);
        listView.setAdapter(adapter);

        // Handle click events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onHeadlineSelected(position);  // Pass selected position to the activity
            }
        });

        return view;
    }
}