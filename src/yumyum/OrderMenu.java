package yumyum;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import yumyum.Table_main;
import yumyum.Pay;

public class OrderMenu extends JFrame {
	
	private JLabel title_label;
	
	private JPanel menu_panel;	//메뉴 카테고리 있는 panel
	private JPanel detail_panel; 	//상세 메뉴들이 나오는 panel
	private JPanel print_panel;	//주문 내역, 취소, 주문있는 panel
	
	private JButton[] menucategory;	//큰 메뉴 카테고리
	private JButton[] detailmenu;	//버튼 15개 생성
	private JButton orderBtn; //주문버튼
	private JButton cancelBtn; //결제버튼
	private JButton before;		//이전버튼
	private JButton payBtn;
	
	private JTable menutable;	//주문내역 보여주는 JTable
	
	private DefaultTableModel model;	//menutable객체에 넣을 model
	private JScrollPane scrollPane;		//menutable객체 추가하는 JScrollPane
	private ArrayList<String> list = new ArrayList<>();	//colNames 요소 추가하는 ArrayList
	private String[] colNames;	
	private String[] answer;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String sql;
	private String sql2;	//다른 메서드들로 넘겨주는 sql
	
	private int[][] cnt = new int[3][15];	//각 메뉴마다 수량 알려주는 배열
	private int[][] num_cnt = new int[3][15];	//몇행인지가 저장된 배열(?)
	private int[][] price = new int[3][15];	//각 메뉴마다의 가격
	private String[][] menuArr ={{"생갈비","양념갈비","돼지갈비","생삼겹살","생목살","생오겹살",
		"냉동삼겹살","냉동목살","냉동오겹살","대패삼겹살","육회","갈매기살","항정살","가브리살"},
		{"계란찜","공기밥","된장찌개","물냉면","비빔냉면","볶음밥","치즈추가"},
		{"사이다(355ml)","콜라(355ml)","환타(355ml)","사이다(1.25L)","콜라(1.25L)","환타(1.25L)","소주",
			"맥주","청하","막걸리",}
		};
	
//	private int row;	//주문 취소할 행
	private int num = 0;	//메뉴 카테고리 인덱스 받아오는 변수
	private int sumprice = 0;	//각 메뉴의 총 가격
	private int totalprice = 0;	//전체 가격
	private int menu = 0;
	private int tableNum = 0;
	
	private String result = "";
	private String finalprice = "";
	
	Table_main t_main;
	
	Pay pay1;
	Pay pay2;
	Pay pay3;
	Pay pay4;
	Pay pay5;
	Pay pay6;
	Pay pay7;
	Pay pay8;
		
	//생성자 메서드
	public OrderMenu(String table_num, Table_main t_main) {
		
		this.getContentPane().repaint();	//컴포넌트 재배치(새로고침 개념)
		setTitle("주문 등록 화면");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//x누르면 창 사라짐
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//창크기 조절 X
		getContentPane().setLayout(null); 	//자유롭게 위치 조정 가능
		getContentPane().setBackground(new Color(242,241,241)); //이부분 신수민이 수정
		
		title_label = new JLabel("주문 등록");
		title_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		title_label.setBounds(448, 2, 187, 60);
		add(title_label);
				
		menu(table_num, t_main);
	}
	
	public void menu(String table_num, Table_main t_main) {	//메뉴 버튼들 생성
		
		menu_panel = new JPanel();
		menu_panel.setBounds(490, 72, 600, 84);
		menu_panel.setLayout(new GridLayout(1,5));
		add(menu_panel);
		
		detail_panel = new JPanel();
		detail_panel.setBounds(490, 159, 600, 300);
		detail_panel.setLayout(new GridLayout(3,5));
		add(detail_panel);
		
		menucategory = new JButton[10];
		for (int i = 0; i<4; i++) {
			menu_panel.add(menucategory[i] = new JButton(""));
			
			menucategory[i].setBackground(new Color(180,166,150));
			menucategory[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
			menucategory[i].setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		}
		
		menucategory[0].setText("고기류");
			
		menucategory[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menueffect(0);				
				}
			});
			
		menucategory[1].setText("식사류");
		
