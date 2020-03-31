package main.com.examination.util;

import main.com.examination.commons.Operator;
import main.com.examination.dao.FileDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author Ming
 * @date 2020/3/26 - 23:13
 * @describe
 */
@SuppressWarnings("all")
public class CreatUtil {

    //日志输出
    private static final Logger logger = Logger.getLogger("CreatUtil");
    List<StringBuilder> formula;
    //备份式子，存储"分子/分母"结构的式子，便于结果计算
    List<StringBuilder> answer;
    StringBuilder extraCopy ;

    public List<StringBuilder> getFormula() {
        return formula;
    }

    public List<StringBuilder> getAnswer() {
        return answer;
    }

    /**
     * 随机生成式子
     * 用List存储式子
     * @param maxNum 最大值
     * @return 返回改字符串
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
     * @param formula 字符串
     * @param maxNum 数
     * @return 返回参数的字符串
     * */
    public StringBuilder creatNum(StringBuilder formula,int maxNum) {
    	int numerator,denominator,type;
		type = (int)(Math.random()*2);
		if(type==0) {//生成整数
			do {
				numerator =(int)(Math.random()*10);
			}while(numerator > maxNum);
			extraCopy.append(numerator+"/"+1+" ");//备份分子/分母
			formula.append(numerator+" ");
		}
		else {
			do {
				numerator = (int)(Math.random()*10);//随机生成分子
				while((denominator=(int)(Math.random()*10))==0){;}//保证分母不等于0
			}while(!numRange(numerator, denominator,maxNum));
			extraCopy.append(numerator+"/"+denominator+" ");//备份分子/分母
			formula.append(ProcessUtil.creatNum(numerator, denominator));
		}
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
        int signType = (int)(Math.random()*4+1);
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
     * 设定随机生成一定数目的式子，并将式子和答案分别存在formula和answer中
     * @param num 生成的式子数目
     * @param maxNum 最大值
     */
    public void formulaNum(int num, int maxNum) throws IOException {
        //存放拆分完的式子
        List<List<String>> formulaLists = new ArrayList<List<String>>(num);
        formula = new ArrayList<StringBuilder>();
        answer = new ArrayList<StringBuilder>();
        //原始式子
        StringBuilder singleFormula;
        for(int i=0; formula.size()<num; i++) {
            formula.add(singleFormula = creat(maxNum));
            CalculateUtil.calculateFormula(extraCopy);
            //式子不符合规范（结果为负数）,并且查重
            if(extraCopy.charAt(0)=='@' || CheckUtil.judgeRepeat(singleFormula,formulaLists,extraCopy,answer)) {
                formula.remove(formula.size()-1);
                continue;
            }
            answer.add(extraCopy);
        }
        int i=0;
        FileDao.storageFile(formula,"Exercises.txt");
        FileDao.storageFile(answer,"Answers.txt");
        for(StringBuilder temp:formula) {
            System.out.println((i+1)+"、"+temp+answer.get(i));
            i++;
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
