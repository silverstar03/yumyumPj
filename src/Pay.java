
//해야될것 
//1.결제취소 누르면 결제 취소되기 + 전체주문 취소
//1-2.계산
//2.결제 완료 누르면 매출 디비에 올라가기 =>now 사용
//3. 몇번 테이블인지
//4.복합계산 => 현금결제,카드 결제 완성 후 구현하기
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pay extends JFrame implements ActionListener{
	
	private JPanel mainP; //main패널
	private JPanel numbers; //계산기 부분 패널
	private JPanel payPanel; //결제창부분
	private JPanel money; //총합계,할인율,받을 금액,받은 금액만 나타내는 부분
	
	private JDialog payOk; //결제완료 다이얼로그
	//money패널
	private JTextField total_price_tf; //총합계
	private JTextField discount_tf; //할인금액
	private JTextField giveMoney_m_tf; //받을 금액
	private JTextField getMoney_m_tf; //받은 금액
	
	private JTextField show_money; //금액나오는
	private JTextField giveMoneytf; //받을돈
	private JTextField getMoneytf; //받은 돈
	private JTextField balancetf; //거스름돈
	private JTextField instalmenttf; //할부

	
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
	private JButton cardPay; //카드결제 
	private JButton cashPay; //현금결제
	
	
	//현금결제
	private JButton cashBtn;
	//카드결제
	private JButton cardBtn;
	//결제 확인 버튼 => 확인 누르면 창이 닫히고 테이블로 간다.
	private JButton okBtn;
	
	private JLabel titleL;
	private JLabel payLabel; //현금결제인지 카드결제인지 알려주는
	private JLabel total_price; //총금액 = OrderMenu에서 받아온 금액
	private JLabel giveMoneyL; //받을 돈
	private JLabel getMoneyL; //받은 돈
	private JLabel balanceL; //거스름돈
	private JLabel instalmentL; //할부 몇개월인지  
	private JLabel carding; //카드결제중
	private JLabel discount; //할인금액
	private JLabel giveMoney_ml;
	private JLabel getMoney_ml;
	private JLabel okSign;
	
	
	private String sum=""; //번호판에서 누른 가격
	private cardPaying cp;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pay();
	}
	
	public Pay() {
		setTitle("계산");
		setSize(936,652);
		setLocationRelativeTo(null); //창이 가운데에서 실행
		
		mainP=new JPanel();
		mainP.setLayout(null);
		titleL=new JLabel("결제하기");
		titleL.setFont(new Font("나눔바른고딕",Font.PLAIN,30));
		titleL.setBounds(400,2,187,60);
		mainP.add(titleL);
		
		//현상황만 알려주는 총금액,받을 금액,거스름돈 =>분할계산 할 경우
		money = new JPanel();
		money.setBounds(22, 348, 430, 231);
		money.setBackground(Color.WHITE);
		money.setLayout(null);

		total_price = new JLabel("총금액: ");
		total_price.setBounds(118,34,52,18);
		discount=new JLabel("할인금액: ");
		discount.setBounds(118, 79, 66, 18);
		giveMoney_ml = new JLabel("받을 금액: ");
		giveMoney_ml.setBounds(118, 127, 80, 18);
		getMoney_ml = new JLabel("받은 금액 : ");
		getMoney_ml.setBounds(118, 176, 80, 18);		
		total_price_tf = new JTextField(10); //총금액 필드
		total_price_tf.setBounds(220, 31, 116, 24);
		discount_tf = new JTextField("0",10); //할인금액 필드
		discount_tf.setBounds(220, 76, 116, 24);
		giveMoney_m_tf = new JTextField(10); //받을 돈 필드
		giveMoney_m_tf.setBounds(220, 124, 116, 24);
		getMoney_m_tf = new JTextField("0",10); //받은 돈 필드
		getMoney_m_tf.setBounds(220, 173, 116, 24);
		
		total_price_tf.setEditable(false);
		discount_tf.setEditable(false);
		giveMoney_m_tf.setEditable(false);
		getMoney_m_tf.setEditable(false);

		money.add(total_price);
		money.add(total_price_tf);
		money.add(discount);
		money.add(discount_tf);
		money.add(giveMoney_ml);
		money.add(giveMoney_m_tf);
		money.add(getMoney_ml);
		money.add(getMoney_m_tf);
		mainP.add(money);

		//주문내역 나오도록 
		
		//결제내역 알려주는 
		payPanel = new JPanel();
		payPanel.setBounds(483, 63, 420, 223);
		payPanel.setBackground(Color.WHITE);
		payPanel.setLayout(null);
		
		payLabel=new JLabel("[결제창]",JLabel.CENTER);
//		payLabel.setBounds(14, 1, 424, 44);
		payLabel.setBounds(14, 1, 100, 44);
		payLabel.setFont(new Font("나눔바른고딕",Font.PLAIN,17));
		
		//
		giveMoneyL = new JLabel("받을 금액: ");
		getMoneyL=new JLabel("");
		balanceL = new JLabel(" ");
		instalmentL=new JLabel("");
		
		//받을금액 보여주는 텍스트필드
		giveMoneytf = new JTextField(7);
		giveMoneytf.setEditable(false);
		giveMoneytf.setVisible(false);
		
		getMoneytf = new JTextField(7);
		getMoneytf.setEditable(false);
		getMoneytf.setVisible(false);
		
		balancetf = new JTextField(7);
		balancetf.setEditable(false);
		balancetf.setVisible(false);
		
		instalmenttf = new JTextField(7);
		instalmenttf.setEditable(false);
		instalmenttf.setVisible(false);
		
		cashPay = new JButton("결제");
		cashPay.setBounds(290,42,110,137);
		cashPay.setVisible(false);
		
		cardPay = new JButton("결제");
		cardPay.setBounds(290,42,110,137);
		cardPay.setVisible(false);
		//현금결제 다이얼로그

		payPanel.add(payLabel); payPanel.add(cashPay); payPanel.add(cardPay);
		mainP.add(payPanel);
		
		
		//결제번호판 나오는 패널
		show_money = new JTextField(10);
		show_money.setBounds(485, 303, 411, 36);
		show_money.setEditable(false);
		mainP.add(show_money);
		calc();
		
		//현금결제버튼
		cashBtn = new JButton("현금 결제");
		cashBtn.setBounds(758,350,128,114);
		
		//카드결제 버튼
		cardBtn=new JButton("카드 결제");
		cardBtn.setBounds(758, 470, 128, 109);
		
		mainP.add(cashBtn); mainP.add(cardBtn);
		
		add(mainP);
	
		cashBtn.addActionListener(this);
		cardBtn.addActionListener(this);
		setResizable(false);
		setVisible(true);
	}
	
	public void calc() {
		numbers=new JPanel();
		numbers.setBounds(485, 348, 269, 235);
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
		
		mainP.add(numbers);
		
		num00.addActionListener(new numberListener());
		num0.addActionListener(new numberListener());
		num1.addActionListener(new numberListener());
		num2.addActionListener(new numberListener());
		num3.addActionListener(new numberListener());
		num4.addActionListener(new numberListener());
		num5.addActionListener(new numberListener());
		num6.addActionListener(new numberListener());
		num7.addActionListener(new numberListener());
		num8.addActionListener(new numberListener());
		num9.addActionListener(new numberListener());
		clear.addActionListener(new numberListener());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// 결제창 패널 제어
		//현금결제버튼을 눌렀을 경우 문제점 : 가끔 거스름돈 부분이 안나오다가 나옴 =>수정필요 =>수정한듯
		if(e.getSource() == cashBtn) {
			payLabel.setText("[현금결제]");
			instalmentL.setText("");
			instalmenttf.setVisible(false);
			cardPay.setVisible(false);

			giveMoneyL.setBounds(64, 42, 83, 30);
			
			//받을금액 tf
			//giveMoneytf.setText("30000");
			giveMoneytf.setBounds(148,42,99,30);
			giveMoneytf.setVisible(true);
			
			//받은 금액
			getMoneyL.setText("받은 금액: ");
			getMoneyL.setBounds(64, 94, 83, 30);
			
			getMoneytf.setBounds(148, 94, 99, 30);
			getMoneytf.setVisible(true);
			
			balanceL.setText("거스름돈: ");
			balanceL.setBounds(64, 149, 83, 30);
			balanceL.setVisible(true);
			balancetf.setBounds(148, 149, 99, 30);
			balancetf.setVisible(true);

			
			
			payPanel.add(giveMoneyL); 
			payPanel.add(giveMoneytf);
			payPanel.add(getMoneyL);
			payPanel.add(getMoneytf);
			payPanel.add(balanceL);
			payPanel.add(balancetf);
			cashPay.setVisible(true);
			
			cashPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					sum="";
					show_money.setText("");
					getMoneytf.setText("");
					instalmenttf.setText("");
					PayOk();
				}
			});
			
		}else if(e.getSource()==cardBtn) {
			//카드 결제를 눌렀을 경우 =>문제점: 카드로 갔다가 현금을 갔다가 다시 카드로 가면 쓰레드가 2번 실행됨
			payLabel.setText("[카드결제]");
			getMoneyL.setText("");
			getMoneytf.setVisible(false);
			balanceL.setText("");
			balancetf.setVisible(false);
			cashPay.setVisible(false); 
			
			giveMoneyL.setText("받을 금액: ");
			giveMoneyL.setBounds(64, 65, 83, 30);
			
			giveMoneytf.setBounds(148,65,99,30);
			giveMoneytf.setVisible(true);
		
			instalmentL.setText("할부: ");
			instalmentL.setBounds(64, 114, 83, 30);
			
			instalmenttf.setBounds(148, 114, 99, 30);
			instalmenttf.setVisible(true);
			
			
			payPanel.add(giveMoneyL); 
			payPanel.add(giveMoneytf);
			
			payPanel.add(instalmentL);
			payPanel.add(instalmenttf);
			
			cardPay.setVisible(true);
			cardPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//new PayTest2();
					cp=new cardPaying();
					PayOk();
				}
			});
		}
	} //ActionEvenet끝
	
	//결제 완료 창
	public void PayOk() {
		payOk = new JDialog(this,"결제완료",true);
		payOk.setSize(276,179);
		payOk.setLayout(null);
		payOk.setResizable(false);
		payOk.setLocationRelativeTo(null);
		okSign = new JLabel("결제가 완료되었습니다.",JLabel.CENTER);
		okSign.setFont(new Font("나눔바른고딕",Font.PLAIN,17));
		okSign.setBounds(40,40,200,23);
		okBtn=new JButton("확인");
		okBtn.setBounds(78,93,105,27);
		payOk.add(okSign); payOk.add(okBtn);
		//확인 버튼을 누르면 다이얼로그 창과 Pay닫히기
		okBtn.addActionListener(new okBtnListener());
		
		payOk.setLocationRelativeTo(null);
		payOk.setVisible(true);
	}
	
	class okBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent okay) {
			if(okay.getSource()==okBtn) {
				payOk.setVisible(false);
				setVisible(false);
			}
		}
	}
	class numberListener implements ActionListener{
		//번호를 누르면 번호판과 현금결제 - 받을 금액 에 출력이 된다.
		@Override
		public void actionPerformed(ActionEvent nn) {
			// TODO Auto-generated method stub
			if(nn.getSource()==num0) {
				sum+=num0.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num1) {
				sum+=num1.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num2) {
				sum+=num2.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num3) {
				sum+=num3.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num4) {
				sum+=num4.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num5) {
				sum+=num5.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num6) {
				sum+=num6.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num7) {
				sum+=num7.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num8) {
				sum+=num8.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num9) {
				sum+=num9.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==num00) {
				sum+=num00.getText();
				show_money.setText(sum);
				getMoneytf.setText(sum);
				instalmenttf.setText(sum);
			}else if(nn.getSource()==clear) {
				sum=" ";
				show_money.setText(" ");
				getMoneytf.setText("");
				instalmenttf.setText("");
			}
		}
	} //end of class
}
