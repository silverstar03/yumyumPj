package yumyum;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesCheck extends JFrame{
	
/*
 * totalPrice : ���� �ֹ��� �޴��� �� ����
 * sales : �� ������ �����Ǿ� �Ϸ� ������ ��� ����
 * 
 * SalesCheck() : ������ �޼ҵ�
 * CountSales() : ������ ������ִ� �޼ҵ�
 * printSales() : ������ ������ ȭ�鿡 ������ִ� �޼ҵ�
 */
	
	private JLabel title_l;	
	
	public SalesCheck() {
		setTitle("���� Ȯ��");
		setSize(1100, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		setBackground(new Color(231, 230, 230));
		
		setting();
	}

	public void setting() {
		title_l = new JLabel("���� Ȯ��");
		title_l.setFont(new Font("��ü�� ���� ��ü", Font.PLAIN, 40));
		title_l.setBounds(448, 2, 187, 60);
		add(title_l);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SalesCheck();
	}

}
