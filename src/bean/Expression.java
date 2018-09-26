package bean;

import java.util.Random;

public class Expression {
    String op1;
    String op2="";
    char op='#';
    private Random randomer = new Random();

    private char[] cSigns={'+','-','×','÷'};
    private Operation oper = new Operation();

    public Expression() {
        if(randomer.nextBoolean())
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
        }
        else
        {
            this.op1 = oper.generateOperand();
        }
    }



}
