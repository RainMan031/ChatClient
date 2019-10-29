package com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {

    ImageButton btnEnviar;
    EditText editMensagem;
    RecyclerView listMensagens;
    ArrayList<Mensagem> arrayListDeMsg;
    RecyclerView.Adapter meuArrayAdapter;
    ArrayList<String> respostas;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEnviar = (ImageButton) this.findViewById(R.id.btnEnviar);
        editMensagem = (EditText) findViewById(R.id.editMensagem);




        arrayListDeMsg = new ArrayList<Mensagem>();
        meuArrayAdapter = new MeuArrayAdapter(this,arrayListDeMsg);
        //seta o adaptador do list view pra ser o meuArrayAdapter, pois ele tem a
        //referência dessa classe como contexto, além do método getView que retorna
        //uma view para o layout mensagem a cada mensagem

        //o que vai fornecer data pro RecyclerView
        listMensagens = (RecyclerView) findViewById(R.id.listMensagens);
        //o que vai fornecer data pro RecyclerView
        listMensagens.setAdapter(meuArrayAdapter);
        //o gerenciador do layout do RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        listMensagens.setLayoutManager(llm);


        btnEnviar.setOnClickListener(this);
        btnEnviar.setFocusable(true);
        editMensagem.setOnKeyListener(this);


        respostas = new ArrayList<String>();


        respostas.add(this.getString(R.string.adeus));
        respostas.add(this.getString(R.string.que_bom));
        respostas.add(this.getString(R.string.como_vc_ta));
        respostas.add(this.getString(R.string.ola));



    }

    private static final String TAG = "MainActivity";


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
                    }else{
                        Mensagem resposta = new Mensagem("outro", this.getString(R.string.adeus),
                                false,new Date());
                        arrayListDeMsg.add(resposta);
                        meuArrayAdapter.notifyDataSetChanged();
                    }
                    listMensagens.scrollToPosition(arrayListDeMsg.size()-1);


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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        Log.i(TAG, "in onKey()");
        if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
            btnEnviar.callOnClick();

            return true;
        }
        else{
            return false;
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

}
