package com.example.bloodnearme2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class DonorList extends ArrayAdapter<user>
{
    private Activity context;
    private List<user> donorList;

    public DonorList(Activity context,List<user> donorList)
    {
        super(context,R.layout.listrow,donorList);
        this.context = context;
        this.donorList  = donorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listrow,null,true);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.displayname);
        TextView textViewemailid = (TextView)listViewItem.findViewById(R.id.displayemail);
        TextView textViewphone = (TextView)listViewItem.findViewById(R.id.displayphone);
        TextView textViewBg  = (TextView)listViewItem.findViewById(R.id.displaybg);
        TextView textViewdob = (TextView)listViewItem.findViewById(R.id.displaydob) ;
        user u = donorList.get(position);
        textViewBg.setText(u.getBloodgroup());
        textViewemailid.setText(u.getEmailid());
        textViewname.setText(u.getName());
        textViewphone.setText(u.getPhonenumber());
        textViewdob.setText(u.getDob());

        return listViewItem;
    }
}
