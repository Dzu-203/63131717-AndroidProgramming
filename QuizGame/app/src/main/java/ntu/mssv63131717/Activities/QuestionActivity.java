package ntu.mssv63131717.Activities;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private ActivityQuestionBinding binding;
    private ArrayList<Question> questions;
    private int count = 0;
    private int position = 0;
    private int score = 0;
    private CountDownTimer countDownTimer;
    private String course, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        questions = new ArrayList<>();

        course = getIntent().getStringExtra("course");
        level = getIntent().getStringExtra("level");
        fetchQuestions();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNext();
            }
        });
    }

    private void handleNext() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer.start();
        binding.btnNext.setEnabled(false);
        binding.btnNext.setAlpha(0.3f);
        enableOption(true);
        position++;
        if (position == questions.size()) {
            Intent intent = new Intent(QuestionActivity.this, CongratulationActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questions.size());
            startActivity(intent);
            finish();
            return;
        }

        count = 0;
        animateQuestion(binding.questionData, 0, questions.get(position).getQuestion());
    }

    private void fetchQuestions() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("courses");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setupCourse(snapshot);
                setupQuestionUI();
                resetTime();
                countDownTimer.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuestionActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupCourse(DataSnapshot snapshot) {
        for (DataSnapshot courseSnapshot : snapshot.getChildren()) {
            String courseName = courseSnapshot.child("name").getValue(String.class);
            if (courseName != null && courseName.equals(course)) {
                setupLevel(courseSnapshot.child("levels"));
                break;
            }
        }
    }

    private void setupLevel(DataSnapshot snapshot) {
        try {
            int levelIndex = Integer.parseInt(level.replace("Level ", "")) - 1;
            getDataQuestions(snapshot.child(String.valueOf(levelIndex)).child("questions"));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Cấp độ không hợp lệ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi không mong muốn vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataQuestions(DataSnapshot snapshot) {
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

    private void setupQuestionUI() {
        for (int i = 0; i < 4; i++) {
            binding.btnOption.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }

        if (!questions.isEmpty()) {
            animateQuestion(binding.questionData, 0, questions.get(position).getQuestion());
        } else {
            Toast.makeText(this, "Không có dữ liệu vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetTime() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                showTimeoutDialog();
            }
        };
    }

    private void showTimeoutDialog() {
        Dialog dialog = new Dialog(QuestionActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.timeout);
        dialog.findViewById(R.id.again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, LevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }

    private void checkAnswer(Button selected) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);
        enableOption(false);

        if (selected.getText().toString().equals(questions.get(position).getOptionCorrect())) {
            score++;
            selected.setBackgroundResource(R.drawable.custombtn_correct);
            selected.setTextColor(Color.WHITE);
        } else {
            selected.setBackgroundResource(R.drawable.custombtn_incorrect);
            selected.setTextColor(Color.WHITE);
            Button correctAnswer = binding.btnOption.findViewWithTag(questions.get(position).getOptionCorrect());
            correctAnswer.setBackgroundResource(R.drawable.custombtn_correct);
            correctAnswer.setTextColor(Color.WHITE);
        }
    }

    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            View view = binding.btnOption.getChildAt(i);
            view.setEnabled(enable);
            view.setBackgroundResource(R.drawable.custom_button);
            if (view instanceof Button) {
                ((Button) view).setTextColor(ResourcesCompat.getColor(getResources(), R.color.text3, null));
            }
        }
    }

    private void animateQuestion(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        if (value == 0 && count < 4) {
                            String option = "";
                            switch (count) {
                                case 0:
                                    option = questions.get(position).getOptionA();
                                    break;
                                case 1:
                                    option = questions.get(position).getOptionB();
                                    break;
                                case 2:
                                    option = questions.get(position).getOptionC();
                                    break;
                                case 3:
                                    option = questions.get(position).getOptionD();
                                    break;
                            }
                            animateQuestion(binding.btnOption.getChildAt(count), 0, option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        if (value == 0) {
                            try {
                                ((TextView) view).setText(data);
                                binding.totalQuestion.setText(String.format("%d/%d", position + 1, questions.size()));
                            } catch (Exception e) {
                                ((Button) view).setText(data);
                            }
                            view.setTag(data);
                            animateQuestion(view, 1, data);
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
}
