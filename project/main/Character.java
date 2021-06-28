package com.example.studyapp;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Character {

    public void getComment(TextView comment) {
        CharacterOut out = new CharacterOut();
        comment.setText(out.readComment());

    }


}
