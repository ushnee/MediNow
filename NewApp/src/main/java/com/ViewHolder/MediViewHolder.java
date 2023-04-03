

package com.ViewHolder;


import androidx.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Interface.MediClickListener;
import com.example.naqvipc.medinow.R;

public class MediViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMediName, txtMediDesc, txtMediPrice;
    public ImageView imgViewMedi;

    public MediClickListener mlistener;

    public MediViewHolder(View itemView) {
        super(itemView);

        imgViewMedi = (ImageView) itemView.findViewById(R.id.imageButtonMedi);
        txtMediName = (TextView) itemView.findViewById(R.id.eTxtMediName);
        txtMediDesc = (TextView) itemView.findViewById(R.id.eTxtMediDescription);
        txtMediPrice = (TextView) itemView.findViewById(R.id.eTxtMediPrice);
    }

    public void setMediClickListener(MediClickListener listener)
    {
        this.mlistener = listener;
    }

    @Override
    public void onClick(View v) {

        mlistener.onClick(v, getAdapterPosition(), false);

    }
}
