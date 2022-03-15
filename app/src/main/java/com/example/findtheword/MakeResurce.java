package com.example.findtheword;

import android.media.Image;

public class MakeResurce {
    private String answer;
    private String tabWord;
    private int image;

    public MakeResurce(String answer, String tabWord, int image) {
        this.answer = answer;
        this.tabWord = tabWord;
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTabWord() {
        return tabWord;
    }

    public int getImage() {
        return image;
    }
}
