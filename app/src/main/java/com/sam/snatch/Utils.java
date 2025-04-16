package com.sam.snatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    public static File convertImageToPdf(Context context, Uri imageUri) {
        try {
            // Load bitmap from image URI
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);

            // Create a PDF document
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    1
            ).create();

            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            canvas.drawBitmap(bitmap, 0, 0, null);
            document.finishPage(page);

            // Output directory
            File pdfDir = new File(context.getExternalFilesDir(null), "snatch_pdfs");
            if (!pdfDir.exists()) {
                pdfDir.mkdirs();
            }

            // File name
            String fileName = "snatch_" + System.currentTimeMillis() + ".pdf";
            File pdfFile = new File(pdfDir, fileName);

            // Write the PDF to file
            FileOutputStream fos = new FileOutputStream(pdfFile);
            document.writeTo(fos);

            // Cleanup
            document.close();
            fos.close();

            return pdfFile;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
