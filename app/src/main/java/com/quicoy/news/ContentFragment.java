package com.quicoy.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContentFragment extends Fragment {

    public static final String ARG_POSITION = "position";
    private int currentPosition = -1;

    // Sample content for each headline
    String[] newsContent = {
            "Tech giants Company A and Company B have officially merged, creating the largest tech conglomerate in history.",
            "In an exciting game last night, the local team won the national championship with a last-minute goal.",
            "A severe storm is approaching the city. Residents are advised to stay indoors and prepare for potential power outages.",
            "Maintaining a balanced diet is essential for good health. Experts recommend including a variety of fruits and vegetables.",
            "The latest blockbuster movie has broken box office records, becoming the highest-grossing film of the year."
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check if there are arguments passed to the fragment (i.e., headline was clicked)
        if (getArguments() != null) {
            currentPosition = getArguments().getInt(ARG_POSITION);
            updateContent(currentPosition);  // Update the content immediately
        }
    }

    // Method to update the content of the news article
    public void updateContent(int position) {
        if (getView() != null) {
            TextView contentView = getView().findViewById(R.id.newsContent);  // Find the TextView
            contentView.setText(newsContent[position]);  // Update the TextView with the selected news content
        }
        currentPosition = position;  // Save the current position
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current selected position for later use (e.g., after configuration changes)
        outState.putInt(ARG_POSITION, currentPosition);
    }
}
