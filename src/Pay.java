//�����������α׷�
//��ĥ��: ������ҵ� �����
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
	
	private JPanel menu_list; //�ֹ��� �޴����� ������ �г�
	private JPanel payPanel; //�����ϴ� �г�
//	private JPanel cashPay; //�����ϴ� �г�
//	private JPanel cardPay;
	private JPanel money; //�ѱݾ�,�����ݾ�,���� �ݾ׸� �����ִ� �г�
	private JPanel numbers; //������ ������ �г�
	private JPanel compute; //����ϴ� �г�(����) + �ݾ� �����ִ� ��ü �г�
	
	//�ؽ�Ʈ�ʵ�
	private JTextField show_money;
	private JTextField giveMoneytf; //���� ��
	private JTextField getMoneytf; //���� ��
	private JTextField balancetf; //�Ž�����
	
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
	
	//���ݰ���
	private JButton cashBtn;
	//ī�����
	private JButton cardBtn;
	private JButton GoPay; //���� ���� ��ư
	
	private JLabel payLabel; //���ݰ������� ī��������� �˷��ִ�
	private JLabel total_price; //�ѱݾ� = OrderMenu���� �޾ƿ� �ݾ�
	private JLabel giveMoneyL; //���� ��
	private JLabel getMoneyL; //���� ��
	private JLabel balanceL; //�Ž�����
	private JLabel instalmentL; //�Һ� �������  
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pay();
	}
	
	public Pay() {
		setTitle("����ϱ�");
		setSize(936,652);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //â�� ������� ����
		setResizable(false); //âũ�� ���� X
		setLayout(null); //�����Ӱ� ��ġ ���� ����
		
		title_label=new JLabel("�����ϱ�");
		title_label.setFont(new Font("�����ٸ����",Font.PLAIN,30));
		title_label.setBounds(400,2,187,60);
		add(title_label);
		
		//�ֹ������� ������ �г�
		menu_list = new JPanel();
		menu_list.setBounds(20,63,439,276);
		menu_list.setLayout(null);
		menu_list.setBackground(Color.WHITE);
		add(menu_list);
		
		//���� ���� �̸� �˷��ִ� �г�
		money = new JPanel();
		money.setBounds(22,348,430,231);
		money.setBackground(Color.WHITE);
		money.setLayout(null);
		
		total_price = new JLabel("�� �ݾ�: ");
		total_price.setBounds(14, 35, 65, 34);
		money.add(total_price);
		add(money);
		
		
		//������ȣ�� ������ �г�
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
		
		//���ݰ��� ��ư
		cashBtn = new JButton("���ݰ���");
		cardBtn = new JButton("ī�����");
		cashBtn.setBounds(297,60,128,114);
		cardBtn.setBounds(297, 186, 128, 109);
		
		compute.add(cardBtn); compute.add(cashBtn);
		add(compute);
		
		payPanel = new JPanel();
		payPanel.setBounds(465,63,430,223);
		payPanel.setBackground(Color.WHITE);
		payPanel.setLayout(null);
		
		payLabel= new JLabel("[����â]");
		payLabel.setBounds(14,1,424,44);
		payPanel.add(payLabel);
		
		giveMoneyL = new JLabel("");
		payPanel.add(giveMoneyL);
		//30000���� OrderMenu���� �޾ƿͼ� �����ϱ�
		//������ �߻� : ������ �ʴ´�!!!
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
		
		GoPay = new JButton("����");
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
			//�ʱ�ȭ����
//			payLabel.revalidate();
//			payLabel.repaint();
			
			//���ݰ����� ������ ���ݰ���â �߱�
			payLabel.setText("[���ݰ���]");
			giveMoneyL.setText("���� �ݾ�: ");
			giveMoneyL.setBounds(64, 42, 83, 30);
			//�����߻�!! �ȳ���!!
			//giveMoneytf.setVisible(true);
			getMoneyL.setText("���� �ݾ� : ");
			balanceL.setText("�Ž�����: ");
			GoPay.setVisible(true);
		}else if(e.getSource()==cardBtn) {
			//�ʱ�ȭ ����
//			payLabel.revalidate();
//			payLabel.repaint();
			//ī�� ������ ������ ī�� ����â �߱�
			payLabel.setText("[ī�����]");
			giveMoneyL.setBounds(64, 65, 83, 30);
			giveMoneyL.setText("���� �ݾ�: ");
			instalmentL.setText("�Һ�: ");
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
