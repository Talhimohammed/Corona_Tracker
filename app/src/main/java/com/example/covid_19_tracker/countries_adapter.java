package com.example.covid_19_tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class countries_adapter extends ArrayAdapter<country> {

    private static final String TAG = "CountryAdapter" ;

    private Context ncontext ;
    int nressource ;
    public List<country> objects ;


    public countries_adapter(Context context, int resource,List<country> objects) {
        super(context, resource, objects);
        ncontext = context ;
        nressource = resource ;
        this.objects = objects ;
    }

    public void update(List<country> re,List<country> result){
                 re = new ArrayList<>();
                 re.addAll(result);
                 notifyDataSetChanged();

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                      final String country = getItem(position).getCountry();
                      int recovred = getItem(position).getRecovred() ;
                      int cases = getItem(position).getCases()       ;
                      int deaths = getItem(position).getDeaths()     ;

        LayoutInflater inflater =  LayoutInflater.from(ncontext);
        convertView = inflater.inflate(nressource,parent,false);

        TextView tvcountry = (TextView)convertView.findViewById(R.id.country);
        TextView tvrecov = (TextView)convertView.findViewById(R.id.revovered);
        TextView tvdeaths = (TextView)convertView.findViewById(R.id.deaths)  ;
        TextView tvcases = (TextView)convertView.findViewById(R.id.confirmed)   ;
        TextView morestatistics = (TextView)convertView.findViewById(R.id.morestat);





        tvcountry.setText(""+country+"");
        tvrecov.setText(""+recovred+" recovered") ;
        tvdeaths.setText(""+deaths+" deaths")  ;
        tvcases.setText(""+cases+" cases")    ;

        morestatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(ncontext,morestatis.class);
                a.putExtra("country",country);
                v.getContext().startActivity(a);




            }
        });


        return  convertView ;


    }
}
