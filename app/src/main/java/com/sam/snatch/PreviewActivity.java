package com.sam.snatch;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {

    private LinearLayout imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageContainer = findViewById(R.id.imageContainer);

        ArrayList<String> imagePaths = getIntent().getStringArrayListExtra("imagePaths");
        if (imagePaths != null) {
            for (String path : imagePaths) {
                ImageView imageView = new ImageView(this);
                imageView.setImageURI(Uri.fromFile(new File(path)));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        600,
                        800
                );
                params.setMargins(16, 16, 16, 16);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imageContainer.addView(imageView);
            }
        }
    }
}
