package main.com.examination.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import main.com.examination.view.MyFrame2;

@SuppressWarnings("all")
public class MyFrame1{

	JFrame frame = new JFrame();

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	Container c;

	private JLabel label1 = new JLabel("欢迎来到四则运算的世界");
	private JLabel label2 = new JLabel("请输入生成式子的数目：");
	private JLabel label3 = new JLabel("请输入操作数的最大值：");

	private JButton button1 = new JButton("生成题目");
	private JButton button2 = new JButton("操作说明");

	private JTextField text1 = new JTextField(5);
	private JTextField text2 = new JTextField(5);

	private JDialog creatWarning() {
		JDialog warning = new JDialog(frame,"警告",true);
		warning.setSize(220, 120);
		warning.setResizable(false);//设置对话框大小不能改变
		warning.setLocationRelativeTo(null);//居中
		warning.setLayout(null);
		Container c = warning.getContentPane();
		JLabel label = new JLabel("请正确填写！！");
		label.setBounds(60, 15, 100, 30);
		c.add(label);
		JButton button1 = new JButton("确认");
		button1.setBounds(80, 50, 60, 25);
		c.add(button1);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				warning.dispose();
			}
		});
		return warning;
	}

	private JDialog creatExpression() {
		JDialog expression = new JDialog(frame,"说明",true);
		expression.setSize(300, 200);
		expression.setResizable(false);//设置对话框大小不能改变
		expression.setLocationRelativeTo(null);//居中
		expression.setLayout(new GridLayout(4,1));
		Container c = expression.getContentPane();
		JLabel label1 = new JLabel("1、本系统只支持10以内的四则运算");
		JLabel label2 = new JLabel("2、答案必须是化简后的结果");
		JLabel label3 = new JLabel("3、答案不可出现假分数");
		JLabel label4 = new JLabel("4、本系统支持统计错题");
		c.add(label1);
		c.add(label2);
		c.add(label3);
		c.add(label4);
		return expression;
	}

	public MyFrame1() {
		frame.setTitle("四则运算软件");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);//居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体，结束运行

		c =frame. getContentPane();//frame的容器
		c.setLayout(new GridLayout(3, 1));
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		panel1.setLayout(new GridBagLayout());//设置网格组布局
		panel2.setLayout(new GridBagLayout());//设置网格组布局
		panel3.setLayout(new GridBagLayout());//设置网格组布局
		init();
		frame.setVisible(true);
	}

	private void init() {
		GridBagConstraints gbc = new GridBagConstraints();
		label1.setFont(new Font("楷体",Font.BOLD,40));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel1.add(label1, gbc);

		label2.setFont(new Font("黑体",Font.BOLD,15));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 5, 0);
		panel2.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 5, 0);
		panel2.add(text1, gbc);

		label3.setFont(new Font("黑体",Font.BOLD,15));
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel2.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel2.add(text2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 0, 30);
		panel3.add(button2, gbc);

		gbc.gridx = 20;
		gbc.gridy = 10;
		panel3.add(button1, gbc);

		//生成式子按钮监听
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("[0-9]+?");
				String str1 = text1.getText();
				String str2 = text2.getText();
				if(pattern.matcher(str1).matches()&&pattern.matcher(str2).matches()) {
					int formulaNum = Integer.parseInt(str1);
					int maxNum = Integer.parseInt(str2);
					if(maxNum>10) {
						text2.setBorder(BorderFactory.createLineBorder(Color.red));
						JDialog warning = creatWarning();
						warning.setVisible(true);
					}
					else {
						frame.dispose();
						MyFrame2 f = new MyFrame2(formulaNum, maxNum);
					}
				}
				else {
					JDialog warning = creatWarning();
					warning.setVisible(true);
					if(!pattern.matcher(str1).matches()) {
						text1.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					else {
						text1.setBorder(BorderFactory.createLineBorder(Color.black));
					}
					if(!pattern.matcher(str2).matches()) {
						text2.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					else{
						text2.setBorder(BorderFactory.createLineBorder(Color.black));
					}
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog expression = creatExpression();
				expression.setVisible(true);
			}
		});
	}

}
