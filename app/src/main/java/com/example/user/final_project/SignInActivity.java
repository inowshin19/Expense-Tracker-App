package com.example.user.final_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    String s1, s2;
    String msg, id1;
    int val,id;
    JSONArray pro;
    EditText ed1,ed2;
    JSONParser jsonParser=new JSONParser();
    UrlSignIn url=new UrlSignIn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
    public void signin(View view){

        ed1=(EditText) findViewById(R.id.editText4);
        ed2=(EditText) findViewById(R.id.editText5);
        s1=ed1.getText().toString();
        s2=ed2.getText().toString();
        new DataSend().execute();
    }
    public void signup(View view){
        Intent intent=new Intent(SignInActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public class DataSend extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);

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
                pairlist.add(new BasicNameValuePair("username", s1));
                pairlist.add(new BasicNameValuePair("password", s2));


                JSONObject jsonObject = jsonParser.makeHttpRequest(url.IP,"POST", pairlist);
                val=jsonObject.getInt("val");
                msg=jsonObject.getString("msg");
                id=jsonObject.getInt("id");
                //pro=jsonObject.getJSONArray("id");
/*
                for(int i=0; i<pro.length(); i++){
                    JSONObject jo=pro.getJSONObject(i);
                     id= jo.getString("id");
                }*/
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
                Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }
    }
}
