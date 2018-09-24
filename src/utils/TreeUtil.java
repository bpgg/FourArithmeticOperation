package utils;

import bean.Node;
import constant.Constant;
import exception.IllegalSituationException;

import java.util.Stack;

public class TreeUtil {
    /**
     * 根据后缀表达式生成表达式树
     * @param expression 表达式
     * @return 根节点
     */
    public static Node createTree(String expression) {
           Node leftNode ;
           Node rightNode ;
        // 利用栈
        Stack<Node> stack = new Stack<>();

        String str[] = expression.split("\\s+");

        for (String s : str) {

            //一个操作符号对应两个子操作数
            if (StringUtil.isOperator(s)) {
                rightNode = stack.pop();
                leftNode = stack.pop();
                Node node = new Node(s,true);
                node.setLeftChild(leftNode);
                node.setRightChild(rightNode);
                stack.push(node);
                continue;
            }
            //不是操作符号就直接生成节点
            Node node = new Node(s);
            stack.push(node);

        }
        return stack.pop();
    }

    /**
     * 后续递归进行计算
     * @param node 表示树
     * @return 结果
     */
    public static String calculate(Node node) throws IllegalSituationException {
        if (!node.isOperate()) {
            return node.getData();
        }
        calculate(node.getLeftChild());
        calculate(node.getRightChild());
        return handleCalculate(node);

    }

    /**
     *  计算俩个子节点
     * @param node 节点
     * @return  结果
     */
    private static String handleCalculate(Node node) throws IllegalSituationException {
        String result ="";
        switch (node.getOperate()) {
            case Constant.ADD:
                result = CalculateUtil.add(node.getLeftChild().getData(),node.getRightChild().getData());
                break;
            case Constant.SUBTRACT:
                result = CalculateUtil.subtract(node.getLeftChild().getData(),node.getRightChild().getData());
                break;
            case Constant.MULTIPLICATION:
                result =CalculateUtil.multiplies(node.getLeftChild().getData(),node.getRightChild().getData());
                break;
            case Constant.DIVISION:
                result =  CalculateUtil.divide(node.getLeftChild().getData(),node.getRightChild().getData());
                break;
            default:
                result = "";
                break;


        }
        if (result!=null&&CalculateUtil.isNegative(result)){
            throw new IllegalSituationException("negative number !");
        }
        node.setData(result);

        return result;

    }

    /**
     * 根据表达式树的结构，比较两个表达式是否重复
     * 例如，23 + 45 = 和45 + 23 = 是重复的题目，
     * 6 × 8 = 和8 × 6 = 也是重复的题目。
     * 3+(2+1)和1+2+3这两个题目是重复的，由于+是左结合的，
     * 1+2+3等价于(1+2)+3，也就是3+(1+2)，也就是3+(2+1)。
     * 但是1+2+3和3+2+1是不重复的两道题，因为1+2+3等价于(1+2)+3，
     * 而3+2+1等价于(3+2)+1，它们之间不能通过有限次交换变成同一个题目。
     *
     * @param Tree1 表达式树 1
     * @param Tree2 表达式树 2
     * @return 是否一样
     */
    public static boolean isEqual(Node Tree1, Node Tree2) {
        //两个都为 null
        if (Tree1 == null && Tree2 == null) {
            return true;
        }
        //有一个且只有一个 null
        if (Tree1 == null || Tree2 == null) {
            return false;
        }

        //两个都不为 null

        //值不等 或者 操作符不等
        if (!Tree1.getData().equals(Tree2.getData()) || !Tree1.getOperate().equals(Tree2.getOperate())) {
            return false;
        }

        //值 和 操作符都相等
        //可交换，不交换比较和交换比较
        if (isExchangedOperator(Tree1.getOperate(), Tree2.getOperate())) {
            return isEqual(Tree1.getLeftChild(), Tree2.getLeftChild()) && isEqual(Tree1.getRightChild(), Tree2.getRightChild())
                    || isEqual(Tree1.getLeftChild(), Tree2.getRightChild()) && isEqual(Tree1.getRightChild(), Tree2.getLeftChild());
        }

        //不可交换，不交换比较
        return isEqual(Tree1.getLeftChild(), Tree2.getLeftChild()) && isEqual(Tree1.getRightChild(), Tree2.getRightChild());


    }

    /**
     *  是否可以进行交换，即 x 和 + 满足交换律
     * @param operator1  操作数 1
     * @param operator2  操作数 2
     * @return 结果
     */
    private static boolean isExchangedOperator(String operator1, String operator2) {
        return (operator1.equals(Constant.ADD) || operator1.equals(Constant.MULTIPLICATION))
                &&
                (operator2.equals(Constant.ADD) || operator2.equals(Constant.MULTIPLICATION));
    }

}
