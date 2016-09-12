package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Container class for holding event-related method handlers
 * @author Mitchell Sawatzky
 * @author Connor Newman
 * @since April 2016
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The value currently being displayed
     */
    String calcValue = "0";

    /**
     * The previous display value
     */
    String lastValue = "0";

    /**
     * Whether or not the decimal key can be hit again
     */
    boolean decimal = false;

    /**
     * Whether or not `=` was the last key hit
     */
    boolean lastEquals = false;

    /**
     * Whether or not the key being hit is following the '=' key
     */
    boolean followsEquals = false;

    /**
     * Whether or not the key being hit is following an operator
     */
    boolean followsOp = false;

    /**
     * The current operator
     */
    char operator = 0;

    /**
     * The TextView display
     */
    TextView tv;

    /**
     * Gets called on app startup
     * @param savedInstanceState - the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView);
        tv.setText("0");
    }

    /**
     * Handler that gets called when any number key is hit
     * @param view - the button that was hit
     */
    public void handlerNum (View view) {
        if (followsEquals) {
            handlerClear(view);
        }
        if (calcValue.length() > 132) {
            handlerClear(view);
            tv.setText("OVERFLOW");
        } else {
            calcValue = (calcValue.equals("0") ? "" : calcValue) + ((Button) view).getText().toString();
            tv.setText(calcValue);
        }
        followsOp = false;
    }

    /**
     * Handler that ges called when the `=` button is hit
     * @param view - the `=` button
     */
    public void handlerEq (View view) {
        followsEquals = true;
        String t = calcValue;
        decimal = false;
        switch (operator) {
            case '+':
                if (!lastEquals) {
                    calcValue = "" + (Double.parseDouble(lastValue) + Double.parseDouble(calcValue));
                    lastValue = t;
                } else {
                    calcValue = "" + (Double.parseDouble(calcValue) + Double.parseDouble(lastValue));
                }
                tv.setText(calcValue);
                break;
            case '-':
                if (!lastEquals) {
                    calcValue = "" + (Double.parseDouble(lastValue) - Double.parseDouble(calcValue));
                    lastValue = t;
                } else {
                    calcValue = "" + (Double.parseDouble(calcValue) - Double.parseDouble(lastValue));
                }
                tv.setText(calcValue);
                break;
            case '*':
                if (!lastEquals) {
                    calcValue = "" + (Double.parseDouble(lastValue) * Double.parseDouble(calcValue));
                    lastValue = t;
                } else {
                    calcValue = "" + (Double.parseDouble(calcValue) * Double.parseDouble(lastValue));
                }
                tv.setText(calcValue);
                break;
            case '/':
                if (!lastEquals) {
                    if (Double.parseDouble(calcValue) == 0.0) {
                        handlerClear(view);
                        tv.setText("ERROR");
                    } else {
                        calcValue = "" + (Double.parseDouble(lastValue) / Double.parseDouble(calcValue));
                        lastValue = t;
                        tv.setText(calcValue);
                    }
                } else {
                    if (Double.parseDouble(lastValue) == 0.0) {
                        handlerClear(view);
                        tv.setText("ERROR");
                    } else {
                        calcValue = "" + (Double.parseDouble(calcValue) / Double.parseDouble(lastValue));
                        tv.setText(calcValue);
                    }
                }
                break;
            case 0:
                break;
            default:
                throw new RuntimeException("Unexpected operator: " + operator);
        }
        lastEquals = true;
        followsOp = false;
    }

    /**
     * The handler that gets called when the decimal key is hit
     * @param view - the decimal button
     */
    public void handlerDec (View view) {
        if (followsEquals) {
            handlerClear(view);
        }
        if (decimal) {
            handlerClear(view);
            tv.setText("ERROR");
        } else {
            decimal = true;
            calcValue += ((Button)view).getText().toString();
            tv.setText(calcValue);
        }
        followsOp = false;
    }

    /**
     * The handler that gets called when the clear button is hit
     * @param view - the clear button
     */
    public void handlerClear (View view) {
        followsEquals = false;
        tv.setText("0");
        lastValue = "0";
        calcValue = "0";
        operator = 0;
        decimal = false;
        followsOp = false;
    }

    /**
     * The handler that gets called when the add button is hit
     * @param view - the add button
     */
    public void handlerAdd (View view) {
        boolean run = false;
        if (followsOp == false && !lastEquals) {
            handlerEq(view);
            run = true;
        }
        followsEquals = false;
        if ((operator != '+' && followsOp == false) || run) {
            lastValue = calcValue;
            calcValue = "0";
            decimal = false;
        }
        operator = '+';
        lastEquals = false;
        followsOp = true;
    }

    /**
     * The handler that gets called when the subtract button is hit
     * @param view - the subtract buttton
     */
    public void handlerSub (View view) {
        boolean run = false;
        if (followsOp == false && !lastEquals) {
            handlerEq(view);
            run = true;
        }
        followsEquals = false;
        if ((operator != '-' && followsOp == false) || run) {
            lastValue = calcValue;
            calcValue = "0";
            decimal = false;
        }
        operator = '-';
        lastEquals = false;
        followsOp = true;
    }

    /**
     * The handler that gets caleld when the multiplication button is hit
     * @param view - the muliplication button
     */
    public void handlerMult (View view) {
        boolean run = false;
        if (followsOp == false && !lastEquals) {
            handlerEq(view);
            run = true;
        }
        followsEquals = false;
        if ((operator != '*' && followsOp == false) || run) {
            lastValue = calcValue;
            calcValue = "0";
            decimal = false;
        }
        operator = '*';
        lastEquals = false;
        followsOp = true;
    }

    /**
     * The handler that gets called when the division button is hit
     * @param view - the division button
     */
    public void handlerDiv (View view) {
        boolean run = false;
        if (followsOp == false && !lastEquals) {
            handlerEq(view);
            run = true;
        }
        followsEquals = false;
        if ((operator != '/' && followsOp == false) || run) {
            lastValue = calcValue;
            calcValue = "0";
            decimal = false;
        }
        operator = '/';
        lastEquals = false;
        followsOp = true;
    }

}
