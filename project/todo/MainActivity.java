package com.example.sikou;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public  class MainActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
    ArrayAdapter<String> adapter;
    ListView list;
    // public void onCreate(Bundle savedInstanceState)
    // {
    //  super.onCreate(savedInstanceState);
    // setContentView(R.layout.activity_main);
    EditText timers_name_edit_text2;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //リスト表示例
        final ArrayList<String> data = new ArrayList<>();
        data.add("胡椒");
        data.add("ターメリック");
        data.add("コリアンダー");
        data.add("生姜");
        data.add("ニンニク");
        data.add("サフラン");

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_multiple_choice, data);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> av,
                                            View view, int position, long id) {
                        StringBuilder msg = new StringBuilder("選択したのは、");
                        for (int i = 0; i < list.getChildCount(); i++) {
                            CheckedTextView check = (CheckedTextView) list.getChildAt(i);
                            if (check.isChecked()) {
                                msg.append(check.getText()).append(",");
                            }
                        }
                        Toast.makeText(MainActivity.this, msg.substring(0, msg.length() - 1), Toast.LENGTH_LONG).show();

                    }
                }
        );
    }




    // showMyDialog();


    //ダイアログを作る。

    // public void showMyDialog()
    public void btn_onClick(View view) {
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = LayoutInflater.from(this);
            final View dialog_view = inflater.inflate(R.layout.my_dialog, null);
            //レイアウトファイルからビューを取得

            //レイアウト、題名、OKボタンとキャンセルボタンをつけてダイアログ作成
            builder.setView(dialog_view)
                    .setTitle("入力画面")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   /*OKのときの処理内容*/
                                }
                            })
                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*キャンセルされたときの処理*/
                        }
                    });

            AlertDialog myDialog = builder.create();
            myDialog.setCanceledOnTouchOutside(false);
            //ダイアログ画面外をタッチされても消えないようにする。
            myDialog.show();
            //ダイアログ表示
        }

    }
    public void timers_name_edit_text2_onClick(View view){
        DialogFragment newFragment = new DatePick();
        newFragment.show(getSupportFragmentManager(), "datePicker");
//newFragment.show(getSupportFragmentManager(),"dialog_date");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}