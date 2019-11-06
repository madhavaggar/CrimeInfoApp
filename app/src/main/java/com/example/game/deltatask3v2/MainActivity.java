package com.example.game.deltatask3v2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void forceinitiate(View view){
        Intent intent=new Intent(this,ForcesListData.class);
        startActivity(intent);
    }
    public void crimeinitiate(View v){
        Intent intent= new Intent(this,SearchCrimeActivity.class);
        startActivity(intent);
    }
    public void favouriteinitiate(View v){
        DatabaseAdapter db= new DatabaseAdapter(getApplicationContext());
        db.open();
        Cursor row=db.fetchAllData();
        if(row.moveToFirst()){
            Intent intent= new Intent(this,FavouriteCrimeActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Favourites section is empty",Toast.LENGTH_SHORT).show();
        }

    }
    public void crimewolinitiate(View v){
        Intent intent= new Intent(this,SearchCrimeWOL.class);
        startActivity(intent);
    }

}
