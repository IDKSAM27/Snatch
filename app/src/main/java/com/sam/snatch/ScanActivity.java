package com.sam.snatch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.compose.ui.tooling.PreviewActivity;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScanActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private String currentPhotoPath;
    private Uri currentPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Button takePhotoButton = findViewById(R.id.takePhotoButton);
        Button okButton = findViewById(R.id.okButton);

        takePhotoButton.setOnClickListener(v -> {
            dispatchTakePictureIntent();
        });

        okButton.setOnClickListener(v -> {
            if (imageUris.isEmpty()) {
                Toast.makeText(this, "No photos taken", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PreviewActivity.class);
                intent.putParcelableArrayListExtra("imageUris", imageUris);
                startActivity(intent);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Failed to create image file", Toast.LENGTH_SHORT).show();
                return;
            }
            if (photoFile != null) {
                currentPhotoUri = FileProvider.getUriForFile(this, "com.sam.snatch.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageUris.add(currentPhotoUri);
            dispatchTakePictureIntent(); // Keep taking photos
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
