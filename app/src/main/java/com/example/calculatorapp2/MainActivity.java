package com.example.calculatorapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b_memcall, b_memrecall, b_clear, b_backspace, b_memsub, b_memadd, b_divide, b_seven, b_eight, b_nine, b_multiply, b_four, b_five, b_six, b_sub, b_one, b_two, b_three, b_add, b_sign, b_decimal, b_zero, b_equal;
    TextView calcField, historyField;
    String currentCalcText = "";
    ArrayList<String> history = new ArrayList<String>();
    double currentAnswer = 0, operand = 0;
    char lastOperation = '+';
    int opCounter=0;
    boolean negative = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning layout elements to variables
        // buttons
        b_memcall = findViewById(R.id.b_memcall);
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
        b_memcall.setOnClickListener(this);
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
                clearVals();
                displayCalc("0");
                displayHistory(history);
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

            default:

                break;
        }
    }

    public void addChar(char c){
        if (currentCalcText.length() < 13){
            currentCalcText += c;
        }

        // clear history if this is first value
        if(opCounter == 0){
            displayHistory(history);
        }

        displayCalc(currentCalcText);
        operand = Double.valueOf(currentCalcText);
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

        opCounter ++;

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
                currentAnswer /= operand;
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

    }

    public void backspace(){
        currentCalcText = currentCalcText.substring(0, currentCalcText.length()-1);
        displayCalc(currentCalcText);
    }

}