		menucategory[1].addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				menueffect(1);				
			}
		});
		
		menucategory[2].setText("음료");
		
		menucategory[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menueffect(2);				
			}
		});

		
		detailmenu = new JButton[15];
		for (int i = 0; i<15; i++) {
			detail_panel.add(detailmenu[i] = new JButton(""));
			
			detailmenu[i].setBackground(new Color(179, 146, 131));
			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border컬러 지정
			detailmenu[i].setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		}
		
		print_panel = new JPanel();
		print_panel.setBounds(0, 71, 486, 582);
		print_panel.setLayout(null);
		print_panel.setBackground(new Color(242,241,241)); //이부분 신수민이 수정
		
		list.add("메뉴");
		list.add("개수");
		list.add("가격");
		colNames = list.toArray(new String[list.size()]);
		answer = new String[3];
		

		model = new DefaultTableModel(colNames, 0) {
			public boolean isCellEditable(int i, int c) {//테이블 더블 클릭시 수정 불가
				return false;
			}
		};
		
		menutable = new JTable(model);
		menutable.getTableHeader().setReorderingAllowed(false); //table header 이동 불가
		menutable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
		menutable.setRowHeight(25);
		scrollPane = new JScrollPane(menutable);
		
		scrollPane.setBounds(0, 0, 486, 385);
		scrollPane.getViewport().setBackground(Color.WHITE);
		print_panel.add(scrollPane);
		
		orderBtn = new JButton("주       문");
		orderBtn.setBackground(new Color(207,185,151));
		orderBtn.setBorderPainted(false);
		orderBtn.setForeground(Color.WHITE);
		orderBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		orderBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableNum = Integer.parseInt(table_num.substring(4));
				if(menutable.getRowCount()!=0) {
					dbinitial();		//주문 버튼 누를 때 이전 메뉴들 디비에서 삭제
					printOrder();		//result에 주문한 메뉴들과 총 가격 넣어줌
					printTable(t_main);		// result에 있는 값을 table_main으로 넘겨줌
					setVisible(false);
					t_main.setVisible(true);
				}
			}
		});
		
		cancelBtn = new JButton("취       소");
		cancelBtn.setBackground(new Color(207,185,151));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		cancelBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					if(menutable.getSelectedRow()!=1) {
						cancel();
					}
				}				
			}
		});
		
		before = new JButton("이   전");
		before.setBorderPainted(false);
		before.setForeground(Color.WHITE);
		before.setBackground(new Color(183,184,176));
		before.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		before.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					setVisible(false);
					t_main.setVisible(true);
				}
			}
		});
		
		payBtn = new JButton("결   제");
		payBtn.setBorderPainted(false);
		payBtn.setForeground(Color.WHITE);
		payBtn.setBackground(new Color(183,184,176));
		payBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		payBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					setVisible(false);
					
					if(table_num.equals("테이블 1")) {
						orderintial();
						pay1=new Pay(t_main,table_num);
						pay1.setVisible(true);
					}
					else if(table_num.equals("테이블 2")) {
						orderintial();
						pay2=new Pay(t_main,table_num);
						pay2.setVisible(true);
					}
					else if(table_num.equals("테이블 3")) {
						orderintial();
						pay3=new Pay(t_main,table_num);
						pay3.setVisible(true);
					}
					else if(table_num.equals("테이블 4")) {
						orderintial();
						pay4=new Pay(t_main,table_num);
						pay4.setVisible(true);
					}
					else if(table_num.equals("테이블 5")) {
						orderintial();
						pay5=new Pay(t_main,table_num);
						pay5.setVisible(true);
					}
					else if(table_num.equals("테이블 6")) {
						orderintial();
						pay6=new Pay(t_main,table_num);
						pay6.setVisible(true);
					}
					else if(table_num.equals("테이블 7")) {
						orderintial();
						pay7=new Pay(t_main,table_num);
						pay7.setVisible(true);
					}
					else if(table_num.equals("테이블 8")) {
						orderintial();
						pay8=new Pay(t_main,table_num);
						pay8.setVisible(true);
					}
					
				}
			}
		});
		
		orderBtn.setBounds(2, 386, 240, 60);
		cancelBtn.setBounds(243,386, 240, 60);
		before.setBounds(2, 550, 100, 30);
		payBtn.setBounds(104, 550, 100, 30);

		print_panel.add(orderBtn);
		print_panel.add(cancelBtn);
		print_panel.add(before);
		print_panel.add(payBtn);
		
		order();
		
		getContentPane().add(print_panel);		
		this.getContentPane().repaint();
	}
	
	//메뉴 카테고리 누를 때 메뉴 바뀌게 해줌
	public void menueffect(int num) {
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
				
			if(num == 0) {
				sql2 = "select * from meatmenu";
				this.num = 0;
			}
			else if(num == 1) {
				sql2 = "select * from mealmenu";
				this.num = 1;
			}
			else if(num == 2) {
				sql2 = "select * from drinks";
				this.num = 2;
			}
				
			pstmt = conn.prepareStatement(sql2);	//java statement 생성
			rs = pstmt.executeQuery(sql2);	//쿼리 execute, 객체 형성
			int i = 0;
			if(num == 0) {	//버튼 누를 때 초기화시켜주는 코드
				for(int j = 0; j<detailmenu.length; j++) {
					detailmenu[j].setText("");
				}
				while(rs.next()) {
					sql = "select * from meatmenu where gid = " +(i+1)+";";
					detailmenu[i].setText("<html><body>" + rs.getString("meat_name") + "<br>"+ rs.getInt("meat_price")+ "</body></html>");
					i++;	//gid값과 detailmenu버튼 인덱스 올려주기 위해 i++
				}
			}
			else if(num == 1) {
				for(int j = 0; j<detailmenu.length; j++) {
					detailmenu[j].setText("");
				}
				while(rs.next()) {
					sql = "select * from mealmenu where mid = " +(i+1)+";";
					detailmenu[i].setText("<html><body>" + rs.getString("meal_name") + "<br>"+ rs.getInt("meal_price")+ "</body></html>");
					i++;
				}
			}
			else if(num == 2) {
				for(int j = 0; j<detailmenu.length; j++) {
					detailmenu[j].setText("");
				}
				while(rs.next()) {
					sql = "select * from drinks where dr_id = " +(i+1)+";";
					detailmenu[i].setText("<html><body>" + rs.getString("dr_name") + "<br>"+ rs.getInt("dr_price")+ "</body></html>");
					i++;
				}
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException se) {
			System.out.println("에러: "+se);
		}
		finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	public void order() {	//원하는 메뉴 누르면
			
		detailmenu[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(1);
					}
				}
			}
			
		});
		
		
		detailmenu[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				// TODO Auto-generated method stub
				if(!detailmenu[1].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(2);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(3);
					}
				}
			}
		});
		
		detailmenu[3].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[3].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(4);
					}
				}
			}
		});
		
		detailmenu[4].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[4].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(5);
					}
				}
			}
		});
		
		detailmenu[5].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[5].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(6);
					}
				}
			}
		});
		
		detailmenu[6].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[6].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(7);
					}
				}
			}
		});
		
		detailmenu[7].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[7].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(8);
					}
				}
			}
		});
		
		detailmenu[8].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[8].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(9);
					}
				}
			}
		});
		
		detailmenu[9].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[9].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(10);
					}
				}
			}
		});
		
		detailmenu[10].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(11);
					}
				}
			}
		});
		
		detailmenu[11].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(12);
						
					}
				}
			}
		});
		detailmenu[12].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[12].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(13);
					}
				}
			}
		});
		detailmenu[13].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[13].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(14);
					}
				}
			}
		});
		detailmenu[14].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[14].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(15);
					}
				}
			}
		});
	}

	
	//고객이 주문한 메뉴들 화면에 출력
	public void printMenu(int me){
		menu = me;//0~14까지 버튼의 index가 들어감
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			pstmt = conn.prepareStatement(sql2);	//java statement 생성
			rs = pstmt.executeQuery(sql2);	//쿼리 execute, 객체 형성
			cnt[num][menu]++;
			while(rs.next()) {
				if(cnt[num][menu] == 1) {	
				//그 버튼을 처음 누르는 거라면(즉, 그 메뉴의 수량이 1이라면)
					if (num == 0) {
						sql2 = "select * from meatmenu where gid = " + menu + ";";
						pstmt=conn.prepareStatement(sql2);
						rs=pstmt.executeQuery(sql2);
						while(rs.next()) {
							answer[0] = rs.getString("meat_name");
							answer[1] = String.valueOf(cnt[num][menu]);
							price[num][menu] = rs.getInt("meat_price");
							answer[2] = String.valueOf(price[num][menu]);
							model.addRow(answer);
							num_cnt[num][menu] = model.getRowCount()-1;
							totalprice += price[num][menu];
						}
					}
					else if (num == 1) {
						sql2 = "select * from mealmenu where mid = " + menu + ";";
						pstmt=conn.prepareStatement(sql2);
						rs=pstmt.executeQuery(sql2);
						while(rs.next()) {
							answer[0] = rs.getString("meal_name");
							answer[1] = String.valueOf(cnt[num][menu]);
							price[num][menu] = rs.getInt("meal_price");
							answer[2] = String.valueOf(price[num][menu]);
							model.addRow(answer);
							num_cnt[num][menu] = model.getRowCount()-1;
							totalprice += price[num][menu];
						}
					}	
					else if (num == 2) {
						sql2 = "select * from drinks where dr_id = " + menu + ";";
						pstmt=conn.prepareStatement(sql2);
						rs=pstmt.executeQuery(sql2);
						while(rs.next()) {
							answer[0] = rs.getString("dr_name");
							answer[1] = String.valueOf(cnt[num][menu]);
							price[num][menu] = rs.getInt("dr_price");
							answer[2] = String.valueOf(price[num][menu]);
							model.addRow(answer);
							num_cnt[num][menu] = model.getRowCount()-1;
							totalprice += price[num][menu];
						}
					}	
				}
				else {	//그 메뉴를 두 번 이상 주문하게 된다면
					model.setValueAt(cnt[num][menu],num_cnt[num][menu], 1);
					//cnt[num][menu]에 들어있는 값이 0행1열에 업데이트됨
					sumprice = price[num][menu] * cnt[num][menu];
					model.setValueAt(sumprice, num_cnt[num][menu], 2);
					totalprice += price[num][menu];
				}
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException se) {
			System.out.println("에러: "+se);
		}
		finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//result에 주문한 메뉴들과 총 가격 넣어줌
	public void printOrder() {
		result ="";
		result += "<html>";
		for(int i=0; i<menutable.getRowCount(); i++) {
			if(i > 3) {
				result += "...";
				result += "<br />";
				break;
		}
		result += menutable.getValueAt(i,0).toString();
		result += " x";
		result += menutable.getValueAt(i, 1);
		result += "<br />";
		}
		finalprice = String.valueOf(totalprice);
		result += "<br />";
		result += finalprice + "원";
		result += "</html>";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			sql = "insert into table_"+Integer.toString(tableNum)+"(menu, num, price) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);	//java statement 생성
			
			for(int i = 0; i < menutable.getRowCount(); i++) {
				pstmt.setString(1, menutable.getValueAt(i, 0).toString());
				pstmt.setInt(2, Integer.parseInt(String.valueOf(menutable.getValueAt(i, 1))));
				pstmt.setInt(3, Integer.parseInt(String.valueOf(menutable.getValueAt(i, 2))));
				pstmt.executeUpdate();
			}	
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException se) {
			System.out.println("에러: "+se);
		}
		finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	
	// result에 있는 값을 table_main으로 넘겨줌
	public void printTable(Table_main t_main) {
		if(tableNum == 1) {
			t_main.Table1(result);
		}
		else if(tableNum == 2) {
			t_main.Table2(result);
		}
		else if(tableNum == 3) {
			t_main.Table3(result);
		}
		else if(tableNum == 4) {
			t_main.Table4(result);
		}
		else if(tableNum == 5) {
			t_main.Table5(result);
		}
		else if(tableNum == 6) {
			t_main.Table6(result);
		}
		else if(tableNum == 7) {
			t_main.Table7(result);
		}
		else if(tableNum == 8) {
			t_main.Table8(result);
		}
	}	
	
	//주문 버튼 누를 때 이전 메뉴들 디비에서 삭제
	public void dbinitial() {
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			String t_num = Integer.toString(tableNum);
			
			sql = "delete from table_" + t_num + ";";
			
			pstmt = conn.prepareStatement(sql);	//java statement 생성
			pstmt.executeUpdate(sql);
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException se) {
			System.out.println("에러: "+se);
		}
		finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void cancel() {
		int row = menutable.getSelectedRow();	//취소버튼 누른 행 가져오기
		int rowNum = menutable.getRowCount();	//JTable의 데이터 개수 가져오기

		delPriceCnt(row);
		for(int i = row+1; i<rowNum; i++) {
			changenum(menutable.getValueAt(i,0),i);
		}
		initcnt(menutable.getValueAt(row, 0));
		
		if(row<0) return;
		model.removeRow(row);	
	}	
	
	//메뉴 취소 시 가격 줄여주기
	public void delPriceCnt(int row) {
		for(int i = 0; i<menuArr.length; i++) {
			for(int j = 0; j<menuArr[i].length; j++) {
				if(menuArr[i][j].equals(menutable.getValueAt(row, 0))) {
					totalprice -= Integer.parseInt(String.valueOf(menutable.getValueAt(row, 2)));
					cnt[i][j]=0;
				}
			}
		}
	}
	
	//테이블 위치 이동
	public void changenum(Object me,int su) {
		for(int i = 0; i<menuArr.length; i++) {
			for(int j = 0 ; j<menuArr[i].length; j++) {
				if(menuArr[i][j].equals(me.toString())){
					num_cnt[i][j]--;
				}
			}
		}
	}
	
	//수량 초기화
	public void initcnt(Object me) {
		for(int i = 0; i<menuArr.length; i++) {
			for(int j = 0; j<menuArr[i].length; j++) {
				if(menuArr[i][j].equals(me.toString())) {
					cnt[i][j] = 0;
					num_cnt[i][j] = 0;
				}
			}
		}
	}

	//결제 버튼 누르면 JTable 초기화
	public void orderintial() {
		for(int i = 0; i<menuArr.length; i++) {
			for(int j = 0; j<menuArr[i].length; j++) {
				cnt[i][j] = 0;
				num_cnt[i][j] = 0;
				price[i][j] = 0;
				model.setNumRows(0);
			}
		}
	}

}