package com.example.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView confirmed ;
    private TextView recovred ;
    private TextView deaths ;
    private ProgressBar bar ;
    private Button search ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirmed = (TextView)findViewById(R.id.confirmed);
        deaths = (TextView)findViewById(R.id.deaths);
        recovred = (TextView)findViewById(R.id.recovred);
        bar = (ProgressBar)findViewById(R.id.pbar);
        search = (Button)findViewById(R.id.search);


        final Timer t = new Timer();
        TimerTask p = new TimerTask(){
            @Override
            public void run() {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://coronavirus-19-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build() ;
                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                Call<List<country>> call = jsonPlaceHolderApi.getcountries();

                call.enqueue(new Callback<List<country>>() {
                    @Override
                    public void onResponse(Call<List<country>> call, Response<List<country>> response) {


                        List<country> countries = response.body();

                        int i1 = 0 ;
                        int i2 = 0 ;
                        int i3 = 0 ;

                        for (country country :countries) {
                            if (country.getCountry().equals("World")) {
                                         i1 = country.getCases();
                                         i2 = country.getRecovred();
                                         i3 = country.getDeaths();
                            }


                        }

                        bar.setVisibility(View.GONE);
                        confirmed.setText("");
                        confirmed.setText(""+i1+"");
                        recovred.setText("");
                        recovred.setText(""+i2+"");
                        deaths.setText("");
                        deaths.setText(""+i3+"");



                    }

                    @Override
                    public void onFailure(Call<List<country>> call, Throwable t) {


                    }
                });                        ///////////////////////


            }
        };

        t.schedule(p,0,1000);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,countries.class);
                startActivity(intent);
            }
        });

    }
}
