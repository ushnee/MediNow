package com.example.naqvipc.medinow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ItemListAdapter extends ArrayAdapter<Items>{

    private ArrayList<Items> items;
    private Context mContext;

    long TOTALAMOUNT=0;

    public ItemListAdapter(ArrayList<Items> items, Context mContext) {
        super(mContext,R.layout.user_info,items);
        this.mContext=mContext;
        this.items=items;

    }


    private static class ViewHolder {
        TextView Amount;
        TextView Name;
        ImageView imageView;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
     /*   String name = getItem(position).getName();
        long price = getItem(position).getPrice();
        long qty = getItem(position).getQty();
        long amount = getItem(position).getAmount();*/

        Items items =getItem(position);

        ViewHolder viewHolder;
        final View result;
        if (convertView==null) {


            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.user_info, parent, false);

            viewHolder=new ViewHolder();

            viewHolder.Amount = (TextView) convertView.findViewById(R.id.itemprice);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.itemname);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.itemimage);

            result=convertView;
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.Amount.setText(String.valueOf(items.getAmount()));
        viewHolder.Name.setText(items.getName());
        Picasso.with(mContext).load(items.getImage()).into(viewHolder.imageView);

        return convertView;



    }
}
