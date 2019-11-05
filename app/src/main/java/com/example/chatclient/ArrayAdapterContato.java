package com.example.chatclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ArrayAdapterContato extends ArrayAdapter<Contatos.FriendInfo> {
    private final Context context;
    private final List<Contatos.FriendInfo> friendInfoArrayList;

    public ArrayAdapterContato(Context context, List<Contatos.FriendInfo> friendInfoArrayList) {
        super(context, R.layout.friend_item, friendInfoArrayList);
        this.context = context;
        this.friendInfoArrayList = friendInfoArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View friendInfoView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Change the layout based on who the message is from
        friendInfoView = inflater.inflate(R.layout.friend_item, parent, false);
        TextView friendName = (TextView) friendInfoView.findViewById(R.id.friendName);
        friendName.setText(friendInfoArrayList.get(position).nome);
        TextView statusMsg = (TextView) friendInfoView.findViewById(R.id.statusMsg);
        statusMsg.setText(friendInfoArrayList.get(position).status);

        return friendInfoView;
    }
}
