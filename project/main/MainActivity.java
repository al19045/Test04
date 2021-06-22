package com.example.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    Mokuhyo mokuhyo = new Mokuhyo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.bigMokuhyo);

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
                                            mokuhyo.readMokuhyo(text);
                                            textView.setText(text);
                                        }
                                    }
                                })
                                        .show();

                                builder.setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                });



                    }
                });
    }

    public void onTapEvent(View view) {
        popup pop = new popup();
        //ダイアログをだす
        pop.show(getSupportFragmentManager(),"popup");
        }
}



