package com.example.chatclient;


import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

//essa classe que adaptará o array de mensagens e linkará cada objeto da classe Mensagem com
//o layout mensagem.xml
public class MeuArrayAdapter extends ArrayAdapter<Mensagem> {
    private final Context contexto;
    private final ArrayList<Mensagem> mensagens;
    private int impar = 1;

    public MeuArrayAdapter(Context contexto, ArrayList<Mensagem> mensagens){
        //cria-se o ArrayAdapter utilizando a MainActivty como contexto, o id do layout de msg
        //como layout e o ArrayList de mensagens
        super(contexto, R.layout.mensagem, mensagens);
        this.contexto = contexto;
        this.mensagens = mensagens;
    }
    // This method constructs the ListItem's view from the data obtained
    // from the Message object. This will be called by ListView while it
    // is being drawn. Quando é chamado o notifyDataSetChanged(), esse metodo
    //também é chamado
    //position do item na lista,convertView é a View do item da lista pra ter o
    //layout mensagem.xml


    public View getView(int position, View convertView, ViewGroup parent) {
        View viewDoLayoutMensgagemInteiro;
    // Get a reference to the LayoutInflater. This helps construct the
    // view from the layout file
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    // The viewDoLayoutMensgagemInteiro is constructed by inflating the message.xml layout file
        viewDoLayoutMensgagemInteiro = inflater.inflate(R.layout.mensagem, parent, false);
    // Get the reference to the two TextViews in the message layout and set the
        LinearLayout layoutLinear = (LinearLayout) viewDoLayoutMensgagemInteiro.findViewById(R.id.layoutLinear);
        TextView msgView = (TextView) viewDoLayoutMensgagemInteiro.findViewById(R.id.txtMensagem);
        msgView.setText(mensagens.get(position).getMensagem());
    // to the time and message string respectively
        TextView timeTime = (TextView) viewDoLayoutMensgagemInteiro.findViewById(R.id.timeTime);
        timeTime.setText(mensagens.get(position).getTime()+" em "+mensagens.get(position).getData());
        if(mensagens.get(position).getDeMim()) {
            layoutLinear.setGravity(Gravity.LEFT);

        }else{
            layoutLinear.setGravity(Gravity.RIGHT);
            msgView.setBackgroundColor(123);
        }
        impar++;
        return viewDoLayoutMensgagemInteiro;
    }
}
