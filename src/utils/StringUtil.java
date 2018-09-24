package utils;

import constant.Constant;

import java.util.Stack;

public class StringUtil {

    private static Stack<String> mStack = new Stack<>();
    private static StringBuilder mExp = new StringBuilder();

    public static String InfixToPostfix(String expression) {


        String str[] = expression.split("\\s+");

        for (String  s : str) {

            //四个操作符
            if (isOperator(s)) {
                handleOperator(s);
                continue;
            }
            //左括号，入栈
            if (s.equals("(")) {
                mStack.push(s);
                continue;
            }

            //右括号，弹出并输出，直到遇到左括号，左括号也弹出
            if (s.equals(")")) {

                while (!mStack.peek().equals("(")) {

                    mExp.append(mStack.pop());
                    mExp.append(" ");
                }
                mStack.pop();

                continue;
            }
            mExp.append(s);
            mExp.append(" ");


        }
        //栈中还有数据，直接出栈输出
        while (!mStack.empty()) {
            mExp.append(mStack.pop());
            mExp.append(" ");
        }

        return mExp.toString();


    }


    private static void handleOperator(String operator) {

        if (mStack.empty()) {
            mStack.push(operator);
            return;
        }
        //栈顶左括号，入栈
        if (mStack.peek().equals("(")) {
            mStack.push(operator);
            return;
        }
        //栈顶优先级比它低，入栈
        if (getPriority(mStack.peek()) < getPriority(operator)) {
            mStack.push(operator);
            return;
        }
        //高，弹出并输出，直到小于（不能等于）或者为空，再入栈
        while (!mStack.empty() && !mStack.peek().equals("(") && getPriority(mStack.peek()) >= getPriority(operator)) {

            mExp.append(mStack.pop());
            mExp.append(" ");
        }
        mStack.push(operator);

    }

    /**
     * 获取优先级，（），x, ÷ 的优先级高
     * @param operator 操作符号
     * @return 优先级
     */
    private static int getPriority(String operator) {
        if (operator.equals(Constant.DIVISION) || operator.equals(Constant.MULTIPLICATION) || operator.equals("(")) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *  判断是否为操作符
     * @param s 符号
     * @return 结果
     */
    public static boolean isOperator(String s) {
        boolean isOperator;
        switch (s) {
            case Constant.ADD:
            case Constant.SUBTRACT:
            case Constant.MULTIPLICATION:
            case Constant.DIVISION:
                isOperator = true;
                break;
            default:
                isOperator = false;
                break;

        }
        return isOperator;

    }


}
