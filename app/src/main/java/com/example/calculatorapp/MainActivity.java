package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = findViewById(R.id.display);
        textBox.setShowSoftInputOnFocus(false);
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

    public void decimalButton(View view){
        updateText(".");
    }

    public void equalButton(View view){
        String userInput = textBox.getText().toString();
        Expression expression = new Expression(userInput);
        String result = String.valueOf(expression.calculate());

        textBox.setText(result);
        textBox.setSelection(result.length());
    }

    public void multiplyButton(View view){
        updateText("*");
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
        updateText("/");
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
        updateText("-");
    }

    public void nineButton(View view){
        updateText("9");
    }

    public void eightButton(View view){
        updateText("8");
    }

    public void sevenButton(View view){
        updateText("7");
    }

    public void addButton(View view){
        updateText("+");
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
}