package com.example.sem_test.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sem_test.Dao.MyDatabaseHelper;
import com.example.sem_test.R;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity {
    private MyDatabaseHelper dbHelper;
    private Spinner spinner_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        /**数据库操作
         * @database = goodOrder.db
         * @table = goods
         */
        dbHelper = new MyDatabaseHelper(this,"goodOrder.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("goods",null,null,null,null,null,null);
        //泛型注入下拉框数据
        List<Integer> list = new ArrayList<>();
        cursor.moveToFirst();
        do {
            list.add(cursor.getInt(cursor.getColumnIndex("number"))); //select number
        } while(cursor.moveToNext());
        //利用adapt进行下拉框配置
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_order = this.findViewById(R.id.spinner_number);
        spinner_order.setAdapter(adapter);
    }
}
