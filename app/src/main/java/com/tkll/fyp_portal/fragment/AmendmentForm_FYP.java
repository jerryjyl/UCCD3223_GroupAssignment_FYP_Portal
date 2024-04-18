package com.tkll.fyp_portal.fragment;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.content.pm.PackageManager;

import com.tkll.fyp_portal.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AmendmentForm_FYP extends Fragment {

    // ActivityResultLauncher for requesting permissions
    private ActivityResultLauncher<String> requestPermissionLauncher;

    // Request code for internet and storage permissions
    private static final int PERMISSION_REQUEST_INTERNET = 1;
    private static final int PERMISSION_REQUEST_STORAGE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_amendmentform_fyp, container, false);

        // Initialize the requestPermissionLauncher
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission granted, proceed with download
                startDownload();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(getActivity(), "Permission denied to download the file.", Toast.LENGTH_SHORT).show();
            }
        });

        // Get reference to download button
        Button downloadButton = rootView.findViewById(R.id.downloadFormButton);

        // Set click listener for the download button
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if internet permission is granted
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted, request it
                    requestPermissionLauncher.launch(Manifest.permission.INTERNET);
                } else if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Permission to write to external storage is not granted, request it
                    requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    // Both permissions are granted, proceed with download
                    startDownload();
                }
            }
        });

        return rootView;
    }

    // Method to start the download process
    private void startDownload() {
        // Get the AssetManager
        AssetManager assetManager = getActivity().getAssets();

        try {
            // Open the input stream for the asset file
            InputStream inputStream = assetManager.open("amendmentform.docx");

            // Set the destination directory for the downloaded file (Downloads directory)
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String destinationPath = downloadsDir.getAbsolutePath() + File.separator + "amendmentform.docx";

            // Create the output stream to write the file
            OutputStream outputStream = new FileOutputStream(destinationPath);

            // Copy the file from the input stream to the output stream
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close the streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            // Show a message indicating successful download
            Toast.makeText(getActivity(), "File downloaded successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show a message indicating failure
            Toast.makeText(getActivity(), "Failed to download the file", Toast.LENGTH_SHORT).show();
        }
    }
}
