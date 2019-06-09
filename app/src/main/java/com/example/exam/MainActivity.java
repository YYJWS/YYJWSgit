package com.example.exam;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private FloatingActionButton fab;
    ListView lv;
    AlertDialog.Builder builder;
    AlertDialog alert;
    private EditText editText;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_view);
        lv = (ListView) findViewById(R.id.NoteList);
        fab = (FloatingActionButton) findViewById(R.id.add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, edit.class);
                startActivity(intent);
            }
        });
        final databasetest dbt = new databasetest(this);
        dbt.open();
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, dbt.findbt());
        lv.setAdapter(myadapter);
        dbt.close();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                                          String content=lv.getItemAtPosition(position).toString();
                                          Intent intent = new Intent(MainActivity.this, view.class);
                                          intent.putExtra("title",content);
                                          startActivity(intent);


                                      }
                                  }
        );
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                builder = new AlertDialog.Builder(MainActivity.this);
                alert = builder.setTitle("提示")
                        .setMessage("确认删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content=lv.getItemAtPosition(position).toString();
                                dbt.open();
                                dbt.del(content);
                                dbt.close();
                                ArrayAdapter<String> myadapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, dbt.findbt());
                                lv.setAdapter(myadapter1);
                            }

                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alert.show();
                return true;
            }
        });
    }
}

