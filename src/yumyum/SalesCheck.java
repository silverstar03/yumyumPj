package yumyum;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class SalesCheck extends JFrame{
	private JPanel menuMaechul;
	private JPanel dayMaechul;
	Connection conn = null,conn2=null;
	PreparedStatement pstmt=null,pstmt2=null;
	ResultSet rs=null,rs2=null;
	String sql="",sql_1="";

	
	private JLabel title_l;	
	private JLabel menu_l;
	private JLabel month_l,year_l; //일,월,년 
	private JButton previous;
	
	JComboBox year_list,month_list, date_list,menu_list; //날짜 선택
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SalesCheck();
	}
	
	public SalesCheck() {
		setTitle("매출 확인");
		setSize(980, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null); 	//자유롭게 위치 조정 가능
		setBackground(new Color(231, 230, 230));
		
		setting();
	}

	public void setting() {
		title_l = new JLabel("매출 확인");
		title_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 33));
		title_l.setBounds(420, 8, 187, 60);
		
		dayMaechul = new JPanel();
		dayMaechul.setLayout(null);
		dayMaechul.setBounds(20,91,460,295);
		dayMaechul.setBackground(Color.WHITE);
		
		menuMaechul = new JPanel();
		menuMaechul.setLayout(null);
		menuMaechul.setBounds(500, 91, 460, 432);
		menuMaechul.setBackground(Color.WHITE);
		
		previous = new JButton("돌아가기");
		previous.setBounds(20,12,96,56);
//		previous.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				setVisible(false);
//				Table_main tm=new Table_main();
//				tm.setVisible(true);
//			}
//		});
		
		menu_l=new JLabel("[메뉴별 매출]");
		menu_l.setBounds(10, 10, 100, 30);
		menuMaechul.add(menu_l);
		
		String[] years=new String[2];
		years[0]="2019";
		years[1]="2020";
		
		//달
		String [] months = new String[12];
		for (int i=0;i<months.length;i++) {
			months[i]=Integer.toString(i+1);
		}
		
		String [] dates = new String [31];
		for (int i=0;i<dates.length;i++) {
			dates[i] = Integer.toString(i+1);
		}
		
		year_list = new JComboBox(years);
		year_list.setBounds(37, 78, 71, 30);
		dayMaechul.add(year_list);
		
		month_list=new JComboBox(months);
		month_list.setBounds(155, 78, 64, 30);
		dayMaechul.add(month_list);
		
		date_list=new JComboBox(dates);
		date_list.setBounds(252, 78, 71, 30);
		dayMaechul.add(date_list);
		
		
		String [] menu = new String[23];
		menu[0]="전체보기";
		for(int i=1;i<15;i++) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost/yumyum1";
				conn=DriverManager.getConnection(url,"gogi1","2203");
				sql = "select * from meatmenu where gid="+i;
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery(sql);
				while(rs.next()) {
					menu[i]=rs.getString("meat_name");
				}
			}catch(ClassNotFoundException ce) {
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
		//meal_menu 들어가기
		for(int i=15;i<23;i++) {
			
		}
		
//		for(int i=1;i<15;i++) {
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				String url="jdbc:mysql://localhost/yumyum1";
//				conn=DriverManager.getConnection(url,"gogi1","2203");
//				sql = "select * from meatmenu where gid="+i;
//				pstmt=conn.prepareStatement(sql);
//				rs=pstmt.executeQuery(sql);
//				while(rs.next()) {
//					menu[i]=rs.getString("meat_name");
//				}
//			}catch(ClassNotFoundException ce) {
//				System.out.println("드라이버로딩실패");
//			}catch(SQLException e) {
//				System.out.println("에러: "+e);
//			}finally {
//				try{
//					if(conn!=null && !conn.isClosed()) {
//						conn.close();
//					}
//					if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
//				}catch(SQLException se) {
//					se.printStackTrace();
//				}
//			}
//		}
		
		
		menu_list = new JComboBox(menu);
		menu_list.setBounds(192, 120, 192, 30);
		dayMaechul.add(menu_list);
		
		JButton show_btn = new JButton("매출보기");
		show_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showM();
			}
		});
		show_btn.setBounds(165, 220, 115, 37);
		dayMaechul.add(show_btn);
		
		
		
		getContentPane().add(title_l);
		getContentPane().add(dayMaechul);
		
		JRadioButton oneday = new JRadioButton("일일매출");
		oneday.setBounds(121, 17, 98, 27);
		dayMaechul.add(oneday);
		
		JRadioButton monthly = new JRadioButton("월별매출");
		monthly.setBounds(235, 17, 98, 27);
		dayMaechul.add(monthly);
		
		
		JLabel lblNewLabel = new JLabel("\uB144");
		lblNewLabel.setBounds(121, 78, 31, 30);
		dayMaechul.add(lblNewLabel);
		
		JLabel label = new JLabel("\uC6D4");
		label.setBounds(224, 78, 31, 30);
		dayMaechul.add(label);
		
		JLabel label_1 = new JLabel("\uC77C");
		label_1.setBounds(337, 78, 31, 30);
		dayMaechul.add(label_1);
		
		JLabel chooseMenu = new JLabel("메뉴선택 : ",JLabel.CENTER);
		chooseMenu.setBounds(37, 120, 126, 30);
		dayMaechul.add(chooseMenu);
		
		getContentPane().add(menuMaechul);
		getContentPane().add(previous);
		
		setVisible(true);
	}
