package yumyum;
//�ؾߵɰ� 
//1.������� ������ ���� ��ҵǱ� + ��ü�ֹ� ���
//1-2.���
//2.���� �Ϸ� ������ ���� ��� �ö󰡱� => ������, �޴��� ����
//3. ��� ���̺����� O
//4.���հ�� => ���ݰ���,ī�� ���� �ϼ� �� �����ϱ�
//5. ������ ���� ��� ������ �ö󰡰� �߰���� ����ֱ�
//�߰���� ����� �� TRUNCATE table_n �ϸ� �� ��
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import yumyum.Table_main;
import yumyum.OrderMenu;

public class Pay extends JFrame implements ActionListener{
	
	private JPanel mainP; //main�г�
	private JPanel orderP; //�ֹ���� �����ִ� �г�
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
	private JLabel discountL; //���αݾ�
	private JLabel giveMoney_ml;
	private JLabel getMoney_ml;
	private JLabel okSign;
	
	
	private String sum=""; //��ȣ�ǿ��� ���� ����
	private cardPaying pt;
	private int total=0; //�Ѱ���

	private int getMoney=0; //������
	private String getM=""; //������
	private int balance=0; //�Ž�����
	private int totalPrice=0; //�Ѿ�
	
	private JLabel menu,mCount,mPrice; //�޴�,����,����
	private JTextField menu_tf,mCount_tf,mPrice_tf; 
	Object ob[][]=new Object[0][3]; //������ǥ�� ���� ������ ����
	DefaultTableModel model; //����������κ�
	JTable menuTable;
	JScrollPane menuPane;
	String header[]= {"�޴�","����","����"}; //�÷���
	
	
	//�����ͺ��̽� ����
	Connection conn = null,conn2=null;
	PreparedStatement pstmt=null,pstmt2=null;
	ResultSet rs=null,rs2=null;
	String sql="",sql_1="";
	
	Table_main tt;
	OrderMenu mm;
	
	public Pay(Table_main tm,String table_num) {
		setTitle("���");
		setSize(936,652);
		setLocationRelativeTo(null); //â�� ������� ����
//		tm.setVisible(false);
		paying(tm,table_num);
		setResizable(false);
		setVisible(true);
	}
	
