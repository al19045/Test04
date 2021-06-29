package com.example.Trytodo;
//ToDO_UI　メイン画面リストの表示消去
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDo_UI extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    //= new Intent(this, Setting.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        //ArrayAdapterオブジェクト生成
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //Buttonオブジェクト取得
        Button btn = (Button) findViewById(R.id.modoru);
        //クリックイベントの通知先指定
        btn.setOnClickListener(new View.OnClickListener() {
            //クリックイベント
            @Override
            public void onClick(View v) {
                //要素追加
                addStringData();
            }
        });
        //Adapterのセット
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> av, View view, int position, long id) {
                                                //リスト個も億を取得削除
                                                adapter.remove((String) ((TextView) view).getText());
                                            }
                                        }
        );
    }     //要素追加処理

    private void addStringData() {
        //EditTextオブジェクト取得
        EditText edit = (EditText) findViewById(R.id.edit_text);
        //EditText(テキスト)を取得し、アダプタに追加
        adapter.add(edit.getText().toString());
    }


        /*final ArrayList<String> data = new ArrayList<>();
        data.add("湖沼");
        data.add("ターメリック");
        data.add("コリアンダー");
        data.add("生姜");
        data.add("ニンニク");
        data.add("サフラン");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        ListView list=findViewById(R.id.list);
        list.setAdapter(adapter);
        //リスト項目をクリックしたときの処理を定義
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                        @Override
                                        public void onItemClick(AdapterView<?> av, View view, int position, long id) {
                                            //リスト個も億を取得削除
                                            adapter.remove((String)((TextView)view).getText());
                                        }
                                    }
        );
    }*/


    public void sakusei_onClick(View v) {
        Intent intent = new Intent(this, ToDo_Input.class);
        startActivityForResult(intent, 1);
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String timers_name_edit_text = data.getStringExtra("String timers_name_edit_text");
            Toast.makeText(this,
                    String.format("こんにちは、%sさん！",
                            timers_name_edit_text  ),
                    Toast.LENGTH_SHORT).show();
        }
    */
    //宣言
}