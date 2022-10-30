//Ashley Kuipers | T00713040 | Fall 2022 | COMP 2161
// Please check out the README.md for documentation on the application

package com.example.calculatorapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaring global variables
    Button b_help, b_memclear, b_memrecall, b_clear, b_backspace, b_memsub, b_memadd, b_divide, b_seven, b_eight, b_nine, b_multiply, b_four, b_five, b_six, b_sub, b_one, b_two, b_three, b_add, b_sign, b_decimal, b_zero, b_equal;
    TextView calcField, historyField, signField;
    Context context;
    Welcome welcome;
    String currentCalcText = "", operations = "+-/*=";
    ArrayList<String> history = new ArrayList<String>();
    BigDecimal operand, currentAnswer, memory = BigDecimal.ZERO, helpModeNumBefore;
    char lastOperation = '+', lastChar;
    int opCounter=0, helped = 0;
    boolean negative = false, allClear = true, welcomed = false, helpMode = false;
    MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

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
        signField = findViewById(R.id.t_sign);

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
                    lastChar = '0';
                }
                break;

            case R.id.b_one:
                if (helpMode){
                    displayHistory("The number one");
                } else {
                    addChar('1');
                    lastChar = '1';
                }
                break;

            case R.id.b_two:
                if (helpMode){
                    displayHistory("The number two");
                } else {
                    addChar('2');
                    lastChar = '2';
                }
                break;

            case R.id.b_three:
                if (helpMode){
                    displayHistory("The number three");
                } else {
                    addChar('3');
                    lastChar = '3';
                }
                break;

            case R.id.b_four:
                if (helpMode){
                    displayHistory("The number four");
                } else {
                    addChar('4');
                    lastChar = '4';
                }
                break;

            case R.id.b_five:
                if (helpMode){
                    displayHistory("The number five");
                } else {
                    addChar('5');
                    lastChar = '5';
                }
                break;

            case R.id.b_six:
                if (helpMode){
                    displayHistory("The number six");
                } else {
                    addChar('6');
                    lastChar = '6';
                }
                break;

            case R.id.b_seven:
                if (helpMode){
                    displayHistory("The number seven");
                } else {
                    addChar('7');
                    lastChar = '7';
                }
                break;

            case R.id.b_eight:
                if (helpMode){
                    displayHistory("The number eight");
                } else {
                    addChar('8');
                    lastChar = '8';
                }
                break;

            case R.id.b_nine:
                if (helpMode){
                    displayHistory("The number nine");
                } else {
                    addChar('9');
                    lastChar = '9';
                }
                break;

            case R.id.b_decimal:
                if (helpMode){
                    displayHistory("The decimal point");
                } else {
                    // check if has a decimal already
                    if(currentCalcText.contains(".")) {
                        error("ERROR");
                    } else {
                        addChar('.');
                        lastChar = '.';
                    }

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
                    lastChar = 'c';
                }
                break;

            case R.id.b_backspace:
                if (helpMode){
                    displayHistory("Backspace: remove one character at a time.");
                } else {
                    backspace();
                    lastChar = 'b';
                }
                break;

            case R.id.b_sign:
                if (helpMode){
                    displayHistory("Change sign of number on screen");
                } else {
                    sign();
                    lastChar = 's';
                }
                break;

            case R.id.b_add:
                if (helpMode){
                    displayHistory("Addition button");
                } else {
                    calculate('+');
                    lastChar = '+';
                }
                break;

            case R.id.b_sub:
                if (helpMode){
                    displayHistory("Subtraction button");
                } else {
                    calculate('-');
                    lastChar = '-';
                }
                break;

            case R.id.b_multiply:
                if (helpMode){
                    displayHistory("Multiply button");
                } else {
                    calculate('*');
                    lastChar = '*';
                }
                break;

            case R.id.b_divide:
                if (helpMode){
                    displayHistory("Divide button");
                } else {
                    calculate('/');
                    lastChar = '/';
                }
                break;

            case R.id.b_equal:
                if (helpMode){
                    displayHistory("Equals button");
                } else {
                    calculate('=');
                    lastChar = '=';
                }
                break;

            case R.id.b_memadd:
                if (helpMode){
                    displayHistory("Adds number on screen to current memory value");
                } else {
                    memAdd();
                    lastChar = 'm';
                }
                break;

            case R.id.b_memsub:
                if (helpMode){
                    displayHistory("Subtracts number on screen from memory value");
                } else {
                    memSub();
                    lastChar = 'm';
                }
                break;

            case R.id.b_memclear:
                if (helpMode){
                    displayHistory("Clears current stored memory value");
                } else {
                    memClear();
                    lastChar = 'm';
                }
                break;

            case R.id.b_memrecall:
                if (helpMode){
                    displayHistory("Displays current stored memory value");
                } else {
                    memRecall();
                    lastChar = 'm';
                }
                break;

            case R.id.b_help:
                help();
                lastChar = 'h';
                break;

            default:
                error("ERROR");
                break;
        }
    }

    // When a digit/decimal is pressed, this adds it to the current working number
    public void addChar(char c){
        if (currentCalcText.length() < 12){
            // account for sign
            if(negative){
                displaySign(true);
            } else {
                displaySign(false);
            }

            // adds character to the current calculator text (accounting for user pressing decimal first)
            if(currentCalcText.length() == 0 && c == '.'){
                currentCalcText += "0.";
            } else {
                currentCalcText += c;
            }

            // displays the empty history arraylist if no operations have happened yet (effectively clears the history after the equals sign has been used)
            if(opCounter == 0){
                displayHistory(history);
            }

            // updates the current operand to the value on the calc screen
            operand = new BigDecimal(currentCalcText);

            // displays the current number on calc screen (using BigDecimal as this will suppress leading 0's)
            displayCalc(operand.toPlainString());

            // if sign was changed, adjust operand
            if(negative){
                operand = operand.negate();
            }

            // change the clear button text to CE
            b_clear.setText("CE");
            allClear = false;
        } else {
            error("OVERFLOW ERR");
        }

    }

    // Displays the inputted text on the calculator screen textview
    public void displayCalc(String text){
        calcField.setText(text);
    }

    // Displays the inputted history arraylist on the history textview
    public void displayHistory(@NonNull ArrayList<String> hist){
        String print = "";
        for (int i=0; i < hist.size(); i++){
            print += hist.get(i);
        }
        if (print.length()>36){
            error("0");
            displayHistory("HISTORY OVERFLOW ERROR");
        } else {
            historyField.setText(print);
        }
    }

    // Overloaded function, displays the inputted text on the history textview (for help mode, etc)
    public void displayHistory(String input){
        historyField.setText(input);
    }

    // Displays the sign on the sign textview
    public void displaySign(boolean neg){
        if (neg){
            signField.setText("-");
        } else {
            signField.setText("");
        }
    }

    // The stuff that happens when an operator is pressed (+. -, /, *, =)
    public void calculate(char op){
        if(operations.contains(String.valueOf(lastChar))){
            error("ERROR");
        } else {
            // add currentCalcText and the current operation to history, and displays on history field
            if (currentCalcText.length() > 0){
                BigDecimal tempOut = new BigDecimal(currentCalcText);
                currentCalcText = tempOut.toPlainString();
            }
            if(negative){
                history.add("-" + currentCalcText);
            } else {
                history.add(currentCalcText);
            }
            history.add(String.valueOf(op));
            displayHistory(history);

            // clears the calcText (so a new number can be started)
            currentCalcText = "";
            negative = false;

            // operate on numbers
            // In order to have a running total, we are always running on the previous operator
            // if this is the first operation, the current operand will display as the running total
            if(opCounter == 0){
                currentAnswer = new BigDecimal(operand.toPlainString());
            } else {
                // operates on the previous operator
                operate(lastOperation);
            }
            // updates previous operation with value of the current one
            lastOperation = op;

            // display running total to the calculator screen
            String output;
            // if answer is neg, remove sign from string, and update sign field to display neg sign
            if(currentAnswer.compareTo(BigDecimal.ZERO) < 0){
                output = currentAnswer.negate().toPlainString();
                displaySign(true);
            } else {
                output = currentAnswer.toPlainString();
            }

            // Formats the output if it is too big to fit on the screen
            if(output.length() > 12){
                if(!(output.contains("."))){
                    // if it is too large of an integer, overflow error
                    error("OVERFLOW ERR");

                    // else check if it's a number with decimal somewhere in the middle, find digits on each side of decimal and round accordingly
                } else if (output.contains(".") && output.charAt(1) != '.') {
                    // finds the number of digits before the decimal
                    int decIndex = output.indexOf(".");
                    int digBefore = output.substring(0, decIndex-1).length();

                    // if the number of digits before the decimal is too large, overflow error
                    if (digBefore > 12){
                        error("OVERFLOW ERR");
                        // else it rounds the number dynamically based on the total digits - number before the decimal
                    } else {
                        MathContext mc2 = new MathContext(12-digBefore, RoundingMode.HALF_EVEN);
                        BigDecimal outputTemp = new BigDecimal(output, mc2);
                        output = outputTemp.toPlainString();
                        displayCalc(output);
                    }

                    //else if the decimal is in the beginning (ie 0.###), round to 10 digits after the decimal
                } else if(output.charAt(1) != '.') {
                    BigDecimal outputTemp = new BigDecimal(output, mc);
                    output = outputTemp.toPlainString();
                    displayCalc(output);
                }
            } else {
                displayCalc(output);
            }

            // increment the operation counter
            opCounter ++;

            // change clear button text back to AC
            b_clear.setText("AC");
            allClear = true;
        }

    }

    // Completes a specified operation and updates the global value currentAnswer
    public void operate(char op){
        switch (op){
            case '+':
                currentAnswer = currentAnswer.add(operand);
                break;

            case '-':
                currentAnswer = currentAnswer.subtract(operand);
                break;

            case '*':
                currentAnswer = currentAnswer.multiply(operand);
                break;

            case '/':
                // accounts for the divide by 0 scenario
                if (operand.equals(BigDecimal.ZERO)){
                    error("DIVIDE BY 0 ERR");
                } else {
                    currentAnswer = currentAnswer.divide(operand, mc);
                }
                break;

            default:
                error("ERROR");
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

        } else {
            // clear all values and history
            clearVals();
        }
        displayCalc("0");
        displayHistory(history);
    }

    // Clears the stored values
    public void clearVals(){
        currentCalcText = "";
        operand = BigDecimal.ZERO;
        opCounter = 0;
        currentAnswer = BigDecimal.ZERO;
        history.clear();
    }

    // Changes the sign of the current number on the calculator screen
    public void sign(){
        if (!negative){
            // change number to negative on screen
            negative = true;
            displaySign(true);

        } else {
            // change to positive on screen
            displaySign(false);
            negative = false;

        }

        // change sign of current working number
        operand = operand.negate();

    }

    // Displays an error and clears the values and history whenever an error occurs
    public void error(String text){
        displayCalc(text);
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
            helpModeNumBefore = new BigDecimal((String) calcField.getText());
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

            // Re-displays currentCalcText and history from before help mode was turned on
            displayCalc(helpModeNumBefore.toPlainString());
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
        BigDecimal screenNumber = new BigDecimal(calcField.getText().toString());
        if(signField.getText().toString().equals("-")){
            screenNumber = screenNumber.negate();
        }

        // adds to memory
        memory = memory.add(screenNumber);

        // displays a toast updating the user about their action and the memory total
        String output = "Added " + screenNumber.toPlainString() + " to memory | TOTAL: " + memory.toPlainString();
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Subtracts the current calculator value from stored memory
    public void memSub(){
        // gets the current number from the screen
        BigDecimal screenNumber = new BigDecimal(calcField.getText().toString());
        if(signField.getText().toString().equals("-")){
            screenNumber = screenNumber.negate();
        }

        // subtracts from memory
        memory = memory.subtract(screenNumber);

        // displays a toast updating the user about their action and the memory total
        String output = "Subtracted " + screenNumber.toPlainString() + " from memory | TOTAL: " + memory.toPlainString();
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Clears the stored memory value
    public void memClear(){
        // clears memory
        memory = BigDecimal.ZERO;
        // displays a toast updating the user about their action and the memory total
        String output = "Memory cleared.";
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();
    }

    // Displays the current memory value
    public void memRecall(){
        displayCalc(memory.toPlainString());
        clearVals();
        displayHistory("CURRENT MEMORY VALUE");
    }

    // Saves variables for when app is in background or rotates
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentCalcText", calcField.getText().toString());
        outState.putStringArrayList("history",  history);
        outState.putChar("lastOperation", lastOperation);
        outState.putInt("opCounter", opCounter);
        outState.putBoolean("negative", negative);
        outState.putBoolean("welcomed", welcomed);
        outState.putBoolean("helpMode", helpMode);
        outState.putBoolean("allClear", allClear);
        outState.putInt("helped", helped);

        if(currentAnswer == null){
            outState.putString("currentAnswer", "0");
        } else {
            outState.putString("currentAnswer", currentAnswer.toPlainString());
        }

        if(operand == null){
            outState.putString("operand", "0");
        } else {
            outState.putString("operand", operand.toPlainString());
        }

        if(memory == null){
            outState.putString("memory", "0");
        } else {
            outState.putString("memory", memory.toPlainString());
        }
    }

    // Restores variables after app comes back to front or rotates
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saved) {
        super.onRestoreInstanceState(saved);
        currentCalcText = saved.getString("currentCalcText");
        history = saved.getStringArrayList("history");

        currentAnswer = new BigDecimal(saved.getString("currentAnswer"));
        operand = new BigDecimal(saved.getString("operand"));
        memory = new BigDecimal(saved.getString("memory"));

        lastOperation = saved.getChar("lastOperation");
        opCounter = saved.getInt("opCounter");
        negative = saved.getBoolean("negative");
        welcomed = saved.getBoolean("welcomed");
        helpMode = saved.getBoolean("helpMode");
        allClear = saved.getBoolean("allClear");
        helped = saved.getInt("helped");

        // if the welcome screen has already been dismissed, dismiss it again on restore
        if(welcomed){
            welcome.b_start.performClick();
        }

        // display saved values to screen
        displayCalc(currentCalcText);
        displayHistory(history);

        // if was in help mode before, activate again
        if(helpMode){
            b_help.setBackgroundColor(getResources().getColor(R.color.lightest));
            b_help.setTextColor(getResources().getColor(R.color.text));
        } else {
            currentCalcText = "";
        }

        // if number was negative before, display sign
        if(negative){
            displaySign(true);
        }

    }

    // Checks with the welcome fragment to see if it has been dismissed (used for save state)
    public void fragmentClicked(boolean isClicked){
        welcomed = isClicked;
    }

}