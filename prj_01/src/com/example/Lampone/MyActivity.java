package com.example.Lampone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity implements View.OnClickListener {

    private static final int LENGTH = 19; // максимальная длина числа в текстовом поле

    private EditText textField; // текстовое поле

    private int summand = 0; // слагаемое
    private String operation = "+"; // операция

    private boolean operationIsLastOne = false; // выбор операции - последнее действие на данный момент
    private boolean nextIsNewChislo = true; // далее в текстовое поле будет вводиться новое число
    private boolean temp = false; // уже была выбрана какая-то операция

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.textField = (EditText) this.findViewById(R.id.textField);
    }

    @Override
    public void onClick(View v) {
        String button = ((Button) this.findViewById(v.getId())).getText().toString();
        String text = this.textField.getText().toString();
        System.out.println(text);
        int x = Integer.parseInt(text);
        int len = text.length();

        switch (button) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (text.equals("0") || this.nextIsNewChislo) {
                    this.textField.setText(button);
                } else if (text.length() < LENGTH) {
                    this.textField.setText(text.concat(button));
                }
                this.nextIsNewChislo = false;
                this.operationIsLastOne = false;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "=":
                if (this.operation.equals("/") && x == 0) {
                    this.textField.setText("Деление на 0!");
                    this.reset(false);
                    break;
                }
                if (button.equals("=")) {
                    this.summand = result(this.summand, x, this.operation);
                    this.textField.setText(String.valueOf(this.summand));
                    this.reset(false);
                    break;
                }
                if (this.operationIsLastOne) {
                    this.operation = button;
                    this.temp = true;
                } else {
                    this.summand = result(this.summand, x, this.operation);
                    this.operation = button;
                    System.out.println(this.summand);
                    if (this.temp) {
                        this.textField.setText(String.valueOf(this.summand));
                    }
                    this.operationIsLastOne = true;
                    this.nextIsNewChislo = true;
                    this.temp = true;
                }
                break;
            case "+/-":
                this.textField.setText(String.valueOf(-x));
                break;
            case "←":
                if (len > 1) {
                    this.textField.setText(text.substring(0, len - 1));
                } else {
                    this.textField.setText("0");
                }
                break;
            case "C":
                this.reset(true);
                break;
        }
    }

    private static int result(int a, int b, String op) {
        if (op == null) {
            return 0;
        }
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }

    private void reset(boolean tf) {
        this.summand = 0;
        this.operation = "+";
        this.operationIsLastOne = false;
        this.nextIsNewChislo = true;
        this.temp = false;
        if (tf) {
            this.textField.setText("0");
        }
    }
}
