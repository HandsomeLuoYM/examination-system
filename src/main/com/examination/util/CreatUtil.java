package main.com.examination.util;

import main.com.examination.commons.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ming
 * @date 2020/3/26 - 23:13
 * @describe
 */
public class CreatUtil {

    //日志输出
    private static final Logger logger = Logger.getLogger("CreatUtil");
    List<StringBuilder> formula = new ArrayList<StringBuilder>();
    List<StringBuilder> copy = new ArrayList<StringBuilder>();
    List<StringBuilder> answer = new ArrayList<StringBuilder>();
    //备份式子，存储"分子/分母"结构的式子，便于结果计算
    StringBuilder extraCopy ;

    /**
     * 随机生成式子
     * 用List存储式子
     * */
    public StringBuilder creat(int maxNum) {
        StringBuilder formula = new StringBuilder();
        extraCopy = new StringBuilder(" ");
        //符号个数 （1,2,3）
        int signNum = (int)(Math.random()*3+1);
        creatNum(formula,maxNum);
        for(int i=0; i<signNum; i++) {
            creatSign(formula);
            creatNum(formula,maxNum);
        }
        formula.append(Operator.EQUAL_SIGN.getExpress() +" ");
        return formula;
    }

    /**
     * 随机生成操作数
     * 并将操作数存入list中
     * */
    public StringBuilder creatNum(StringBuilder formula,int maxNum) {
        int numerator,denominator;
        //判断是否在范围之内
        do {
            //随机生成分子
            numerator = (int)(Math.random()*8+1);
            //保证分母不等于0
            denominator=(int)(Math.random()*8+1);
        }while(!numRange(numerator, denominator,maxNum));
        //备份分子/分母
        extraCopy.append(numerator+"/"+denominator+" ");
        formula.append(ProcessUtil.creatNum(numerator, denominator));
        return formula;
    }

    /**
     *  随机生成符号
     * 并将符号存入list中
     * @param formula 符号
     * @return 返回符号
     */
    public StringBuilder creatSign(StringBuilder formula) {
        //符号类型（+ - * /）
        int signType = (int)(Math.random()*4+1) % 4;
        switch (signType){
            case 1 :
                formula.append(Operator.PLUS_SIGN.getExpress());
                extraCopy.append(Operator.PLUS_SIGN.getExpress());
                break;
            case 2 :
                formula.append(Operator.MINUS_SIGN.getExpress());
                extraCopy.append(Operator.MINUS_SIGN.getExpress());
                break;
            case 3 :
                formula.append(Operator.MULTIPLIED_SIGN.getExpress());
                extraCopy.append(Operator.MULTIPLIED_SIGN.getExpress());
                break;
            case 4 :
                formula.append(Operator.DIVISION_SIGN.getExpress());
                extraCopy.append(Operator.DIVISION_SIGN.getExpress());
                break;
            default:

        }
        extraCopy.append(" ");
        formula.append(" ");
        return formula;
    }

    /**
     * 设定随机生成式子的数目
     * @param num 生成的式子数目
     * @param maxNum 最大数
     */
    public void formulaNum(int num,int maxNum) {
        for(int i=0; formula.size()<num; i++) {
            formula.add(creat(maxNum));
            copy.add(extraCopy);
            StringBuilder an = new StringBuilder();
            an = copy.get(i);
            CalculateUtil.calculateFormula(an);
            //式子不符合规范（结果为负数）
            if(an.charAt(0)=='@') {
                formula.remove(formula.size()-1);
                continue;
            }
            answer.add(an);
        }
        for(StringBuilder temp:formula) {
            System.out.println(temp);
        }
        for(StringBuilder temp:answer) {
            System.out.println(temp);
        }
    }

    /**
     * 设定操作数的最大数值
     * @param numerator 分子
     * @param denominator 分母
     * @param maxNum 最大值
     * @return 是否超过最大值
     */
    public boolean numRange(int numerator, int denominator,int maxNum) {
        if((numerator/denominator)<maxNum) {
            return true;
        }else if((numerator/denominator)==maxNum) {
            if((numerator%denominator)==0) {
                return true;
            }
        }
        return false;
    }
}
