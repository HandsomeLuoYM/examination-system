package main.com.examination.util;

public class CalculateUtil {


	/**
	 * 加法运算
	 * @param numerator1 分子1
	 * @param denominator1 分母1
	 * @param numerator2 分子2
	 * @param denominator2 分母2
	 * @return 返回结果
	 */
	public static StringBuilder add(int numerator1,int denominator1,int numerator2,int denominator2) {
		int numerator,denominator;
		StringBuilder result = new StringBuilder();
		numerator = numerator1*denominator2+numerator2*denominator1;
		denominator = denominator1 * denominator2;
		if(numerator!=0) {
			//化简分子分母（除以最大公因数）
			int gcdNum = ProcessUtil.gcd(numerator,denominator);
			numerator /= gcdNum;
			denominator /= gcdNum;
		}

		result.append(numerator+"/"+denominator);
		return result;
	}

	/**
	 * 减法运算
	 * @param numerator1 分子1
	 * @param denominator1 分母1
	 * @param numerator2 分子2
	 * @param denominator2 分母2
	 * @return 返回计算结果
	 */
	public static StringBuilder minus(int numerator1,int denominator1,int numerator2,int denominator2) {
		int numerator,denominator;
		StringBuilder result = new StringBuilder();

		numerator = numerator1*denominator2-numerator2*denominator1;
		denominator = denominator1*denominator2;
		//化简分子分母（除以最大公因数）
		if(numerator!=0) {
			int gcdNum = ProcessUtil.gcd(numerator,denominator);
			numerator /= gcdNum;
			denominator /= gcdNum;
		}
		result.append(numerator+"/"+denominator);
		return result;
	}

	/**
	 * 乘法运算
	 * @param numerator1 分子1
	 * @param denominator1 分母1
	 * @param numerator2 分子2
	 * @param denominator2 分母2
	 * @return 返回计算结果
	 */
	public static StringBuilder multiply(int numerator1,int denominator1,int numerator2,int denominator2) {
		int numerator,denominator;
		StringBuilder result = new StringBuilder();
		//操作数有一个等于0的情况
		if(numerator1==0||numerator2==0) {
			result.append(0+"/"+1);
		}
		//操作数大于0的情况
		else {
			numerator = numerator1*numerator2;
			denominator = denominator1*denominator2;
			//化简分子分母（除以最大公因数）
			if(numerator!=0) {
				int gcdNum = ProcessUtil.gcd(numerator,denominator);
				numerator /= gcdNum;
				denominator /= gcdNum;
			}
			result.append(numerator+"/"+denominator);
		}

		return result;
	}

	/**
	 * 除法运算
	 * @param numerator1 分子1
	 * @param denominator1 分母1
	 * @param numerator2 分子2
	 * @param denominator2 分母2
	 * @return 返回计算结果
	 */
	public static StringBuilder divide(int numerator1,int denominator1,int numerator2,int denominator2) {
		int numerator,denominator;
		StringBuilder result = new StringBuilder();
		numerator = numerator1*denominator2;
		denominator = denominator1*numerator2;
		//化简分子分母（除以最大公因数）
		if(numerator!=0) {
			int gcdNum = ProcessUtil.gcd(numerator,denominator);
			numerator /= gcdNum;
			denominator /= gcdNum;
		}
		result.append(numerator+"/"+denominator);
		return result;
	}

