package com.example.databinding.app;

public class ChangedText {
    private final String text;
    private final int color;
    private final boolean isLarge;
    private final boolean isVisible;

    public ChangedText(String text, int color, boolean isLarge, boolean isVisible) {
        this.text = text;
        this.color = color;
        this.isLarge = isLarge;
        this.isVisible = isVisible;
    }

    public int getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public boolean isLarge() {
        return isLarge;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
