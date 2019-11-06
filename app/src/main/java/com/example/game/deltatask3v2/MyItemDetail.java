package com.example.game.deltatask3v2;

import android.support.annotation.Nullable;

import androidx.recyclerview.selection.ItemDetailsLookup;

public class MyItemDetail extends ItemDetailsLookup.ItemDetails {
    private final int adapterPosition;
    private final Forces selectionKey;

    public MyItemDetail(int adapterPosition, Forces selectionKey) {
        this.adapterPosition = adapterPosition;
        this.selectionKey = selectionKey;
    }

    @Override
    public int getPosition() {
        return adapterPosition;
    }

    @Nullable
    @Override
    public Object getSelectionKey() {
        return selectionKey;
    }
}
