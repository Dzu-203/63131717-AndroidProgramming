package ntu.mssv63131717.Activities;

import static java.lang.String.*;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import ntu.mssv63131717.Models.Question;
import ntu.mssv63131717.R;
import ntu.mssv63131717.databinding.ActivityQuestionBinding;

public class QuestionActivity extends AppCompatActivity {
    ActivityQuestionBinding binding;
    ArrayList<Question> questions;
    int count = 0;
    int position = 0;
    int score = 0;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String level = getIntent().getStringExtra("level");
        switch (level){
            case "level 1":
                renderQuestion1();
                break;
            case "level 2":
                renderQuestion2();
                break;
            case "level 3":
                renderQuestion3();
                break;
            case "level 4":
                renderQuestion4();
                break;
            case "level 5":
                renderQuestion5();
                break;
        }
        for (int i = 0; i < 4; i++){
            binding.btnOption.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnNext.setEnabled(false);
                binding.btnNext.setAlpha((float) 0.3);
                enableOption(true);
                position+=1;
                if (position == questions.size()){
                    Intent intent = new Intent(QuestionActivity.this,CongratulationActivity.class);
                    intent.putExtra("score",score);
                    intent.putExtra("total",questions.size());
                    startActivity(intent);
                    finish();
                    return;
                }
                count = 0;
                animationQuestion(binding.questionData,0,questions.get(position).getQuestion());
            }
        });
    }

    private void animationQuestion(TextView view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                if (value == 0 && count < 4){
                    String option = "";
                    if (count == 0){
                        option = questions.get(position).getOptionA();
                    }else if (count == 1){
                        option = questions.get(position).getOptionB();
                    }else if (count == 2){
                        option = questions.get(position).getOptionC();
                    }else if (count == 3){
                        option = questions.get(position).getOptionD();
                    }
                    animationQuestion(binding.btnOption.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                if (value == 0){
                    try {
                        ((TextView)view).setText(data);
                        binding.currentQuestion.setText(String.format("%d%d",position+1,questions.size()));
                    }
                    catch (Exception e){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    animationQuestion(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
    }


    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++){
            binding.btnOption.getChildAt(i).setEnabled(enable);
            if (enable){
                binding.btnOption.getChildAt(i).setBackgroundResource(R.drawable.custom_button);
            }
        }
    }

    private void checkAnswer(Button selected) {
        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);
        if (selected.getText().toString().equals(questions.get(position).getOptionCorrect())){
            score+=1;
            selected.setBackgroundResource(R.drawable.custom_correct);
        }else {
            selected.setBackgroundResource(R.drawable.custom_incorrect);
            Button correctAnswer = binding.btnOption.findViewWithTag(questions.get(position).getOptionCorrect());
            correctAnswer.setBackgroundResource(R.drawable.custom_correct);
        }
    }

    private void renderQuestion5() {
        questions.add(new Question("What is the purpose of the <br> element in HTML?", "The <br> element is used to create a line break in the text", "The <br> element is used to create a form input field", "The <br> element is used to create a table", "The <br> element is used to create a list", "The <br> element is used to create a line break in the text"));
        questions.add(new Question("What is the purpose of the <hr> element in HTML?", "The <hr> element is used to create a horizontal rule that separates content", "The <hr> element is used to create a form input field", "The <hr> element is used to create a table", "The <hr> element is used to create a list", "The <hr> element is used to create a horizontal rule that separates content"));
        questions.add(new Question("What is the purpose of the <meta> element in HTML?", "The <meta> element is used to provide metadata about the document, such as its character encoding and viewport settings", "The <meta> element is used to create a form input field", "The <meta> element is used to create a table", "The <meta> element is used to create a list", "The <meta> element is used to provide metadata about the document, such as its character encoding and viewport settings"));
        questions.add(new Question("What is the purpose of the <ul> element in HTML?", "The <ul> element is used to create an unordered list", "The <ul> element is used to create an ordered list", "The <ul> element is used to create a definition list", "The <ul> element is used to create a table", "The <ul> element is used to create an unordered list"));
        questions.add(new Question("What is the purpose of the <ol> element in HTML?", "The <ol> element is used to create an ordered list", "The <ol> element is used to create an unordered list", "The <ol> element is used to create a definition list", "The <ol> element is used to create a table", "The <ol> element is used to create an ordered list"));
    }

    private void renderQuestion4() {
        questions.add(new Question("What is the purpose of the <input> element in HTML?", "The <input> element is used to create a form input field, such as a text input field or a checkbox", "The <input> element is used to create a button", "The <input> element is used to create a table", "The <input> element is used to create a list", "The <input> element is used to create a form input field, such as a text input field or a checkbox"));
        questions.add(new Question("What is the purpose of the <select> element in HTML?", "The <select> element is used to create a dropdown menu", "The <select> element is used to create a button", "The <select> element is used to create a table", "The <select> element is used to create a list", "The <select> element is used to create a dropdown menu"));
        questions.add(new Question("What is the purpose of the <option> element in HTML?", "The <option> element is used to create an option in a dropdown menu", "The <option> element is used to create a button", "The <option> element is used to create a table", "The <option> element is used to create a list", "The <option> element is used to create an option in a dropdown menu"));
        questions.add(new Question("What is the purpose of the <div> element in HTML?", "The <div> element is used to group and style elements together", "The <div> element is used to create a form input field", "The <div> element is used to create a table", "The <div> element is used to create a list", "The <div> element is used to group and style elements together"));
        questions.add(new Question("What is the purpose of the <span> element in HTML?", "The <span> element is used to group and style elements together without affecting the document structure", "The <span> element is used to create a form input field", "The <span> element is used to create a table", "The <span> element is used to create a list", "The <span> element is used to group and style elements together without affecting the document structure"));
    }

    private void renderQuestion3() {
        questions.add(new Question("What is the purpose of the <table> element in HTML?", "The <table> element is used to create a table that can be used to display data in a tabular format", "The <table> element is used to create a form", "The <table> element is used to create a list", "The <table> element is used to create a navigation menu", "The <table> element is used to create a table that can be used to display data in a tabular format"));
        questions.add(new Question("What is the purpose of the <tr> element in HTML?", "The <tr> element is used to create a row in a table", "The <tr> element is used to create a column in a table", "The <tr> element is used to create a header in a table", "The <tr> element is used to create a footer in a table", "The <tr> element is used to create a row in a table"));
        questions.add(new Question("What is the purpose of the <td> element in HTML?", "The <td> element is used to create a cell in a table", "The <td> element is used to create a row in a table", "The <td> element is used to create a header in a table", "The <td> element is used to create a footer in a table", "The <td> element is used to create a cell in a table"));
        questions.add(new Question("What is the purpose of the <th> element in HTML?", "The <th> element is used to create a header cell in a table", "The <th> element is used to create a data cell in a table", "The <th> element is used to create a row in a table", "The <th> element is used to create a footer in a table", "The <th> element is used to create a header cell in a table"));
        questions.add(new Question("What is the purpose of the <form> element in HTML?", "The <form> element is used to create a form that can be used to collect user input", "The <form> element is used to create a table", "The <form> element is used to create a list", "The <form> element is used to create a navigation menu", "The <form> element is used to create a form that can be used to collect user input"));
    }

    private void renderQuestion2() {
        questions.add(new Question("What is the purpose of the <!DOCTYPE> declaration in HTML?", "The <!DOCTYPE> declaration is used to specify the version of HTML that is used in the document", "The <!DOCTYPE> declaration is used to specify the character encoding of the document", "The <!DOCTYPE> declaration is used to specify the title of the document", "The <!DOCTYPE> declaration is used to specify the author of the document", "The <!DOCTYPE> declaration is used to specify the version of HTML that is used in the document"));
        questions.add(new Question("What is the purpose of the <head> element in HTML?", "The <head> element is used to contain meta-information about the document, such as its title and character encoding", "The <head> element is used to contain the content of the document", "The <head> element is used to contain the style of the document", "The <head> element is used to contain the behavior of the document", "The <head> element is used to contain meta-information about the document, such as its title and character encoding"));
        questions.add(new Question("What is the purpose of the <body> element in HTML?", "The <body> element is used to contain the content of the document, such as text, images, and videos", "The <body> element is used to contain the meta-information of the document", "The <body> element is used to contain the style of the document", "The <body> element is used to contain the behavior of the document", "The <body> element is used to contain the content of the document, such as text, images, and videos"));
        questions.add(new Question("What is the purpose of the <a> element in HTML?", "The <a> element is used to create a hyperlink to another web page or a specific location within the same page", "The <a> element is used to create a button", "The <a> element is used to create a text input field", "The <a> element is used to create a dropdown menu", "The <a> element is used to create a hyperlink to another web page or a specific location within the same page"));
        questions.add(new Question("What is the purpose of the <img> element in HTML?", "The <img> element is used to embed an image into the document", "The <img> element is used to create a button", "The <img> element is used to create a text input field", "The <img> element is used to create a dropdown menu", "The <img> element is used to embed an image into the document"));
    }

    private void renderQuestion1() {
        questions.add(new Question("What is HTML?", "HyperText Markup Language", "HyperLink Transfer Markup Language", "HyperText Making Language", "Hyper Tool Markup Language", "HyperText Markup Language"));
        questions.add(new Question("What is CSS?", "Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets", "Creative Style Sheets", "Cascading Style Sheets"));
        questions.add(new Question("What is JavaScript?", "A programming language used to create interactive web pages", "A markup language used to create web pages", "A scripting language used to create web pages", "A database language used to create web pages", "A programming language used to create interactive web pages"));
        questions.add(new Question("What is a tag in HTML?", "A tag is a special keyword in HTML that is used to mark up the start and end of an element", "A tag is a special keyword in HTML that is used to mark up the start of an element", "A tag is a special keyword in HTML that is used to mark up the end of an element", "A tag is a special keyword in HTML that is used to mark up the content of an element", "A tag is a special keyword in HTML that is used to mark up the start and end of an element"));
        questions.add(new Question("What is an attribute in HTML?", "An attribute is a property of an HTML element that provides additional information about the element", "An attribute is a property of an HTML element that provides the content of the element", "An attribute is a property of an HTML element that provides the style of the element", "An attribute is a property of an HTML element that provides the behavior of the element", "An attribute is a property of an HTML element that provides additional information about the element"));

    }
}