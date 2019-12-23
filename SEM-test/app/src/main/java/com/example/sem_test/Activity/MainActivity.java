package com.example.sem_test.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sem_test.Dao.MyDatabaseHelper;
import com.example.sem_test.R;

public class MainActivity extends BaseActivity {
    private EditText edit_name;
    private EditText edit_number;
    private EditText edit_reserve;
    private EditText edit_value;
    private EditText edit_maxinum;
    private Button button_order;
    private Button button_input;
    private Button button_output;
    private Button button_select;
    private TextView text_info;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = this.findViewById(R.id.edit_name);
        edit_number = this.findViewById(R.id.edit_number);
        edit_reserve = this.findViewById(R.id.edit_reserve);
        edit_value = this.findViewById(R.id.edit_value);
        edit_maxinum = this.findViewById(R.id.edit_maxinum);
        button_order = this.findViewById(R.id.button_order);
        button_input = this.findViewById(R.id.button_input);
        button_output = this.findViewById(R.id.button_output);
        button_select = this.findViewById(R.id.button_select);
        text_info = this.findViewById(R.id.text_info);
        button_order.setOnClickListener(new Action());
        button_input.setOnClickListener(new Action());
        button_output.setOnClickListener(new Action());
        button_select.setOnClickListener(new Action());
        dbHelper = new MyDatabaseHelper(this,"goodOrder.db",null,1);
    }

    //注册按钮事件
    class Action implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_order:
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.clear();
                    //判断数据不为空
                    if(!(edit_name.getText().toString().isEmpty() || edit_maxinum.getText().toString().isEmpty()|| edit_number.getText().toString().isEmpty()||
                     edit_reserve.getText().toString().isEmpty() || edit_value.getText().toString().isEmpty())){
                        //数据插入
                        values.put("name",edit_name.getText().toString());
                        values.put("number",Integer.valueOf(edit_number.getText().toString()));
                        values.put("reserve",Integer.valueOf(edit_reserve.getText().toString()));
                        values.put("maxinum",Integer.valueOf(edit_maxinum.getText().toString()));
                        values.put("value",Integer.valueOf(edit_value.getText().toString()));
                        db.insert("goods",null,values);
                        text_info.setText("订货成功");
                    }
                    else
                        text_info.setText("上述有信息为空");
                    break;
                case R.id.button_input:
                    //
                    break;
                case R.id.button_output:
                    //
                    break;
                case R.id.button_select:
                    //跳转至ShowActivity
                    Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
