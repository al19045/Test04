package com.example.studyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textNext;
    private TextView comment;
    Mokuhyo mokuhyo = new Mokuhyo();
    Character chara = new Character();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //(初期処理)大きな目標表示
        textView = findViewById(R.id.bigMokuhyo);
        textView.setText(mokuhyo.getMokuhyo());

        //(初期処理)次の目標パーセント表示
        textNext = findViewById(R.id.textNext);
        int persent = 0;
        textNext.setText("次の目標まで " + persent + "%");


        if(persent<26){

        }else if(persent<51){
        }else if(persent<76) {
        }else{
        }

        //(初期処理)コメント表示
        chara.getComment();

        //大きい目標更新処理
        ((Button) findViewById(R.id.mokuhyo)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //テキスト入力を受け付けるビューを作成します。

                        final EditText bigMokuhyo = new EditText(MainActivity.this);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setIcon(android.R.drawable.ic_dialog_info);
                                builder.setTitle("目標を入力してください");
                                builder.setView(bigMokuhyo);
                                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //textを取得
                                        String text = bigMokuhyo.getText().toString();
                                        if (text.length() > 20) {
                                            Toast myToast = Toast.makeText(
                                                    getApplicationContext(),
                                                    "20文字以内で入力してください",
                                                    Toast.LENGTH_SHORT
                                            );
                                            myToast.show();
                                        } else {
                                            mokuhyo.setMokuhyo(text);
                                            textView.setText(text);
                                        }
                                    }
                                })
                                .setNegativeButton("キャンセル", null)
                                .show();



                    }
                });
        //宣言
        BottomNavigationView menu = findViewById(R.id.bnv);
        menu.getMenu().findItem(R.id.Timer).setChecked(true);


        //画面遷移
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
               /* switch (item.getItemId()){
                    case R.id.Main :
                        return true;
                    case R.id.Timer :
                        intent = new Intent(MainTimerFrame.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Study :
                        intent = new Intent(MainTimerFrame.this, StudyRecord_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Calendar :
                        intent = new Intent(MainTimerFrame.this, Calendar_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.ToDo :
                        intent = new Intent(MainTimerFrame.this, Todo_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;

                } */
                return false;
            }
        });
    }

    //キャラタッチ処理
    public void onTapChara(View view){
        //コメントを更新
        chara.getComment();
    }

    //ヘルプボタン処理
    public void onTapEvent(View view) {
        popup pop = new popup();
        //ダイアログをだす
        pop.show(getSupportFragmentManager(),"popup");
        }


}




