package yumyum;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesCheck extends JFrame{
	
/*
 * totalPrice : 고객이 주문한 메뉴의 총 가격
 * sales : 총 가격이 누적되어 하루 매출이 담긴 변수
 * 
 * SalesCheck() : 생성자 메소드
 * CountSales() : 매출을 계산해주는 메소드
 * printSales() : 누적된 매출을 화면에 출력해주는 메소드
 */
	
	private JLabel title_l;	
	
	public SalesCheck() {
		setTitle("매출 확인");
		setSize(1100, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null); 	//자유롭게 위치 조정 가능
		setBackground(new Color(231, 230, 230));
		
		setting();
	}

	public void setting() {
		title_l = new JLabel("매출 확인");
		title_l.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 40));
		title_l.setBounds(448, 2, 187, 60);
		add(title_l);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SalesCheck();
	}

}
