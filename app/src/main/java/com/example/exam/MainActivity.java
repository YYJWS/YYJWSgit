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
        final EditText edit = new EditText(MainActivity.this);
        dbt.open();
        psw=(String)dbt.findmm().get(0);
        Log.d(TAG,psw);
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
                builder=new AlertDialog.Builder(MainActivity.this);
                alert=builder.setTitle("输入新密码")
                        .setView(edit)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                npsw=edit.getText().toString();
                                ContentValues values = new ContentValues();
                                values.put("mm",npsw);
                                dbt.open();
                                dbt.updatemm(values);
                                dbt.close();
                                reload();


                            }
                        })
                        .setNegativeButton("取消",null)
                        .create();
                alert.show();

            }
        });
    }
    public void reload() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
