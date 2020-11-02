import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Table_main extends JFrame {
	private JPanel jp;
	
	//���̺� 8��
	private JButton table1;
	private JButton table2;
	private JButton table3;
	private JButton table4;
	private JButton table5;
	private JButton table6;
	private JButton table7;
	private JButton table8;
	//����Ȯ���ϴ� ��ư 
	private JButton check_maechul;
	//���� 
	private JLabel price1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Table_main();
	}
	public Table_main() {
		initialize();
	}
	private void initialize() {
		//�����ͺ��̽� ������ �ʿ��� ��ҵ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		setTitle("�ȳ�����");
		setBounds(100,100,761,520);
		setLocationRelativeTo(null);	//â�� ������� ����
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/yumyum1";
			//mysql ���� database,���̺� �̸�,��й�ȣ
			conn=DriverManager.getConnection(url,"gogi1","2209");
			System.out.println("���� ����");
			//gid ���° ������� ���� => �޴����� ��ư�� �����ϸ� �� ���̵� �Ѿ��.
			int num=1;
			String sql="select * from meatmenu where gid = "+num+";";
			//������� �ޱ����ؼ� 
			pstmt=conn.prepareStatement(sql);
			//�� ������ ���������ν� ������� rs�� �־��ش�.
			rs=pstmt.executeQuery(sql);
			while (rs.next()) {
				String meat_name=rs.getString("meat_name");
				int price=rs.getInt("meat_price");
				jp=new JPanel();
				jp.setLayout(null);
				System.out.println("��� ����: "+meat_name+" / ����: "+price);
				price1=new JLabel();
				price1.setBounds(10, 10, 147, 30);
				price1.setText("���� : " + Integer.toString(price));
				
				table1 = new JButton("���̺� 1");
				table1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//��ư�� ������ ���� �гη� �Ѿ��
						
					}
				});
				table1.setBounds(29, 47, 147, 127);
				table1.add(price1);
				
				table2 = new JButton("���̺� 2");
				table2.setBounds(215, 47, 147, 127);
		
			    table3 = new JButton("���̺� 3");
				table3.setBounds(397, 47, 147, 127);
		
				table4 = new JButton("���̺� 4");
				table4.setBounds(582, 47, 147, 127);
		
				table5 = new JButton("���̺� 5");
				table5.setBounds(29, 247, 147, 127);
		
				table6 = new JButton("���̺� 6");
				table6.setBounds(215, 247, 147, 127);

				table7 = new JButton("���̺� 7");
				table7.setBounds(397, 247, 147, 127);
		
				table8 = new JButton("���̺� 8");
				table8.setBounds(582, 247, 147, 127);

				check_maechul = new JButton("����Ȯ��");
				check_maechul.setBounds(609, 420, 120, 27);
				
				jp.add(table1); 
				jp.add(table2); jp.add(table3); jp.add(table4);
				jp.add(table5); jp.add(table6); jp.add(table7); jp.add(table8);
				jp.add(check_maechul);
				add(jp);
				
				setVisible(true);
			}
			conn.close();
		}catch(ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		}catch(SQLException se) {
			System.out.println("����: "+se);
		}
		finally {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException se) { }
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
