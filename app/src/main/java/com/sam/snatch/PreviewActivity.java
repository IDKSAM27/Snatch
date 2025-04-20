package com.sam.snatch;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<String> uriStrings = getIntent().getStringArrayListExtra("photoUris");
        ArrayList<Uri> photoUris = new ArrayList<>();
        if (uriStrings != null) {
            for (String uriStr : uriStrings) {
                photoUris.add(Uri.parse(uriStr));
            }
        }

        adapter = new PhotoAdapter(this, photoUris);
        recyclerView.setAdapter(adapter);
    }
}
