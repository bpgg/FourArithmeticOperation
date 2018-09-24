package bean;

import java.util.Random;

public class Expression {
    String op1="";
    String op2="";
    char op='#';
    Random randomer = new Random();
    boolean has2Operand = false;
    char[] cSigns={'+','-','×','÷'};
    Operation oper = new Operation();

    public Expression() {
        if(has2Operand=randomer.nextBoolean())
        {
            //初始化生成的操作数
            this.op1 = oper.generateOperand();
            //规定第二个操作数不为0

            this.op=cSigns[randomer.nextInt(4)];
            if(this.op=='-')
            {
                this.op2=oper.generateOperand();
            }
            else
            {
                this.op2 =oper.generateOperand();
            }

//            if(this.op=='/'){
//                this.op='÷';
//            }
//            if(this.op=='*'){
//                this.op='×';
//                System.out.println("转换乘号-子表达式");
//            }
        }
        else
        {
            this.op1 = oper.generateOperand();
        }
    }

    public static void main(String[] args) {
        Operation oper = new Operation();
        int i=0;
        Expression e;
        while (i<100)
        {
            e=new Expression();
            System.out.println(oper.getStringExpression(e));
            i++;
        }
        System.out.println();
    }

}
