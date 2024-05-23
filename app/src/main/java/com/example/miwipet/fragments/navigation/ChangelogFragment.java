package com.example.miwipet.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miwipet.BuildConfig;
import com.example.miwipet.R;

public class ChangelogFragment extends Fragment {
    private TextView headerText, versionText, bodyText;
    private String headerString, versionString, bodyString;

    private final String versionName = BuildConfig.VERSION_NAME;
    private final int versionCode = BuildConfig.VERSION_CODE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_changelog, container, false);

        headerText = view.findViewById(R.id.headerText);
        versionText = view.findViewById(R.id.versionText);
        bodyText = view.findViewById(R.id.bodyText);


        headerString = "\uD83D\uDC36\uD83D\uDE3A";
        headerString += "\n" + getString(R.string.app_name) + " - Changelog";

        versionString = "\uD83C\uDF1F Version " + versionName + " \uD83C\uDF1F - Released May 23, 2024";

        bodyString = "New Features:";
        bodyString += "\n\n\n\uD83D\uDC8E New Eggs Added: Introducing the new Nostalgia Egg and Mythic Egg";
        bodyString += "\n\n\uD83D\uDC8E New Food System: This food system is still under development";

        bodyString += "\n\n\n\nImprovements:";
        bodyString += "\n\n\n\uD83D\uDCAB Pet Art Improved: Improved some of the pet arts in different eggs";
        bodyString += "\n\n\uD83D\uDCAB Auto Refresh Images: Refreshed pet, food, egg images so that in the next update " +
                "the images will remain accurate";

        bodyString += "\n\n\n\nBug Fixes:";
        bodyString += "\n\n\n\uD83D\uDC7E Fixed an issue whenever there is new update, the existing pet images in inventory " +
                "will be inaccurate and behave strangely";


        headerText.setText(headerString);
        versionText.setText(versionString);
        bodyText.setText(bodyString);

        return view;
    }
}