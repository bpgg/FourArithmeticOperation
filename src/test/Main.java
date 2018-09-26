package test;


import bean.Expression;
import bean.Node;
import bean.Operation;
import bean.Result;
import exception.IllegalSituationException;
import utils.StringUtil;
import utils.TreeUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String[] mStrings = {"+", "-", "×", "÷"}; //注意一些大小写显示的问题 还有符号英文汉语不一样的问题
    private static List<Result> mList = new ArrayList<>();
    private static Result mResult;
    private  static int iQuestions=500;
    private  static  int iRange=10;
    public static void main(String[] args) {
//        doWork();
//        if(args[0]=="-n"){
//            iQuestions=Integer.valueOf(args[1]);
//        }
//        if(args[0]=="-r"){
//            iRange = Integer.valueOf(args[1]);
//        }
        System.out.println(System.currentTimeMillis());
        while (mList.size()!=10000){

            String ex = Operation.generateQuestion();
           Node tree =  TreeUtil.createTree(StringUtil.InfixToPostfix(ex ));
            if(generate1(tree).isIllegal()&&!isEquals(mResult)){
                mResult.setExpression(ex);
                mList.add(mResult);
                //添加案到答案文件
            }



        }
        for (Result result:mList) {
            System.out.println(result.getExpression()+" ="+result.getResult());
        }
        System.out.println(System.currentTimeMillis());

    }


    public static void  doWork(){
        //表达对象
        int index = 0;
        //int iQuestions=500;
        String[] strs =new String[iQuestions];
        Node[] nodes = new Node[iQuestions];
        String[] qs = new String [iQuestions];

        //文件操作对象
        FileOutputStream outSTr1 ;
        FileOutputStream outSTr2 ;
        BufferedOutputStream Buff1;
        BufferedOutputStream Buff2;
        try{
            outSTr1 = new FileOutputStream(new File(".\\Exercises.txt"));
            outSTr2 = new FileOutputStream(new File(".\\Answers.txt"));
            Buff1 = new BufferedOutputStream(outSTr1);
            Buff2 = new BufferedOutputStream(outSTr2);
            long begin0 = System.currentTimeMillis();

            while(index<iQuestions)
            {
                //随机生成表达式子
                qs[index]=Operation.generateQuestion();
                //给txt文件写入换行要用 \r\n 或者是用 newLine
                qs[index]=(index+1)+"、 "+qs[index]+"\r\n";

                //将表达式写入文件
                Buff1.write(qs[index].getBytes());

                strs[index] = StringUtil.InfixToPostfix(qs[index] );
                try {
                    nodes[index] = TreeUtil.createTree(strs[index]);   //生成树

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                if(generate1(nodes[index]).isIllegal()&&!isEquals(mResult)){
                    mResult.setExpression(qs[index]);
                    mList.add(mResult);
                    System.out.println(mResult.getResult());
                    //添加答案到答案文件

                    String sAnswer = (index+1)+"、 "+mResult.getResult()+"\r\n";
                    Buff2.write(sAnswer.getBytes());
                    index++;
                }
            }
            Buff1.flush();
            Buff2.flush();
            Buff1.close();
            Buff2.close();
            long end0 = System.currentTimeMillis();
            System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
        }catch (Exception e) {
            System.out.println("出现异常:"+e.getMessage());
        }
    }

    //查重复
    private static boolean isEquals(Result tree){
        for (Result result:mList) {
            if (TreeUtil.isEqual(result.getTree(),tree.getTree())){
                return true;
            }

        }
        return false;
    }
    //生成结果
    private static Result generate1(Node tree){
        try {
            String s = TreeUtil.calculate(tree);//生成树
            mResult = new Result(true,s,tree);//计算结果
        } catch (IllegalSituationException e) {
            //在这里去做另一个生成
            mResult = new Result(false,"",null);

            return mResult;
        }
        return mResult;
    }


    //产生负数和除号为 0 的情况都会抛出一个 不合法参数异常，直接catch 后再生成另一个。
}
