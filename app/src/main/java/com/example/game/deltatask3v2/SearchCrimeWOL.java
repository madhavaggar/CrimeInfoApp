package com.example.game.deltatask3v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchCrimeWOL extends AppCompatActivity {

    private String date;
    private String category;
    private String force;
    private boolean wol=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_crime_wol);
    }
        public void Proceed(View V){
            date=((EditText)findViewById(R.id.date)).getText().toString();
            category=((EditText)findViewById(R.id.latitude)).getText().toString();
            force=((EditText)findViewById(R.id.longitude)).getText().toString();
            Intent intent= new Intent(SearchCrimeWOL.this,CrimeInfo.class);
            if(date.equals("") || category.equals("") || force.equals("")) {
                Toast.makeText(this, "You cannot leave any of these fields empty!", Toast.LENGTH_SHORT).show();
            }
            else {
                intent.putExtra("date", date);
                intent.putExtra("latitude", category);
                intent.putExtra("longitude", force);
                intent.putExtra("withoutlocation", wol);
                startActivity(intent);
            }
        }

}
