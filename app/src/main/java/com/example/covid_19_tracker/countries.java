package com.example.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.companion.CompanionDeviceManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class countries extends AppCompatActivity  {


     private ProgressBar p  ;
     private LinearLayout ln ;

    interface dataCallbacks {
        public void getDataFromResult(List<country> countryList);
    }

    private static final String TAG = "countries" ;
    public static List<country> countriess = new ArrayList<>();

    dataCallbacks callbacks ;

    public static List<country> getlist = new ArrayList<>() ;
    public ListView listView1 ;
    TextView back ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        Log.d(TAG,"Oncreate :started");
        final ListView listView = (ListView)findViewById(R.id.listcountries);
        listView1 = listView ;

        p = (ProgressBar)findViewById(R.id.pbar);
        ln = (LinearLayout)findViewById(R.id.LinearLayout);
         back = (TextView)findViewById(R.id.back);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coronavirus-19-api.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build() ;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<country>> call = jsonPlaceHolderApi.getcountries();

        //////   Interface :

        this.callbacks = new dataCallbacks() {
            @Override
            public void getDataFromResult( List<country> countryList) {

                    getlist = countryList ;


            }
        };

        ///////


        call.enqueue(new Callback<List<country>>() {

            public void onResponse(Call<List<country>> call, Response<List<country>> response ) {



                countriess = response.body();

                callbacks.getDataFromResult(countriess);
                final countries_adapter  adapter = new countries_adapter(countries.this,R.layout.adapter_view_countries,countriess);
                listView.setAdapter(adapter);

                p.setVisibility(View.GONE);
                ln.setVisibility(View.VISIBLE);



            }

            @Override
            public void onFailure(Call<List<country>> call, Throwable t) {
              //  Toast.makeText(getApplicationContext(),"Wait a second",Toast.LENGTH_SHORT).show();

            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.SearchMenu);

        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 List<country> result = new ArrayList<>();
                 for (country x:getlist){

                     if(x.getCountry().contains(newText)){
                         result.add(x);
                     }

                 }


                final countries_adapter  adapter = new countries_adapter(countries.this,R.layout.adapter_view_countries,result);
                listView1.setAdapter(adapter);



                return false;
            }
        });
        return  super.onCreateOptionsMenu(menu);




    }


    public  void movetostatistics(String country){

        Intent a = new Intent(this,morestatis.class);
        startActivity(a);

    }



}
