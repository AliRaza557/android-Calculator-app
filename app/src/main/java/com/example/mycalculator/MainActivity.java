package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;

    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniTextViews();
    }
    private void iniTextViews() {
        workingsTV = (TextView)findViewById(R.id.workingtext);
        resultsTV = (TextView)findViewById(R.id.resulttext);
    }
    private void setWorkings(String givenValue)
    {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }public void equalonClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (SecurityException | ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result !=null)
            resultsTV.setText(String.valueOf(result.doubleValue()));
    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }
        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }
        String original = numberLeft + "^" + numberRight;
        String changed  = "Math.pow(" + numberLeft + ", "+ numberRight+")";
        tempFormula = tempFormula.replace(original,changed);

    }

    private boolean isNumeric(char c) {
        if (( c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }


    public void clearonClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;

    }
    boolean leftBracket = true;

    public void bracketonclick(View view) {
        if (leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
        {
            setWorkings(")");
            leftBracket = true;
        }

    }
    public void poweronClick(View view) {
        setWorkings("^");
    }

    public void eightonClick(View view) {
        setWorkings("8");
    }

    public void nineonClick(View view) {
        setWorkings("9");
    }
    public void multipleonClick(View view) {
        setWorkings("*");
    }

    public void fouronClick(View view) {
        setWorkings("4");
    }

    public void fiveonClick(View view) {
        setWorkings("5");
    }

    public void sixonClick(View view) {
        setWorkings("6");
    }

    public void subonClick(View view) {
        setWorkings("-");
    }

    public void oneonClick(View view) {
        setWorkings("1");
    }

    public void twoonClick(View view) {
        setWorkings("2");
    }

    public void threeonClick(View view) {
        setWorkings("3");
    }

    public void addonClick(View view) {
        setWorkings("+");
    }

    public void pointonClick(View view) {
        setWorkings(".");
    }

    public void zeroonClick(View view) {
        setWorkings("0");
    }


    public void divideClick(View view) {
        setWorkings("/");
    }

    public void sevenonClick(View view) {
        setWorkings("7");
    }
}