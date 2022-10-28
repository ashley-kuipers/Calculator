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
    // TODO: suppress leading zeroes

    // declaring global variables
    Button b_help, b_memclear, b_memrecall, b_clear, b_backspace, b_memsub, b_memadd, b_divide, b_seven, b_eight, b_nine, b_multiply, b_four, b_five, b_six, b_sub, b_one, b_two, b_three, b_add, b_sign, b_decimal, b_zero, b_equal;
    TextView calcField, historyField;
    Context context;
    Welcome welcome;
    String currentCalcText = "";
    ArrayList<String> history = new ArrayList<String>();
    double currentAnswer = 0, operand = 0, memory = 0;
    char lastOperation = '+';
    int opCounter=0, helped = 0;
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

    // Adds function to each calculator button (if in help mode, each button displays it's use in the history field)
    public void onClick(View v){
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
                if(helpMode){
                    if(!allClear){
                        displayHistory("CE: Clears only current value");
                    } else {
                        displayHistory("AC: Clears all history (except stored memory)");
                    }
                } else {
                    clear();
                }
                break;

            case R.id.b_backspace:
                if (helpMode){
                    displayHistory("Backspace: remove one character at a time.");
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
                    displayHistory("Equals button");
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
                    memClear();
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

    // When a digit/decimal is pressed, this adds it to the current working number
    public void addChar(char c){
        if (currentCalcText.length() < 13){
            // adds character to the current calculator text
            currentCalcText += c;

            // displays the empty history arraylist if no operations have happened yet (effectively clears the history after the equals sign has been used)
            if(opCounter == 0){
                displayHistory(history);
            }

            // displays the current number on calc screen
            displayCalc(currentCalcText);

            // updates the current operand to the value on the calc screen
            operand = Double.valueOf(currentCalcText);

            // changes the clear button text to CE so if pressed, it will clear only the current number
            b_clear.setText("CE");
            allClear = false;
        } else {
            error();
        }

    }

    // Displays the inputted text on the calculator screen textview
    public void displayCalc(String text){
        calcField.setText(text);
    }

    // Displays the inputted history arraylist on the history textview
    public void displayHistory(ArrayList<String> hist){
        String print = "";
        for (int i=0; i < hist.size(); i++){
            print += hist.get(i);
        }
        historyField.setText(print);
    }

    // Overloaded function, displays the inputted text on the history textview (for help mode, etc)
    public void displayHistory(String input){
        historyField.setText(input);
    }

    // The stuff that happens when an operator is pressed (+. -, /, *, =)
    public void calculate(char op){
        // add currentCalcText and the current operation to history, and displays on history field
        history.add(currentCalcText);
        history.add(String.valueOf(op));
        displayHistory(history);

        // clears the calcText (so a new number can be started)
        currentCalcText = "";

        // operate on numbers
        // In order to have a running total, we are always running on the previous operator
        // if this is the first operation, the current operand will display as the running total
        if(opCounter == 0){
            currentAnswer = operand;
        } else {
            // operates on the previous operator
            operate(lastOperation);
        }
        // updates previous operation with value of the current one
        lastOperation = op;

        // display running total to the calculator screen
        displayCalc(String.valueOf(currentAnswer));

        // increment the operation counter
        opCounter ++;

        // change clear button text back to AC
        b_clear.setText("AC");
        allClear = true;

    }

    // Completes a specified operation and updates the global value currentAnswer
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
                // accounts for the divide by 0 scenario
                if (operand == 0){
                    error();
                } else {
                    currentAnswer /= operand;
                }
                break;

            default:
                error();
                // since we always add to the operation counter in the calculate function, need to subtract an operation if there is an error
                opCounter--;
                break;

        }
    }

    // Clear button: displays CE when entering a number (to delete just current working number) and then after one click, will display AC (to clear the entire history)
    public void clear(){
        if (!allClear){
            // clear only current value
            currentCalcText = "";
            b_clear.setText("AC");
            allClear = !allClear;
            displayCalc("0");
            displayHistory(history);

        } else if(opCounter > 0 && allClear){
            // clear all values
            clearVals();
            displayCalc("0");
            displayHistory(history);
        }
    }

    // Clears the stored values
    public void clearVals(){
        currentCalcText = "";
        operand = 0;
        opCounter = 0;
        currentAnswer = 0;
        history.clear();
    }

    // Changes the sign of the current number on the calculator screen
    public void sign(){
        if (!negative){
            // change number to negative on screen
            currentCalcText = "-" + currentCalcText;
            displayCalc(currentCalcText);

            negative = true;

        } else {
            // change to positive on screen
            currentCalcText = currentCalcText.substring(1, currentCalcText.length());
            displayCalc(currentCalcText);

            negative = false;

        }

        // change sign of current working number
        operand *= -1;

    }

    // Displays an error and clears the values and history whenever an error occurs
    public void error(){
        displayCalc("ERROR");
        clearVals();
        displayHistory(history);
    }

    // Removes one digit at a time from the current working number
    public void backspace(){
        currentCalcText = currentCalcText.substring(0, currentCalcText.length()-1);
        displayCalc(currentCalcText);
    }

    // Enables help mode (makes every button display it's function on click)
    public void help(){
        String out = "";
        if(!helpMode){
            // turn help mode on
            helpMode = true;

            // changes colors of buttons so user can tell when mode is on/off
            b_help.setBackgroundColor(getResources().getColor(R.color.lightest));
            b_help.setTextColor(getResources().getColor(R.color.text));

            // update output for toast
            out += "Help-mode is on. Press any button.";
        } else {
            // turn help mode off
            helpMode = false;

            // changes colors of buttons to user can tell when mode is on/off
            b_help.setBackgroundColor(getResources().getColor(R.color.medium));
            b_help.setTextColor(getResources().getColor(R.color.textlight));

            // redisplays currentCalcText and history from before help mode was turned on
            if(currentCalcText.length()>0){
                displayCalc(currentCalcText);
            } else {
                displayCalc("0");
            }
            displayHistory(history);

            // update output for toast
            out += "Help-mode off";
        }
        // notifies the user when help mode is enabled or disabled the first time they use it
        if (helped < 2){
            Toast.makeText(context, out, Toast.LENGTH_LONG).show();
        }
        helped++;

    }

    // Adds current value on calculator to stored memory
    public void memAdd(){
        // gets value from screen
        double screenNumber = Double.parseDouble(calcField.getText().toString());
        // adds to memory
        memory += screenNumber;
        // displays a toast updating the user about their action and the memory total
        String output = "Added " + screenNumber + " to memory | TOTAL: " + memory;
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Clears the stored memory value
    public void memClear(){
        // clears memory
        memory = 0;
        // displays a toast updating the user about their action and the memory total
        String output = "Memory cleared.";
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Subtracts the current calculator value from stored memory
    public void memSub(){
        // gets the current number from the screen
        double screenNumber = Double.parseDouble(calcField.getText().toString());
        // subtracts from memory
        memory -= screenNumber;
        // displays a toast updating the user about their action and the memory total
        String output = "Subtracted " + screenNumber + " from memory | TOTAL: " + memory;
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Displays the current memory value
    public void memRecall(){
        displayCalc(String.valueOf(memory));
        clearVals();
        displayHistory("CURRENT MEMORY VALUE");
    }

    // Saves variables for when app is in background or rotates
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
        outState.putBoolean("helpMode", helpMode);
        outState.putInt("helped", helped);
    }

    // Restores variables after app comes back to front or rotates
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
        helpMode = saved.getBoolean("helpMode");
        helped = saved.getInt("helped");

        // if the welcome screen has already been dismissed, dismiss it again on restore
        if(welcomed){
            welcome.b_start.performClick();
        }

        if(helpMode){
            b_help.setBackgroundColor(getResources().getColor(R.color.lightest));
            b_help.setTextColor(getResources().getColor(R.color.text));
        }

        // display saved values to screen
        displayCalc(currentCalcText);
        currentCalcText = "";
        displayHistory(history);
    }

    // Checks with the welcome fragment to see if it has been dismissed (used for save state)
    public void fragmentClicked(boolean isClicked){
        welcomed = isClicked;
    }

}