import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

public class OrderMenu extends JFrame {
	
	private JLabel title_label = new JLabel("주문 등록");
	
	private JPanel menu_panel = new JPanel();	//메뉴 카테고리 있는 panel
	private JPanel detail_panel = new JPanel(); 	//상세 메뉴들이 나오는 panel
	
	private JButton[] menucategory = new JButton[10];
	private JButton[] detailmenu = new JButton[15];	//버튼 15개 생성
	
	private int[][] cnt = new int[3][15];
	private int[][] num = new int[3][15];
	private int sumprice = 0;
	
//	private String[][] menu;
//	private String[][] price;
	
	private DefaultTableModel model;
	
	private boolean[] flag = new boolean[5];
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public static void main(String[] args) {

			new OrderMenu();
		
	}
		
	public OrderMenu() {
		
		this.getContentPane().repaint();	//컴포넌트 재배치(새로고침 개념)
		setTitle("주문 등록 화면");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//창크기 조절 X
		getContentPane().setLayout(null); 	//자유롭게 위치 조정 가능
		
		title_label.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 40));
		title_label.setBounds(448, 2, 187, 60);
		getContentPane().add(title_label);
		
//		Menulist ml = new Menulist();
//		this.menu = ml.menu();
//		this.price = ml.price();
		
		menu();
		order();
	}
	
	
	public void menu() {
		
		menu_panel.setBounds(490, 72, 600, 84);
		menu_panel.setBackground(Color.LIGHT_GRAY);
		menu_panel.setLayout(new GridLayout(1,5));
		getContentPane().add(menu_panel);

		for (int i = 0; i<4; i++) {
			menu_panel.add(menucategory[i] = new JButton(""));
			
			menucategory[i].setBackground(Color.LIGHT_GRAY);
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
	}
	
	
	public void order() {
		
		detail_panel.setBounds(490, 159, 600, 297);
		detail_panel.setBackground(Color.WHITE);
		detail_panel.setLayout(new GridLayout(3,5));
		getContentPane().add(detail_panel);
		
		
		for (int i = 0; i<15; i++) {
			detail_panel.add(detailmenu[i] = new JButton(""));
			
			detailmenu[i].setBackground(new Color(204, 169, 221));
			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border컬러 지정
		}
		
		detailmenu[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						//tableshow(0);
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
						//tableshow(1);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
						//tableshow(2);
					}
				}
			}
			
		});
		
	}
	

	
	public void menueffect(int num) {
		conn = null;
		pstmt = null;
		rs = null;
		String sql = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");	//공동으로 써야하는 코드
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			//System.out.println("연결 성공");
			
			if(num == 0) {
				sql = "select * from meatmenu";
			}
//			else if(num == 1) {
//				sql = "select * from "
//			}
			
			pstmt = conn.prepareStatement(sql);	//java statement 생성
			rs = pstmt.executeQuery(sql);	//쿼리 execute, 객체 형성
			
			while(rs.next()) {
				if(num == 0) {					
					for (int i = 1; i<=15; i++) {
						sql = "select * from meatmenu where gid =" +i+";";
						detailmenu[i-1].setText("<html><body>" + rs.getString("meat_name") + "<br>"+ rs.getInt("meat_price")+ "</body></html>");
						System.out.println(rs.getString("meat_name")+rs.getInt("meat_price"));
					}
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
	
	
	//고객이 주문한 메뉴들 화면에 출력
	public void printMenu(){
		
	}
}








