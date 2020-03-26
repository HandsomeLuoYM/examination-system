package main.com.examination.test;

import main.com.examination.util.CalculateUtil;
import main.com.examination.util.CreatUtil;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {

		CreatUtil creatUtil = new CreatUtil();
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入生成式子的操作数的最大范围：");
		int maxNum = scanner.nextInt();
		System.out.println(maxNum);
		creatUtil.formulaNum(10,maxNum);
	}
}

