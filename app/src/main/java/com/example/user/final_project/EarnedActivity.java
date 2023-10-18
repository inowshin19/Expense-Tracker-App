package com.example.user.final_project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EarnedActivity extends AppCompatActivity {

    String s1, s2,s3,s4;
    ListView list;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    String dbData;
    ArrayList<String> dataSet=new ArrayList<String>();
    TextView textView5;
    EditText editText_amount;
    EditText editText_purpose;
    String msg;
    int val,id,id1;
    JSONParser jsonParser=new JSONParser();
    JSONParser jParser=new JSONParser();
    URL url=new URL();
    JSONArray pro;
    CustomAdapter custom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earned);
        id=getIntent().getExtras().getInt("id");
        list = (ListView) findViewById(R.id.list);
        custom = new CustomAdapter(this, arrayList1,arrayList2,arrayList3);
        new DataReceive().execute();



        textView5 = (TextView) findViewById(R.id.textview5);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        String dateString = sdf.format(date);
        textView5.setText(dateString);
    }


    public void btn_earn(View view) {
        editText_amount = (EditText) findViewById(R.id.editText2);
        editText_purpose = (EditText) findViewById(R.id.editText);
        s1 = editText_amount.getText().toString();
        s2 = editText_purpose.getText().toString();
        s3 = textView5.getText().toString();
        s4=Integer.toString(id);
        //list = (ListView) findViewById(R.id.list);
        //custom = new CustomAdapter(this, arrayList1,arrayList2,arrayList3);
        new DataSend().execute();
        //new DataReceive().execute();


    }
    public class DataSend extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(EarnedActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                List<NameValuePair> pairlist = new ArrayList<NameValuePair>();
                s4=Integer.toString(id);
                pairlist.add(new BasicNameValuePair("user_id",s4));
                pairlist.add(new BasicNameValuePair("amount", s1));
                pairlist.add(new BasicNameValuePair("purpose", s2));
                pairlist.add(new BasicNameValuePair("date", s3));


                JSONObject jsonObject = jsonParser.makeHttpRequest(url.IP,"POST", pairlist);
                val=jsonObject.getInt("val");
                msg=jsonObject.getString("msg");
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(val==0){
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class DataReceive extends AsyncTask <String, String, String>{

        ProgressDialog dialog=new ProgressDialog(EarnedActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                List<NameValuePair> pairList1=new ArrayList<NameValuePair>();
                s4=Integer.toString(id);
                pairList1.add(new BasicNameValuePair("id", s4));
                JSONObject jsonObject=jsonParser.makeHttpRequest(url.RECEIVE_LINK,"POST",pairList1);
                val=jsonObject.getInt("val");
                msg=jsonObject.getString("msg");
                id1=jsonObject.getInt("id");
                pro=jsonObject.getJSONArray("information");

                for(int i=0; i<pro.length(); i++){
                    JSONObject jo=pro.getJSONObject(i);
                    String amount= jo.getString("amount");
                    String purpose= jo.getString("purpose");
                    String date= jo.getString("date");

                    arrayList1.add(amount);
                    arrayList2.add(purpose);
                    arrayList3.add(date);
                    //dbData=amount+"  "+purpose+"  "+date+"\n";
                    //dataSet.add(dbData);

                }

            }catch (Exception c){

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            dialog.dismiss();
            if(val==1){
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                //ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,dataSet);
                //list.setAdapter(arrayAdapter);
                list.setAdapter(custom);

            }
            else{

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

            }
        }
    }
}