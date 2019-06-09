package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class view extends AppCompatActivity {
    private TextView bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        Intent intent =getIntent();
        String title=intent.getStringExtra("title");
        databasetest dbt=new databasetest(this);
        dbt.findzw(title);



    }
}
