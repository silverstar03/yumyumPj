import java.awt.Color;

import javax.swing.JFrame;

public class SalesCheck extends JFrame{
	
/*
 * totalPrice : ���� �ֹ��� �޴��� �� ����
 * sales : �� ������ �����Ǿ� �Ϸ� ������ ��� ����
 * 
 * SalesCheck() : ������ �޼ҵ�
 * CountSales() : ������ ������ִ� �޼ҵ�
 * printSales() : ������ ������ ȭ�鿡 ������ִ� �޼ҵ�
 */
	
	
	public SalesCheck() {
		setTitle("���� Ȯ��");
		setSize(1100, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		setBackground(new Color(231, 230, 230));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SalesCheck();
	}

}
