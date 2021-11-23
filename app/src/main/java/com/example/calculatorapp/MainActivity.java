package com.example.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    private String oldNumber = "";
    private String op = "+";
    private boolean b = false;
    private boolean forPoint = true;
    private boolean sign = true;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        editText.setRawInputType(InputType.TYPE_NULL);
    }

    public void numberEvent(View view) {
        if(editText.getText().toString().equals("0") || b) {
            editText.setText("");
            b = false;
        }
        String num = editText.getText().toString();
        switch (view.getId()) {
            case R.id.zeroBtn:
                num += "0";
                break;

            case R.id.oneBtn:
                num += "1";
                break;

            case R.id.twoBtn:
                num += "2";
                break;

            case R.id.threeBtn:
                num += "3";
                break;

            case R.id.fourBtn:
                num += "4";
                break;

            case R.id.fiveBtn:
                num += "5";
                break;

            case R.id.sixBtn:
                num += "6";
                break;

            case R.id.sevenBtn:
                num += "7";
                break;

            case R.id.eightBtn:
                num += "8";
                break;

            case R.id.nineBtn:
                num += "9";
                break;

            case R.id.pointBtn:
                while (forPoint) {
                    if(num.equals("")){
                        num += "0.";
                    }
                    else{
                        num += ".";
                    }
                    forPoint = false;
                }
                break;

            case R.id.plusMinusBtn:
                if(num.equals("0") || num.isEmpty()) {
                    num = num=="0" ? "0" : "";
                }
                else{
                    if(sign) {
                        num = "-" + num;
                        sign = false;
                    }
                    else {
                        num = num.substring(1);
                        sign = true;
                    }
                }
                break;

            case R.id.backspaceBtn:
                if(num.length() >= 1) {
                    num = num.substring(0, num.length() - 1);
                }
                break;
        }
        editText.setText(num);

    }

    public void operatorEvent(View view) {
        b = true;
        forPoint = true;
        oldNumber = editText.getText().toString();
        if(oldNumber.equals("")) {
            editText.setText("");
        }
        else {
            switch (view.getId()) {
                case R.id.plusBtn:
                    op = "+";
                    break;

                case R.id.minusBtn:
                    op = "-";
                    break;

                case R.id.multiplyBtn:
                    op = "×";
                    break;

                case R.id.divideBtn:
                    op = "÷";
                    break;
            }
            textView.setText(oldNumber + op);
            editText.setText("");
        }
    }

    public void equalEvent(View view) {
        vibrator.vibrate(70);
        if (editText.getText().toString().equals("")) {
            editText.setText("");
        }
        else {
            String newNumber = editText.getText().toString();
            double result = 0;
            switch (op) {
                case "+":
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                    break;

                case "-":
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                    break;

                case "×":
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                    break;

                case "÷":
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                    break;
            }
            textView.setText(oldNumber + op + newNumber);
            editText.setText(result + "");
        }
    }

    public void clearEvent(View view) {
        editText.setText("");
        textView.setText("");
    }

    public void percentEvent(View view) {
        if(!editText.getText().toString().equals("")) {
            double number = Double.parseDouble(editText.getText().toString()) / 100;
            editText.setText(number + "");
        }
    }

}