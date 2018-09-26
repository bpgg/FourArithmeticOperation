package bean;




import java.util.Random;

public class Operation {

    private static Random randomer = new Random();
    private static char[] cSigns={'+','-','×','÷'};
    private static Expression e1;
    private  static Expression e2;
    public static String generateQuestion()
    {
        e1 = new Expression();
        e2 = new Expression();
        char op = cSigns[randomer.nextInt(4)];

        //判断是否添加括号
        if(randomer.nextBoolean())
        {
           // return addBracket(e1,e2,op);
        }
        return getStringExpression(e1)+" "+op+" "+getStringExpression(e2);
    }
    //添加括号
    public String addBracket(Expression e1,Expression e2,char op)
    {
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
        else if(e1.op=='#')
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

    //获取表达式对象对应的式子
    public static String getStringExpression(Expression e) {
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


        switch(randomer.nextInt(3)) {
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
    private String generatePrimes()
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
    private int getComDivisor(int a,int b)
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
}
