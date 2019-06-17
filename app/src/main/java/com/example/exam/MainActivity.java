package com.example.exam;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private EditText editText;
    private TextView textView;
    private Button button;
    private String psw;
    private String npsw;
    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lock);
        editText=(EditText)findViewById(R.id.psw);
        textView=(TextView)findViewById(R.id.cancel);
        button=(Button)findViewById(R.id.mdf);
        final databasetest dbt=new databasetest(getApplicationContext());
        dbt.open();
        psw=(String)dbt.findmm().get(0);
        dbt.close();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(psw)){
                    Intent intent = new Intent(MainActivity.this,mainface.class);
                    startActivity(intent);
                    finish();
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

    }
    public void add(){
        final EditText edit1 = new EditText(MainActivity.this);
        edit1.setHint("请输入旧密码");
        final EditText edit2 = new EditText(MainActivity.this);
        edit2.setHint("请输入新密码");
        final EditText edit3 = new EditText(MainActivity.this);
        edit3.setHint("请重复新密码");
        LinearLayout layout=new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(edit1);
        layout.addView(edit2);
        layout.addView(edit3);
        builder=new AlertDialog.Builder(MainActivity.this);
        alert=builder.setTitle("修改密码")
                .setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edit1.getText().toString().equals(psw)){
                            if(edit2.getText().toString().equals(edit3.getText().toString())&&edit2.length()!=0){
                                npsw=edit3.getText().toString();
                                ContentValues values = new ContentValues();
                                values.put("mm",npsw);
                                dbt.open();
                                dbt.updatemm(values);
                                dbt.close();
                                finish();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplication(),"两次密码不一致或输入为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplication(),"旧密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        alert.show();

    }
        });
    }
}
