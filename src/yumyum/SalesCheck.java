package 냠냠쩝쩝;

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



public class SalesCheck extends JFrame{
	private JPanel menuMaechul;
	private JPanel dayMaechul;
	Connection conn = null,conn2=null;
	PreparedStatement pstmt=null,pstmt2=null;
	ResultSet rs=null,rs2=null;
	String sql="",sql_1="";
/*
 * totalPrice : 고객이 주문한 메뉴의 총 가격
 * sales : 총 가격이 누적되어 하루 매출이 담긴 변수
 * 
 * SalesCheck() : 생성자 메소드
 * CountSales() : 매출을 계산해주는 메소드
 * printSales() : 누적된 매출을 화면에 출력해주는 메소드
 */
	
	private JLabel title_l;	
	private JLabel menu_l;
	private JLabel day_l,month_l,year_l; //일,월,년 
	private JButton previous;
	
	JComboBox month_list, date_list,menu_list; //날짜 선택
	
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
		setLayout(null); 	//자유롭게 위치 조정 가능
		setBackground(new Color(231, 230, 230));
		
		setting();
	}

	public void setting() {
		title_l = new JLabel("매출 확인");
		title_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 33));
		title_l.setBounds(420, 8, 187, 60);
		
		dayMaechul = new JPanel();
		dayMaechul.setLayout(null);
		dayMaechul.setBounds(20,91,460,432);
		dayMaechul.setBackground(Color.WHITE);
		
		menuMaechul = new JPanel();
		menuMaechul.setLayout(null);
		menuMaechul.setBounds(500, 91, 460, 432);
		menuMaechul.setBackground(Color.WHITE);
		
		previous = new JButton("돌아가기");
		previous.setBounds(20,12,105,60);
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
		
		String [] months = new String[12];
		for (int i=0;i<months.length;i++) {
			months[i]=Integer.toString(i+1);
		}
		
		String [] dates = new String [32];
		dates[0]="0";
		for (int i=1;i<dates.length;i++) {
			dates[i] = Integer.toString(i);
		}
		
		month_list=new JComboBox(months);
		month_list.setBounds(20, 52, 50, 30);
		dayMaechul.add(month_list);
		
		date_list=new JComboBox(dates);
		date_list.setBounds(80, 52, 50, 30);
		dayMaechul.add(date_list);
		
		day_l=new JLabel("[일별 매출]");
		day_l.setBounds(14,22,100,30);
		/*month_l=new JLabel("[월별 매출]");
		month_l.setBounds(14, 160, 100, 30);
		year_l=new JLabel("[연도별 매출]");
		year_l.setBounds(14, 302, 100, 30);*/
		
		dayMaechul.add(day_l);
		//dayMaechul.add(month_l);
		//dayMaechul.add(year_l);
		
		//메뉴리스트 나오는것 : 테스트용이기때문에 노가다로 함 => 적용 안됨 =>볼필요없음
		String [] menu = new String[5];
		menu[0]="생고기";
		menu[1]="양념갈비";
		menu[2]="공기밥";
		menu[3]="계란찜";
		menu[4]="육회";
		
		menu_list=new JComboBox(menu);
		menu_list.setBounds(146, 55, 87, 30);
		dayMaechul.add(month_list);
		
		JButton show_btn = new JButton("\uB9E4\uCD9C\uBCF4\uAE30");
		show_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showM();
			}
		});
		show_btn.setBounds(260, 54, 105, 27);
		dayMaechul.add(show_btn);
		
		
		
		add(title_l);
		add(dayMaechul);
		add(menuMaechul);
		add(previous);
		
		setVisible(true);
	}

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
			conn=DriverManager.getConnection(url,"gogi1","2209");
			
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
