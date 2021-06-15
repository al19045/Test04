package com.websarva.wings.android.application1b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GoalSet extends AppCompatActivity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalset);

        Context context = getApplicationContext();
        String filename = "goaltime.txt";
        file = new File(context.getFilesDir(), filename);

        Button ngbutton = (Button) findViewById(R.id.ngbutton);//リスナーをボタンに登録
        ngbutton.setOnClickListener(new View.OnClickListener() { //キャンセルボタンが押されたときの処理
            public void onClick(View view){
                finish(); //メイン画面に戻る
            }
        });
        Button okbutton = (Button) findViewById(R.id.okbutton);//リスナーをボタンに登録
        okbutton.setOnClickListener(new View.OnClickListener() { //確認ボタンが押されたときの処理
            @Override
            public void onClick(View view) {
                EditText goaltime = (EditText) findViewById(R.id.inputtime); //EditTextで入力された値を受け取る
                String str = goaltime.getText().toString(); //Stirng型で読み込む
                int inttime = Integer.parseInt(str); //int型での値も用意する
                if(inttime > 168){ //入力された時間が168時間より大きかった場合
                    DialogFragment dialogFragment = new TimeAlertDialog();
                    dialogFragment.show(getSupportFragmentManager(),"Timeerrordialog"); //TimeAlertDiaolgを呼び出す
                } else { //入力された時間が168時間以下だった場合
                    saveTime(str); //入力された値をファイルに入力
                    finish(); //StudyRecord＿UIに戻る
                }
            }

            public void saveTime(String str) { //入力された値をファイルに保存する
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}