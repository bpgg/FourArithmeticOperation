package test;


import bean.Expression;
import bean.Node;
import bean.Operation;
import utils.StringUtil;
import utils.TreeUtil;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Expression e1 = new Expression();
        Expression e2 = new Expression();
        Operation oper = new Operation();
        String sQuestion =   oper.generateQuestion(e1,e2);
        int index = 0;
        int iArea=10;
        String[] strs =new String[iArea];
        Node[] nodes = new Node[iArea];
        String[] qs = new String [iArea];
        while(index<iArea)
        {
            qs[index]=oper.generateQuestion(new Expression(),new Expression());
            strs[index] = StringUtil.InfixToPostfix(qs[index] );
            nodes[index] = TreeUtil.createTree(strs[index]);   //生成树
          //  System.out.println(TreeUtil.calculate(nodes[index])); //计算结果
         //   System.out.println(qs[index]+ " = "+TreeUtil.calculate(nodes[index]));
            index++;


        }
        System.out.println("ending");
    }
}
