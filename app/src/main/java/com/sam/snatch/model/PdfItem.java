package com.sam.snatch.model;

public class PdfItem {
    private final String name;
    private final String thumbnailPath;

    public PdfItem(String name, String thumbnailPath) {
        this.name = name;
        this.thumbnailPath = thumbnailPath;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
}
