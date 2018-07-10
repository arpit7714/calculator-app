package com.example.calculator;

import java.util.*;
import java.lang.*;

public class calculations{

    public static double calculateexpression(String expression){
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        if (!isExpressionValid(expression))
            return 0;

        String currentinteger ="";
        int i=0;
        while(i<expression.length()){
            if (Character.isDigit(expression.charAt(i))){
                currentinteger=""+expression.charAt(i);
                i++;
                while(i!=expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i)=='.'))
                {
                    currentinteger=currentinteger+expression.charAt(i);
                    i++;
                }
                operandStack.push(Double.parseDouble(currentinteger));
            }
            else{
                Character currentoperator=expression.charAt(i);
                Character lastoperator=(operatorStack.isEmpty() ? null : operatorStack.peek() );

                //if the precedence of the currentoperator is less
                if (lastoperator!=null && precedence(currentoperator)<=precedence(lastoperator)){
                    performarithmeticoperation(operandStack,operatorStack);
                }
                operatorStack.push(currentoperator);
                i++;
            }

        }

        while (!operatorStack.isEmpty()) {
            performarithmeticoperation(operandStack, operatorStack);
        }

        //    System.out.println(Arrays.toString(operandStack.toArray()));
        //    System.out.println(Arrays.toString(operatorStack.toArray()));

        return operandStack.pop();
    }

    public static void performarithmeticoperation(Stack<Double> operandStack, Stack<Character> operatorStack) {
        try {
            double value1 = operandStack.pop();
            double value2 = operandStack.pop();
            char operator = operatorStack.pop();

            double intermediateResult = arithmeticOperation(value1, value2, operator);
            operandStack.push(intermediateResult);
        } catch (EmptyStackException e) {
            System.out.println("Not a valid expression to evaluate");
            throw e;
        }
    }

    public static double arithmeticOperation(double value2, double value1, Character operator) {

        double result=0;

        switch (operator) {

            case '+':
                result = value1 + value2;
                break;

            case '-':
                result = value1 - value2;
                break;

            case '*':
                result = value1 * value2;
                break;

            case '/':
                result = value1 / value2;
                break;

            case '^':
                result = Math.pow(value1,value2);
                break;
        }
        return result;
    }
    public static int precedence(Character operator1){
        switch(operator1){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return 0;
    }


    public static boolean isExpressionValid(String expression){
        if (expression==" ")
            return false;
        else if ((!Character.isDigit(expression.charAt(0))) || (!Character.isDigit(expression.charAt(expression.length()-1))))
            return false;
        else{
            for(int i=0;i<expression.length();){
                if (!Character.isDigit(expression.charAt(i))){
                    if(!Character.isDigit(expression.charAt(i+1)))
                        return false;
                }
                i++;
            }
        }
        return true;
    }

}