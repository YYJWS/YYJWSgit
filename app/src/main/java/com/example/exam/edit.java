package com.example.exam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends Activity {
    private EditText editText1,editText2;
    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_view);
        editText1=(EditText)findViewById(R.id._Ytitle);
        editText2=(EditText)findViewById(R.id.mainnote); 
        button1=(Button)findViewById(R.id.save);
        button2=(Button)findViewById(R.id._back);

        editText1.setFocusable(true);
        editText1.setFocusableInTouchMode(true);
        editText1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>50){
                    editText1.setText(s.toString().substring(0,50)); //设置EditText只显示前面50位字符
                    editText1.setSelection(50);//让光标移至末端
                    Toast.makeText(edit.this, "输入字数已达上限", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasetest dbt= new databasetest(getApplicationContext());
                dbt.open();
                String title=editText1.getText().toString();
                String name=editText2.getText().toString();
                ContentValues values = new ContentValues();
                values.put("title",title);
                values.put("value",name);
                dbt.add(values);
                dbt.close();
                Intent intent = new Intent(edit.this, mainface.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edit.this, mainface.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });


    }
}

