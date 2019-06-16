package com.example.exam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class view extends Activity {
    private EditText bt;
    private EditText zw;
    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.text_view);
        bt=(EditText) findViewById(R.id._Ytitle_1);
        zw=(EditText)findViewById(R.id.mainnote_1);
        button1=(Button)findViewById(R.id.save_1) ;
        button2=(Button)findViewById(R.id._back1);
        bt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>50){
                    bt.setText(s.toString().substring(0,50)); //设置EditText只显示前面50位字符
                    bt.setSelection(50);//让光标移至末端
                    Toast.makeText(view.this, "输入字数已达上限", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Intent intent =getIntent();
        final String title1=intent.getStringExtra("title");
        final databasetest dbt=new databasetest(this);
        bt.setText(title1);
        zw.setText(dbt.findzw(title1).toString().replaceAll("\\[|\\]", ""));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=bt.getText().toString();
                String name=zw.getText().toString();
                ContentValues values = new ContentValues();
                values.put("title",title);
                values.put("value",name);
                dbt.open();
                dbt.update(values,title1);
                dbt.close();
                Intent intent = new Intent(view.this,mainface.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.this,mainface.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });








    }
}
