package com.example.eiko.justjava;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by eiko on 7/11/2016.
 */
public class Menu extends ListActivity {
    String menu[]={"MainActivity","WeatherActivity"};

    @Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this,
                android.R.layout.simple_list_item_1,
                menu));
}
    @Override
    protected void onListItemClick(ListView l, View v,int position,
                                   long id){
        super.onListItemClick(l, v, position, id);
        String menuclick = menu[position];
        try {
            Class ourclass = Class.forName("com.example.eiko.justjava."+menuclick);
            Intent intent = new Intent(Menu.this, ourclass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
