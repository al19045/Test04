
/****************************************************
 *** Module Name    :   MyCursorAdapter
 *** Version        :   V1.0
 *** Designer       :   馬場　章
 *** Date           :   2021.07.12
 *** Purpose        :   メイン画面での消去ボタンを作動させるためのもの
 ***
 ***************************************************/
/*
 *** Revision    :
 *** V1.0        :   馬場　章　2021.07.12 作成

 */


package com.example.studysupport;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

//SimpleCurSorAdapterの拡張
public class MyCursorAdapter extends SimpleCursorAdapter {
//コンストラクタ
    public MyCursorAdapter(Context context, int layout, Cursor c,
                           String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    //MyListViewとつなげる
    public View getView(int position, View convertView, ViewGroup parent) {
        // viewのセット
        View view = super.getView(position, convertView, parent);

        Button recordTextView = (Button) view.findViewById(
                R.id.delete_textview);
        recordTextView.setTag(position);
        recordTextView.setOnClickListener((MyListView)parent);


        TextView editTextView = (TextView)view.findViewById(
                R.id.list_row1);
        editTextView.setTag(position);
        editTextView.setOnClickListener((MyListView)parent);

        return view;

    }
}