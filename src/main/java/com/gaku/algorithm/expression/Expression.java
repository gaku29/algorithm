package com.gaku.algorithm.expression;

import redis.clients.jedis.ScanResult;

import java.util.Stack;

/**
 * 算符优先法
 */
public class Expression {

    public static void main(String[] args) {
        Expression instance = new Expression();

        int ret  = instance.computeExpression("3+ 2 * 5 - 20 / 6");
        System.out.println(ret);


    }

    public int computeExpression(String infixExpression){
        Stack<Integer> operand = new Stack();
        Stack<Character> operator = new Stack<>();
        operator.push('#');
        infixExpression += '#';


        char cur = infixExpression.charAt(0);
        int i = 0;

        while (cur != '#' || operator.peek() != '#'){
            if (cur == ' ') {
                cur = infixExpression.charAt(++i);
                continue;
            }

            if (isOperand(cur)){

                StringBuilder sb = new StringBuilder();
                sb.append(cur);
                i++;
                while (isOperand(infixExpression.charAt(i))){
                    sb.append(infixExpression.charAt(i));
                    i++;
                }
                operand.push(Integer.valueOf(sb.toString()));
                cur = infixExpression.charAt(i);
            }else {
                switch (precede(operator.peek(), cur)){
                    case '<':
                        operator.push(cur);
                        cur = infixExpression.charAt(++i);
                        break;

                    case '=':
                        operator.pop();
                        cur = infixExpression.charAt(++i);
                        break;

                    case '>':
                        int temp = operand.pop();
                        int result = operand.pop();
                        operand.push(operate(result, temp, operator.pop()));
                        break;
                }

            }

        }

        return operand.peek();


    }

    private int operate(int result, int temp , char operator){
        switch (operator){
            case '+':
                return result + temp;
            case '-':
                return result - temp;

            case '*':
                return result * temp;

            case '/':
                return result / temp;

            default:
                throw new IllegalArgumentException("dd");
        }
    }

    private boolean isOperand(char cur) {

        if (cur >= '0' && cur <= '9'){
            return true;
        }else {
            return false;
        }


    }

    private char precede(char first, char last){
        String operator = "+-*/()#";
        char[][] level = {
            {'>','>','<','<','<','>','>'},
            {'>','>','<','<','<','>','>'},
            {'>','>','>','>','<','>','>'},
            {'>','>','>','>','<','>','>'},
            {'<','<','<','<','<','=',' '},
            {'>','>','>','>',' ','>','>'},
            {'<','<','<','<','<',' ','='}
        };

        int row = operator.indexOf(first);
        int column = operator.indexOf(last);
        return level[row][column];
    }
}
