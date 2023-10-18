package com.example.user.final_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=getIntent().getExtras().getInt("id");

    }
    public void earn(View view){
        Intent intent=new Intent(MainActivity.this,EarnedActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public void alarm(View view){
        Intent intent=new Intent(MainActivity.this,AlarmActivity.class);
        startActivity(intent);
    }
    public void check(View view){
        Intent intent=new Intent(MainActivity.this,CheckActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public void spent(View view){
        Intent intent=new Intent(MainActivity.this,SpentActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
