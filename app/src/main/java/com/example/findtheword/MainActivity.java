package com.example.findtheword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button[] answerButtons;
    private Button[] bottomButton;
    ArrayList<MakeResurce> list = new ArrayList<>();
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createWord();
        initImage();
        initAnswerButton();
        initBottomButton();
        clickBottom();
        clickCenter();
        winGame();
    }

    public void createWord() {
        int a = R.drawable.rain;
        MakeResurce makeResurce = new MakeResurce("rain", "ertnkahpiwyb", a);
        list.add(makeResurce);
        int s = R.drawable.tree;
        MakeResurce makeResurce5 = new MakeResurce("tree","utimewvreopb",s);
        list.add(makeResurce5);
        int b = R.drawable.sweet;
        MakeResurce makeResurce1 = new MakeResurce("sweet", "etwkasihpqeo", b);
        list.add(makeResurce1);

        int h = R.drawable.java;
        MakeResurce makeResurce4 = new MakeResurce("java", "ljughatveaoz", h);
        list.add(makeResurce4);
        int f = R.drawable.sky;
        MakeResurce makeResurce6 = new MakeResurce("sky","prodimytswck",f);
        list.add(makeResurce6);


    }

    public void nextPage() {
        ++currentPage;
        TextView textView = findViewById(R.id.question_num);
        textView.setText(String.valueOf(currentPage + 1));
        initImage();
        initAnswerButton();
        initBottomButton();
        clickBottom();
        clickCenter();
        winGame();

    }

    public void initImage() {
            ImageView imageView = findViewById(R.id.image_head);
            imageView.setImageResource(list.get(currentPage).getImage());

    }

    public void initAnswerButton() {
        ViewGroup viewGroup = findViewById(R.id.container1);
        answerButtons = new Button[viewGroup.getChildCount()];
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                answerButtons[i] = (Button) viewGroup.getChildAt(i);
                answerButtons[i].setText("");
                answerButtons[i].setVisibility(View.VISIBLE);
                if (i >= list.get(currentPage).getAnswer().length()) {
                    answerButtons[i].setVisibility(View.GONE);
                }
            }
    }

    public void initBottomButton() {
        ViewGroup viewGroup = findViewById(R.id.container2);
        bottomButton = new Button[viewGroup.getChildCount()];
        for (int i = 0; i < bottomButton.length; i++) {
            bottomButton[i] = (Button) viewGroup.getChildAt(i);
            if (i >= list.get(currentPage).getTabWord().length()) {
                bottomButton[i].setVisibility(View.GONE);

            } else {
                String str = String.valueOf(list.get(currentPage).getTabWord().charAt(i));
                bottomButton[i].setText(str);
            }
        }
    }

    public void clickBottom() {
        for (int i = 0; i < bottomButton.length; i++) {
            bottomButton[i].setTag(i);
            bottomButton[i].setOnClickListener(this::onClickBottom);

        }
    }

    public void clickCenter() {
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setOnClickListener(this::onClickCenter);
        }
    }


    public void onClickBottom(View view) {
            if (!fullAnswer()) {
                AppCompatButton appCompatButton = (AppCompatButton) view;
                int num = (int) appCompatButton.getTag();
                int index = -1;
                for (int i = 0; i < list.get(currentPage).getAnswer().length(); i++) {
                    if (answerButtons[i].getText().equals("")) {
                        index = i;
                        break;
                    }
                }


                if (index != -1) {
                    answerButtons[index].setText(appCompatButton.getText());
                    answerButtons[index].setTag(num);
                    bottomButton[num].setText("");
                }
                winGame();

            }
        }


    public boolean fullAnswer() {
            for (int i = 0; i < list.get(currentPage).getAnswer().length(); i++) {
                if (answerButtons[i].getText().equals("")) {
                    return false;
                }
            }
            return true;
    }

    public void onClickCenter(View view) {
        AppCompatButton appCompatButton = (AppCompatButton) view;

        if (!appCompatButton.getText().equals("")) {
            int num = (int) appCompatButton.getTag();
            bottomButton[num].setText(appCompatButton.getText());
            appCompatButton.setText("");
        }


    }

    public void winGame() {
            String str = "";
            for (int i = 0; i < answerButtons.length; i++) {
                String a = (String) answerButtons[i].getText();
                str += a;
            }
            if (str.equals(list.get(currentPage).getAnswer())) {
                TextView textView = findViewById(R.id.all_question);
                String text = (String) textView.getText();
                text = text.substring(1);
                int a = Integer.parseInt(text);
                if (currentPage + 1 == a) {
                    Toast.makeText(this, "The End", Toast.LENGTH_SHORT).show();
                } else {
                    nextPage();
                }
            } else if (!str.equals(list.get(currentPage).getAnswer()) && str.length() == list.get(currentPage).getAnswer().length()) {
                Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show();
            }
        }



    public void help(View view) {
        TextView textView = findViewById(R.id.coin);
        String str = (String) textView.getText();
        int amount = Integer.parseInt(str);
        if (amount >= 10) {
            int index = -1;
            for (int i = 0; i < list.get(currentPage).getAnswer().length(); i++) {
                if (answerButtons[i].getText().equals("")) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                amount -= 10;
                textView.setText(String.valueOf(amount));
                answerButtons[index].setText(String.valueOf(list.get(currentPage).getAnswer().charAt(index)));
            }

            winGame();
        } else {
            Toast.makeText(this, "You don't have enough coins!", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        finish();
    }


}
