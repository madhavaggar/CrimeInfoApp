package com.example.game.deltatask3v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchCrimeActivity extends AppCompatActivity {

    private String date;
    private String latitude;
    private String longitude;
    private boolean wol=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_crime);
    }
    public void Proceed(View V){
        date=((EditText)findViewById(R.id.date)).getText().toString();
        latitude=((EditText)findViewById(R.id.latitude)).getText().toString();
        longitude=((EditText)findViewById(R.id.longitude)).getText().toString();
        Intent intent= new Intent(SearchCrimeActivity.this,CrimeInfo.class);
        if(date.equals("") || latitude.equals("") || longitude.equals("")) {
            Toast.makeText(this,"You cannot leave any of these fields empty!",Toast.LENGTH_SHORT).show();
        }
        else{
            intent.putExtra("date", date);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putExtra("withoutlocation", wol);
            startActivity(intent);

        }
    }
}
