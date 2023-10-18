package com.example.user.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 11/25/2016.
 */
public class CustomAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;

    public CustomAdapter(EarnedActivity earnedActivity, ArrayList<String> arrayList1, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        this.context=earnedActivity;
        this.arrayList1=arrayList1;
        this.arrayList2=arrayList2;
        this.arrayList3=arrayList3;
    }



    @Override
    public int getCount() {
        return arrayList1.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false);
        }
        String amount_earn=arrayList1.get(position);
        String purpose_earn=arrayList2.get(position);
        String date_earn=arrayList3.get(position);
        TextView date=(TextView) convertView.findViewById(R.id.text_date);
        TextView purpose=(TextView) convertView.findViewById(R.id.text_purpose);
        TextView amount=(TextView) convertView.findViewById(R.id.text_amount);

        amount.setText(amount_earn);
        purpose.setText(purpose_earn);
        date.setText(date_earn);
        return convertView;
    }

}
