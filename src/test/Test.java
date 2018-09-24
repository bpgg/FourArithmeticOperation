package test;

import bean.Node;
import bean.Result;
import exception.IllegalSituationException;
import utils.StringUtil;
import utils.TreeUtil;

import java.util.ArrayList;

import java.util.List;


public class Test {
    private static String[] mStrings = {"+", "-", "×", "÷"}; //注意一些大小写显示的问题 还有符号英文汉语不一样的问题
    private static  List<Result> mList = new ArrayList<>();
    private static Result mResult;
    public static void main(String[] args) {
        String expression = "";
        expression = "3 ÷ ( 1 - 2 )";
        String string1 = StringUtil.InfixToPostfix(expression);  //参数就是表达式
        Node tree1 = TreeUtil.createTree(string1);
        if (generate1(tree1).isIllegal()&&!isEquals(mResult)){
            mResult.setExpression(expression);
            mList.add(mResult);
        }

        expression = "3 + 2 + 1";
        String string2 = StringUtil.InfixToPostfix(expression);
        Node tree2 = TreeUtil.createTree(string2);
        if (generate1(tree2).isIllegal()&&!isEquals(mResult)){
            mResult.setExpression(expression);
            mList.add(mResult);
        }

        expression = "( 1 - 1 ) ÷ 3";
        String string3 = StringUtil.InfixToPostfix(expression);
        Node tree3 = TreeUtil.createTree(string3);
        if (generate1(tree3).isIllegal()&&!isEquals(mResult)){
            mResult.setExpression(expression);
            mList.add(mResult);
        }

        expression = "3 ÷ ( 1 - 1 )";
        String string4 = StringUtil.InfixToPostfix(expression);
        Node tree4 = TreeUtil.createTree(string4);
        if (generate1(tree4).isIllegal()&&!isEquals(mResult)){
            mResult.setExpression(expression);
            mList.add(mResult);
        }

        System.out.println("符合条件的有：");
        System.out.println("---------------------");
        for (Result result:mList) {
            System.out.println(result.getExpression() +" = "  +result.getResult());
        }
        System.out.println("---------------------");
    }

    private static boolean isEquals(Result tree){
        for (Result result:mList) {
            if (TreeUtil.isEqual(result.getTree(),tree.getTree())){
                return true;
            }

        }
        return false;
    }
    private static Result generate1(Node tree){
        try {
            String s = TreeUtil.calculate(tree);//生成树
            mResult = new Result(true,s,tree);//计算结果
            System.out.println(mResult.getResult());
        } catch (IllegalSituationException e) {
            //在这里去做另一个生成
            mResult = new Result(false,"",null);
            System.out.println("再去生成另一个 ");
            return mResult;
        }
        return mResult;
    }


    //产生负数和除号为 0 的情况都会抛出一个 不合法参数异常，直接catch 后再生成另一个。


}
