package com.quicoy.news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if we're in portrait mode (i.e., fragment_container is present)
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;  // If there's a saved state, don't re-add fragments
            }

            // Add HeadlineFragment dynamically in portrait mode
            HeadlineFragment headlineFragment = new HeadlineFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, headlineFragment).commit();
        }
    }

    @Override
    public void onHeadlineSelected(int position) {
        // Check if the content fragment is available (for landscape mode)
        ContentFragment contentFragment = (ContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.content_fragment);

        if (contentFragment != null) {
            // We're in landscape mode, so update the existing content fragment
            contentFragment.updateContent(position);
        } else {
            // In portrait mode, replace the HeadlineFragment with the ContentFragment
            ContentFragment newFragment = new ContentFragment();
            Bundle args = new Bundle();
            args.putInt(ContentFragment.ARG_POSITION, position);  // Pass the selected headline position
            newFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)  // Replace with content fragment
                    .addToBackStack(null)  // Allow navigation back to headline
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
