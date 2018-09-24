package utils;

import exception.IllegalSituationException;

public class CalculateUtil {

    //加法

    /**
     * 两个数的相加
     *
     * @param a 加数
     * @param b 加数
     * @return 结果
     */
    public static String add(String a, String b) {
        if (a.equals("0")||b.equals("0")){
            return a.equals("0")?b:a;
        }
        //两个都是自然数
        if (isNaturalNumber(a) && isNaturalNumber(b)) {
            return String.valueOf(Integer.valueOf(a) + Integer.valueOf(b));
        }

        //两个都是分数
        if (!isNaturalNumber(a) && !isNaturalNumber(b)) {
            String m = getRealFraction(a);
            String n = getRealFraction(b);

            return simplify(addFractionAndFraction(m, n));

        }
        //一个是自然数一个是分数
        if (isNaturalNumber(a) && !isNaturalNumber(b)) {
            String fraction = getRealFraction(b);
            return simplify(addFractionAndNatural(a, fraction));
        }

        //一个是分数一个是自然数
        if (!isNaturalNumber(a) && isNaturalNumber(b)) {
            String fraction = getRealFraction(a);
            return simplify(addFractionAndNatural(b, fraction));
        }

        return null;

    }

    /**
     * 分数加自然数 ，int[0] 为分子， int[1]  为分母
     *
     * @param natural  自然数
     * @param fraction 分数
     * @return 结果
     */
    private static String addFractionAndNatural(String natural, String fraction) {
        int nat = Integer.valueOf(natural);
        int[] numB = getDenominatorAndMolecule(fraction);

        int molecule = nat * numB[1] + numB[0];   //分子
        int denominator = numB[1];  // 分母
        return molecule + "/" + denominator;
    }

    /**
     * 两个分数相加 int[0] 为分子， int[1]  为分母
     *
     * @param a 加数
     * @param b 加数
     * @return 结果
     */
    private static String addFractionAndFraction(String a, String b) {
        int[] numA = getDenominatorAndMolecule(a);
        int[] numB = getDenominatorAndMolecule(b);

        int molecule = numA[0] * numB[1] + numB[0] * numA[1];
        int denominator = numA[1] * numB[1];

        return molecule + "/" + denominator;

    }

    //减法

    /**
     * 两个数的减法
     *
     * @param a 减数
     * @param b 被减数
     * @return 结果
     */
    public static String subtract(String a, String b) {
        if (a.equals("0")||b.equals("0")){
            return a.equals("0")? "-"+b:a;
        }
        //两个都是自然数
        if (isNaturalNumber(a) && isNaturalNumber(b)) {
            return String.valueOf(Integer.valueOf(a) - Integer.valueOf(b));
        }

        //两个都是分数
        if (!isNaturalNumber(a) && !isNaturalNumber(b)) {
            String m = getRealFraction(a);
            String n = getRealFraction(b);

            return simplify(subtractFractionAndFraction(m, n));

        }
        //一个是自然数一个是分数
        if (isNaturalNumber(a) && !isNaturalNumber(b)) {
            String fraction = getRealFraction(b);
            return simplify(subtractFractionAndFraction(naturalToFraction(a, fraction), fraction));
        }

        //一个是分数一个是自然数
        if (!isNaturalNumber(a) && isNaturalNumber(b)) {
            String fraction = getRealFraction(a);
            return simplify(subtractFractionAndFraction(fraction, naturalToFraction(b, fraction)));
        }

        return null;
    }

    /**
     * 自然数转分数 int[0] 为分子， int[1]  为分母
     *
     * @param natural  自然数
     * @param fraction 分数
     * @return 结果
     */
    private static String naturalToFraction(String natural, String fraction) {
        int[] numFrac = getDenominatorAndMolecule(fraction);
        int molecule = Integer.valueOf(natural) * numFrac[1]; //分子
        int denominator = numFrac[1];  // 分母
        return molecule + "/" + denominator;

    }

    /**
     * 分数减分数 int[0] 为分子， int[1]  为分母
     *
     * @param a 分数
     * @param b 分数
     * @return 结果
     */
    private static String subtractFractionAndFraction(String a, String b) {
        int[] numA = getDenominatorAndMolecule(a);
        int[] numB = getDenominatorAndMolecule(b);

        int molecule = numA[0] * numB[1] - numB[0] * numA[1]; //分子
        int denominator = numA[1] * numB[1];  //分母

        return molecule + "/" + denominator;
    }

    //乘法

    /**
     * 乘法
     *
     * @param a 乘数
     * @param b 乘数
     * @return 结果
     */
    public static String multiplies(String a, String b) {
        if (a.equals("0")||b.equals("0")){
            return String.valueOf(0);
        }
        //两个都是自然数
        if (isNaturalNumber(a) && isNaturalNumber(b)) {
            return String.valueOf(Integer.valueOf(a) * Integer.valueOf(b));
        }

        //两个都是分数
        if (!isNaturalNumber(a) && !isNaturalNumber(b)) {
            String m = getRealFraction(a);
            String n = getRealFraction(b);

            return simplify(multipliesFractionAndFraction(m, n));

        }
        //一个是自然数一个是分数
        if (isNaturalNumber(a) && !isNaturalNumber(b)) {
            String fraction = getRealFraction(b);
            return simplify(multipliesFractionAndFraction(naturalToFraction(a, fraction), fraction));
        }

        //一个是分数一个是自然数
        if (!isNaturalNumber(a) && isNaturalNumber(b)) {
            String fraction = getRealFraction(a);
            return simplify((multipliesFractionAndFraction(fraction, naturalToFraction(b, fraction))));
        }
        return null;
    }

