package com.example.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class morestatis extends AppCompatActivity {

    TextView country        ;
    TextView todaycase      ;
    TextView todaydeath     ;
    TextView activecase     ;
    TextView totaltests     ;
    TextView criticalcases  ;
    LinearLayout statis ;
    ProgressBar pbar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morestatis);

       country = (TextView)findViewById(R.id.name);
       todaycase = (TextView)findViewById(R.id.todaycase);
       todaydeath = (TextView)findViewById(R.id.todaydeaths);
       activecase = (TextView)findViewById(R.id.activecases);
       totaltests = (TextView)findViewById(R.id.totaltests);
       criticalcases = (TextView)findViewById(R.id.criticalcases);
       statis = (LinearLayout)findViewById(R.id.statis);
       pbar = (ProgressBar)findViewById(R.id.progressBar);

       TextView back = (TextView)findViewById(R.id.back);







       final String countryname =  getIntent().getStringExtra("country");

       country.setText(countryname);

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

                      int  i1 = 0 ;
                      int  i2  = 0;
                      int  i3  = 0;
                      int  i4  = 0;
                      int  i5  = 0;



                        for (country country :countries) {
                            if (country.getCountry().equals(countryname)) {
                                i1 = country.getTodayCases() ;
                                i2 = country.getTodayDeaths();
                                i3 = country.getActive() ;
                                i4 = country.getTotalTests() ;
                                i5 = country.getCritical() ;
                            }
                        }

                         pbar.setVisibility(View.GONE);
                         statis.setVisibility(View.VISIBLE);

                         todaycase.setText("+"+i1);
                         todaydeath.setText(""+i2);

                         activecase.setText(""+i3);
                         totaltests.setText(""+i4);
                         criticalcases.setText(""+i5);







                    }

                    @Override
                    public void onFailure(Call<List<country>> call, Throwable t) {


                    }
                });                        ///////////////////////



            }
        };

        t.schedule(p,0,1000);

     back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            finish();
         }
     });



    }
}