	/*
	 * 对运算符号左右的两个数进行运算
	 * */
	public static StringBuilder calculate(int index,StringBuilder extraCopy) {
		char sign = extraCopy.charAt(index);//保存符号
		/**
		 * 这里可以想办法封装一下（字符转成相应数字的分子分母）
		 * 这里生成两个操作数的分子分母需要用到
		 * 下面答案转化需要用到
		 * 后面判断重复，需要判断数字是否相同，也同样要用到
		 * */
		int[] blanks = ProcessUtil.charFind(" ", extraCopy);//存储空格的位序，方便找到完整的操作数
		int[] backslash = ProcessUtil.charFind("/", extraCopy);//存储反斜杠的位序，方便找到操作数的分子分母
		int idBlank = ProcessUtil.indexFind(index, blanks);//第二个操作数的开头空格位序
		int idBack = ProcessUtil.indexFind(blanks[idBlank], backslash);//第二个操作数的反斜杠位序
		/*
		 * 分别存储两个操作数的分子分母
		 * 通过changeNum将数字字符转为数字
		 * */
		int numerator1 = ProcessUtil.changeNum(extraCopy, blanks[idBlank-2], backslash[idBack-1]);
		int denominator1 = ProcessUtil.changeNum(extraCopy, backslash[idBack-1], blanks[idBlank-1]);
		int numerator2 = ProcessUtil.changeNum(extraCopy, blanks[idBlank], backslash[idBack]);
		int denominator2 = ProcessUtil.changeNum(extraCopy, backslash[idBack], blanks[idBlank+1]);
		extraCopy.delete(blanks[idBlank-2]+1,blanks[idBlank+1]);//删除数字部分
		//根据符号进行相应的运算
		switch(sign){
			case '+':
				extraCopy.insert(blanks[idBlank-2]+1, add(numerator1,denominator1,numerator2,denominator2));
				break;
			case '-':
				if(!ProcessUtil.judge(numerator1, denominator1, numerator2, denominator2)) {
					extraCopy.insert(0, "@ ");//识别答案是否为负数，是的话在开头插入@作为识别
					break;
				}
				else{
					extraCopy.insert(blanks[idBlank-2]+1, minus(numerator1,denominator1,numerator2,denominator2));
					break;
				}
			case '*':
				extraCopy.insert(blanks[idBlank-2]+1, multiply(numerator1,denominator1,numerator2,denominator2));
				break;
			case '÷':
				if(numerator2 == 0) {
					extraCopy.insert(0, "@ ");//识别答案是否为负数，是的话在开头插入@作为识别
					break;
				}
				else{
					extraCopy.insert(blanks[idBlank-2]+1, divide(numerator1,denominator1,numerator2,denominator2));
					break;
				}
			default: break;
		}
		return extraCopy;
	}

	/**
	 * 按优先级进行运算（*  /  +  -）
	 * @param extraCopy copy副本
	 * @return 返回
	 */
	public static StringBuilder calculateFormula(StringBuilder extraCopy) {
		System.out.println(extraCopy);
		//记录符号的位序
		int index = -1;
		//计算式子
		while((extraCopy.indexOf("*")>=0)||(extraCopy.indexOf("÷")>=0)||(extraCopy.indexOf("+")>=0)||(extraCopy.indexOf("-")>=0)) {
			if((index = extraCopy.indexOf("*"))>=0) {
				CalculateUtil.calculate(index, extraCopy);
			}
			if((index = extraCopy.indexOf("÷"))>=0) {
				CalculateUtil.calculate(index, extraCopy);
				if(extraCopy.charAt(0)=='@') {
					break;
				}
			}
			if((index = extraCopy.indexOf("+"))>=0) {
				CalculateUtil.calculate(index, extraCopy);
			}
			if((index = extraCopy.indexOf("-"))>=0) {
				CalculateUtil.calculate(index, extraCopy);
				if(extraCopy.charAt(0)=='@') {
					break;
				}
			}
		}
		/**
		 * 生成分子分母可以的话封装一下
		 * */
		//如果运算结束后（式子正确），调整答案格式
		if(extraCopy.charAt(0)!='@') {
			int[] blanks01 = ProcessUtil.charFind(" ", extraCopy);//存储空格的位序
			int[] backslash01 = ProcessUtil.charFind("/", extraCopy);//存储反斜杠的位序
			int numerator = ProcessUtil.changeNum(extraCopy, blanks01[0], backslash01[0]);
			int denominator = ProcessUtil.changeNum(extraCopy, backslash01[0], blanks01[1]);
			extraCopy.setLength(0);//将原存储内容清空
			extraCopy.append(ProcessUtil.creatNum(numerator, denominator));//将答案换成标准格式
		}
		return extraCopy;
	}
}
