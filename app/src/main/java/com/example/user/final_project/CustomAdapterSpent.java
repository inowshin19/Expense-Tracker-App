package com.example.user.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 11/26/2016.
 */
public class CustomAdapterSpent extends BaseAdapter{
    Context context;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    public CustomAdapterSpent(SpentActivity spentActivity, ArrayList<String> arrayList1, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        this.context=spentActivity;
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
        String amount_spent=arrayList1.get(position);
        String purpose_spent=arrayList2.get(position);
        String date_spent=arrayList3.get(position);
        TextView date=(TextView) convertView.findViewById(R.id.text_date);
        TextView purpose=(TextView) convertView.findViewById(R.id.text_purpose);
        TextView amount=(TextView) convertView.findViewById(R.id.text_amount);
        amount.setText(amount_spent);
        purpose.setText(purpose_spent);
        date.setText(date_spent);
        return convertView;
    }
}
