package com.example.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contatos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    String[] nomes;

    ListView friendView;

    ArrayAdapterContato ArrayAdapterContato;

    public class FriendInfo {
        int id;
        String nome;
        String status;


        public String getNome(){
            return this.nome;
        }

    }

    List<FriendInfo> friendInfoList = null;

    String friendJsonString = "[" +
            "{" +
            "\"id\": 1," +
            "\"nome\": \"John\"," +
            "\"status\": \"Imagine all the people ...\"" +
            "}," +
            "{" +
            "\"id\": 2," +
            "\"nome\": \"Paul\"," +
            "\"status\": \"Let it be ...\"" +
            "}," +
            "{" +
            "\"id\": 3," +
            "\"nome\": \"George\"," +
            "\"status\": \"Wait mister postman ...\"" +
            "}," +
            "{" +
            "\"id\": 4," +
            "\"nome\": \"Ringo\"," +
            "\"status\": \"Yellow submarine ...\"" +
            "}" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        processFriendInfo(friendJsonString);

        ArrayAdapterContato = new ArrayAdapterContato(this, friendInfoList);


        friendView = (ListView) findViewById(R.id.friendListView);

        friendView.setAdapter(ArrayAdapterContato);
        friendView.setOnItemClickListener(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Should set up to add new contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void processFriendInfo(String friendJsonString) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        friendInfoList = new ArrayList<FriendInfo>();
        friendInfoList = Arrays.asList(gson.fromJson(friendJsonString, FriendInfo[].class));

        for(int i=0;i<friendInfoList.size();i++){

                String num = String.valueOf(friendInfoList.size());
                Log.i("ContatosfriendInfoList", friendInfoList.get(i).getNome());

        }

    }

    //infla o menu ao clicar nele
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent mIntent = new Intent(this,MainActivity.class);
        TextView friendName = (TextView) view.findViewById(R.id.friendName);
        mIntent.putExtra(getString(R.string.friend), friendName.getText().toString());
        startActivity(mIntent);

    }
}
