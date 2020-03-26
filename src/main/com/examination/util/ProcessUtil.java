package main.com.examination.util;

/**
 * @author Ming
 * @date 2020/3/26 - 22:28
 * @describe
 */
public class ProcessUtil {

    /**
     * 将答案按规范生成出来
     * @param numerator 分子
     * @param denominator 分母
     * @return
     */
    public static StringBuilder creatNum(int numerator, int denominator) {
        StringBuilder num = new StringBuilder();
        int gcdNum = gcd(numerator, denominator);
        numerator /= gcdNum;
        denominator /= gcdNum;
        if (numerator >= denominator) {
            //分子大于等于分母
            if (numerator % denominator == 0) {
                //结果为整数
                num.append(numerator / denominator + " ");
            } else {
                //结果为带分数
                int interger = numerator / denominator;
                numerator = numerator - (interger * denominator);
                num.append(interger + "’" + numerator + "/" + denominator + " ");
            }
        } else {
            //分子小于分母
            if (numerator == 0) {
                //分子小于0
                num.append(0 + " ");
            } else {
                //其他情况
                num.append(numerator + "/" + denominator + " ");
            }
        }
        return num;
    }

    /**
     * 求两数的最大公因数
     * @param num01 数字1
     * @param num02 数字2
     * @return 返回公因数
     */
    public static int gcd(int num01, int num02) {
        int num = 0;
        while (num02 != 0) {
            num = num01 % num02;
            num01 = num02;
            num02 = num;
        }
        return num01;
    }

    /**
     * 将式子中指定字符的所有位序存储起来
     * @param str 字符串
     * @param formula
     * @return
     */
    public static int[] charFind(String str, StringBuilder formula) {
        int[] indexs = new int[20];
        for (int i = 0; ; i++) {
            if (i == 0) {
                indexs[i] = formula.indexOf(str, 0);
                continue;
            }
            if (str.equals(" ") && (indexs[i - 1] == formula.length() - 1)) {
                break;
            }
            if (str.equals(" ") || str.equals("/")) {
                indexs[i] = formula.indexOf(str, indexs[i - 1] + 1);
            }
            if (str.equals("/") && (formula.length() - 1 - indexs[i] <= 4)) {
                break;
            }
        }
        return indexs;
    }

    /**
     * 找到刚好比某特定字符大的另一字符位序
     *
     * @param index  索引值
     * @param indexs 计算值
     * @return 返回字符索引
     */
    public static int indexFind(int index, int[] indexs) {
        int i;
        for (i = 0; i < indexs.length; i++) {
            if (indexs[i] > index) {
                break;
            }
        }
        return i;
    }

    /**
     * 将指定数字字符串转为数字值
     * @param formula
     * @param fromIndex 开始索引值
     * @param endIndex 结束索引值
     * @return
     */
    public static int changeNum(StringBuilder formula, int fromIndex, int endIndex) {
        int num = -1;
        //根据数字的位数进行转换
        int sum = 0, temp;
        for (int i = 1; i < (endIndex - fromIndex); i++) {
            temp = (int) Math.pow((double) 10, (double) (i - 1));
            num = (int) (formula.charAt(endIndex - i) - 48) * temp;
            sum += num;
        }
        return sum;
    }

    /**
     * 判断被减数、减数是否符合规范
     * @param numerator1 分子1
     * @param denominator1 分母1
     * @param numerator2 分子1
     * @param denominator2 分母2
     * @return false:被减数<减数 || true:被减数>减数
     */
    public static boolean judge(int numerator1, int denominator1, int numerator2, int denominator2) {
        int numerator = numerator1 * denominator2 - numerator2 * denominator1;
        if (numerator < 0) {
            return false;
        }
        return true;
    }


}
