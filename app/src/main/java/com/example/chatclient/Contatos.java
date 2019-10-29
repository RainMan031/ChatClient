package com.example.chatclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Contatos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    String[] names;

    ListView friendView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        names = getResources().getStringArray(R.array.friends);

        // If you are using a ListView widget, then your activity should implement
        // the onItemClickListener. Then you should set the OnItemClickListener for
        // teh ListView.
        friendView = (ListView) findViewById(R.id.friendListView);
        friendView.setAdapter(new ArrayAdapter<String>(this, R.layout.friend_item, names));
        friendView.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Should set up to add new contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        mIntent.putExtra(getString(R.string.friend), names[position]);
        startActivity(mIntent);

    }
}
