package com.example.user.final_project;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckActivity extends AppCompatActivity {

    String s4, msg, total;
    int id,id1, val;
    TextView text;
    JSONParser jsonParser=new JSONParser();
    URLCheck url=new URLCheck();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        text=(TextView) findViewById(R.id.text_balance);
        id=getIntent().getExtras().getInt("id");
        new DataReceive().execute();
    }
    public class DataReceive extends AsyncTask<String, String, String> {

        ProgressDialog dialog=new ProgressDialog(CheckActivity.this);

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
                total=jsonObject.getString("sum");
                id1=jsonObject.getInt("id");

                //id1=jsonObject.getInt("id");
                //pro=jsonObject.getJSONArray("information");

              /*  for(int i=0; i<pro.length(); i++){
                    JSONObject jo=pro.getJSONObject(i);
                    String amount= jo.getString("amount");
                    String purpose= jo.getString("purpose");
                    String date= jo.getString("date");

                    //arrayList1.add(amount);
                    //arrayList2.add(purpose);
                    //arrayList3.add(date);
                    dbData=amount+"  "+purpose+"  "+date+"\n";
                    dataSet.add(dbData);

                }*/

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
                text.setText(total);
                //ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,dataSet);
                //list.setAdapter(arrayAdapter);
                //list.setAdapter(custom);

            }
            else{

                Toast.makeText(getApplicationContext(),msg+id1,Toast.LENGTH_SHORT).show();

            }
        }
    }
}