	public void paying(Table_main tm,String table_num) {
		mainP=new JPanel();
		mainP.setLayout(null);
		titleL=new JLabel(table_num+" �����ϱ�");
		titleL.setFont(new Font("�����ٸ����",Font.PLAIN,29));
		titleL.setBounds(330,2,220,60);
		mainP.add(titleL);
		
		//�ֹ����� �������� 
		orderP=new JPanel();
		orderP.setBounds(20, 63, 430, 260);
		orderP.setBackground(Color.WHITE);
		orderP.setLayout(null);
		model=new DefaultTableModel(header,0);
		model.setColumnCount(3);
		menuTable=new JTable(model);
		menuPane=new JScrollPane(menuTable);
		menuPane.setBounds(0, 0, 430, 260);
		menuPane.getViewport().setBackground(Color.WHITE);
		//connect();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/yumyum1";
			conn=DriverManager.getConnection(url,"gogi1","2209");
			if(table_num.equals("���̺� 1")) {
				sql = "select * from table_1";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				String menu_name="";
				int count=0;
				int price=0;
				while(rs.next()) {
					//�޴�,����,����
					menu_name=rs.getString("menu");
					count=rs.getInt("num");
					price = rs.getInt("price");
					totalPrice+=count*price;
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println(menu_name+", "+count+", "+price);
					System.out.println("�Ѿ�: "+totalPrice);
				}
				
			}else if(table_num.equals("���̺� 2")) {
				sql = "select * from table_2";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�2 >> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 3")) {
				sql = "select * from table_3";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�3 >> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 4")) {
				sql = "select * from table_4";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�4>> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 5")) {
				sql = "select * from table_5";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�5 >> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 6")) {
				sql = "select * from table_6";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					
					model.addRow(data);
					System.out.println("���̺�6 >> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 7")) {
				sql = "select * from table_7";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�7 >> "+menu_name+", "+count+", "+price);
				}
			}else if(table_num.equals("���̺� 8")) {
				sql = "select * from table_8";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//�޴�,����,����
					String menu_name=rs.getString("menu");
					int count=rs.getInt("num");
					int price = rs.getInt("price");
					totalPrice+=count*price;	
					Object data[]= {menu_name,count,price};
					model.addRow(data);
					System.out.println("���̺�8 >> "+menu_name+", "+count+", "+price);
				}
			}
		}catch(Exception se){
			System.out.println("select �������: "+se);
		}finally {
			try {
				conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		orderP.add(menuPane);
		mainP.add(orderP);
		//select(table_num,tm);
		//����Ȳ�� �˷��ִ� �ѱݾ�,���� �ݾ�,�Ž����� =>���Ұ�� �� ���
		money = new JPanel();
		money.setBounds(22, 348, 430, 231);
		money.setBackground(Color.WHITE);
		money.setLayout(null);

		total_price = new JLabel("�ѱݾ�: ");
		total_price.setBounds(118,34,52,18);
		discountL=new JLabel("���αݾ�: ");
		discountL.setBounds(118, 79, 66, 18);
		giveMoney_ml = new JLabel("���� �ݾ�: ");
		giveMoney_ml.setBounds(118, 127, 80, 18);
		getMoney_ml = new JLabel("���� �ݾ� : ");
		getMoney_ml.setBounds(118, 176, 80, 18);		
		total_price_tf = new JTextField(Integer.toString(totalPrice),10); //�ѱݾ� �ʵ�
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
		money.add(discountL);
		money.add(discount_tf);
		money.add(giveMoney_ml);
		money.add(giveMoney_m_tf);
		money.add(getMoney_ml);
		money.add(getMoney_m_tf);
		mainP.add(money);

		
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
		//���� �ݾ� == �ѱݾ� ����� �� �����ݾ��� int�� ��ȯ�ؼ� ����ϱ� 
		giveMoneytf = new JTextField(total_price_tf.getText(),7);
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
		cashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				getMoneytf.setText(Integer.toString(balance));
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
						//getMoneytf.setText("");
						instalmenttf.setText("");
						calculate();
						PayOk(tm,table_num);
					}
				});
			}
		});
		cashBtn.setBounds(758,350,128,114);
		
		//ī����� ��ư
		cardBtn=new JButton("ī�� ����");
		cardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				//ī��������� ���� ��������� �߻�
				cardPay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//new PayTest2();
						pt=new cardPaying();
						PayOk(tm,table_num);
					}
				});
			}
		});
		cardBtn.setBounds(758, 470, 128, 109);
		
		mainP.add(cashBtn); mainP.add(cardBtn);
		
		add(mainP);
	
		cashBtn.addActionListener(this);
		cardBtn.addActionListener(this);
	}
	//
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

	//���� �Ϸ� â
	//������: ������ư�� ������ �Ž����� text�� �������.
		public void PayOk(Table_main tt,String table_num) {
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
			
			if(table_num.equals("���̺� 1")) {
				tt.Table1("���̺� 1");del("���̺� 1");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 2")) {
				tt.Table2("���̺� 2");del("���̺� 2");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 3")) {
				tt.Table3("���̺� 3");del("���̺� 3");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 4")) {
				tt.Table4("���̺� 4");del("���̺� 4");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 5")) {
				tt.Table5("���̺� 5");del("���̺� 5");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 6")) {
				tt.Table6("���̺� 6");del("���̺� 6");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 7")) {
				tt.Table7("���̺� 7");del("���̺� 7");tt.setVisible(true);
			}
			else if(table_num.equals("���̺� 8")) {
				tt.Table8("���̺� 8");del("���̺� 8");tt.setVisible(true);
			}
		}
		
		//����ϴ� �޼���
		public void calculate() {
			total=Integer.parseInt(giveMoneytf.getText());
			//�����ʿ�  �ٽ� ������ ��� �ϳ��� �������� ��ư�� �ʿ�
			getMoney=Integer.parseInt(getMoneytf.getText().trim()); //���⼭ clear�� �ѹ� ������ �ϸ� ����+���ڶ� �����߻� 
			if(total>=getMoney) {
				balance=total-getMoney;
			}
			balancetf.setText(Integer.toString(balance));
			//Ȯ����
			System.out.println("[���ݰ���]");
			System.out.println("�ѱݾ�:"+total+"��");
			System.out.println("���� ��: "+getMoney+"��");
			System.out.println("�Ž�����: "+balance+"��");
		}
		
		//��� ����ֱ�
		public void del(String table_num){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2209");
				switch (table_num) {
				case "���̺�1": sql="truncate table_1;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�2": sql="truncate table_2;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�3": sql="truncate table_3;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�4": sql="truncate table_4;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�5": sql="truncate table_5;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�6": sql="truncate table_6;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�7": sql="truncate table_7;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺�8": sql="truncate table_8;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
			}
			}catch(ClassNotFoundException e) {
				System.out.println("����̹��ε�����");
			}catch(SQLException e) {
				System.out.println("����: "+e);
			}finally {
				try{
					if(conn!=null && !conn.isClosed()) {
						conn.close();
					}
					if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
				}catch(SQLException se) {
					se.printStackTrace();
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
				show_money.setText("");
				getMoneytf.setText("");
				instalmenttf.setText("");
			}
		}
	} //end of class
	
	class okBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent okay) {
			if(okay.getSource()==okBtn) {
				payOk.setVisible(false);
				setVisible(false);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
