package com.sam.snatch.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sam.snatch.R;
import com.sam.snatch.model.PdfItem;

import java.util.List;

public class PdfListAdapter extends RecyclerView.Adapter<PdfListAdapter.ViewHolder> {

    private final List<PdfItem> pdfList;
    private final Context context;

    public PdfListAdapter(Context context, List<PdfItem> pdfList) {
        this.context = context;
        this.pdfList = pdfList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailView;
        TextView titleView;

        public ViewHolder(View view) {
            super(view);
            thumbnailView = view.findViewById(R.id.pdf_thumbnail);
            titleView = view.findViewById(R.id.pdf_title);
        }
    }

    @Override
    public PdfListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pdf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PdfListAdapter.ViewHolder holder, int position) {
        PdfItem item = pdfList.get(position);
        holder.titleView.setText(item.getName());

        // Load thumbnail if available
        if (item.getThumbnailPath() != null) {
            holder.thumbnailView.setImageBitmap(BitmapFactory.decodeFile(item.getThumbnailPath()));
        }
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }
}
