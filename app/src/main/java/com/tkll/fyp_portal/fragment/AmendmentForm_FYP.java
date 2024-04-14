package com.tkll.fyp_portal.fragment;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
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
        // URL of the file to download
        String fileUrl = "https://www2.utar.edu.my/fict-pk/file/FYP%20Amendment%20Request%20Form%20(FYP1%20FYP2%20only).docx";

        // Get the DownloadManager service
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        // Parse the file URL
        Uri uri = Uri.parse(fileUrl);

        // Create a DownloadManager.Request with the file URL
        DownloadManager.Request request = new DownloadManager.Request(uri);

        // Set the title of the download notification
        request.setTitle("Amendment Form Download");

        // Set the destination directory for the downloaded file
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "FYP_Amendment_Form.docx");

        // Enqueue the download and get the download ID
        long downloadId = downloadManager.enqueue(request);

        // Optionally, you can listen for download completion or failure
        // For example:
        // new DownloadCompleteReceiver().setOnDownloadCompleteListener(downloadId, getActivity());
    }
}