    /**
     * 分数乘分数
     *
     * @param a 分数
     * @param b 分数
     * @return 结果
     */
    private static String multipliesFractionAndFraction(String a, String b) {
        int[] numA = getDenominatorAndMolecule(a);
        int[] numB = getDenominatorAndMolecule(b);

        int molecule = numA[0] * numB[0];  //分子
        int denominator = numA[1] * numB[1];  // 分母

        return molecule + "/" + denominator;
    }

    /**
     *  除法
     * @param a 除数
     * @param b 被除数
     * @return 结果
     */
    public static String divide(String a, String b) throws IllegalSituationException {
        if (a.equals("0")){
            return String.valueOf(0);
        }
        if (b.equals("0")){
            throw new IllegalSituationException("Argument b can’t be zero");
        }
        //两个都是自然数
        if (isNaturalNumber(a) && isNaturalNumber(b)) {
            return simplify(a + "/" + b);
        }

        //两个都是分数
        if (!isNaturalNumber(a) && !isNaturalNumber(b)) {
            String m = getRealFraction(a);
            String n = getRealFraction(b);
            return simplify(divideFractionAndFraction(m, n));

        }
        //一个是自然数一个是分数
        if (isNaturalNumber(a) && !isNaturalNumber(b)) {
            String fraction = getRealFraction(b);
            return simplify(divideFractionAndFraction(naturalToFraction(a, fraction), fraction));
        }

        //一个是分数一个是自然数
        if (!isNaturalNumber(a) && isNaturalNumber(b)) {
            String fraction = getRealFraction(a);
            return simplify(divideFractionAndFraction(fraction, naturalToFraction(b, fraction)));
        }

        return null;
    }

    /**
     * 分数除分数
     * @param a 除数
     * @param b 被除数
     * @return  结果
     */
    private static String divideFractionAndFraction(String a, String b) {
        int[] numA = getDenominatorAndMolecule(a);
        int[] numB = getDenominatorAndMolecule(b);

        int molecule = numA[0] * numB[1];
        int denominator = numA[1] * numB[0];

        return molecule + "/" + denominator;
    }


    /**
     * 获取分数的分子和分母
     *
     * @param a 分数
     * @return 结果，int[0] 为分子， int[1]  为分母
     */
    private static int[] getDenominatorAndMolecule(String a) {
        String numA[] = a.split("[/]");
        int numInt[] = new int[numA.length];
        for (int i = 0; i < numInt.length; i++) {
            numInt[i] = Integer.valueOf(numA[i]);

        }
        return numInt;
    }

    /**
     * 分数形式的转换
     *
     * @param s 分数
     * @return 结果
     */
    private static String getRealFraction(String s) {
        if (isFalseFraction(s)) { //1"1/2
            String numStr[] = s.split("[’/]");
            int numInt[] = new int[numStr.length];
            for (int i = 0; i < numInt.length; i++) {
                numInt[i] = Integer.valueOf(numStr[i]);

            }
            int denominator = numInt[0] * numInt[2] + numInt[1];
            return denominator + "/" + numStr[2];
        }

        return s;
    }

    /**
     * 判断是否为自然数
     *
     * @param s 数
     * @return 结果
     */
    private static boolean isNaturalNumber(String s) {
        return !s.contains("/");
    }

    /**
     * 判断是否为 假分数
     *
     * @param s 数
     * @return 结果
     */
    private static boolean isFalseFraction(String s) {
        return s.contains("’");
    }

    private static String simplify(String fraction){
        int[] num = getDenominatorAndMolecule(fraction);
        int molecule = num[0] ;
        int denominator = num[1] ;
        if (molecule==0){
            return String.valueOf(0);
        }
        if (molecule==denominator){
            return "1";
        }

        if (molecule<denominator){
            int i ;
            if ((i=gcd(molecule,denominator))==1){
                return molecule +"/" +denominator;
            }
            molecule = molecule/i;
            denominator = denominator/i;
            return molecule +"/" +denominator;
        }

        if (molecule>denominator){
           int i = gcd(molecule,denominator);
           molecule = molecule/i;
           denominator = denominator/i;

           if (denominator==1){
               return molecule+"";

           }

           return getWithFraction(molecule,denominator);

        }

        return null;

    }

    /**
     *  获取带分数 
     * @param molecule 分子
     * @param denominator 分母 
     * @return 结果
     */
    private static String getWithFraction(int molecule,int denominator){
        int withFraction = (molecule - (molecule%denominator)) / denominator;
        molecule = molecule%denominator;
        return withFraction+"’"+molecule+"/"+denominator;
    }

    /**
     *  求最大公约数，欧几里得方法
     * @param m 数1
     * @param n 数
     * @return 结果
     */
    private static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static boolean isNegative(String num){
        return num.contains("-");
    }
}
