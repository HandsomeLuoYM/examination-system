package main.com.examination.test;

import main.com.examination.dao.FileDao;
import main.com.examination.util.CreatUtil;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) throws IOException {
		CreatUtil c = new CreatUtil();
//		System.out.print("请输入生成式子的数目：");
//		Scanner scanner = new Scanner(System.in);
//		int num = scanner.nextInt();
		c.formulaNum(100,10);
//		FileDao.readFile(new File("D:\\idea_maven\\examination-system\\Exercises.txt"));
	}
}

