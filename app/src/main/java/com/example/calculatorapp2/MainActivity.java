package com.example.calculatorapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // regular stuff
    // TODO: Figure out rounding
    // TODO: add function to undefined button
    // TODO: add some kind of feedback to the buttons
    // TODO: comment everything
    // TODO: suppress leading zeroes
    // TODO: hide action bar

    // three extra features
    // TODO: settings screen

    Button b_rand, b_memrecall, b_clear, b_backspace, b_memsub, b_memadd, b_divide, b_seven, b_eight, b_nine, b_multiply, b_four, b_five, b_six, b_sub, b_one, b_two, b_three, b_add, b_sign, b_decimal, b_zero, b_equal;
    TextView calcField, historyField;
    Context context;

    String currentCalcText = "";
    ArrayList<String> history = new ArrayList<String>();
    double currentAnswer = 0, operand = 0, memory = 0;
    char lastOperation = '+';
    int opCounter=0;
    boolean negative = false, allClear = true;


    private static final int FRAGMENT_FRAME_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();


        // assigning layout elements to variables
        // buttons
        b_rand = findViewById(R.id.b_memclear);
        b_memrecall = findViewById(R.id.b_memrecall);
        b_clear = findViewById(R.id.b_clear);
        b_backspace = findViewById(R.id.b_backspace);
        b_memsub = findViewById(R.id.b_memsub);
        b_memadd = findViewById(R.id.b_memadd);
        b_divide = findViewById(R.id.b_divide);
        b_seven = findViewById(R.id.b_seven);
        b_eight = findViewById(R.id.b_eight);
        b_nine = findViewById(R.id.b_nine);
        b_multiply = findViewById(R.id.b_multiply);
        b_four = findViewById(R.id.b_four);
        b_five = findViewById(R.id.b_five);
        b_six = findViewById(R.id.b_six);
        b_sub = findViewById(R.id.b_sub);
        b_one = findViewById(R.id.b_one);
        b_two = findViewById(R.id.b_two);
        b_three = findViewById(R.id.b_three);
        b_add = findViewById(R.id.b_add);
        b_sign = findViewById(R.id.b_sign);
        b_decimal = findViewById(R.id.b_decimal);
        b_zero = findViewById(R.id.b_zero);
        b_equal = findViewById(R.id.b_equal);

        // fields
        calcField = findViewById(R.id.t_calc);
        historyField = findViewById(R.id.t_history);

        // setting onclick listeners to buttons
        b_rand.setOnClickListener(this);
        b_memrecall.setOnClickListener(this);
        b_clear.setOnClickListener(this);
        b_backspace.setOnClickListener(this);
        b_memsub.setOnClickListener(this);
        b_memadd.setOnClickListener(this);
        b_divide.setOnClickListener(this);
        b_seven.setOnClickListener(this);
        b_eight.setOnClickListener(this);
        b_nine.setOnClickListener(this);
        b_multiply.setOnClickListener(this);
        b_four.setOnClickListener(this);
        b_five.setOnClickListener(this);
        b_six.setOnClickListener(this);
        b_sub.setOnClickListener(this);
        b_one.setOnClickListener(this);
        b_two.setOnClickListener(this);
        b_three.setOnClickListener(this);
        b_add.setOnClickListener(this);
        b_sign.setOnClickListener(this);
        b_decimal.setOnClickListener(this);
        b_zero.setOnClickListener(this);
        b_equal.setOnClickListener(this);

        FragmentManager fr = getSupportFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        Welcome welcome = new Welcome();
        ft.add(R.id.layout_welcome, welcome);
        ft.commit();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentCalcText", calcField.getText().toString());
        outState.putStringArrayList("history",  history);
        outState.putDouble("currentAnswer", currentAnswer);
        outState.putDouble("operand", operand);
        outState.putDouble("memory", memory);
        outState.putChar("lastOperation", lastOperation);
        outState.putInt("opCounter", opCounter);
        outState.putBoolean("negative", negative);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saved) {
        super.onRestoreInstanceState(saved);
        currentCalcText = saved.getString("currentCalcText");
        history = saved.getStringArrayList("history");
        currentAnswer = saved.getDouble("currentAnswer");
        operand = saved.getDouble("operand");
        memory = saved.getDouble("memory");
        lastOperation = saved.getChar("lastOperation");
        opCounter = saved.getInt("opCounter");
        negative = saved.getBoolean("negative");

        displayCalc(currentCalcText);
        currentCalcText = "";
        displayHistory(history);
    }

    public void onClick(View v){
        // gives function to each button
        switch (v.getId()){
            case R.id.b_zero:
                addChar('0');
                break;

            case R.id.b_one:
                addChar('1');
                break;

            case R.id.b_two:
                addChar('2');
                break;

            case R.id.b_three:
                addChar('3');
                break;

            case R.id.b_four:
                addChar('4');
                break;

            case R.id.b_five:
                addChar('5');
                break;

            case R.id.b_six:
                addChar('6');
                break;

            case R.id.b_seven:
                addChar('7');
                break;

            case R.id.b_eight:
                addChar('8');
                break;

            case R.id.b_nine:
                addChar('9');
                break;

            case R.id.b_decimal:
                addChar('.');
                break;

            case R.id.b_clear:
                if (!allClear){
                    // clear only current value
                    currentCalcText = "";
                    displayCalc("0");
                    b_clear.setText("AC");
                } else {
                    // clear all values
                    b_clear.setText("CE");
                    clearVals();
                    displayCalc("0");
                    displayHistory(history);
                }
                allClear = !allClear;
                break;

            case R.id.b_backspace:
                backspace();
                break;

            case R.id.b_sign:
                sign();
                break;

            case R.id.b_add:
                calculate('+');
                break;

            case R.id.b_sub:
                calculate('-');
                break;

            case R.id.b_multiply:
                calculate('*');
                break;

            case R.id.b_divide:
                calculate('/');
                break;

            case R.id.b_equal:
                calculate('=');
                clearVals();
                break;

            case R.id.b_memadd:
                memAdd();
                break;

            case R.id.b_memsub:
                memSub();
                break;

            case R.id.b_memclear:
                memClear();
                break;

            case R.id.b_memrecall:
                memRecall();
                break;

            default:
                error();
                break;
        }
    }

    public void addChar(char c){
        if (currentCalcText.length() < 13){
            currentCalcText += c;

            // clear history if this is first value
            if(opCounter == 0){
                displayHistory(history);
            }

            displayCalc(currentCalcText);
            operand = Double.valueOf(currentCalcText);

            b_clear.setText("CE");
            allClear = false;
        } else {
            error();
        }

    }

    public void displayCalc(String text){
        calcField.setText(text);
    }

    public void displayHistory(ArrayList<String> hist){
        String print = "";
        for (int i=0; i < hist.size(); i++){
            print += hist.get(i);
        }
        historyField.setText(print);
    }

    public void displayHistory(String input){
        historyField.setText(input);
    }

    // what happens when an operation is clicked (+. -, /, *, =)
    public void calculate(char op){
        // add currentCalcText to history
        history.add(currentCalcText);
        history.add(String.valueOf(op));

        // display history
        displayHistory(history);

        // clear calcText
        currentCalcText = "";

        // operate on numbers
        if(opCounter == 0){
            currentAnswer = operand;
        } else {
            operate(lastOperation);
        }
        lastOperation = op;

        // display intermediate total
        if (opCounter > 0){
            displayCalc(String.valueOf(currentAnswer));
        }

        // increment counter
        opCounter ++;

        // change clear back to AC
        b_clear.setText("AC");
        allClear = true;

    }

    public void operate(char op){
        switch (op){
            case '+':
                currentAnswer += operand;
                break;

            case '-':
                currentAnswer -= operand;
                break;

            case '*':
                currentAnswer *= operand;
                break;

            case '/':
                if (operand == 0){
                    error();
                } else {
                    currentAnswer /= operand;
                }
                break;

            default:
                error();
                opCounter--;
                break;

        }
    }

    public void clearVals(){
        currentCalcText = "";
        operand = 0;
        opCounter = 0;
        currentAnswer = 0;
        history.clear();
    }

    public void sign(){
        if (!negative){
            // change to negative
            // on calc screen
            currentCalcText = "-" + currentCalcText;
            displayCalc(currentCalcText);

            negative = true;

        } else {
            // change to positive
            currentCalcText = currentCalcText.substring(1, currentCalcText.length());
            displayCalc(currentCalcText);

            negative = false;

        }

        // change sign of operand
        operand *= -1;

    }

    public void error(){
        displayCalc("ERROR");
        clearVals();
        displayHistory(history);
    }

    public void backspace(){
        currentCalcText = currentCalcText.substring(0, currentCalcText.length()-1);
        displayCalc(currentCalcText);
    }

    public void memAdd(){
        // adds current value on screen to memory
        double screenNumber = Double.parseDouble(calcField.getText().toString());
        memory += screenNumber;
        String output = "Added " + screenNumber + " to memory | TOTAL: " + memory;
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    public void memClear(){
        memory = 0;
        String output = "Memory cleared.";
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    public void memSub(){
        // subtracts current value to memory
        double screenNumber = Double.parseDouble(calcField.getText().toString());
        memory -= screenNumber;
        String output = "Subtracted " + screenNumber + " from memory | TOTAL: " + memory;
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    public void memRecall(){
        // adds current value to memory
        displayCalc(String.valueOf(memory));
        clearVals();
        displayHistory("CURRENT MEMORY VALUE");
    }

}