package com.example.srilekha.foodbin;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class vollistadapter extends ArrayAdapter<Vollistitem> {

    private Context mContext;

    private List<Vollistitem> llist = new ArrayList<>();


    public vollistadapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Vollistitem> objects) {
        super(context, 0 , objects);
        mContext = context;
        llist = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.vollistitem,parent,false);

        Vollistitem currentMovie = llist.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(currentMovie.getdname());

        TextView contact = (TextView) listItem.findViewById(R.id.contact);
        contact.setText(currentMovie.getcontact());

        TextView da = (TextView) listItem.findViewById(R.id.donoraddress);
        da.setText(currentMovie.getdonoraddr());

        /*TextView dad = (TextView) listItem.findViewById(R.id.deliveryaddress);
        dad.setText(currentMovie.getdeliveryaddress());*/

        return listItem;
    }
}