//	public void menuList() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			String url="jdbc:mysql://localhost/yumyum1";
//			conn=DriverManager.getConnection(url,"gogi1","2203");
//			
//			sql = "select  product,sum(count) count,sum(price*count) total "
//					+ "from maechul_2 where m_year="+Integer.parseInt(today_year)
//					+" and m_month="+Integer.parseInt(tm)+" and m_date="+Integer.parseInt(td)+" group by product;";
//			pstmt=conn.prepareStatement(sql);
//			rs=pstmt.executeQuery(sql);
//			while(rs.next()) {
//				System.out.println("product : "+rs.getString(1)+" /count : "+rs.getInt(2)+" /total : "+rs.getInt(3));
//			 }
//		}catch(ClassNotFoundException e) {
//			System.out.println("드라이버로딩실패");
//		}catch(SQLException e) {
//			System.out.println("에러: "+e);
//		}finally {
//			try{
//				if(conn!=null && !conn.isClosed()) {
//					conn.close();
//				}
//				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) {}
//			}catch(SQLException se) {
//				se.printStackTrace();
//			}
//		}
//	}
	public void showM() {
		SimpleDateFormat tyear = new SimpleDateFormat ( "yyyy");
		SimpleDateFormat tmonth = new SimpleDateFormat ( "MM");
		SimpleDateFormat tdate = new SimpleDateFormat ( "dd");
		
		Date time = new Date();
		
		String today_year = tyear.format(time);
		String today_month = tmonth.format(time);
		String today_date = tdate.format(time);
		
		String tm=month_list.getSelectedItem().toString(); //선택된 달
		String td=date_list.getSelectedItem().toString(); //선택된 일
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/yumyum1";
			conn=DriverManager.getConnection(url,"gogi1","2203");
			
			// 0 : 한달 전체보기
			if(td.equals("0")) {
				sql = "select  product,sum(count) count,sum(price*count) total "
						+ "from maechul_1 where m_year="+Integer.parseInt(today_year)
						+" and m_month="+Integer.parseInt(tm)+" group by product;";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println("product : "+rs.getString(1)+" /count : "+rs.getInt(2)+" /total : "+rs.getInt(3));
				}
			}
			//그 외의 값이라면
			else {
				sql = "select  product,sum(count) count,sum(price*count) total "
						+ "from maechul_1 where m_year="+Integer.parseInt(today_year)
						+" and m_month="+Integer.parseInt(tm)+" and m_date="+Integer.parseInt(td)+" group by product;";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println("product : "+rs.getString(1)+" /count : "+rs.getInt(2)+" /total : "+rs.getInt(3));
				}
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
}
