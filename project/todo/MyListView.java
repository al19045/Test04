/****************************************************
 *** Module Name    :   MyListView
 *** Version        :   V1.0
 *** Designer       :   馬場　章
 *** Date           :   2021.07.12
 *** Purpose        :   メイン画面での消去ボタンを作動させるためのもの
 ***
 ***************************************************/
/*
 *** Revision    :
 *** V1.0        :   馬場　章　2021.07.3 作成

 */


package com.example.studysupport;


import android.content.Context;
import android.net.LinkAddress;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;


 // リスト内にボタンを配置して、ボタンが押された時にonItemClickを通知する,ListView を拡張MしたMyListView

public class MyListView extends ListView implements View.OnClickListener {

    //コンストラクタ
    public MyListView(Context context) {
        super(context);
    }


    public MyListView(Context context,AttributeSet attrs){
        super(context,attrs);
    }

//リスト内のボタンクリックで呼ばれる
    public void onClick(View v) {
        int pos = (Integer)v.getTag();
        this.performItemClick(v, pos, v.getId());
    }
}

