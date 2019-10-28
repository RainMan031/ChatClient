package com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEnviar;
    EditText editMensagem;
    ListView listMensagens;
    ArrayList<Mensagem> arrayListDeMsg;
    MeuArrayAdapter meuArrayAdapter;
    ArrayList<String> respostas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = (Button) this.findViewById(R.id.btnEnviar);
        editMensagem = (EditText) findViewById(R.id.editMensagem);
        listMensagens = (ListView) findViewById(R.id.listMensagens);


        btnEnviar.setOnClickListener(this);
        arrayListDeMsg = new ArrayList<Mensagem>();
        meuArrayAdapter = new MeuArrayAdapter(this,arrayListDeMsg);
        //seta o adaptador do list view pra ser o meuArrayAdapter, pois ele tem a
        //referência dessa classe como contexto, além do método getView que retorna
        //uma view para o layout mensagem a cada mensagem
        listMensagens.setAdapter((ListAdapter) meuArrayAdapter);

        respostas = new ArrayList<String>();
        respostas.add(this.getString(R.string.adeus));
        respostas.add(this.getString(R.string.que_bom));
        respostas.add(this.getString(R.string.como_vc_ta));
        respostas.add(this.getString(R.string.ola));



    }

    private static final String TAG = "MainActivity";

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnviar:
                String mensagemDeTexto = editMensagem.getText().toString();


                //se a mensagem não for nula
                if (!mensagemDeTexto.equals("")) {
                    Mensagem mensagemMinha = new Mensagem("",mensagemDeTexto,true,new Date());
                    arrayListDeMsg.add(mensagemMinha);
                    // Notify the adapter that the data has changed due to the add
                    //ition
                        // of a new message object. This triggers an update of the ListView
                    meuArrayAdapter.notifyDataSetChanged();
                    mensagemMinha = null;
                    editMensagem.setText("");

                    if(respostas.size()>0) {
                        Mensagem resposta = new Mensagem("outro", respostas.get(respostas.size()-1),
                                false,new Date());
                        arrayListDeMsg.add(resposta);
                        meuArrayAdapter.notifyDataSetChanged();
                        respostas.remove(respostas.size()-1);
                    }


                }


                break;
            default:
                break;
        }


    }
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "in onStart()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "in onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "in onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "in onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "in onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "in onDestroy()");
    }
}
