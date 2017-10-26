package com.javatest.ShuttingYard;

import java.util.*;

public class ShunttingYard {
	// Enumerated class for operators
    private enum Operator
    {
        ADD(2), SUBTRACT(2), MULTIPLY(4), DIVIDE(4);
        final int precedence;
        //Constructor
        private Operator(int p) { 
        	precedence = p; 
        }
    }
    
    //Map which contain all operators
    private static Map<String, Operator> ops = new HashMap<String, Operator>() {
    	{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    	}
    };
    
    //Operator precedence comparator
    private static boolean isHigerPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
    
    // Converts string with expression to "Reverse Polish Notation"
    // String characters must be space separated incorrect: (3+2) correct: ( 3 + 2 )
    public static String postfix(String expression)
    {
        StringBuilder output = new StringBuilder();
        Stack<String> stack  = new Stack<String>();

        for (String token : expression.split("\\s")) {
            // operator
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigerPrec(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

            // left parenthesis
            } else if (token.equals("(")) {
                stack.push(token);

            // right parenthesis
            } else if (token.equals(")")) {
                while ( ! stack.peek().equals("("))
                    output.append(stack.pop()).append(' ');
                stack.pop();

            // digit
            } else {
                output.append(token).append(' ');
            }
        }
        
        while ( ! stack.isEmpty())
            output.append(stack.pop()).append(' ');
        
        return output.toString();
    }
    
    // Calculates value of expression after converting it to "Reverse Polish Notation" 
    public static float result(String expression){
    	Stack<Float> stack = new Stack<Float>();
    	String postString = postfix(expression);
    	List<String> tokens = new ArrayList<String>(Arrays.asList(postString.split("\\s")));	
		for(String token: tokens){
			//If number
			if(!ops.containsKey(token)){
				stack.push(Float.parseFloat(token));
			//If operator
			}else{
				float num1 = stack.pop();
				float num2 = stack.pop();
				if (token.equals("+")) {
					stack.push(num1 + num2);
				} else if (token.equals("-")) {
					stack.push(num1 - num2);
				} else if (token.equals("*")) {
					stack.push(num1 * num2);
				} else {
					stack.push(num1 / num2);
				}
			}
		}	
		return stack.pop();
    }
}