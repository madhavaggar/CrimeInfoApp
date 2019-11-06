package com.example.game.deltatask3v2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import androidx.recyclerview.selection.ItemKeyProvider;

import java.util.List;

public class MyItemKeyProvider extends ItemKeyProvider {
    private final List<Forces> forceList;

    public MyItemKeyProvider(int scope, List<Forces> forceList){
        super(scope);
        this.forceList=forceList;
    }

    @Nullable
    @Override
    public Object getKey(int position){
        return forceList.get(position);
    }

    @Override
    public int getPosition(@NonNull Object key){
        return forceList.indexOf(key);
    }
}