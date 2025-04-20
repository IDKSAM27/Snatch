package com.sam.snatch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ScanActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private final ArrayList<String> photoPaths = new ArrayList<>();
    private String latestPhotoPath; // holds the current photo being taken
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PreviewActivity.class);
            intent.putStringArrayListExtra("imagePaths", photoPaths);
            startActivity(intent);
        });

        dispatchTakePictureIntent(); // start camera immediately
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            try {
                photoFile = createImageFile();
                latestPhotoPath = photoFile.getAbsolutePath(); // Save path before launching intent
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }

            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.sam.snatch.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (latestPhotoPath != null) {
                photoPaths.add(latestPhotoPath);
            }
            dispatchTakePictureIntent(); // Launch camera again
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
