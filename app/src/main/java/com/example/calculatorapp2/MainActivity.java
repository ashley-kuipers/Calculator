package com.example.calculatorapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // regular stuff
    // TODO: Figure out rounding
    // TODO: add some kind of feedback to the buttons
    // TODO: comment everything
    // TODO: suppress leading zeroes
    // TODO: logic of clear/all clear buttons not quite right

    // declaring global variables
    Button b_help, b_memclear, b_memrecall, b_clear, b_backspace, b_memsub, b_memadd, b_divide, b_seven, b_eight, b_nine, b_multiply, b_four, b_five, b_six, b_sub, b_one, b_two, b_three, b_add, b_sign, b_decimal, b_zero, b_equal;
    TextView calcField, historyField;
    Context context;
    Welcome welcome;
    String currentCalcText = "";
    ArrayList<String> history = new ArrayList<String>();
    double currentAnswer = 0, operand = 0, memory = 0;
    char lastOperation = '+';
    int opCounter=0;
    boolean negative = false, allClear = true, welcomed = false, helpMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide default action bar (adds more room to calc screen)
        Objects.requireNonNull(getSupportActionBar()).hide();

        // assigning layout elements to variables
        // buttons
        b_memclear = findViewById(R.id.b_memclear);
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
        b_help = findViewById(R.id.b_help);

        // TextViews
        calcField = findViewById(R.id.t_calc);
        historyField = findViewById(R.id.t_history);

        // Setting onclick listeners to buttons
        b_memclear.setOnClickListener(this);
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
        b_help.setOnClickListener(this);

        // getting context for Toasts
        context = getApplicationContext();

        // starts welcome screen fragment
        FragmentManager fr = getSupportFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        welcome = new Welcome();
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
        outState.putBoolean("welcomed", welcomed);
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
        welcomed = saved.getBoolean("welcomed");

        if(welcomed){
            welcome.b_start.performClick();
        }

        displayCalc(currentCalcText);
        currentCalcText = "";
        displayHistory(history);
    }

    public void onClick(View v){
        // gives function to each button
        switch (v.getId()){
            case R.id.b_zero:
                if (helpMode){
                    displayHistory("The number zero");
                } else {
                    addChar('0');
                }
                break;

            case R.id.b_one:
                if (helpMode){
                    displayHistory("The number one");
                } else {
                    addChar('1');
                }
                break;

            case R.id.b_two:
                if (helpMode){
                    displayHistory("The number two");
                } else {
                    addChar('2');
                }
                break;

            case R.id.b_three:
                if (helpMode){
                    displayHistory("The number three");
                } else {
                    addChar('3');
                }
                break;

            case R.id.b_four:
                if (helpMode){
                    displayHistory("The number four");
                } else {
                    addChar('4');
                }
                break;

            case R.id.b_five:
                if (helpMode){
                    displayHistory("The number five");
                } else {
                    addChar('5');
                }
                break;

            case R.id.b_six:
                if (helpMode){
                    displayHistory("The number six");
                } else {
                    addChar('6');
                }
                break;

            case R.id.b_seven:
                if (helpMode){
                    displayHistory("The number seven");
                } else {
                    addChar('7');
                }
                break;

            case R.id.b_eight:
                if (helpMode){
                    displayHistory("The number eight");
                } else {
                    addChar('8');
                }
                break;

            case R.id.b_nine:
                if (helpMode){
                    displayHistory("The number nine");
                } else {
                    addChar('9');
                }
                break;

            case R.id.b_decimal:
                if (helpMode){
                    displayHistory("The decimal point");
                } else {
                    addChar('.');
                }
                break;

            case R.id.b_clear:

                if (!allClear){
                    if (helpMode){
                        displayHistory("CE: Clears only current value");
                    } else {
                        // clear only current value
                        currentCalcText = "";
                        displayCalc("0");
                        b_clear.setText("AC");
                        allClear = !allClear;

                    }
                } else if(opCounter > 0 && allClear){
                    if (helpMode){
                        displayHistory("AC: Clears all history (except stored memory)");
                    } else {
                        // clear all values
                        b_clear.setText("CE");
                        clearVals();
                        displayCalc("0");
                        displayHistory(history);

                    }
                }
                break;

            case R.id.b_backspace:
                if (helpMode){
                    displayHistory("Backspace one character at a time.");
                } else {
                    backspace();
                }
                break;

            case R.id.b_sign:
                if (helpMode){
                    displayHistory("Change sign of number on screen");
                } else {
                    sign();
                }
                break;

            case R.id.b_add:
                if (helpMode){
                    displayHistory("Addition button");
                } else {
                    calculate('+');
                }
                break;

            case R.id.b_sub:
                if (helpMode){
                    displayHistory("Subtraction button");
                } else {
                    calculate('-');
                }
                break;

            case R.id.b_multiply:
                if (helpMode){
                    displayHistory("Multiply button");
                } else {
                    calculate('*');
                }
                break;

            case R.id.b_divide:
                if (helpMode){
                    displayHistory("Divide button");
                } else {
                    calculate('/');
                }
                break;

            case R.id.b_equal:
                if (helpMode){
                    displayHistory("Equals button. Gives total of all operations");
                } else {
                    calculate('=');
                }
                break;

            case R.id.b_memadd:
                if (helpMode){
                    displayHistory("Adds number on screen to current memory value");
                } else {
                    memAdd();
                }
                break;

            case R.id.b_memsub:
                if (helpMode){
                    displayHistory("Subtracts number on screen from memory value");
                } else {
                    memSub();
                }
                break;

            case R.id.b_memclear:
                if (helpMode){
                    displayHistory("Clears current stored memory value");
                } else {
                    memAdd();
                }
                break;

            case R.id.b_memrecall:
                if (helpMode){
                    displayHistory("Displays current stored memory value");
                } else {
                    memRecall();
                }
                break;

            case R.id.b_help:
                help();
                break;

            default:
                error();
                break;
        }
    }

    public void help(){
        String out = "";
         if(!helpMode){
             helpMode = true;
             // turn background of button darker
             out += "Help-mode is on. Press any button.";
         } else {
             helpMode = false;
             out += "Help-mode off";
             if(currentCalcText.length()>0){
                 displayCalc(currentCalcText);
             } else {
                 displayCalc("0");
             }
             displayHistory(history);
         }
         Toast.makeText(context, out, Toast.LENGTH_LONG).show();
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

    // lets us know that the welcome fragment has been clicked for save state
    public void fragmentClicked(boolean isClicked){
        welcomed = isClicked;
    }

}