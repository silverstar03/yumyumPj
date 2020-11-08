//결제관리프로그램
//고칠점: 결제취소도 만들기
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pay extends JFrame implements ActionListener{
	private JLabel title_label;
	
	private JPanel menu_list; //주문한 메뉴들이 나오는 패널
	private JPanel payPanel; //결제하는 패널
//	private JPanel cashPay; //결제하는 패널
//	private JPanel cardPay;
	private JPanel money; //총금액,받을금액,받은 금액만 보여주는 패널
	private JPanel numbers; //숫자판 나오는 패널
	private JPanel compute; //계산하는 패널(계산기) + 금액 보여주는 전체 패널
	
	//텍스트필드
	private JTextField show_money;
	private JTextField giveMoneytf; //받을 돈
	private JTextField getMoneytf; //받은 돈
	private JTextField balancetf; //거스름돈
	
	//번호판을 구성하는 버튼들
	private JButton num00;
	private JButton num0;
	private JButton num1;
	private JButton num2;
	private JButton num3;
	private JButton num4;
	private JButton num5;
	private JButton num6;
	private JButton num7;
	private JButton num8;
	private JButton num9;
	private JButton clear;
	
	//현금결제
	private JButton cashBtn;
	//카드결제
	private JButton cardBtn;
	private JButton GoPay; //최종 결제 버튼
	
	private JLabel payLabel; //현금결제인지 카드결제인지 알려주는
	private JLabel total_price; //총금액 = OrderMenu에서 받아온 금액
	private JLabel giveMoneyL; //받을 돈
	private JLabel getMoneyL; //받은 돈
	private JLabel balanceL; //거스름돈
	private JLabel instalmentL; //할부 몇개월인지  
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pay();
	}
	
	public Pay() {
		setTitle("계산하기");
		setSize(936,652);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //창이 가운데에서 실행
		setResizable(false); //창크기 조절 X
		setLayout(null); //자유롭게 위치 조정 가능
		
		title_label=new JLabel("결제하기");
		title_label.setFont(new Font("나눔바른고딕",Font.PLAIN,30));
		title_label.setBounds(400,2,187,60);
		add(title_label);
		
		//주문내역들 나오는 패널
		menu_list = new JPanel();
		menu_list.setBounds(20,63,439,276);
		menu_list.setLayout(null);
		menu_list.setBackground(Color.WHITE);
		add(menu_list);
		
		//결제 내역 미리 알려주는 패널
		money = new JPanel();
		money.setBounds(22,348,430,231);
		money.setBackground(Color.WHITE);
		money.setLayout(null);
		
		total_price = new JLabel("총 금액: ");
		total_price.setBounds(14, 35, 65, 34);
		money.add(total_price);
		add(money);
		
		
		//결제번호판 나오는 패널
		compute=new JPanel();
		compute.setLayout(null);
		compute.setBounds(463,292,439,307);
		
		show_money=new JTextField("");
		show_money.setColumns(10);
		show_money.setBounds(14,12,411,36);
		compute.add(show_money);
		
		numbers=new JPanel();
		numbers.setBounds(14,60,269,235);
		compute.add(numbers);
		numbers.setLayout(new GridLayout(4,3));
		num7=new JButton("7");
		num8=new JButton("8");
		num9=new JButton("9");
		num4=new JButton("4");
		num5=new JButton("5");
		num6=new JButton("6");
		num1=new JButton("1");
		num2=new JButton("2");
		num3=new JButton("3");
		num0=new JButton("0");
		num00=new JButton("00");
		clear = new JButton("C");
		
		numbers.add(num7); numbers.add(num8); numbers.add(num9); numbers.add(num4); 
		numbers.add(num5); numbers.add(num6);numbers.add(num1); numbers.add(num2);
		numbers.add(num3); numbers.add(num0);numbers.add(num00); numbers.add(clear);
		
		//현금결제 버튼
		cashBtn = new JButton("현금결제");
		cardBtn = new JButton("카드결제");
		cashBtn.setBounds(297,60,128,114);
		cardBtn.setBounds(297, 186, 128, 109);
		
		compute.add(cardBtn); compute.add(cashBtn);
		add(compute);
		
		payPanel = new JPanel();
		payPanel.setBounds(465,63,430,223);
		payPanel.setBackground(Color.WHITE);
		payPanel.setLayout(null);
		
		payLabel= new JLabel("[결제창]");
		payLabel.setBounds(14,1,424,44);
		payPanel.add(payLabel);
		
		giveMoneyL = new JLabel("");
		payPanel.add(giveMoneyL);
		//30000원은 OrderMenu에서 받아와서 저장하기
		//문제점 발생 : 나오지 않는다!!!
		giveMoneytf = new JTextField(8);
		giveMoneytf.setLocation(83,39);
		giveMoneytf.setText("30000");
		giveMoneytf.setVisible(false);
		payPanel.add(giveMoneytf);
		
		getMoneyL = new JLabel("");
		getMoneyL.setBounds(64, 94, 83, 30);
		payPanel.add(getMoneyL);
		
		balanceL = new JLabel("");
		balanceL.setBounds(64, 149, 83, 30);
		payPanel.add(balanceL);
		
		GoPay = new JButton("결제");
		GoPay.setBounds(290, 42, 110, 137);
		GoPay.setVisible(false);
		payPanel.add(GoPay);
		
		instalmentL = new JLabel("");
		instalmentL.setBounds(64, 110,83,30);
		payPanel.add(instalmentL);
		
		add(payPanel);
		
		
		cashBtn.addActionListener(this);
		cardBtn.addActionListener(this);
		GoPay.addActionListener(new GoPayListener());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cashBtn) {
			//초기화역할
//			payLabel.revalidate();
//			payLabel.repaint();
			
			//현금결제가 눌리면 현금결제창 뜨기
			payLabel.setText("[현금결제]");
			giveMoneyL.setText("받을 금액: ");
			giveMoneyL.setBounds(64, 42, 83, 30);
			//문제발생!! 안나와!!
			//giveMoneytf.setVisible(true);
			getMoneyL.setText("받은 금액 : ");
			balanceL.setText("거스름돈: ");
			GoPay.setVisible(true);
		}else if(e.getSource()==cardBtn) {
			//초기화 역할
//			payLabel.revalidate();
//			payLabel.repaint();
			//카드 결제가 눌리면 카드 결제창 뜨기
			payLabel.setText("[카드결제]");
			giveMoneyL.setBounds(64, 65, 83, 30);
			giveMoneyL.setText("받을 금액: ");
			instalmentL.setText("할부: ");
			GoPay.setVisible(true);
		}
	}
	
	class GoPayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==GoPay) {
				new Table_main();
				setVisible(false);
			}
		}
		
	}
}
