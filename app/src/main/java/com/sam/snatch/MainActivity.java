package com.sam.snatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.snatch.adapter.PdfListAdapter;
import com.sam.snatch.model.PdfItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pdfRecyclerView;
    private PdfListAdapter adapter;
    private List<PdfItem> pdfItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDummyPdfs();  // <- Save if not exist
        pdfItems = loadPdfList(); // <- Load files

        pdfRecyclerView = findViewById(R.id.pdfRecyclerView);
        pdfRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PdfListAdapter(this, pdfItems);
        pdfRecyclerView.setAdapter(adapter);
    }

    private void generateDummyPdfs() {
        String[] names = {"Diary_2025_04_13.pdf", "Todo_2025_04_12.pdf", "Journal_2025_04_11.pdf"};

        for (String name : names) {
            File file = new File(getFilesDir(), name);
            if (!file.exists()) {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(("This is a dummy PDF file: " + name).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<PdfItem> loadPdfList() {
        File dir = getFilesDir();
        File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf"));
        List<PdfItem> list = new ArrayList<>();

        if (files != null) {
            for (File f : files) {
                list.add(new PdfItem(f.getName(), null));
            }
        }

        return list;
    }



}
