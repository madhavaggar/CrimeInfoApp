package com.example.game.deltatask3v2;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteCrimeActivity extends AppCompatActivity {
    DatabaseAdapter db;
    private android.support.v7.widget.SearchView searchView;
    CrimeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewforforceadapter);
        db = new DatabaseAdapter(this);
        db.open();
        Toolbar toolbar = findViewById(R.id.tb_toolbarsearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favourite-Crime");

        Cursor row = db.fetchAllData();
        List<CrimesLocAll> crimelist = new ArrayList<CrimesLocAll>();
        CrimesLocAll crime;
        row.moveToFirst();
            do {
                crime = new CrimesLocAll(row.getString(row.getColumnIndexOrThrow("category")) + "", null, null, null,
                        null, null, row.getInt(row.getColumnIndexOrThrow("id")), null, null);
                crimelist.add(crime);
            } while (row.moveToNext());
            row.moveToFirst();
            RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
            mAdapter = new CrimeAdapter(FavouriteCrimeActivity.this, crimelist);
            recyclerView.setAdapter(mAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavouriteCrimeActivity.this);
            recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menu_toolbarsearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_toolbarsearch) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
