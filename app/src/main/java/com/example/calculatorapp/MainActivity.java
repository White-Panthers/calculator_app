package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.display);
        textBox.setShowSoftInputOnFocus(false);
        Switch toggle = (Switch) findViewById(R.id.switch1);
        Boolean switchState = toggle.isChecked();

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    toggle.setText("Dark");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    toggle.setText("Light");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }



    private void updateText(String string){
        String oldString = textBox.getText().toString();
        int cursorPosition = textBox.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        textBox.setText(String.format("%s%s%s",leftString, string, rightString));
        textBox.setSelection(cursorPosition+1);
    }

    public void zeroButton(View view){
        updateText("0");
    }

    public boolean checkOperand(char last){
        char[] op = { '+', '-', '*', '/', '^' };

        for(char ch : op){
            if(ch == last)
                return true;
        }
        return false;
    }

    public void decimalButton(View view){
        String text = textBox.getText().toString();
        String[] splits = text.split("(?<=[-+*/])|(?=[-+*/])");


        if(!splits[splits.length-1].contains(".")) {
            updateText(".");
        }
    }

    public void equalButton(View view){
        String userInput = textBox.getText().toString();
        Expression expression = new Expression(userInput);
        String result = String.valueOf(expression.calculate());

        textBox.setText(result);
        textBox.setSelection(result.length());
    }

    public void multiplyButton(View view) {
        String text = textBox.getText().toString();
        char last = text.charAt(text.length() - 1);

        if (last == '*') {
            return;
        }
        else {
            if (!checkOperand(last)) {
                updateText("*");
            }
            else {
                text = text.substring(0, text.length() - 1) + "*";
                textBox.setText(text);
                textBox.setSelection(text.length());
            }
        }
    }

    public void threeButton(View view){
        updateText("3");
    }

    public void twoButton(View view){
        updateText("2");
    }

    public void oneButton(View view){
        updateText("1");
    }

    public void divideButton(View view){
        String text = textBox.getText().toString();
        char last = text.charAt(text.length() - 1);

        if (last == '/') {
            return;
        }
        else {
            if (!checkOperand(last)) {
                updateText("/");
            }
            else {
                text = text.substring(0, text.length() - 1) + "/";
                textBox.setText(text);
                textBox.setSelection(text.length());
            }
        }
    }

    public void sixButton(View view){
        updateText("6");
    }

    public void fiveButton(View view){
        updateText("5");
    }

    public void fourButton(View view){
        updateText("4");
    }

    public void subtractButton(View view){
        String text = textBox.getText().toString();
        char last = text.charAt(text.length() - 1);

        if (last == '-') {
            return;
        }
        else {
            if (!checkOperand(last)) {
                updateText("-");
            }
            else {
                text = text.substring(0, text.length() - 1) + "-";
                textBox.setText(text);
                textBox.setSelection(text.length());
            }
        }
    }

    public void nineButton(View view){
        updateText("9");
    }

    public void eightButton(View view){
        updateText("8");
    }

    public void negativeBtn(View view){
            updateText("");
    }

    public void sevenButton(View view){
        updateText("7");
    }

    public void addButton(View view){
        String text = textBox.getText().toString();
        char last = text.charAt(text.length() - 1);

        if (last == '+') {
            return;
        }
        else {
            if (!checkOperand(last)) {
                updateText("+");
            }
            else {
                text = text.substring(0, text.length() - 1) + "+";
                textBox.setText(text);
                textBox.setSelection(text.length());
            }
        }
    }

    public void deleteButton(View view){
        int cursorPosition = textBox.getSelectionStart();
        int length = textBox.getText().length();

        if(cursorPosition != 0 && length != 0){
            SpannableStringBuilder text = (SpannableStringBuilder) textBox.getText();
            text.replace(cursorPosition - 1, cursorPosition, "");
            textBox.setText(text);
            textBox.setSelection(cursorPosition-1);
        }
    }

    public void clearButton(View view){
        textBox.setText("");
    }

    public void powerButton(View view){
        String text = textBox.getText().toString();
        char last = text.charAt(text.length() - 1);

        if (last == '^') {
            return;
        }
        else {
            if (!checkOperand(last)) {
                updateText("^");
            }
            else {
                text = text.substring(0, text.length() - 1) + "^";
                textBox.setText(text);
                textBox.setSelection(text.length());
            }
        }
    }

    public void parenthesisButton(View view){
        int cursorPosition = textBox.getSelectionStart();
        int length = textBox.getText().length();
        int openParenthesis = 0;
        int closedParenthesis = 0;

        for(int i=0; i<cursorPosition; i++){
            if(textBox.getText().toString().substring(i, i+1).equals("(")){
                openParenthesis = openParenthesis+1;
            }
            else if(textBox.getText().toString().substring(i, i+1).equals(")")){
                closedParenthesis = closedParenthesis+1;
            }
        }
        if(openParenthesis  == closedParenthesis || textBox.getText().toString().substring(length - 1, length).equals("(")){
            updateText("(");
        }
        else if(closedParenthesis  < openParenthesis && !textBox.getText().toString().substring(length - 1, length).equals("(")){
            updateText(")");

        }
        textBox.setSelection(cursorPosition+1);
    }

}