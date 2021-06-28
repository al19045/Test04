package com.example.studyapp;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

public class CharacterOut {

    //コメントを読み込むメソッド
    String readComment() {
        Context context = AppContext.getAppContext();
        String fileName = "Comment.txt";
        LinkedList<String> comment = new LinkedList<>();
        String line;

        //コメントファイルから文字列をリストに追加
        InputStream is = null;
        BufferedReader br = null;
        try {
            try {
                // assetsフォルダを開く
                AssetManager assets = context.getResources().getAssets();
                is = assets.open(fileName);
                br = new BufferedReader(new InputStreamReader(is));

                // 文字列読み込み
                while ((line = br.readLine()) != null) {
                    comment.add(line);
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //コメントをランダムにひとつ抽出
        int index = new Random().nextInt(comment.size());
        String result = comment.get(index);

        return result;
    }
}
