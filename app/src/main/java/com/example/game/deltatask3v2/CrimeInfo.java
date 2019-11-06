package com.example.game.deltatask3v2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.recyclerview.selection.SelectionTracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrimeInfo extends AppCompatActivity {

    private CrimeAdapter mAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewforadapters);
        progressDoalog = new ProgressDialog(CrimeInfo.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        String date,lat,lng;
        Intent intent = getIntent();
        date=intent.getStringExtra("date");
        lat=intent.getStringExtra("latitude");
        lng=intent.getStringExtra("longitude");
        boolean wol;
        wol=intent.getExtras().getBoolean("withoutlocation");

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<CrimesLocAll>> call;
        if(wol==false) {
           call = service.getcrimesforloc(date, lat, lng);
        }
        else{
            call=service.getcrimenolocation(lat,lng,date);
        }

        call.enqueue(new Callback<List<CrimesLocAll>>() {
            @Override
            public void onResponse(Call<List<CrimesLocAll>> call, Response<List<CrimesLocAll>> response) {
                List<CrimesLocAll> crimeList=response.body();
                progressDoalog.dismiss();
                recyclerView = findViewById(R.id.my_recycler_view);
                mAdapter = new CrimeAdapter(CrimeInfo.this,crimeList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CrimeInfo.this);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(layoutManager);
                SwipeController swipeController = new SwipeController(mAdapter);
                ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
                itemTouchhelper.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onFailure(Call<List<CrimesLocAll>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(CrimeInfo.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
