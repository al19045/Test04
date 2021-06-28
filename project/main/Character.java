package com.example.studyapp;

import android.widget.TextView;

public class Character {
    private TextView comment;

    public void getComment() {
        comment = comment.findViewById(R.id.bigMokuhyo);
        CharacterOut out = new CharacterOut();
        comment.setText(out.readComment());
    }


}
