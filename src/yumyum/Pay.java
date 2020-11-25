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
	
	private JPanel mainP; //main패널
	private JPanel orderP; //주문기록 보여주는 패널
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
	private JLabel discountL; //할인금액
	private JLabel giveMoney_ml;
	private JLabel getMoney_ml;
	private JLabel okSign;
	
	
	private String sum=""; //번호판에서 누른 가격
	private cardPaying pt;
	private int total=0; //총가격

	private int getMoney=0; //받은돈
	private int giveMoney=0; //받을돈
	private int balance=0; //거스름돈
	private int totalPrice=0; //총액
	
	String tYear="";
	String tMonth="";
	String tDate="";
	
	Object ob[][]=new Object[0][3]; //데이터표시 열만 나오게 설정
	DefaultTableModel model; //데이터저장부분
	JTable menuTable;
	JScrollPane menuPane;
	String header[]= {"메뉴","수량","가격"}; //컬럼명
	
	
	//데이터베이스 연동
	Connection conn = null,conn2=null;
	PreparedStatement pstmt=null,pstmt2=null;
	ResultSet rs=null,rs2=null;
	String sql="",sql_1="";
	
	Table_main tt;
	OrderMenu mm;
	
	public Pay(Table_main tm,String table_num) {
		changeAllSwingComponentDefaultFont();
		setTitle("계산");
		setSize(936,652);
		setLocationRelativeTo(null); //창이 가운데에서 실행
		paying(tm,table_num);
		setResizable(false);
		setVisible(true);
	}
	
	public void paying(Table_main tm,String table_num) {
		mainP=new JPanel();
		mainP.setLayout(null);
		mainP.setBackground(new Color(242,241,241));
		titleL=new JLabel(table_num+" 결제하기");
		titleL.setFont(new Font("나눔바른고딕",Font.PLAIN,29));
		titleL.setBounds(330,2,220,60);
		mainP.add(titleL);
		
		//주문내역 나오도록 
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
				if(table_num.equals("테이블 1")) {
					sql = "select * from table_1";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					String menu_name="";
					int count=0;
					int price=0;
					while(rs.next()) {
						//메뉴,개수,가격
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

				}else if(table_num.equals("테이블 2")) {
					sql = "select * from table_2";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 3")) {
					sql = "select * from table_3";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 4")) {
					sql = "select * from table_4";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 5")) {
					sql = "select * from table_5";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 6")) {
					sql = "select * from table_6";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 7")) {
					sql = "select * from table_7";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				}else if(table_num.equals("테이블 8")) {
					sql = "select * from table_8";
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						//메뉴,개수,가격
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
				System.out.println("select 실행오류: "+se);
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

		total_price = new JLabel("총금액: ");
		total_price.setBounds(118,34,52,18);
		discountL=new JLabel("할인금액: ");
		discountL.setBounds(118, 79, 66, 18);
		giveMoney_ml = new JLabel("받을 금액: ");
		giveMoney_ml.setBounds(118, 127, 80, 18);
		getMoney_ml = new JLabel("받은 금액 : ");
		getMoney_ml.setBounds(118, 176, 80, 18);		
		total_price_tf = new JTextField(Integer.toString(totalPrice),10); //총금액 필드
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
		money.add(discountL);
		money.add(discount_tf);
		money.add(giveMoney_ml);
		money.add(giveMoney_m_tf);
		money.add(getMoney_ml);
		money.add(getMoney_m_tf);
		mainP.add(money);

		
		//결제내역 알려주는 
		payPanel = new JPanel();
		payPanel.setBounds(483, 63, 420, 223);
		payPanel.setBackground(Color.WHITE);
		payPanel.setLayout(null);
		
		payLabel=new JLabel("[결제창]",JLabel.CENTER);
		payLabel.setBounds(14, 1, 100, 44);
		payLabel.setFont(new Font("나눔바른고딕",Font.PLAIN,17));
		
		//
		giveMoneyL = new JLabel("받을 금액: ");
		getMoneyL=new JLabel("");
		balanceL = new JLabel(" ");
		instalmentL=new JLabel("");
		
		//받을금액 보여주는 텍스트필드
		//받을 금액 == 총금액 계산할 때 받을 금액을 int로 변환해서 계산하기 
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
		
		cashPay = new JButton("결제");
		cashPay.setBounds(290,42,110,137);
		cashPay.setBackground(new Color(184, 195, 189));
		cashPay.setBorderPainted(false);
		cashPay.setVisible(false);
		
		cardPay = new JButton("결제");
		cardPay.setBackground(new Color(184, 195, 189));
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
		cashBtn.setBackground(new Color(214, 164, 149));
		cashBtn.setBackground(new Color(214, 164, 149));
		cashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payLabel.setText("[현금결제]");
				instalmentL.setText("");
				instalmenttf.setVisible(false);
				cardPay.setVisible(false);

				giveMoneyL.setBounds(64, 42, 83, 30);
				
				//받을금액 tf
				giveMoneytf.setBounds(148,42,99,30);
				giveMoneytf.setVisible(true);
				
				//받은 금액
				getMoneyL.setText("받은 금액: ");
				getMoneyL.setBounds(64, 94, 83, 30);
				
				getMoneytf.setText(Integer.toString(balance));
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
						//getMoneytf.setText("");
						instalmenttf.setText("");
						calculate(tm,table_num);
					}
				});
			}
		});
		cashBtn.setBounds(758,350,128,114);
		
		//카드결제 버튼
		cardBtn=new JButton("카드 결제");
		cardBtn.setBackground(new Color(214, 164, 149));
		cardBtn.setBorderPainted(false);
		cardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				total_price_tf.setText(giveMoneytf.getText());
				discount_tf.setText("0");
				giveMoney_m_tf.setText(giveMoneytf.getText());
				
				payPanel.add(giveMoneyL); 
				payPanel.add(giveMoneytf);
				
				payPanel.add(instalmentL);
				payPanel.add(instalmenttf);
				
				cardPay.setVisible(true);
				//카드결제에서 결제 눌렀을경우 발생
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

	//결제 완료 창
		public void PayOk(Table_main tt,String table_num,boolean flag) {
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
			if(flag==true) {
				okBtn.addActionListener(new okBtnListener());
				
				payOk.setLocationRelativeTo(null);
				payOk.setVisible(true);
				
				if(table_num.equals("테이블 1")) {
					tt.Table1("테이블 1");addToMaechul();del("테이블 1");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 2")) {
					tt.Table2("테이블 2");addToMaechul();del("테이블 2");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 3")) {
					tt.Table3("테이블 3");addToMaechul();del("테이블 3");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 4")) {
					tt.Table4("테이블 4");addToMaechul();del("테이블 4");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 5")) {
					tt.Table5("테이블 5");addToMaechul();del("테이블 5");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 6")) {
					tt.Table6("테이블 6");addToMaechul();del("테이블 6");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 7")) {
					tt.Table7("테이블 7");addToMaechul();del("테이블 7");tt.setVisible(true);
				}
				else if(table_num.equals("테이블 8")) {
					tt.Table8("테이블 8");addToMaechul();del("테이블 8");tt.setVisible(true);
				}

			}
		}
		
		//계산하는 메서드
		public void calculate(Table_main tm,String table_num) {
			//결제해야하는 총액
			total=Integer.parseInt(giveMoneytf.getText().trim()); //52000
			//받은 돈
			getMoney=Integer.parseInt(getMoneytf.getText().trim()); //trim사용하여 앞뒤 공백 제거
			
			//1. 받은돈>=총액  거스름돈 반환
			//2. 만약에 0원을 입력했을 경우 => 받은 돈 입력해달라고 다이얼로그 뜨게 하기
			//3. 총액 >= 받은돈 : 더 받아야되는경우 1번 상황이 될 때까지 종료시키면 안됨
			
			//1
			if(getMoney>=total) {
				balance=getMoney-total;
				balancetf.setText(Integer.toString(balance));
				JOptionPane.showMessageDialog(this, "거스름돈: "+Integer.toString(balance)+" 원","거스름돈",
						JOptionPane.INFORMATION_MESSAGE);
				PayOk(tm,table_num,true);
			}
			
			//2 만약에 0원일경우 (입력안하고 눌렀을 때)
			if(getMoneytf.getText().equals("0")) {
				JOptionPane.showMessageDialog(this, "돈을 입력하세요!","Message",JOptionPane.ERROR_MESSAGE);
				PayOk(tm,table_num,false);
			}
						
			if(total>getMoney) {
				giveMoney=total-getMoney; //giveMoney=2000
				giveMoney_m_tf.setText(Integer.toString(giveMoney)); //받을 금액 2000
				giveMoneytf.setText(Integer.toString(giveMoney)); 
				getMoney_m_tf.setText(Integer.toString(getMoney)); //받은 금액 => 내가 방금 받은 돈
				total=Integer.parseInt(giveMoneytf.getText().trim());
				getMoneytf.setText("");
			}
			
		}
		
		//매출디비에 넣어주기
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
				System.out.println("드라이버로딩실패");
			}catch(SQLException e) {
				System.out.println("에러: "+e);
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
		
		//전체폰트설정
		private void changeAllSwingComponentDefaultFont() {
			    try {
			         UIDefaults swingComponentDefaultTable = UIManager.getDefaults();
			         Enumeration allDefaultKey = swingComponentDefaultTable.keys();
			         while(allDefaultKey.hasMoreElements()) {
			              String defaultKey = allDefaultKey.nextElement().toString();
			              if(defaultKey.indexOf("font") != -1) {
			                   Font newDefaultFont = new Font("나눔바른고딕", Font.PLAIN, 14);
			                   UIManager.put(defaultKey, newDefaultFont);
			               }
			        }
			   } catch (Exception e) {
			      e.printStackTrace();
			   }
		}
		
		//디비 비워주기
		public void del(String table_num) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2203");
				switch (table_num) {
				case "테이블 1": sql="truncate table_1;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 2": sql="truncate table_2;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 3": sql="truncate table_3;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 4": sql="truncate table_4;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 5": sql="truncate table_5;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 6": sql="truncate table_6;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 7": sql="truncate table_7;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
				case "테이블 8": sql="truncate table_8;"; pstmt=conn.prepareStatement(sql); pstmt.executeUpdate(sql);break;
			}
			}catch(ClassNotFoundException e) {
				System.out.println("드라이버로딩실패");
			}catch(SQLException e) {
				System.out.println("에러: "+e);
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
				//매출디비에 올려주고 
				//나머지 요소들 초기화해주기
				total_price_tf.setText(""); //총합계
				discount_tf.setText(""); //할인금액
				giveMoney_m_tf.setText(""); //받을 금액
				getMoney_m_tf.setText(""); //받은 금액
				

				giveMoneytf.setText(""); //받을돈
				getMoneytf.setText(""); //받은 돈
				balancetf.setText(""); //거스름돈
				instalmenttf.setText(""); //할부
				
				total=0;
				getMoney=0;
				balance=0;
				
				payOk.setVisible(false);
				setVisible(false);
			}
		}

	}

	//implement actionlistener로 인해 자동 생성
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
