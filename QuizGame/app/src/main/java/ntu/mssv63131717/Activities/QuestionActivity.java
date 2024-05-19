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
    String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ResetTime();
        countDownTimer.start();
        questions = new ArrayList<>();
        course = getIntent().getStringExtra("course");
        String level = getIntent().getStringExtra("level");
        switch (level){
            case "Level 1":
                renderQuestion1();
                break;
            case "Level 2":
                renderQuestion2();
                break;
            case "Level 3":
                renderQuestion3();
                break;
            case "Level 4":
                renderQuestion4();
                break;
            case "Level 5":
                renderQuestion5();
                break;
            default:
                Toast.makeText(this,"Lỗi không mong muốn vui lòng thử lại !!",Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < 4; i++){
            binding.btnOption.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }
        animationQuestion(binding.questionData,0,questions.get(position).getQuestion());
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
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

    private void renderQuestion1() {
        switch (course){
            case "HTML":
                questions.add(new Question("What is HTML?", "HyperText Markup Language", "HyperLink Transfer Markup Language", "HyperText Making Language", "Hyper Tool Markup Language", "HyperText Markup Language"));
                questions.add(new Question("What is CSS?", "Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets", "Creative Style Sheets", "Cascading Style Sheets"));
                questions.add(new Question("What is JavaScript?", "A programming language used to create interactive web pages", "A markup language used to create web pages", "A scripting language used to create web pages", "A database language used to create web pages", "A programming language used to create interactive web pages"));
                questions.add(new Question("What is a tag in HTML?", "A tag is a special keyword in HTML that is used to mark up the start and end of an element", "A tag is a special keyword in HTML that is used to mark up the start of an element", "A tag is a special keyword in HTML that is used to mark up the end of an element", "A tag is a special keyword in HTML that is used to mark up the content of an element", "A tag is a special keyword in HTML that is used to mark up the start and end of an element"));
                questions.add(new Question("What is an attribute in HTML?", "An attribute is a property of an HTML element that provides additional information about the element", "An attribute is a property of an HTML element that provides the content of the element", "An attribute is a property of an HTML element that provides the style of the element", "An attribute is a property of an HTML element that provides the behavior of the element", "An attribute is a property of an HTML element that provides additional information about the element"));
                break;
            case "JavaScript":
                questions.add(new Question("What is JavaScript?", "A programming language used to create interactive web pages", "A scripting language used to create web pages", "A markup language used to create web pages", "A database language used to create web pages", "A programming language used to create interactive web pages"));
                questions.add(new Question("What is a variable in JavaScript?", "A variable is a named container for storing data values", "A variable is a reserved word in JavaScript", "A variable is a function in JavaScript", "A variable is a conditional statement in JavaScript", "A variable is a named container for storing data values"));
                questions.add(new Question("What is a function in JavaScript?", "A function is a block of code designed to perform a specific task", "A function is a data type in JavaScript", "A function is an HTML element in JavaScript", "A function is a loop in JavaScript", "A function is a block of code designed to perform a specific task"));
                questions.add(new Question("What is an array in JavaScript?", "An array is a special variable that can hold multiple values", "An array is a conditional statement in JavaScript", "An array is a reserved word in JavaScript", "An array is a data type in JavaScript", "An array is a special variable that can hold multiple values"));
                questions.add(new Question("What is an object in JavaScript?", "An object is a collection of key-value pairs", "An object is a loop in JavaScript", "An object is a data type in JavaScript", "An object is a reserved word in JavaScript", "An object is a collection of key-value pairs"));
                break;
            case "Python":
                questions.add(new Question("What is Python?", "Python is a high-level programming language known for its simplicity and readability", "Python is a markup language used for creating web pages", "Python is a database language used for managing data", "Python is a scripting language used for automating tasks", "Python is a high-level programming language known for its simplicity and readability"));
                questions.add(new Question("What are the advantages of using Python?", "Advantages of Python include its readability, ease of learning, large standard library, and community support", "Advantages of Python include its speed and performance, low memory consumption, and strong typing", "Advantages of Python include its ability to create interactive web pages, handle databases, and manipulate data", "Advantages of Python include its support for object-oriented programming, multithreading, and network programming", "Advantages of Python include its readability, ease of learning, large standard library, and community support"));
                questions.add(new Question("What are the data types available in Python?", "Data types in Python include numbers, strings, lists, tuples, dictionaries, and more", "Data types in Python include integers, floats, characters, and booleans", "Data types in Python include classes, objects, arrays, and structures", "Data types in Python include files, directories, and streams", "Data types in Python include numbers, strings, lists, tuples, dictionaries, and more"));
                questions.add(new Question("What is the difference between a list and a tuple in Python?", "A list is mutable, meaning its elements can be modified, added, or removed, while a tuple is immutable and its elements cannot be changed", "A list can store multiple data types, while a tuple can only store one data type", "A list is ordered, while a tuple is unordered", "A list is indexed from 1, while a tuple is indexed from 0", "A list is mutable, meaning its elements can be modified, added, or removed, while a tuple is immutable and its elements cannot be changed"));
                questions.add(new Question("How do you comment in Python?", "In Python, you can use the '#' symbol to add comments", "In Python, you can use the '//' symbol to add comments", "In Python, you can use the '/* */' symbols to add comments", "In Python, you can use the '<!-- -->' symbols to add comments", "In Python, you can use the '#' symbol to add comments"));
                break;
            case "Android":
                questions.add(new Question("What is Android?", "Android is an operating system designed for mobile devices, based on the Linux kernel", "Android is a programming language used for building web applications", "Android is a development framework for creating desktop applications", "Android is a database management system", "Android is an operating system designed for mobile devices, based on the Linux kernel"));
                questions.add(new Question("What is an Activity in Android?", "An Activity is a single screen with a user interface", "An Activity is a background process that runs independently", "An Activity is a data container used to store information", "An Activity is a network communication component", "An Activity is a single screen with a user interface"));
                questions.add(new Question("What is an Intent in Android?", "An Intent is a messaging object that is used to request an action from another component", "An Intent is a layout file that defines the user interface of an Activity", "An Intent is a data structure used to store key-value pairs", "An Intent is a database query used to retrieve information", "An Intent is a messaging object that is used to request an action from another component"));
                questions.add(new Question("What is a Fragment in Android?", "A Fragment is a modular section of an Activity, representing a portion of user interface and logic", "A Fragment is a background thread that performs long-running operations", "A Fragment is a collection of key-value pairs used for storing data", "A Fragment is a network communication component", "A Fragment is a modular section of an Activity, representing a portion of user interface and logic"));
                questions.add(new Question("What is the layout file in Android?", "A layout file defines the user interface of an Activity or Fragment using XML", "A layout file is a file that contains the executable code of an Android application", "A layout file is a file used for storing data in a structured format", "A layout file is a file that defines the database schema", "A layout file defines the user interface of an Activity or Fragment using XML"));
                break;
            default:
                break;
        }

    }
    private void renderQuestion2() {
        switch (course) {
            case "HTML":
                questions.add(new Question("What is the purpose of the `<head>` tag in HTML?", "To define the document title and metadata", "To define the document body content", "To define the document header and footer", "To define the document navigation menu", "To define the document title and metadata"));
                questions.add(new Question("What is the difference between `<strong>` and `<b>` tags in HTML?", "The `<strong>` tag indicates strong importance, while the `<b>` tag indicates bold font style", "The `<strong>` tag indicates bold font style, while the `<b>` tag indicates strong importance", "The `<strong>` tag is used for headings, while the `<b>` tag is used for paragraphs", "The `<strong>` tag is used for links, while the `<b>` tag is used for images", "The `<strong>` tag indicates strong importance, while the `<b>` tag indicates bold font style"));
                questions.add(new Question("What is the purpose of the `alt` attribute in HTML?", "To provide alternative text for images", "To provide a tooltip for images", "To provide a caption for images", "To provide a link to a larger image", "To provide alternative text for images"));
                questions.add(new Question("What is the difference between HTML and XHTML?", "HTML is a markup language, while XHTML is a hybrid of HTML and XML", "HTML is a scripting language, while XHTML is a markup language", "HTML is used for web pages, while XHTML is used for mobile devices", "HTML is used for desktop applications, while XHTML is used for web applications", "HTML is a markup language, while XHTML is a hybrid of HTML and XML"));
                questions.add(new Question("What is the purpose of the `DOCTYPE` declaration in HTML?", "To declare the document type and version", "To declare the character encoding", "To declare the document title", "To declare the document author", "To declare the document type and version"));
                break;
            case "JavaScript":
                questions.add(new Question("What is the difference between `null` and `undefined` in JavaScript?", "Null represents an intentional absence of any object value, while undefined represents an uninitialized variable", "Null represents an uninitialized variable, while undefined represents an intentional absence of any object value", "Null is a primitive value, while undefined is an object", "Null is used for numbers, while undefined is used for strings", "Null represents an intentional absence of any object value, while undefined represents an uninitialized variable"));
                questions.add(new Question("What is the purpose of the `this` keyword in JavaScript?", "To refer to the current object", "To refer to the global object", "To refer to the window object", "To refer to the document object", "To refer to the current object"));
                questions.add(new Question("What is the difference between `==` and `===` operators in JavaScript?", "The `==` operator checks for value equality, while the `===` operator checks for both value and type equality", "The `==` operator checks for type equality, while the `===` operator checks for value equality", "The `==` operator is used for strings, while the `===` operator is used for numbers", "The `==` operator is used for arrays, while the `===` operator is used for objects", "The `==` operator checks for value equality, while the `===` operator checks for both value and type equality"));
                questions.add(new Question("What is the purpose of the `prototype` property in JavaScript?", "To define the prototype of an object", "To define the constructor of an object", "To define the properties of an object", "To define the methods of an object", "To define the prototype of an object"));
                questions.add(new Question("What is the difference between a `function` and a `method` in JavaScript?", "A function is a standalone block of code, while a method is a function that is part of an object", "A function is a part of an object, while a method is a standalone block of code", "A function is used for calculations, while a method is used for data manipulation", "A function is used for event handling, while a method is used for animation", "A function is a standalone block of code, while a method is a function that is part of an object"));
                break;
            case "Python":
                questions.add(new Question("What is the difference between `list` and `tuple` in Python?", "A list is mutable, while a tuple is immutable", "A list is immutable, while a tuple is mutable", "A list is used for strings, while a tuple is used for numbers", "A list is usedfor arrays, while a tuple is used for objects", "A list is mutable, while a tuple is immutable"));
                questions.add(new Question("What is the purpose of the `__init__` method in Python?", "To initialize the object's state", "To define the object's methods", "To define the object's properties", "To define the object's constructor", "To initialize the object's state"));
                questions.add(new Question("What is the difference between `is` and `==` operators in Python?", "The `is` operator checks for object identity, while the `==` operator checks for value equality", "The `is` operator checks for value equality, while the `==` operator checks for object identity", "The `is` operator is used for strings, while the `==` operator is used for numbers", "The `is` operator is used for arrays, while the `==` operator is used for objects", "The `is` operator checks for object identity, while the `==` operator checks for value equality"));
                questions.add(new Question("What is the purpose of the `with` statement in Python?", "To define a block of code that is executed in a specific context", "To define a block of code that is executed in a loop", "To define a block of code that is executed in a conditional statement", "To define a block of code that is executed in a function", "To define a block of code that is executed in a specific context"));
                questions.add(new Question("What is the difference between `raise` and `except` statements in Python?", "The `raise` statement is used to raise an exception, while the `except` statement is used to catch and handle an exception", "The `raise` statement is used to catch and handle an exception, while the `except` statement is used to raise an exception", "The `raise` statement is used for strings, while the `except` statement is used for numbers", "The `raise` statement is used for arrays, while the `except` statement is used for objects", "The `raise` statement is used to raise an exception, while the `except` statement is used to catch and handle an exception"));
                break;
            case "Android":
                questions.add(new Question("What is the difference between `Activity` and `Fragment` in Android?", "An `Activity` is a single screen with a user interface, while a `Fragment` is a modular section of an `Activity` representing a portion of user interface and logic", "An `Activity` is a modular section of an `Activity` representing a portion of user interface and logic, while a `Fragment` is a single screen with a user interface", "An `Activity` is used for calculations, while a `Fragment` is used for data manipulation", "An `Activity` is used for event handling, while a `Fragment` is used for animation", "An `Activity` is a single screen with a user interface, while a `Fragment` is a modular section of an `Activity` representing a portion of user interface and logic"));
                questions.add(new Question("What is the purpose of the `onCreate()` method in Android?", "To initialize the `Activity`'s state", "To define the `Activity`'s methods", "To define the `Activity`'s properties", "To define the `Activity`'s constructor", "To initialize the `Activity`'s state"));
                questions.add(new Question("What is the difference between `findViewById()` and `findViewWithTag()` methods in Android?", "The `findViewById()` method is used to find a view by its ID, while the `findViewWithTag()` method is used to find a view by its tag", "The `findViewById()` method is used to find a view by its tag, while the `findViewWithTag()` method is used to find a view by its ID", "The `findViewById()` method is used for strings, while the `findViewWithTag()` method is used for numbers", "The `findViewById()` method is used for arrays, while the `findViewWithTag()` method is used for objects", "The `findViewById()` method is used to find a view by its ID, while the `findViewWithTag()` method is used to find a view by its tag"));
                questions.add(new Question("What is the purpose of the `Intent` class in Android?", "To start a new `Activity` or service", "To define the layout of an `Activity`", "To define the properties of a view", "To define the behavior of a button", "To start a new`Activity` or service"));
                questions.add(new Question("What is the difference between `startActivity()` and `startActivityForResult()` methods in Android?", "The `startActivity()` method is used to start a new `Activity`, while the `startActivityForResult()` method is used to start a new `Activity` and wait for a result", "The `startActivity()` method is used to wait for a result, while the `startActivityForResult()` method is used to start a new `Activity`", "The `startActivity()` method is used for strings, while the `startActivityForResult()` method is used for numbers", "The `startActivity()` method is used for arrays, while the `startActivityForResult()` method is used for objects", "The `startActivity()` method is used to start a new `Activity`, while the `startActivityForResult()` method is used to start a new `Activity` and wait for a result"));
                break;
            default:
                break;
        }
    }
    private void renderQuestion3() {
        switch (course) {
            case "HTML":
                questions.add(new Question("What is the purpose of the `<meta>` tag in HTML?", "To define metadata about the document, such as character encoding, viewport, and author", "To define the document title and metadata", "To define the document body content", "To define the document header and footer", "To define metadata about the document, such as character encoding, viewport, and author"));
                questions.add(new Question("What is the difference between `<div>` and `<span>` tags in HTML?", "The `<div>` tag is a block-level element, while the `<span>` tag is an inline element", "The `<div>` tag is an inline element, while the `<span>` tag is a block-level element", "The `<div>` tag is used for headings, while the `<span>` tag is used for paragraphs", "The `<div>` tag is used for links, while the `<span>` tag is used for images", "The `<div>` tag is a block-level element, while the `<span>` tag is an inline element"));
                questions.add(new Question("What is the purpose of the `data-*` attributes in HTML?", "To store custom data private to the page or application", "To define the document title", "To define the document author", "To define the document language", "To store custom data private to the page or application"));
                questions.add(new Question("What is the difference between `<script>` and `<style>` tags in HTML?", "The `<script>` tag is used to include JavaScript code, while the `<style>` tag is used to include CSS styles", "The `<script>` tag is used to include CSS styles, while the `<style>` tag is used to include JavaScript code", "The `<script>` tag is used for strings, while the `<style>` tag is used for numbers", "The `<script>` tag is used for arrays, while the `<style>` tag is used for objects", "The `<script>` tag is used to include JavaScript code, while the `<style>` tag is used to include CSS styles"));
                questions.add(new Question("What is the purpose of the `<noscript>` tag in HTML?", "To define an alternate content for users who have disabled JavaScript", "To define the document title and metadata", "To define the document body content", "To define the document header and footer", "To define an alternate content for users who have disabled JavaScript"));
                break;
            case "JavaScript":
                questions.add(new Question("What is the difference between `let` and `const` keywords in JavaScript?", "The `let` keyword is used to declare a block-scoped variable, while the `const` keyword is used to declare a constant", "The `let` keyword is used to declare a constant, while the `const` keyword is used to declare a block-scoped variable", "The `let` keyword is used for strings, while the `const` keyword is used for numbers", "The `let` keyword is used for arrays, while the `const` keyword is used for objects", "The `let` keyword is used to declare a block-scoped variable, while the `const` keyword is used to declare a constant"));
                questions.add(new Question("What is the purpose of the `this` keyword in JavaScriptfunctions?", "To refer to the current object that the function is a method of", "To refer to the global object", "To refer to the window object", "To refer to the document object", "To refer to the current object that the function is a method of"));
                questions.add(new Question("What is the difference between `call()` and `apply()` methods in JavaScript?", "The `call()` method is used to call a function with a given `this` value and arguments provided individually, while the `apply()` method is used to call a function with a given `this` value and arguments provided as an array", "The `call()` method is used to call a function with a given `this` value and arguments provided as an array, while the `apply()` method is used to call a function with a given `this` value and arguments provided individually", "The `call()` method is used for strings, while the `apply()` method is used for numbers", "The `call()` method is used for arrays, while the `apply()` method is used for objects", "The `call()` method is used to call a function with a given `this` value and arguments provided individually, while the `apply()` method is used to call a function with a given `this` value and arguments provided as an array"));
                questions.add(new Question("What is the purpose of the `bind()` method in JavaScript?", "To create a new function with a specified `this` value and partially applied arguments", "To define the function's behavior", "To define the function's properties", "To define the function's constructor", "To create a new function with a specified `this` value and partially applied arguments"));
                questions.add(new Question("What is the difference between `try-catch` and `finally` statements in JavaScript?", "The `try-catch` statement is used to catch and handle exceptions, while the `finally` statement is used to specify a block of code to be executed after the `try` and `catch` blocks", "The `try-catch` statement is used to specify a block of code to be executed after the `try` and `catch` blocks, while the `finally` statement is used to catch and handle exceptions", "The `try-catch` statement is used for strings, while the `finally` statement is used for numbers", "The `try-catch` statement is used for arrays, while the `finally` statement is used for objects", "The `try-catch` statement is used to catch and handle exceptions, while the `finally` statement is used to specify a block of code to be executed after the `try` and `catch` blocks"));
                break;
            case "Python":
                questions.add(new Question("What is the difference between `list` and `dict` data types in Python?", "A `list` is an ordered collection of items, while a `dict` is an unordered collection of key-value pairs", "A `list` is an unordered collection of items, while a `dict` is an ordered collection of key-value pairs", "A `list` is used for strings, while a `dict` is used for numbers", "A `list` is used for arrays, while a `dict` is used for objects", "A `list` is an ordered collection of items, while a `dict` is an unordered collection of key-value pairs"));
                questions.add(new Question("What is the purpose of the `__str__()` method in Python?", "To define the string representation of an object", "To define the object's methods", "To define the object's properties", "To define the object's constructor", "To define the string representation of an object"));
                questions.add(new Question("What is the difference between `raise` and `except` statements in Python?", "The `raise` statement is used to raise an exception, while the `except` statement is used to catch and handle an exception", "The `raise` statement is used to catch and handle an exception, while the `except` statement is used to raise an exception", "The `raise` statement is used for strings, while the `except` statement is used for numbers", "The `raise` statement is used for arrays, while the `except` statement is used for objects", "The `raise` statement is used to raise an exception, while the `except` statement is used to catch and handle an exception"));
                questions.add(new Question("What is the purpose of the `with` statement in Python?", "To define a block of code that is executed in a specific context", "To define a block of codethat is executed in a loop", "To define a block of code that is executed in a conditional statement", "To define a block of code that is executed in a function", "To define a block of code that is executed in a specific context"));
                questions.add(new Question("What is the difference between `is` and `==` operators in Python?", "The `is` operator checks for object identity, while the `==` operator checks for value equality", "The `is` operator checks for value equality, while the `==` operator checks for object identity", "The `is` operator is used for strings, while the `==` operator is used for numbers", "The `is` operator is used for arrays, while the `==` operator is used for objects", "The `is` operator checks for object identity, while the `==` operator checks for value equality"));
                break;
            case "Android":
                questions.add(new Question("What is the difference between `startActivity()` and `startActivityForResult()` methods in Android?", "The `startActivity()` method is used to start a new `Activity`, while the `startActivityForResult()` method is used to start a new `Activity` and wait for a result", "The `startActivity()` method is used to wait for a result, while the `startActivityForResult()` method is used to start a new `Activity`", "The `startActivity()` method is used for strings, while the `startActivityForResult()` method is used for numbers", "The `startActivity()` method is used for arrays, while the `startActivityForResult()` method is used for objects", "The `startActivity()` method is used to start a new `Activity`, while the `startActivityForResult()` method is used to start a new `Activity` and wait for a result"));
                questions.add(new Question("What is the purpose of the `onSaveInstanceState()` method in Android?", "To save the state of an `Activity` before it is destroyed", "To define the layout of an `Activity`", "To define the properties of a view", "To define the behavior of a button", "To save the state of an `Activity` before it is destroyed"));
                questions.add(new Question("What is the difference between `findViewById()` and `findViewWithTag()` methods in Android?", "The `findViewById()` method is used to find a view by its ID, while the `findViewWithTag()` method is used to find a view by its tag", "The `findViewById()` method is used to find a view by its tag, while the `findViewWithTag()` method is used to find a view by its ID", "The `findViewById()` method is used for strings, while the `findViewWithTag()` method is used for numbers", "The `findViewById()` method is used for arrays, while the `findViewWithTag()` method is used for objects", "The `findViewById()` method is used to find a view by its ID, while the `findViewWithTag()` method is used to find a view by its tag"));
                questions.add(new Question("What is the purpose of the `BroadcastReceiver` class in Android?", "To receive and handle broadcast intents", "To define the layout of an `Activity`", "To define the properties of a view", "To define the behavior of a button", "To receive and handle broadcast intents"));
                questions.add(new Question("What is the difference between `implicit` and `explicit` intents in Android?", "An `implicit` intent specifies an action to perform, but does not specify the component to perform it, while an `explicit` intent specifies the component to perform an action", "An `implicit` intent specifies the component to perform an action, while an `explicit` intent specifies an action to perform", "An `implicit` intent is used for strings, while an `explicit` intent is used for numbers", "An `implicit` intent is used for arrays, while an `explicit` intent is used for objects", "An `implicit` intent specifies an action to perform, but does not specify the component to perform it, while an `explicit` intent specifies the component to perform an action"));
                break;
            default:
                break;
        }
    }
    private void renderQuestion4() {
        switch (course) {
            case "HTML":
                questions.add(new Question("What is HTML?", "HyperText Markup Language", "HyperLink Transfer Markup Language", "HyperText Making Language", "Hyper Tool Markup Language", "HyperText Markup Language"));
                questions.add(new Question("What is the purpose of the <!DOCTYPE> declaration in HTML?", "It specifies the document type and HTML version", "It links to an external CSS file", "It declares a comment", "It links to an external JavaScript file", "It specifies the document type and HTML version"));
                questions.add(new Question("What is the <meta> tag used for in HTML?", "It provides metadata about the HTML document", "It creates a hyperlink", "It defines the main content of the document", "It embeds a video", "It provides metadata about the HTML document"));
                questions.add(new Question("How do you create a hyperlink in HTML?", "Using the <a> tag", "Using the <link> tag", "Using the <href> tag", "Using the <hyperlink> tag", "Using the <a> tag"));
                questions.add(new Question("What is the difference between <section> and <div> tags in HTML5?", "<section> is used for thematic grouping of content, while <div> is a generic container", "<section> is used for styling purposes, while <div> is not", "<section> is used for inline elements, while <div> is used for block elements", "<section> is a deprecated tag, while <div> is not", "<section> is used for thematic grouping of content, while <div> is a generic container"));
                break;
            case "JavaScript":
                questions.add(new Question("What is JavaScript?", "A programming language used to create interactive web pages", "A scripting language used to create web pages", "A markup language used to create web pages", "A database language used to create web pages", "A programming language used to create interactive web pages"));
                questions.add(new Question("What is 'use strict' in JavaScript?", "It enables strict mode which helps in catching common coding errors", "It is used to declare variables", "It is used to define functions", "It is used to create objects", "It enables strict mode which helps in catching common coding errors"));
                questions.add(new Question("What is a callback function in JavaScript?", "A function that is passed as an argument to another function", "A function that returns another function", "A function that is called when an event occurs", "A function that is called at the end of a script", "A function that is passed as an argument to another function"));
                questions.add(new Question("What are promises in JavaScript?", "Objects that represent the eventual completion or failure of an asynchronous operation", "Functions that run automatically when an event occurs", "Variables that hold multiple values", "Statements that handle exceptions", "Objects that represent the eventual completion or failure of an asynchronous operation"));
                questions.add(new Question("What is the difference between '==' and '===' in JavaScript?", "'==' compares values with type conversion, while '===' compares values without type conversion", "'==' compares values without type conversion, while '===' compares values with type conversion", "'==' is used for string comparison, while '===' is used for number comparison", "'==' is used in loops, while '===' is used in conditionals", "'==' compares values with type conversion, while '===' compares values without type conversion"));
                break;
            case "Python":
                questions.add(new Question("What is Python?", "Python is a high-level programming language known for its simplicity and readability", "Python is a markup language used for creating web pages", "Python is a database language used for managing data", "Python is a scripting language used for automating tasks", "Python is a high-level programming language known for its simplicity and readability"));
                questions.add(new Question("What is a list comprehension in Python?", "A concise way to create lists using a single line of code", "A method to define functions", "A technique to handle exceptions", "A way to import modules", "A concise way to create lists using a single line of code"));
                questions.add(new Question("What is the purpose of the 'self' keyword in Python?", "It refers to the instance of the class", "It is used to declare global variables", "It is used to define static methods", "It is used to return values from functions", "It refers to the instance of the class"));
                questions.add(new Question("What is the difference between 'append()' and 'extend()' methods in Python?", "'append()' adds a single element to a list, while 'extend()' adds multiple elements", "'append()' adds multiple elements, while 'extend()' adds a single element", "'append()' is used for strings, while 'extend()' is used for lists", "'append()' modifies the original list, while 'extend()' creates a new list", "'append()' adds a single element to a list, while 'extend()' adds multiple elements"));
                questions.add(new Question("How do you handle exceptions in Python?", "Using the 'try' and 'except' blocks", "Using the 'if' and 'else' statements", "Using the 'for' and 'while' loops", "Using the 'import' and 'from' statements", "Using the 'try' and 'except' blocks"));
                break;
            case "Android":
                questions.add(new Question("What is Android?", "Android is an operating system designed for mobile devices, based on the Linux kernel", "Android is a programming language used for building web applications", "Android is a development framework for creating desktop applications", "Android is a database management system", "Android is an operating system designed for mobile devices, based on the Linux kernel"));
                questions.add(new Question("What is an Activity in Android?", "An Activity is a single screen with a user interface", "An Activity is a background process that runs independently", "An Activity is a data container used to store information", "An Activity is a network communication component", "An Activity is a single screen with a user interface"));
                questions.add(new Question("What is an Intent in Android?", "An Intent is a messaging object that is used to request an action from another component", "An Intent is a layout file that defines the user interface of an Activity", "An Intent is a data structure used to store key-value pairs", "An Intent is a database query used to retrieve information", "An Intent is a messaging object that is used to request an action from another component"));
                questions.add(new Question("What is a Fragment in Android?", "A Fragment is a modular section of an Activity, representing a portion of user interface and logic", "A Fragment is a background thread that performs long-running operations", "A Fragment is a collection of key-value pairs used for storing data", "A Fragment is a network communication component", "A Fragment is a modular section of an Activity, representing a portion of user interface and logic"));
                questions.add(new Question("What is the difference between 'Service' and 'IntentService' in Android?", "A 'Service' can run in the background indefinitely, while an 'IntentService' handles asynchronous requests one at a time and stops itself", "A 'Service' can only handle UI operations, while an 'IntentService' can handle background tasks", "A 'Service' must be manually stopped, while an 'IntentService' stops automatically", "A 'Service' is used for network operations, while an 'IntentService' is used for database operations", "A 'Service' can run in the background indefinitely, while an 'IntentService' handles asynchronous requests one at a time and stops itself"));
                break;
            default:
                break;
        }
    }
    private void renderQuestion5() {
        switch (course) {
            case "HTML":
                questions.add(new Question("What is HTML?", "HyperText Markup Language", "HyperLink Transfer Markup Language", "HyperText Making Language", "Hyper Tool Markup Language", "HyperText Markup Language"));
                questions.add(new Question("What is the difference between HTML4 and HTML5?", "HTML5 introduces new elements like <video> and <audio>, while HTML4 does not", "HTML4 is only for desktop browsers, while HTML5 is for mobile browsers", "HTML5 supports CSS3, while HTML4 does not", "HTML5 is a server-side language, while HTML4 is a client-side language", "HTML5 introduces new elements like <video> and <audio>, while HTML4 does not"));
                questions.add(new Question("What is semantic HTML?", "Using HTML elements that convey meaning about the content they contain", "Using HTML elements for styling purposes", "Using HTML elements for linking to external resources", "Using HTML elements for embedding multimedia", "Using HTML elements that convey meaning about the content they contain"));
                questions.add(new Question("How do you embed a video in HTML?", "Using the <video> tag", "Using the <embed> tag", "Using the <object> tag", "Using the <iframe> tag", "Using the <video> tag"));
                questions.add(new Question("What is the role of the <canvas> element in HTML5?", "It is used to draw graphics on the web page via scripting (usually JavaScript)", "It is used to embed videos", "It is used to link to external stylesheets", "It is used to create forms", "It is used to draw graphics on the web page via scripting (usually JavaScript)"));
                break;
            case "JavaScript":
                questions.add(new Question("What is JavaScript?", "A programming language used to create interactive web pages", "A scripting language used to create web pages", "A markup language used to create web pages", "A database language used to create web pages", "A programming language used to create interactive web pages"));
                questions.add(new Question("What is the event loop in JavaScript?", "It is a process that handles asynchronous callbacks", "It is a loop that runs continuously to check for events", "It is a method for iterating over arrays", "It is a function that runs when the DOM is ready", "It is a process that handles asynchronous callbacks"));
                questions.add(new Question("What is the difference between 'let' and 'var' in JavaScript?", "'let' has block scope, while 'var' has function scope", "'let' is used for constants, while 'var' is used for variables", "'let' can be redeclared, while 'var' cannot", "'let' is used in strict mode, while 'var' is not", "'let' has block scope, while 'var' has function scope"));
                questions.add(new Question("What is closure in JavaScript?", "A function that has access to its own scope, the scope of the outer function, and the global scope", "A function that returns another function", "A function that is used as a callback", "A function that is automatically called when an event occurs", "A function that has access to its own scope, the scope of the outer function, and the global scope"));
                questions.add(new Question("What is the purpose of the 'this' keyword in JavaScript?", "It refers to the object from which the function was called", "It refers to the current function", "It refers to the global object", "It refers to the parent object", "It refers to the object from which the function was called"));
                break;
            case "Python":
                questions.add(new Question("What is Python?", "Python is a high-level programming language known for its simplicity and readability", "Python is a markup language used for creating web pages", "Python is a database language used for managing data", "Python is a scripting language used for automating tasks", "Python is a high-level programming language known for its simplicity and readability"));
                questions.add(new Question("What is the GIL in Python?", "Global Interpreter Lock, a mutex that protects access to Python objects", "Global Inheritance List, a list of inherited classes", "Global Import Lock, a lock for importing modules", "Global Index List, a list of indexed elements", "Global Interpreter Lock, a mutex that protects access to Python objects"));
                questions.add(new Question("What is a lambda function in Python?", "A small anonymous function defined with the lambda keyword", "A function that is called when a module is imported", "A function that is used to create generators", "A function that returns another function", "A small anonymous function defined with the lambda keyword"));
                questions.add(new Question("What is the difference between deep copy and shallow copy in Python?", "Deep copy creates a new object and recursively adds copies of nested objects, while shallow copy creates a new object and inserts references into it", "Deep copy duplicates only the top-level objects, while shallow copy duplicates all objects", "Deep copy is used for strings, while shallow copy is used for lists", "Deep copy creates a reference to the original object, while shallow copy creates a new object", "Deep copy creates a new object and recursively adds copies of nested objects, while shallow copy creates a new object and inserts references into it"));
                questions.add(new Question("What is the purpose of decorators in Python?", "To modify the behavior of a function or method", "To define a new class", "To handle exceptions", "To import modules", "To modify the behavior of a function or method"));
                break;
            case "Android":
                questions.add(new Question("What is Android?", "Android is an operating system designed for mobile devices, based on the Linux kernel", "Android is a programming language used for building web applications", "Android is a development framework for creating desktop applications", "Android is a database management system", "Android is an operating system designed for mobile devices, based on the Linux kernel"));
                questions.add(new Question("What is the AndroidManifest.xml file used for?", "It contains essential information about the app, including permissions, components, and metadata", "It defines the UI of the app", "It stores user data", "It manages network communication", "It contains essential information about the app, including permissions, components, and metadata"));
                questions.add(new Question("What is the difference between 'px', 'dp', 'sp' in Android?", "'px' is pixels, 'dp' is density-independent pixels, 'sp' is scale-independent pixels", "'px' is percentage, 'dp' is dots per inch, 'sp' is screen pixels", "'px' is proportional pixels, 'dp' is device pixels, 'sp' is scalable pixels", "'px' is pixels per inch, 'dp' is display pixels, 'sp' is screen points", "'px' is pixels, 'dp' is density-independent pixels, 'sp' is scale-independent pixels"));
                questions.add(new Question("What is the ViewHolder pattern in Android?", "A pattern that improves the performance of ListView by caching views", "A pattern for managing fragments", "A pattern for database access", "A pattern for network communication", "A pattern that improves the performance of ListView by caching views"));
                questions.add(new Question("What is a Content Provider in Android?", "A component that manages access to a structured set of data", "A component that handles background tasks", "A component that displays data to the user", "A component that manages network communication", "A component that manages access to a structured set of data"));
                break;
            default:
                break;
        }
    }


}