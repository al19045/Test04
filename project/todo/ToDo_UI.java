/****************************************************
 *** Module Name    :   ToDo_UI
 *** Version        :   V1.0
 *** Designer       :   馬場　章
 *** Date           :   2021.07.3
 *** Purpose        :   ToDoリスト表示画面処理部
 ***
 ***************************************************/
/*
 *** Revision    :
 *** V1.0        :   馬場　章　2021.07.3 作成
 *** V2.0        :   馬場　章　2021.07.12 ヒントボタンの改良　消去ボタンの改良
 */


package com.example.studysupport;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.studysupport.helper.ToDoOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToDo_UI extends
        ListActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyActivity";
    //ImageButton btn;
    Button btn;         //ヒントボタン
    Cursor cursor;      //データベースへの要求結果

    private long a = 0;     //listの仮ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //  Button, BottomNavigationViewの設定
        BottomNavigationView menu = findViewById(R.id.bnv);
        menu.getMenu().findItem(R.id.ToDo).setChecked(true);
        btn = (Button) findViewById(R.id.buttonHelp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //ヒントボタンが押されたとき
            public void onClick(View v) {
                openPopUpWindow();
            }
        });

        // BottomNavigationViewの詳細設定(各画面への遷移)
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.Main:
                        intent = new Intent(ToDo_UI.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Timer:
                        intent = new Intent(ToDo_UI.this, MainTimerFrame.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Study:
                        intent = new Intent(ToDo_UI.this, StudyRecord_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Calendar:
                        intent = new Intent(ToDo_UI.this, calendarUI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.ToDo:
                        return true;
                }
                return false;
            }
        });

    }
// ヒントポップアップの表示(画面遷移)
    private void openPopUpWindow() {
        Intent popup = new Intent(ToDo_UI.this, PopUpWindow.class);
        startActivity(popup);
    }




    //データベースと接続，リストの表示
    @Override
    protected void onResume() {

        super.onResume();
        SQLiteDatabase database = null;
        try {
            SQLiteOpenHelper helper = new ToDoOpenHelper(ToDo_UI.this);
            database = helper.getReadableDatabase();

            cursor = database.query("ToDoList", null, null, null, null, null, null);
            // SimpleCursorAdapter adapter = new SimpleCursorAdapter(ToDo_UI.this, R.layout.list_row, cursor, new String[]{"title"}, new int[]{R.id.list_row1}, MyCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            MyCursorAdapter adapter = new MyCursorAdapter(this, R.layout.list_row, cursor, new String[]{"title"}, new int[]{R.id.list_row1}, MyCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);

            //エラー処理
        } catch (Exception e) {
            Log.e("エラー", e.getMessage(), e);
        } finally {
            database.close();
        }
    }


    //リスト内のアイテムがタッチされたとき
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        //完了ボタンタッチでタスクの削除
     if (view.getId() == R.id.delete_textview) {
         //タスクの内容を取得
            View aa = (View) view.getParent();
            TextView textView = (TextView) aa.findViewById(R.id.list_row1);
            String task = String.valueOf(textView.getText());

            SQLiteOpenHelper helperDelete = null;
            SQLiteDatabase databaseDelete = null;


            try {
                //データベース読み込み
                helperDelete = new ToDoOpenHelper(ToDo_UI.this);
                databaseDelete = helperDelete.getWritableDatabase();
                //dbからタスク内容が一致するもの削除
                int deleteCount = databaseDelete.delete("ToDoList", "title=?", new String[]{String.valueOf(task)});
                //消去成功時に「消去しました」と表示，失敗は「登録できませんでした」
                if (deleteCount >= 1) {
                    Toast.makeText(ToDo_UI.this, R.string.toast_delete, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ToDo_UI.this, R.string.toast_failed, Toast.LENGTH_LONG).show();
                }
                //画面のリロード
                onResume();
                //例外処理
            } catch (Exception e) {

                Log.e("エラー", e.getMessage(), e);
            } finally {
                //dbクローズ
                databaseDelete.close();
            }
            //リストの中で完了以外をタッチでそのタスクの編集画面に遷移
        } else {
            Log.i(TAG, "oseta");
            Log.i(TAG, String.valueOf(id));
            Intent intent = new Intent(ToDo_UI.this, ToDo_Setting.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }




    //作成ボタンが押されたとき入力画面に移動する
    public void sakusei_onClick(View v) {
        Intent intent = new Intent(this, ToDo_Input.class);
        startActivityForResult(intent, 1);
    }

    //移動時にdbから切断
    @Override
    protected void onStop() {
        super.onStop();
        if (cursor != null || !cursor.isClosed()) {
            cursor.close();
        }
    }}





