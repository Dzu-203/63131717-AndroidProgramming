package ntu.mssv63131717.Activities;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    String course,level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        questions = new ArrayList<>();
//        ResetTime();
//        countDownTimer.start();
        course = getIntent().getStringExtra("course");
        level = getIntent().getStringExtra("level");
        fetchQuestions(level);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNext();
            }
        });
    }

    private void handleNext() {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        countDownTimer.start();
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

    private void fetchQuestions(String level) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("courses");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                seTupCourse(snapshot);
                setupQuestionUI(level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void seTupCourse(DataSnapshot snapshot) {
        switch (course) {
            case "HTML":
                seTupLevel(snapshot.child("0").child("levels"));
                break;
            case "JavaScript":
                seTupLevel(snapshot.child("1").child("levels"));
                break;
            case "Python":
                seTupLevel(snapshot.child("2").child("levels"));
                break;
            case "Android":
                seTupLevel(snapshot.child("3").child("levels"));
                break;
            default:
                Toast.makeText(this, "Course not found", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void seTupLevel(DataSnapshot snapshot) {
        switch (level) {
            case "Level 1":
                getDataQuestion(snapshot.child("0").child("questions"));
                break;
            case "Level 2":
                getDataQuestion(snapshot.child("1").child("questions"));
                break;
            case "Level 3":
                getDataQuestion(snapshot.child("2").child("questions"));
                break;
            case "Level 4":
                getDataQuestion(snapshot.child("3").child("questions"));
                break;
            default:
                Toast.makeText(this, "Lỗi không mong muốn vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void getDataQuestion(DataSnapshot snapshot) {
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            String question = dataSnapshot.child("question").getValue(String.class);
            String op1 = dataSnapshot.child("options").child("0").getValue(String.class);
            String op2 = dataSnapshot.child("options").child("1").getValue(String.class);
            String op3 = dataSnapshot.child("options").child("2").getValue(String.class);
            String op4 = dataSnapshot.child("options").child("3").getValue(String.class);
            String answer = dataSnapshot.child("answer").getValue(String.class);
            questions.add(new Question(question, op1, op2, op3, op4, answer));
        }
    }
    private void setupQuestionUI(String level) {
        for (int i = 0; i < 4; i++) {
            binding.btnOption.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }

        if (questions.size() > 0) {
            animationQuestion(binding.questionData, 0, questions.get(position).getQuestion());
        } else {
            Toast.makeText(this, "Không có dữ liệu vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }


    private void ResetTime() {
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(QuestionActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timeout);
                dialog.findViewById(R.id.again).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuestionActivity.this,LevelActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show();
            }
        };
    }

    private void checkAnswer(Button selected) {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);
        if (selected.getText().toString().equals(questions.get(position).getOptionCorrect())){
            score+=1;
            selected.setBackgroundResource(R.drawable.custombtn_correct);
            selected.setTextColor(Color.WHITE);
        }else {
            selected.setBackgroundResource(R.drawable.custombtn_incorrect);
            selected.setTextColor(Color.WHITE);
            Button correctAnswer = (Button) binding.btnOption.findViewWithTag(questions.get(position).getOptionCorrect());
            correctAnswer.setBackgroundResource(R.drawable.custombtn_correct);
            correctAnswer.setTextColor(Color.WHITE);
        }
    }
    private void enableOption(boolean enable) {

        for (int i = 0; i < 4; i++){
            View view = binding.btnOption.getChildAt(i);
            view.setEnabled(enable);
            view.setBackgroundResource(R.drawable.custom_button);
            if (view instanceof Button) {
                ((Button) view).setTextColor(ResourcesCompat.getColor(getResources(), R.color.text3, null)); // Hoặc bất kỳ màu chữ nào bạn muốn
            }
        }
    }
    private void animationQuestion(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).
                setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
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
                                binding.totalQuestion.setText(position+1+"/"+questions.size());
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
    private void renderQuestion1(DataSnapshot snapshot) {

    }
    private void renderQuestion2(DataSnapshot snapshot) {

    }
    private void renderQuestion3(DataSnapshot snapshot) {

    }
    private void renderQuestion4(DataSnapshot snapshot) {

    }
    private void renderQuestion5(DataSnapshot snapshot) {

    }


}