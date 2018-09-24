package bean;


import utils.CalculateUtil;

import java.util.Random;

public class Operation {

    Random randomer = new Random();
    char[] cSigns={'+','-','×','÷'};
    CalculateUtil ca = new CalculateUtil();
    public String generateQuestion(Expression e1,Expression e2)
    {
        char op = cSigns[randomer.nextInt(4)];

        //判断是否添加括号
        if(randomer.nextBoolean())
        {
            return addBracket(e1,e2,op);
        }
        return getStringExpression(e1)+" "+op+" "+getStringExpression(e2);
    }
    //添加括号
    public String addBracket(Expression e1,Expression e2,char op)
    {
//        //变换 / 号
//        if(op=='/')
//        {
//           op='÷';
//        }
//        if(op=='*'){
//            op='×';
//        }
        int iModule;
        //先确定模式，再添加括号
        if(e1.op=='#'&&e2.op=='#')
        {
            iModule=0;
        }
        else if(e1.op!='#'&&e2.op=='#')
        {
            iModule=1;
        }
        else if(e1.op=='#'&&e2.op!='#')
        {
            iModule=2;
        }
        else
        {
            iModule=3;
        }
        switch(iModule)
        {
            case 0://两个操作数
                return "( "+e1.op1+" "+op+" "+e2.op1+" )";
            case 1://三个操作数1
                if(randomer.nextBoolean())
                    return  "( "+e1.op1+" "+e1.op+" "+e1.op2+" )"+" "+op+" "+e2.op1;
                else
                    return e1.op1+" "+e1.op+" ( "+e1.op2+" "+op+" "+e2.op1+" )";
            case 2://三个操作数2
                if(randomer.nextBoolean())
                    return  "( "+e1.op1+" "+op+" "+e2.op1+" )"+" "+e2.op+" "+e2.op2;
                else
                    return e1.op1+" "+op+" ( "+e2.op1+" "+e2.op+" "+e2.op2+" )";
            case 3://四个操作数
                /**
                 * 模式0-2 添加1个括号，包含两个操作数
                 * 模式3 添加1个括号，包含3个操作数
                 */

                if(iModule==0)
                {
                    return  "( "+e1.op1+" "+e1.op+" "+e1.op2+" )"+" "+op+" "+e2.op1+" "+e2.op+" "+e2.op2;
                }
                else if(iModule==1)
                {
                    return  e1.op1+" "+e1.op+" "+e1.op2+" "+op+" ( "+e2.op1+" "+e2.op+" "+e2.op2+" )";
                }
                else if(iModule==2)
                {
                    return  e1.op1+" "+e1.op+" ( "+e1.op2+" "+op+" "+e2.op1+" ) "+e2.op+" "+e2.op2;
                }
                else if(iModule==3)
                {
                    if(randomer.nextBoolean())
                    {
                        return "( "+e1.op1+" "+e1.op+" "+e1.op2+" "+op+" "+e2.op1+" ) "+e2.op+" "+e2.op2;
                    }
                    else
                    {
                        return e1.op1+" "+e1.op+" ( "+e1.op2+" "+op+" "+e2.op1+" "+e2.op+" "+e2.op2+" )";
                    }

                }
                break;


                default:
                    System.out.println("没有添加括号");

        }

        return "";
    }
    //判断对象对应的表达式是否合理
    ///////////////////////////////////////////////////////////////////////////////
    boolean isRight(Expression e) {
        if(e.op=='#')
        {
            return true;
        }
        //不能为负数。op1<op2的时候要交换数据
        if(e.op=='-')
        {
            if(CalculateUtil.subtract(e.op1,e.op2).contains("-"))
            {
                swaapOperand(e);
            }
        }
        //因为没有生成0的情况，所以不考虑除
        //但是两个e的时候要考虑
        return false;
    }
    ///////////////////////////////////////////////////
    //交换操作数位置
    public void swaapOperand(Expression e)
    {
        String op3 = e.op1;
        e.op1 = e.op2;
        e.op2 = op3;
    }
    //获取表达式对象对应的式子
    public String getStringExpression(Expression e) {
        if(e.op=='#')
        {
            return e.op1;
        }
        else
        {
            return e.op1+" "+e.op+" "+e.op2;
        }
    }
    //生成字符串类型操作数
    public String generateOperand()
    {
        String sOpreand="";

        int iFlag;
        switch(iFlag=randomer.nextInt(3)) {
            case 0://生成带分数
                sOpreand = (randomer.nextInt(10)+1)+"’"+generatePrimes();
                break;
            case 1:
                sOpreand = generatePrimes();
                break;
            case 2:
                sOpreand =String.valueOf(randomer.nextInt(10)+1);
                break;
        }
        return sOpreand;
    }

    //生成分数
    public String generatePrimes()
    {
        //分子最大为8
        int iNumerator=randomer.nextInt(10)+1;
        int iDenominator=iNumerator+randomer.nextInt(10)+1;

        //保证两个数互质
        if(iNumerator==0)
        {
            //分子为0，分母直接使用
            return iNumerator+"/"+iDenominator;
        }
        int comDivisor = getComDivisor(iNumerator, iDenominator);
        iNumerator/=comDivisor;
        iDenominator/=comDivisor;

        return iNumerator+"/"+iDenominator;
    }
    //求最大公约数
    public int getComDivisor(int a,int b)
    {
        int i=1;
        int comDivisor=1;
        int c = a;
        if(c<b)
        {
            c=b;
        }
        while(i<=c)
        {
            if(a%i==0&b%i==0)
            {
                comDivisor=i;
            }
            i++;
        }
        return comDivisor;
    }

    public boolean compareValue(String val1,String val2)
    {
        //自然数&自然数

        //真分数与真分数

        //带分数与带分数

        //自然数&带分数

        //自然数与真分数

        //真分数与带分数


        return  false;
    }
}
