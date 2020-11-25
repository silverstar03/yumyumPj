package yumyum;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
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
	private int giveMoney=0; //������
	private int balance=0; //�Ž�����
	private int totalPrice=0; //�Ѿ�
	
	String tYear="";
	String tMonth="";
	String tDate="";
	
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
		changeAllSwingComponentDefaultFont();
		setTitle("���");
		setSize(936,652);
		setLocationRelativeTo(null); //â�� ������� ����
		paying(tm,table_num);
		setResizable(false);
		setVisible(true);
	}
	
	public void paying(Table_main tm,String table_num) {
		mainP=new JPanel();
		mainP.setLayout(null);
		mainP.setBackground(new Color(242,241,241));
		titleL=new JLabel(table_num+" �����ϱ�");
		titleL.setFont(new Font("�����ٸ����",Font.PLAIN,29));
		titleL.setBounds(330,2,220,60);
		mainP.add(titleL);
		
		//�ֹ����� �������� 
		orderP=new JPanel();
		orderP.setBounds(20, 63, 430, 260);
		orderP.setBackground(new Color(243, 237, 230));
		orderP.setLayout(null);
		model=new DefaultTableModel(header,0);
		model.setColumnCount(3);
		menuTable=new JTable(model);
		menuPane=new JScrollPane(menuTable);
		menuPane.setBounds(0, 0, 430, 260);
		menuPane.getViewport().setBackground(Color.WHITE);
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2203");
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}						
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}	
						Object data[]= {menu_name,count,price};
						
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}	
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
						if(count==1) {
							totalPrice+=count*price;
						}else if(count>=2) {
							totalPrice+=price;
						}
						Object data[]= {menu_name,count,price};
						model.addRow(data);
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
		payLabel.setBounds(14, 1, 100, 44);
		payLabel.setFont(new Font("�����ٸ����",Font.PLAIN,17));
		
		//
		giveMoneyL = new JLabel("���� �ݾ�: ");
		getMoneyL=new JLabel("");
		balanceL = new JLabel(" ");
		instalmentL=new JLabel("");
		
		//�����ݾ� �����ִ� �ؽ�Ʈ�ʵ�
		//���� �ݾ� == �ѱݾ� ����� �� ���� �ݾ��� int�� ��ȯ�ؼ� ����ϱ� 
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
		cashPay.setBackground(new Color(184, 195, 189));
		cashPay.setBorderPainted(false);
		cashPay.setVisible(false);
		
		cardPay = new JButton("����");
		cardPay.setBackground(new Color(184, 195, 189));
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
		cashBtn.setBackground(new Color(214, 164, 149));
		cashBtn.setBackground(new Color(214, 164, 149));
		cashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payLabel.setText("[���ݰ���]");
				instalmentL.setText("");
				instalmenttf.setVisible(false);
				cardPay.setVisible(false);

				giveMoneyL.setBounds(64, 42, 83, 30);
				
				//�����ݾ� tf
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
						calculate(tm,table_num);
					}
				});
			}
		});
		cashBtn.setBounds(758,350,128,114);
		
		//ī����� ��ư
		cardBtn=new JButton("ī�� ����");
		cardBtn.setBackground(new Color(214, 164, 149));
		cardBtn.setBorderPainted(false);
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
				
				total_price_tf.setText(giveMoneytf.getText());
				discount_tf.setText("0");
				giveMoney_m_tf.setText(giveMoneytf.getText());
				
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
						PayOk(tm,table_num,true);
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
		
		num0.setBackground(new Color(233, 220, 216));
		num0.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		num1.setBackground(new Color(233, 220, 216));
		num1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		num2.setBackground(new Color(233, 220, 216));
		num2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		num3.setBackground(new Color(233, 220, 216));
		num3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num4.setBackground(new Color(233, 220, 216));
		num4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num5.setBackground(new Color(233, 220, 216));
		num5.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num6.setBackground(new Color(233, 220, 216));
		num6.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num7.setBackground(new Color(233, 220, 216));
		num7.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num8.setBackground(new Color(233, 220, 216));
		num8.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num9.setBackground(new Color(233, 220, 216));
		num9.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		num00.setBackground(new Color(233, 220, 216));
		num00.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		clear.setBackground(new Color(233, 220, 216));
		clear.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
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
		public void PayOk(Table_main tt,String table_num,boolean flag) {
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
			if(flag==true) {
				okBtn.addActionListener(new okBtnListener());
				
				payOk.setLocationRelativeTo(null);
				payOk.setVisible(true);
				
				if(table_num.equals("���̺� 1")) {
					tt.Table1("���̺� 1");addToMaechul();del("���̺� 1");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 2")) {
					tt.Table2("���̺� 2");addToMaechul();del("���̺� 2");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 3")) {
					tt.Table3("���̺� 3");addToMaechul();del("���̺� 3");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 4")) {
					tt.Table4("���̺� 4");addToMaechul();del("���̺� 4");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 5")) {
					tt.Table5("���̺� 5");addToMaechul();del("���̺� 5");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 6")) {
					tt.Table6("���̺� 6");addToMaechul();del("���̺� 6");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 7")) {
					tt.Table7("���̺� 7");addToMaechul();del("���̺� 7");tt.setVisible(true);
				}
				else if(table_num.equals("���̺� 8")) {
					tt.Table8("���̺� 8");addToMaechul();del("���̺� 8");tt.setVisible(true);
				}

			}
		}
		
		//����ϴ� �޼���
		public void calculate(Table_main tm,String table_num) {
			//�����ؾ��ϴ� �Ѿ�
			total=Integer.parseInt(giveMoneytf.getText().trim()); //52000
			//���� ��
			getMoney=Integer.parseInt(getMoneytf.getText().trim()); //trim����Ͽ� �յ� ���� ����
			
			//1. ������>=�Ѿ�  �Ž����� ��ȯ
			//2. ���࿡ 0���� �Է����� ��� => ���� �� �Է��ش޶�� ���̾�α� �߰� �ϱ�
			//3. �Ѿ� >= ������ : �� �޾ƾߵǴ°�� 1�� ��Ȳ�� �� ������ �����Ű�� �ȵ�
			
			//1
			if(getMoney>=total) {
				balance=getMoney-total;
				balancetf.setText(Integer.toString(balance));
				JOptionPane.showMessageDialog(this, "�Ž�����: "+Integer.toString(balance)+" ��","�Ž�����",
						JOptionPane.INFORMATION_MESSAGE);
				PayOk(tm,table_num,true);
			}
			
			//2 ���࿡ 0���ϰ�� (�Է¾��ϰ� ������ ��)
			if(getMoneytf.getText().equals("0")) {
				JOptionPane.showMessageDialog(this, "���� �Է��ϼ���!","Message",JOptionPane.ERROR_MESSAGE);
				PayOk(tm,table_num,false);
			}
						
			if(total>getMoney) {
				giveMoney=total-getMoney; //giveMoney=2000
				giveMoney_m_tf.setText(Integer.toString(giveMoney)); //���� �ݾ� 2000
				giveMoneytf.setText(Integer.toString(giveMoney)); 
				getMoney_m_tf.setText(Integer.toString(getMoney)); //���� �ݾ� => ���� ��� ���� ��
				total=Integer.parseInt(giveMoneytf.getText().trim());
				getMoneytf.setText("");
			}
			
		}
		
		//������ �־��ֱ�
		public void addToMaechul() {
			SimpleDateFormat tyear = new SimpleDateFormat ( "yyyy");
			SimpleDateFormat tmonth = new SimpleDateFormat ( "MM");
			SimpleDateFormat tdate = new SimpleDateFormat ( "dd");
			
			Date time = new Date();
			
			String today_year = tyear.format(time);
			String today_month = tmonth.format(time);
			String today_date = tdate.format(time);

			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2203");
				sql = "insert into maechul_1(m_year,m_month,m_date,product,count,price) values (?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				
				for(int i=0;i<menuTable.getRowCount();i++) {
					pstmt.setInt(1, Integer.parseInt(today_year));
					pstmt.setInt(2, Integer.parseInt(today_month));
					pstmt.setInt(3, Integer.parseInt(today_date));
					//name
					pstmt.setString(4, String.valueOf(menuTable.getValueAt(i, 0)));
					//count
					int countMenu=Integer.parseInt(String.valueOf(menuTable.getValueAt(i, 1)));
					pstmt.setInt(5,countMenu);
					//price
					int priceMenu=Integer.parseInt(String.valueOf(menuTable.getValueAt(i, 2)));
					int originP=priceMenu/countMenu;
					pstmt.setInt(6,originP);
					pstmt.executeUpdate();
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
		
		//��ü��Ʈ����
		private void changeAllSwingComponentDefaultFont() {
			    try {
			         UIDefaults swingComponentDefaultTable = UIManager.getDefaults();
			         Enumeration allDefaultKey = swingComponentDefaultTable.keys();
			         while(allDefaultKey.hasMoreElements()) {
			              String defaultKey = allDefaultKey.nextElement().toString();
			              if(defaultKey.indexOf("font") != -1) {
			                   Font newDefaultFont = new Font("�����ٸ����", Font.PLAIN, 14);
			                   UIManager.put(defaultKey, newDefaultFont);
			               }
			        }
			   } catch (Exception e) {
			      e.printStackTrace();
			   }
		}
		
		//��� ����ֱ�
		public void del(String table_num) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2203");
				switch (table_num) {
				case "���̺� 1": sql="truncate table_1;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 2": sql="truncate table_2;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 3": sql="truncate table_3;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 4": sql="truncate table_4;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 5": sql="truncate table_5;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 6": sql="truncate table_6;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 7": sql="truncate table_7;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "���̺� 8": sql="truncate table_8;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
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
				//������ �÷��ְ� 
				//������ ��ҵ� �ʱ�ȭ���ֱ�
				total_price_tf.setText(""); //���հ�
				discount_tf.setText(""); //���αݾ�
				giveMoney_m_tf.setText(""); //���� �ݾ�
				getMoney_m_tf.setText(""); //���� �ݾ�
				

				giveMoneytf.setText(""); //������
				getMoneytf.setText(""); //���� ��
				balancetf.setText(""); //�Ž�����
				instalmenttf.setText(""); //�Һ�
				
				total=0;
				getMoney=0;
				balance=0;
				
				payOk.setVisible(false);
				setVisible(false);
			}
		}

	}

	//implement actionlistener�� ���� �ڵ� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
