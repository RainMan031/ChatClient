package com.example.chatclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contatos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar toolbar;
    String[] nomes;

    ListView friendView;

    ArrayAdapterContato ArrayAdapterContato;

    Context mContext;

    public class FriendInfo {
        int id;
        String nome;
        String status;
        String imageURL;


        public String getNome(){
            return this.nome;
        }

    }

    List<FriendInfo> friendInfoList = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        mContext= this;





        friendView = (ListView) findViewById(R.id.friendListView);
        Log.i("oiOnCreate","colecoleccole");
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

        FriendsProcessor mytask = new FriendsProcessor();
        mytask.execute("friendsjson.txt");


    }

    // This AsyncTask processes the Json string by reading it from a file in the assets folder and
    // then converts the string into a list of FriendInfo objects. You will also see the use of
    // a progress dialog to show that work is being processed in the background.
    //    AsyncTasks should ideally be used for short operations (a few seconds at the most.)
//    An asynchronous task is defined by 3 generic types, called Params, Progress and Result, and 4
//    steps, called onPreExecute, doInBackground, onProgressUpdate and onPostExecute.

    private class FriendsProcessor extends AsyncTask<String, Void, Integer> {

        ProgressDialog progressDialog;
        int delay = 5000 ; // ms

        public FriendsProcessor() {
            super();
        }

        // The onPreExecute is executed on the main UI thread before background processing is
        // started. In this method, we start the progressdialog.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show the progress dialog on the screen
            progressDialog = ProgressDialog.show(mContext, "Wait!","Downloading Friends List");
        }

        // This method is executed in the background and will return a result to onPostExecute
        // method. It receives the file name as input parameter.
        @Override
        protected Integer doInBackground(String... strings) {

            // Open an input stream to read the file
            InputStream inputStream;
            BufferedReader in;

            // this try/catch is used to create a simulated delay for doing the background
            // processing so that you can see the progress dialog on the screen. If the
            // data to be processed is large, then you don't need this.
            try {
                // Pretend downloading takes a long time
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Now we read the file, line by line and construct the
            // Json string from the information read in.
            try {

                // TODO read the file and process the string
                inputStream = mContext.getAssets().open(strings[0]);
                in = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                StringBuffer buf = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    buf.append(readLine);
                }
// Convert the read in information to a Json string
                String infoString = buf.toString();
// now process the string using the method that we implemented in the previous
                processFriendInfo(infoString);
                if (null != in) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return (Integer) 1;
        }

        // This method will be executed on the main UI thread and can access the UI and update
        // the listview. We dismiss the progress dialog after updating the listview.
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            Log.i("oiPostExe", String.valueOf(friendInfoList.size()));
            ArrayAdapterContato = new ArrayAdapterContato(mContext, friendInfoList);

            friendView.setAdapter((ListAdapter) ArrayAdapterContato);

            progressDialog.dismiss();
        }

        // This method is called if we cancel the background processing
        @Override
        protected void onCancelled() {
            super.onCancelled();

            progressDialog.dismiss();
        }
    }

    private void processFriendInfo(String infoString) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        friendInfoList = new ArrayList<FriendInfo>();
        friendInfoList = Arrays.asList(gson.fromJson(infoString, FriendInfo[].class));



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
