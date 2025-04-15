package com.sam.snatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.snatch.adapter.PdfListAdapter;
import com.sam.snatch.model.PdfItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pdfRecyclerView;
    private PdfListAdapter adapter;
    private List<PdfItem> pdfItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // this should match your XML

        pdfRecyclerView = findViewById(R.id.pdfRecyclerView);
        pdfRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Temporary dummy data
        pdfItems = new ArrayList<>();
        pdfItems.add(new PdfItem("Diary Entry - Apr 14", null));
        pdfItems.add(new PdfItem("Todo List - Apr 13", null));

        adapter = new PdfListAdapter(this, pdfItems);
        pdfRecyclerView.setAdapter(adapter);
    }
}
