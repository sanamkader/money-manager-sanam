package com.example.y.moneymanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y on 26-11-2016.
 */

public class Listdataadapter extends ArrayAdapter {
    List list = new ArrayList();
    public Listdataadapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Layouthandler layouthandler;
        View row = convertView;
        if(row==null){
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_view,parent,false);
            layouthandler = new Layouthandler();
            layouthandler.tv_year = (TextView) row.findViewById(R.id.tv_year);
            layouthandler.tv_month = (TextView) row.findViewById(R.id.tv_month);
            layouthandler.tv_week = (TextView) row.findViewById(R.id.tv_week);
            layouthandler.tv_date = (TextView) row.findViewById(R.id.tv_date);
            layouthandler.tv_category = (TextView) row.findViewById(R.id.tv_category);
            layouthandler.tv_amount = (TextView) row.findViewById(R.id.tv_amount);
            row.setTag(layouthandler);


        }else{
            layouthandler =(Layouthandler) row.getTag();

        }
        Dataprovider dataprovider =(Dataprovider) this.getItem(position);
        layouthandler.tv_year.setText(dataprovider.getYear());
        layouthandler.tv_month.setText(dataprovider.getMonth());
        layouthandler.tv_week.setText(dataprovider.getWeek());
        layouthandler.tv_date.setText(dataprovider.getDate());
        layouthandler.tv_category.setText(dataprovider.getCategory());
        layouthandler.tv_amount.setText(dataprovider.getAmount());
        return row;

    }


    static  class Layouthandler{
        TextView tv_month,tv_week,tv_date,tv_category,tv_amount,tv_year;

    }
}
