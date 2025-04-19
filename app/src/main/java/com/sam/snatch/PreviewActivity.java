package com.sam.snatch;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        LinearLayout imageContainer = findViewById(R.id.imageContainer);
        ArrayList<Uri> imageUris = getIntent().getParcelableArrayListExtra("imageUris");

        if (imageUris != null) {
            for (Uri uri : imageUris) {
                ImageView imageView = new ImageView(this);
                imageView.setImageURI(uri);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(600, 800));
                imageView.setPadding(16, 16, 16, 16);
                imageContainer.addView(imageView);
            }
        }
    }
}
