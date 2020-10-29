import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JTextField;
//database ver.
public class Table_main {
	
	private JFrame frame;
	protected Object frame2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table_main window = new Table_main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Table_main() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setTitle("냠냠쩝쩝");
		frame.setBounds(100, 100, 761, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			//DB연동
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/yumyum1";
			//mysql을 아예 내가 올려주면 은별이가 pull받으면 될 것 같음.
			conn=DriverManager.getConnection(url,"gogi1","2209");
			System.out.println("연결 성공");
			int num=1;
			String sql="select * from meatmenu where gid = "+num+";";
			pstmt=conn.prepareStatement(sql);
			//이 문장을 실행함으로써 결과값을 rs에 넣어준다.
			rs=pstmt.executeQuery(sql);
			while (rs.next()) {
				String meat_name=rs.getString("meat_name");
				int price=rs.getInt("meat_price");
				
				System.out.println("고기 종류: "+meat_name+" / 가격: "+price);
				
				JLabel label = new JLabel("가격 : ");
				label.setAlignmentX(100);
				label.setAlignmentY(400);
				
				JLabel pricet1 = new JLabel("");
				pricet1.setText(Integer.toString(price));
				
				JButton button1 = new JButton("테이블 1");
				button1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//버튼을 누르면 다음 패널로 넘어가욤
						
					}
				});
				button1.setBounds(29, 47, 147, 127);
				frame.getContentPane().add(button1);
				button1.add(label);
				button1.add(pricet1);
				
				JButton button2 = new JButton("테이블 2");
				button2.setBounds(215, 47, 147, 127);
				frame.getContentPane().add(button2);
		
				JButton button3 = new JButton("테이블 3");
				button3.setBounds(397, 47, 147, 127);
				frame.getContentPane().add(button3);
		
				JButton button4 = new JButton("테이블 4");
				button4.setBounds(582, 47, 147, 127);
				frame.getContentPane().add(button4);
		
				JButton button5 = new JButton("테이블 5");
				button5.setBounds(29, 247, 147, 127);
				frame.getContentPane().add(button5);
		
				JButton button6 = new JButton("테이블 6");
				button6.setBounds(215, 247, 147, 127);
				frame.getContentPane().add(button6);
		
				JButton button7 = new JButton("테이블 7");
				button7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				button7.setBounds(397, 247, 147, 127);
				frame.getContentPane().add(button7);
		
				JButton button8 = new JButton("테이블 8");
				button8.setBounds(582, 247, 147, 127);
				frame.getContentPane().add(button8);
		
				JButton checkbtn = new JButton("매출확인");
				checkbtn.setBounds(609, 420, 120, 27);
				frame.getContentPane().add(checkbtn);
				}
			conn.close();
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
