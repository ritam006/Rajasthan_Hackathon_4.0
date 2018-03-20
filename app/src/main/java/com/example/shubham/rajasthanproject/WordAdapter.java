package com.example.shubham.rajasthanproject;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shubham.rajasthanproject.CustomString;
import com.example.shubham.rajasthanproject.R;

import java.util.ArrayList;

/**
 * Created by Ritam Mallick on 02-01-2017.
 */

public class WordAdapter extends ArrayAdapter<CustomString> {

    public WordAdapter(Context context, ArrayList<CustomString> list){
        super(context,0,list);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        CustomString str=getItem(position);
        TextView text1=(TextView)view.findViewById(R.id.name_of_head);
        text1.setText(str.getName());

        TextView text2=(TextView)view.findViewById(R.id.primary_location);
        text2.setText(str.getAddress());
        TextView text3 = (TextView)view.findViewById(R.id.mag);
        text3.setText(String.valueOf(str.getMagnitude()));
        GradientDrawable circle=(GradientDrawable)text3.getBackground();
        circle.setColor(getMagnitudeColor(str.getMagnitude()));

        /*TextView text3=(TextView)view.findViewById(R.id.primary_location);
        text3.setText(str.getString(2));
        TextView text4=(TextView)view.findViewById(R.id.time);
        text4.setText(str.getString(3));
        TextView text5=(TextView)view.findViewById(R.id.date);
        text5.setText(str.getString(4));*/

        return view;
    }

    public int getMagnitudeColor(Double magnitude){
        int magnitudeColorId;
        //int mag=(int)Math.floor(magnitude);
        if(magnitude <10)
            magnitudeColorId = ContextCompat.getColor(getContext(),R.color.magnitude1);
        else if(magnitude >20 && magnitude <30)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude2);
        else if(magnitude >30 && magnitude <40)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude3);
        else if(magnitude >40 && magnitude <50)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude4);
        else if(magnitude >50 && magnitude <60)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude5);
        else if(magnitude >60 && magnitude <70)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude6);
        else if(magnitude >70 && magnitude <80)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude7);
        else if(magnitude >80 && magnitude <90)
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude8);
        else
            magnitudeColorId= ContextCompat.getColor(getContext(),R.color.magnitude9);

        /*switch(mag){
            case 0:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 1:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 2:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 3:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 4:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:magnitudeColorId=ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:magnitudeColorId=0;
        }*/
        return magnitudeColorId;
    }
}