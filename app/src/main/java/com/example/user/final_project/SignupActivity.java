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
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    String s1, s2,s3;
    String msg;
    int val,id;
    EditText ed1,ed2,ed3;
    JSONParser jsonParser=new JSONParser();
    UrlSignup url=new UrlSignup();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void signup(View view){
        //Intent intent=new Intent(SignupActivity.this,MainActivity.class);
         ed1=(EditText) findViewById(R.id.editText_username_signup);
         ed2=(EditText) findViewById(R.id.editText_email_signup);
         ed3=(EditText) findViewById(R.id.editText_pass_signup);
        s1=ed1.getText().toString();
        s2=ed2.getText().toString();
        s3=ed3.getText().toString();
        new DataSend().execute();
        //startActivity(intent);

    }
    public class DataSend extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);

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
                pairlist.add(new BasicNameValuePair("email", s2));
                pairlist.add(new BasicNameValuePair("password", s3));

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
                Intent intent=new Intent(SignupActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        }
    }
}
