package com.example.game.deltatask3v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecForceInfo extends AppCompatActivity {
    private static final String BASE_URL = "https://data.police.uk/api/";
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_force_info);
        Intent intent = getIntent();
        String id=intent.getStringExtra("id");
        info= findViewById(R.id.expandinfo);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Spec_Forces> call = service.getspec_force(id);
        call.enqueue(new Callback<Spec_Forces>() {
            @Override
            public void onResponse(Call<Spec_Forces> call, Response<Spec_Forces> response) {
                if (!response.isSuccessful()){
                    info.setText("Invalid Name");
                }
                Spec_Forces Force=response.body();
                if(Force==null){
                    info.setText("Invalid Name");
                }
                else{
                    String str= "Name: " + Force.getName() + "\nDescription: " + Force.getDescription() + "\nURL: " + Force.getUrl() + "\nEngagement Methods: [\n";
                    info.setText(str);
                    for(int i=0;i<Force.getEng_Meth().length;i++){
                        str= "{\nURL: " + Force.getEng_Meth(i).getUrl()+ "\nDescription: " + Force.getEng_Meth(i).getDescription()+ "\nTitle: " + Force.getEng_Meth(i).getTitle()+ '\n';
                        info.append(str);
                    }
                    info.append("\nTelephone: " + Force.getTelephone() + "\nID: " + Force.getId() + "\nName: " + Force.getName() + "\n}");
                }
            }

            @Override
            public void onFailure(Call<Spec_Forces> call, Throwable t) {
                Toast.makeText(SpecForceInfo.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
