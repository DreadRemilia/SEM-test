package com.example.sem_test.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sem_test.Dao.MyDatabaseHelper;
import com.example.sem_test.R;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity {
    private MyDatabaseHelper dbHelper;
    private Spinner spinner_order;
    private TextView text_number;
    private TextView text_name;
    private TextView text_reserve;
    private TextView text_value;
    private TextView text_maxinum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        text_number = this.findViewById(R.id.text_number);
        text_name = this.findViewById(R.id.text_name);
        text_reserve = this.findViewById(R.id.text_reserve);
        text_value = this.findViewById(R.id.text_value);
        text_maxinum = this.findViewById(R.id.text_maxinum);

        /**数据库操作
         * @database = goodOrder.db
         * @table = goods
         */
        dbHelper = new MyDatabaseHelper(this,"goodOrder.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        final Cursor cursor = db.query("goods",null,null,null,null,null,null);
        //泛型注入下拉框数据
        final List<Integer> list = new ArrayList<>();
        cursor.moveToFirst();
        do {
            list.add(cursor.getInt(cursor.getColumnIndex("number"))); //select number
        } while(cursor.moveToNext());
        //利用adapt进行下拉框配置
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_order = this.findViewById(R.id.spinner_number);
        spinner_order.setAdapter(adapter);
        //设置spinner监听事件
        spinner_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = Integer.valueOf(adapterView.getItemAtPosition(i).toString());
                //根据index显示数据
                cursor.moveToFirst();
                do{
                    if(cursor.getInt(cursor.getColumnIndex("number")) == index){
                        text_number.setText(cursor.getString(cursor.getColumnIndex("number")));
                        text_name.setText(cursor.getString(cursor.getColumnIndex("name")));
                        text_reserve.setText(cursor.getString(cursor.getColumnIndex("reserve")));
                        text_maxinum.setText(cursor.getString(cursor.getColumnIndex("maxinum")));
                        text_value.setText(cursor.getString(cursor.getColumnIndex("value")));
                        break;
                    }
                } while(cursor.moveToNext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
