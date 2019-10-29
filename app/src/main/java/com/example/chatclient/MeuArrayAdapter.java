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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//
public class MeuArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<Mensagem> mensagens;

    private static final int VIEW_HOLDER_TYPE_1=1;
    private static final int VIEW_HOLDER_TYPE_2=2;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder_Type1 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mensagemTextView, mytimeTime;
        public ViewHolder_Type1(View v) {
            super(v);
            this.mensagemTextView = (TextView) v.findViewById(R.id.txtMensagem);
            this.mytimeTime = (TextView) v.findViewById(R.id.timeTime);
        }


    }

    public static class ViewHolder_Type2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtMensagem, timeTime;
        public ViewHolder_Type2(View v) {
            super(v);
            this.txtMensagem = (TextView) v.findViewById(R.id.txtMensagem);
            this.timeTime = (TextView) v.findViewById(R.id.timeTime);
        }


    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MeuArrayAdapter(Context context, ArrayList<Mensagem> mensagens) {
        this.context = context;
        this.mensagens = mensagens;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View v;

        switch (viewType) {
            // create a new view

            case VIEW_HOLDER_TYPE_1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mensagem, parent, false);
                ViewHolder_Type1 vh1 = new ViewHolder_Type1(v);
                return vh1;

            case VIEW_HOLDER_TYPE_2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mensagem_outro, parent, false);
                ViewHolder_Type2 vh2 = new ViewHolder_Type2(v);
                return vh2;

            default:
                break;
        }

        return null;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        switch (getItemViewType(position)) {

            case VIEW_HOLDER_TYPE_1:
                ViewHolder_Type1 viewholder1 = (ViewHolder_Type1) holder;
                TextView mytimeView = (TextView) viewholder1.mytimeTime;
                mytimeView.setText(mensagens.get(position).getTime());
                TextView mymsgView = (TextView) viewholder1.mensagemTextView;
                mymsgView.setText(mensagens.get(position).getMensagem());
                break;

            case VIEW_HOLDER_TYPE_2:
                ViewHolder_Type2 viewholder2 = (ViewHolder_Type2) holder;
                TextView timeView = (TextView) viewholder2.timeTime;
                timeView.setText(mensagens.get(position).getTime());
                TextView msgView = (TextView) viewholder2.txtMensagem;
                msgView.setText(mensagens.get(position).getMensagem());
                break;

            default:
                break;
        }

    }



    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 1 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (mensagens.get(position).getDeMim())
            return VIEW_HOLDER_TYPE_1;
        else
            return VIEW_HOLDER_TYPE_2;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mensagens.size();
    }
}
