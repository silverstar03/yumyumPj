
//�ؾߵɰ� 
//1.������� ������ ���� ��ҵǱ� + ��ü�ֹ� ���
//1-2.���
//2.���� �Ϸ� ������ ���� ��� �ö󰡱� =>now ���
//3. ��� ���̺�����
//4.���հ�� => ���ݰ���,ī�� ���� �ϼ� �� �����ϱ�
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
	
	private JPanel mainP; //main�г�
	private JPanel numbers; //���� �κ� �г�
	private JPanel payPanel; //����â�κ�
	private JPanel money; //���հ�,������,���� �ݾ�,���� �ݾ׸� ��Ÿ���� �κ�
	
	private JDialog payOk; //�����Ϸ� ���̾�α�
	//money�г�
	private JTextField total_price_tf; //���հ�
	private JTextField discount_tf; //���αݾ�
	private JTextField giveMoney_m_tf; //���� �ݾ�
	private JTextField getMoney_m_tf; //���� �ݾ�
	
	private JTextField show_money; //�ݾ׳�����
	private JTextField giveMoneytf; //������
	private JTextField getMoneytf; //���� ��
	private JTextField balancetf; //�Ž�����
	private JTextField instalmenttf; //�Һ�

	
	//��ȣ���� �����ϴ� ��ư��
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
	private JButton cardPay; //ī����� 
	private JButton cashPay; //���ݰ���
	
	
	//���ݰ���
	private JButton cashBtn;
	//ī�����
	private JButton cardBtn;
	//���� Ȯ�� ��ư => Ȯ�� ������ â�� ������ ���̺�� ����.
	private JButton okBtn;
	
	private JLabel titleL;
	private JLabel payLabel; //���ݰ������� ī��������� �˷��ִ�
	private JLabel total_price; //�ѱݾ� = OrderMenu���� �޾ƿ� �ݾ�
	private JLabel giveMoneyL; //���� ��
	private JLabel getMoneyL; //���� ��
	private JLabel balanceL; //�Ž�����
	private JLabel instalmentL; //�Һ� �������  
	private JLabel carding; //ī�������
	private JLabel discount; //���αݾ�
	private JLabel giveMoney_ml;
	private JLabel getMoney_ml;
	private JLabel okSign;
	
	
	private String sum=""; //��ȣ�ǿ��� ���� ����
	private cardPaying cp;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pay();
	}
	
	public Pay() {
		setTitle("���");
		setSize(936,652);
		setLocationRelativeTo(null); //â�� ������� ����
		
		mainP=new JPanel();
		mainP.setLayout(null);
		titleL=new JLabel("�����ϱ�");
		titleL.setFont(new Font("�����ٸ����",Font.PLAIN,30));
		titleL.setBounds(400,2,187,60);
		mainP.add(titleL);
		
		//����Ȳ�� �˷��ִ� �ѱݾ�,���� �ݾ�,�Ž����� =>���Ұ�� �� ���
		money = new JPanel();
		money.setBounds(22, 348, 430, 231);
		money.setBackground(Color.WHITE);
		money.setLayout(null);

		total_price = new JLabel("�ѱݾ�: ");
		total_price.setBounds(118,34,52,18);
		discount=new JLabel("���αݾ�: ");
		discount.setBounds(118, 79, 66, 18);
		giveMoney_ml = new JLabel("���� �ݾ�: ");
		giveMoney_ml.setBounds(118, 127, 80, 18);
		getMoney_ml = new JLabel("���� �ݾ� : ");
		getMoney_ml.setBounds(118, 176, 80, 18);		
		total_price_tf = new JTextField(10); //�ѱݾ� �ʵ�
		total_price_tf.setBounds(220, 31, 116, 24);
		discount_tf = new JTextField("0",10); //���αݾ� �ʵ�
		discount_tf.setBounds(220, 76, 116, 24);
		giveMoney_m_tf = new JTextField(10); //���� �� �ʵ�
		giveMoney_m_tf.setBounds(220, 124, 116, 24);
		getMoney_m_tf = new JTextField("0",10); //���� �� �ʵ�
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

		//�ֹ����� �������� 
		
		//�������� �˷��ִ� 
		payPanel = new JPanel();
		payPanel.setBounds(483, 63, 420, 223);
		payPanel.setBackground(Color.WHITE);
		payPanel.setLayout(null);
		
		payLabel=new JLabel("[����â]",JLabel.CENTER);
//		payLabel.setBounds(14, 1, 424, 44);
		payLabel.setBounds(14, 1, 100, 44);
		payLabel.setFont(new Font("�����ٸ����",Font.PLAIN,17));
		
		//
		giveMoneyL = new JLabel("���� �ݾ�: ");
		getMoneyL=new JLabel("");
		balanceL = new JLabel(" ");
		instalmentL=new JLabel("");
		
		//�����ݾ� �����ִ� �ؽ�Ʈ�ʵ�
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
		
		cashPay = new JButton("����");
		cashPay.setBounds(290,42,110,137);
		cashPay.setVisible(false);
		
		cardPay = new JButton("����");
		cardPay.setBounds(290,42,110,137);
		cardPay.setVisible(false);
		//���ݰ��� ���̾�α�

		payPanel.add(payLabel); payPanel.add(cashPay); payPanel.add(cardPay);
		mainP.add(payPanel);
		
		
		//������ȣ�� ������ �г�
		show_money = new JTextField(10);
		show_money.setBounds(485, 303, 411, 36);
		show_money.setEditable(false);
		mainP.add(show_money);
		calc();
		
		//���ݰ�����ư
		cashBtn = new JButton("���� ����");
		cashBtn.setBounds(758,350,128,114);
		
		//ī����� ��ư
		cardBtn=new JButton("ī�� ����");
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
		// ����â �г� ����
		//���ݰ�����ư�� ������ ��� ������ : ���� �Ž����� �κ��� �ȳ����ٰ� ���� =>�����ʿ� =>�����ѵ�
		if(e.getSource() == cashBtn) {
			payLabel.setText("[���ݰ���]");
			instalmentL.setText("");
			instalmenttf.setVisible(false);
			cardPay.setVisible(false);

			giveMoneyL.setBounds(64, 42, 83, 30);
			
			//�����ݾ� tf
			//giveMoneytf.setText("30000");
			giveMoneytf.setBounds(148,42,99,30);
			giveMoneytf.setVisible(true);
			
			//���� �ݾ�
			getMoneyL.setText("���� �ݾ�: ");
			getMoneyL.setBounds(64, 94, 83, 30);
			
			getMoneytf.setBounds(148, 94, 99, 30);
			getMoneytf.setVisible(true);
			
			balanceL.setText("�Ž�����: ");
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
			//ī�� ������ ������ ��� =>������: ī��� ���ٰ� ������ ���ٰ� �ٽ� ī��� ���� �����尡 2�� �����
			payLabel.setText("[ī�����]");
			getMoneyL.setText("");
			getMoneytf.setVisible(false);
			balanceL.setText("");
			balancetf.setVisible(false);
			cashPay.setVisible(false); 
			
			giveMoneyL.setText("���� �ݾ�: ");
			giveMoneyL.setBounds(64, 65, 83, 30);
			
			giveMoneytf.setBounds(148,65,99,30);
			giveMoneytf.setVisible(true);
		
			instalmentL.setText("�Һ�: ");
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
	} //ActionEvenet��
	
	//���� �Ϸ� â
	public void PayOk() {
		payOk = new JDialog(this,"�����Ϸ�",true);
		payOk.setSize(276,179);
		payOk.setLayout(null);
		payOk.setResizable(false);
		payOk.setLocationRelativeTo(null);
		okSign = new JLabel("������ �Ϸ�Ǿ����ϴ�.",JLabel.CENTER);
		okSign.setFont(new Font("�����ٸ����",Font.PLAIN,17));
		okSign.setBounds(40,40,200,23);
		okBtn=new JButton("Ȯ��");
		okBtn.setBounds(78,93,105,27);
		payOk.add(okSign); payOk.add(okBtn);
		//Ȯ�� ��ư�� ������ ���̾�α� â�� Pay������
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
		//��ȣ�� ������ ��ȣ�ǰ� ���ݰ��� - ���� �ݾ� �� ����� �ȴ�.
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
