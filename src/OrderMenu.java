import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrderMenu extends JFrame {
	
	private JLabel title_label;
	
	private JPanel menu_panel;	//메뉴 카테고리 있는 panel
	private JPanel detail_panel; 	//상세 메뉴들이 나오는 panel
	private JPanel print_panel;
	
	private JButton[] menucategory;
	private JButton[] detailmenu;	//버튼 15개 생성
	
	private JLabel name;
	private JLabel num;
	private JTextField price;
	
	private JTable menutable;

	private int menu;
	
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private String[] colNames;
	private String[] answer;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//프로그램 완성되면 지울 main
	public static void main(String[] args) {
			new OrderMenu();
	}
		
	//생성자 메서드
	public OrderMenu() {
		
		this.getContentPane().repaint();	//컴포넌트 재배치(새로고침 개념)
		setTitle("주문 등록 화면");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//창크기 조절 X
		setLayout(null); 	//자유롭게 위치 조정 가능
		getContentPane().setBackground(new Color(231, 230, 230));
		
		title_label = new JLabel("주문 등록");
		title_label.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 40));
		title_label.setBounds(448, 2, 187, 60);
		add(title_label);
		
//		printMenu(sql, menu);
		menu();
	}
	
	
	public void menu() {	//메뉴 버튼들 생성
		
		menu_panel = new JPanel();
		menu_panel.setBounds(490, 72, 600, 84);
		menu_panel.setLayout(new GridLayout(1,5));
		getContentPane().add(menu_panel);
		
		detail_panel = new JPanel();
		detail_panel.setBounds(490, 159, 600, 300);
		detail_panel.setLayout(new GridLayout(3,5));
		getContentPane().add(detail_panel);
		
		menucategory = new JButton[10];
		for (int i = 0; i<4; i++) {
			menu_panel.add(menucategory[i] = new JButton(""));
			
			menucategory[i].setBackground(new Color(135,152,168));
			menucategory[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
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
			
			detailmenu[i].setBackground(new Color(179, 197, 207));
			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border컬러 지정
		}
	}
	
	//메뉴 카테고리 누를 때 메뉴 바뀌게 해줌
		public void menueffect(int num) {
			String sql="";
			try {
				Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
				String url = "jdbc:mysql://localhost/yumyum1";
				conn = DriverManager.getConnection(url,"gogi1","2203");
				//System.out.println("연결 성공");
				
				if(num == 0) {
					sql = "select * from meatmenu";
					order(sql);
				}
				else if(num == 1) {
					sql = "select * from mealmenu";
					order(sql);
				}
				else if(num == 2) {
					sql = "select * from drinks";
					order(sql);
				}
				
				pstmt = conn.prepareStatement(sql);	//java statement 생성
				rs = pstmt.executeQuery(sql);	//쿼리 execute, 객체 형성
				
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
	
	
	public void order(String sql) {	//원하는 메뉴 누르면 
		detailmenu[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,1);
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
						printMenu(sql,2);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,3);
					}
				}
			}
		});
		
		detailmenu[3].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[3].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,4);
					}
				}
			}
		});
		
		detailmenu[4].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[4].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,5);
					}
				}
			}
		});
		
		detailmenu[5].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[5].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,6);
					}
				}
			}
		});
		
		detailmenu[6].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[6].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,7);
					}
				}
			}
		});
		
		detailmenu[7].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[7].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,8);
					}
				}
			}
		});
		
		detailmenu[8].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[8].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,9);
					}
				}
			}
		});
		
		detailmenu[9].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[9].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,10);
					}
				}
			}
		});
		
		detailmenu[10].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,11);
					}
				}
			}
		});
		
		detailmenu[11].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,12);
					}
				}
			}
		});
		detailmenu[12].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[12].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,13);
					}
				}
			}
		});
		detailmenu[13].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[13].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,14);
					}
				}
			}
		});
		detailmenu[14].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[14].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						printMenu(sql,15);
					}
				}
			}
		});
	}

	
	//고객이 주문한 메뉴들 화면에 출력
	public void printMenu(String sql, int menu){
		print_panel = new JPanel();
		print_panel.setBounds(0, 71, 486, 582);
		print_panel.setLayout(null);
		print_panel.setBackground(new Color(231, 230, 230));
		
		colNames = new String[3];
//		colNames[0] = "메뉴";
		
		
		String answer[] = new String[3];
		int a = 1;
		model = new DefaultTableModel(colNames, 0);
		menutable = new JTable(model);
		menutable.getTableHeader().setReorderingAllowed(false); //table header 이동 불가
		menutable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가

		scrollPane = new JScrollPane(menutable);
		
		scrollPane.setBounds(0, 0, 486, 385);
		scrollPane.getViewport().setBackground(Color.WHITE);
		print_panel.add(scrollPane);
		getContentPane().add(print_panel);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			pstmt = conn.prepareStatement(sql);	//java statement 생성
			rs = pstmt.executeQuery(sql);	//쿼리 execute, 객체 형성
			
			while(rs.next()) {//ResultSet에 저장된 데이터 얻기
				rs.next();
					if(sql == "select * from meatmenu") {
						sql += "where gid = " +menu+";";
						answer[0] = rs.getString("meat_name");
						answer[1] = rs.getString("meat_price");
						answer[2] = String.valueOf(a);
						model.addRow(answer);
					}
					else if(sql == "select * from drinks") {
						sql += "where mid = " + menu + ";";
						answer[0] = rs.getString("meal_name");
						answer[1] = rs.getString("meal_price");
						answer[2] = String.valueOf(a);
						model.addRow(answer);
					}
					else if(sql == "select * from drinks") {
						sql += "where dr_id = " + menu + ";";
						answer[0] = rs.getString("dr_name");
						answer[1] = rs.getString("dr_price");
						answer[2] = String.valueOf(a);
						model.addRow(answer);
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
}








