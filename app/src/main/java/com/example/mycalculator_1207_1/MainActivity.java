package com.example.mycalculator_1207_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private int openParenthesis = 0;
    private boolean dotUsed = false;
    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;

    Button  btnNum0,btnNum1,btnNum2,btnNum3,btnNum4,
            btnNum5,btnNum6,btnNum7,btnNum8,btnNum9,
            btnClear,btnParentheses,btnPercent,btnDivision,btnMul,
            btnSub,btnAdd,btnEqual,btnDot;

    TextView tvInputNumbers;

    ScriptEngine scriptEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scriptEngine = new ScriptEngineManager().getEngineByName("rhino");
        initializeViewVariables(); //직접 메서드 구현1
        setOnClickListener(); //직접 메서드 구현2
        setOnTouchListener();
    }



    private void initializeViewVariables() {
        btnNum0 = (Button)findViewById(R.id.button_zero);
        btnNum1 = (Button)findViewById(R.id.button_one);
        btnNum2 = (Button)findViewById(R.id.button_two);
        btnNum3 = (Button)findViewById(R.id.button_three);
        btnNum4 = (Button)findViewById(R.id.button_four);
        btnNum5 = (Button)findViewById(R.id.button_five);
        btnNum6 = (Button)findViewById(R.id.button_six);
        btnNum7 = (Button)findViewById(R.id.button_seven);
        btnNum8 = (Button)findViewById(R.id.button_eight);
        btnNum9 = (Button)findViewById(R.id.button_nine);

        btnClear = (Button)findViewById(R.id.button_clear);
        btnParentheses = (Button)findViewById(R.id.button_parentheses);
        btnPercent = (Button)findViewById(R.id.button_percent);
        btnDivision = (Button)findViewById(R.id.button_division);
        btnMul = (Button)findViewById(R.id.button_multiplication);
        btnSub = (Button)findViewById(R.id.button_subtraction);
        btnAdd = (Button)findViewById(R.id.button_addition);
        btnEqual = (Button)findViewById(R.id.button_equal);
        btnDot = (Button)findViewById(R.id.button_dot);
        tvInputNumbers = (TextView)findViewById(R.id.textView_input_numbers);
    }

    private void setOnClickListener() {
        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);

        btnClear.setOnClickListener(this);
        btnParentheses.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnDot.setOnClickListener(this);
    }

    private void setOnTouchListener() {
        btnNum0.setOnTouchListener(this);
        btnNum1.setOnTouchListener(this);
        btnNum2.setOnTouchListener(this);
        btnNum3.setOnTouchListener(this);
        btnNum4.setOnTouchListener(this);
        btnNum5.setOnTouchListener(this);
        btnNum6.setOnTouchListener(this);
        btnNum7.setOnTouchListener(this);
        btnNum8.setOnTouchListener(this);
        btnNum9.setOnTouchListener(this);

        btnClear.setOnTouchListener(this);
        btnParentheses.setOnTouchListener(this);
        btnPercent.setOnTouchListener(this);
        btnDivision.setOnTouchListener(this);
        btnMul.setOnTouchListener(this);
        btnSub.setOnTouchListener(this);
        btnAdd.setOnTouchListener(this);
        //왜 "="이 빠졌는지 고민할 것
    }

    @Override
    //addNumber()
    //addOperand()
    //addDot()
    //addParentesis()
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zero:
                if(addNumber("0")) equalClicked = false;
                break;
            case R.id.button_one :
                if(addNumber("1")) equalClicked = false;
                break;
            case R.id.button_two :
                if(addNumber("2")) equalClicked = false;
                break;
            case R.id.button_three :
                if(addNumber("3")) equalClicked = false;
                break;
            case R.id.button_four :
                if(addNumber("4")) equalClicked = false;
                break;
            case R.id.button_five :
                if(addNumber("5")) equalClicked = false;
                break;
            case R.id.button_six :
                if(addNumber("6")) equalClicked = false;
                break;
            case R.id.button_seven :
                if(addNumber("7")) equalClicked = false;
                break;
            case R.id.button_eight :
                if(addNumber("8")) equalClicked = false;
                break;
            //9번 버튼이 동작안했던 것은 여기에 9번에 대한 이벤트 처리가 빠졌기 때문이다.
            case R.id.button_nine :
                if(addNumber("9")) equalClicked = false;
                break;
            case R.id.button_addition :
                if(addOperand("+")) equalClicked = false;
                break;
            case R.id.button_subtraction :
                if(addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplication :
                Log.d("text","addOperand('x')메서드를 호출한다.");
                if(addOperand("x")) equalClicked = false;
                break;
                //\u00F7은 DIVISION 의 유니코드 문자다.
            case R.id.button_division :
                if(addOperand("\u00F7")) equalClicked = false;
                break;
            case R.id.button_percent :
                if(addOperand("%")) equalClicked = false;
                break;
            case R.id.button_dot :
                if(addDot()) equalClicked = false;
                break;
            case R.id.button_parentheses :
                if(addParenthesis()) equalClicked = false;
                break;
            case R.id.button_clear :
                tvInputNumbers.setText("");
                openParenthesis = 0;
                dotUsed = false;
                equalClicked = false;
                break;
            case R.id.button_equal :
                if(tvInputNumbers.getText().toString() != null && !tvInputNumbers.getText().toString().equals(""))
                    Log.d("text", "=이 정상적으로 동작한다.");
                    calculate(tvInputNumbers.getText().toString());
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN :
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP : {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addDot() {
        boolean done = false;
        if(tvInputNumbers.getText().length()==0) {
            tvInputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if(dotUsed == true) {

        } else if(defineLastCharacter(tvInputNumbers.getText().charAt(tvInputNumbers.getText().length()-1)+"")==IS_OPERAND) {
            tvInputNumbers.setText(tvInputNumbers.getText()+"0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(tvInputNumbers.getText().charAt(tvInputNumbers.getText().length()-1)+"")==IS_NUMBER) {
            tvInputNumbers.setText(tvInputNumbers.getText()+".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private boolean addParenthesis() {
        boolean done = false;
        int operationLength = tvInputNumbers.getText().length();
        if(operationLength == 0) {
            tvInputNumbers.setText(tvInputNumbers.getText()+"(");
            dotUsed = false;
            openParenthesis++;
            done = true;
        } else if(openParenthesis > 0 && operationLength > 0) {
            String lastInput = tvInputNumbers.getText().charAt(operationLength -1)+"";
            switch(defineLastCharacter(lastInput)) {
                case IS_NUMBER :
                    tvInputNumbers.setText(tvInputNumbers.getText()+")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_OPERAND :
                    tvInputNumbers.setText(tvInputNumbers.getText()+"(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_OPEN_PARENTHESIS :
                    tvInputNumbers.setText(tvInputNumbers.getText()+"(");
                    done = true;
                    openParenthesis++;
                    dotUsed=false;
                    break;
                case IS_CLOSE_PARENTHESIS :
                    tvInputNumbers.setText(tvInputNumbers.getText()+")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
            }
        } else if(openParenthesis == 0 && operationLength > 0) {
            String lastInput = tvInputNumbers.getText().charAt(operationLength -1) + "";
            if(defineLastCharacter(lastInput) == IS_OPERAND) {
                tvInputNumbers.setText(tvInputNumbers.getText()+"(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            } else {
                tvInputNumbers.setText(tvInputNumbers.getText()+"x(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            }
        }
        return done;
    }

    private boolean addOperand(String operand) {
        Log.d("text","넘어온 값은: "+operand);
        boolean done = false;
        int operationLength = tvInputNumbers.getText().length();
        Log.d("text","operationLengh : "+operationLength);
        if(operationLength>0) {
            Log.d("text",(operationLength-1)+"번째 문자열을 가져와서 저장");
            String lastInput = tvInputNumbers.getText().charAt(operationLength -1) + "";
            Log.d("text","lastInput : " + lastInput);

            if((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%"))){
                Toast.makeText(getApplicationContext(), "addOperand() - 잘못된 포맷입니다.", Toast.LENGTH_LONG).show();
            } else if(operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER) {
                tvInputNumbers.setText(tvInputNumbers.getText()+operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done=true;
            } else if(!operand.equals("%")) {
                tvInputNumbers.setText(tvInputNumbers.getText()+operand);
                dotUsed = false;
                lastExpression = "";
                done = true;
            }
        } else {
            Toast.makeText(getApplicationContext(), "잘못된 포맷입니다.", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number) {
        boolean done=false;
        int operationLength = tvInputNumbers.getText().length();
        if(operationLength > 0) {
            String lastCharacter = tvInputNumbers.getText().charAt(operationLength-1) +"";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if(operationLength==1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0")) {
                tvInputNumbers.setText(number);
                done = true;
            } else if(lastCharacterState == IS_OPEN_PARENTHESIS) {
                tvInputNumbers.setText(tvInputNumbers.getText()+number);
                done=true;
            } else if(lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%")) {
                tvInputNumbers.setText(tvInputNumbers.getText() + "x" + number);
                done = true;
            } else if(lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT) {
                tvInputNumbers.setText(tvInputNumbers.getText()+number);
                done = true;
            }
        } else {
            tvInputNumbers.setText(tvInputNumbers.getText()+number);
            done=true;
        }
        return done;
    }

    private void calculate(String input) {
        Log.d("text","=을 통해서 넘어온 문자열 : " + input);
        String result = "";
        try {
            String temp = input;
            if(equalClicked) {
                temp = input+lastExpression;
            } else {
                Log.d("text","예상은 여기를 실행함");
                saveLastExpression(input);//마지막 표현식을 저장하는 메서드
            }
            result = scriptEngine.eval((temp.replaceAll("%", "/100").replaceAll("X","*").replaceAll("[^\\x00-\\x7F]","/"))).toString();
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8,BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "잘못된 포맷입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(result.equals("Infinity")) {
            Toast.makeText(getApplicationContext(), "0으로 나누는것은 허용되지 않습니다.", Toast.LENGTH_SHORT).show();
            tvInputNumbers.setText(input);
        } else if(result.contains(".")) {
            result = result.replaceAll("\\.?0$", "");
            tvInputNumbers.setText(result);
        }
    }

    private void saveLastExpression(String input) {
        Log.d("text","calculate()에서 넘어온 문자열 : " + input);
        Log.d("text",(input.length()-1)+"번째 문자열을 자정한다.");
        String lastOfExpression = input.charAt(input.length() -1) + "";
        Log.d("text", "lastOfExpression의 값 : " + lastOfExpression);
        if(input.length() > 1) {
            if(lastOfExpression.equals(")")) {
                lastExpression  = ")";
                int numberOfCloseParenthesis  = 1;
                for(int i=input.length()-2; i>=0; i--) {
                    if(numberOfCloseParenthesis  > 0) {
                        String last = input.charAt(i)+"";
                        if(last.equals(")")) {
                            numberOfCloseParenthesis ++;
                        } else if(last.equals("")) {
                            numberOfCloseParenthesis --;
                        }
                        lastExpression  = last+lastExpression ;
                    } else if(defineLastCharacter(input.charAt(i)+"")==IS_OPERAND){
                        lastExpression  = input.charAt(i) + lastExpression ;
                        break;
                    } else {
                        lastExpression ="";
                    }
                }
            } else if(defineLastCharacter(lastOfExpression+"")==IS_NUMBER) {
                Log.d("text","예상은 여기를 실행함2)"+true);
                lastExpression  = lastOfExpression;
                for(int i=input.length()-1; i>=0; i--) {
                    String last = input.charAt(i) + "";
                    Log.d("text","last의 값은?: " + last);
                    if((defineLastCharacter(last) == IS_NUMBER)||(defineLastCharacter(last)==IS_DOT)){
                        lastExpression  = last + lastExpression ;
                        Log.d("text","예상은 여기를 실행함3)" + lastExpression);
                    } else if(defineLastCharacter(last) == IS_OPERAND) {
                        lastExpression  = last+lastExpression ;
                        Log.d("text","예상은 여기를 실행함3)" + lastExpression);
                        break;
                    }
                    if(i==0) {
                        lastExpression  = "";
                        Log.d("text","if(i==0) lastExpression: " + lastExpression);
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter) {
        Log.d("text", "saveLastExpression()에서 넘어온 값: " + lastCharacter);
        try {
            Integer.parseInt(lastCharacter);
            Log.d("text","IS_NUMBER반환");
            return IS_NUMBER;
        } catch (NumberFormatException e) {}
        if((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%"))) {
            Log.d("text", "IS_OPERAND반환");
            return IS_OPERAND;
        }
        if(lastCharacter.equals("(")) {
            Log.d("text", "( = IS_OPEN_PARENTHESIS반환");
            return IS_OPEN_PARENTHESIS;

        }
        if(lastCharacter.equals(")")) {
            Log.d("text", ") = IS_CLOSE_PARENTHESIS반환");
            return IS_CLOSE_PARENTHESIS;
        }
        if(lastCharacter.equals(".")) {
            Log.d("text", "IS_DOT반환");
            return IS_DOT;
        }
        return -1;
    }
}