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
	
	private JLabel title_label = new JLabel("�ֹ� ���");
	
	private JPanel menu_panel = new JPanel();	//�޴� ī�װ� �ִ� panel
	private JPanel detail_panel = new JPanel(); 	//�� �޴����� ������ panel
	
	private JButton[] menucategory = new JButton[10];
	private JButton[] detailmenu = new JButton[15];	//��ư 15�� ����
	
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
		
		this.getContentPane().repaint();	//������Ʈ ���ġ(���ΰ�ħ ����)
		setTitle("�ֹ� ��� ȭ��");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//âũ�� ���� X
		getContentPane().setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		
		title_label.setFont(new Font("��ü�� ���� ��ü", Font.PLAIN, 40));
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
		
		menucategory[0].setText("����");
			
		menucategory[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menueffect(0);				
				}
			});
			
		menucategory[1].setText("�Ļ��");
		
		menucategory[1].addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				menueffect(1);				
			}
		});
		
		menucategory[2].setText("����");
		
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
			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border�÷� ����
		}
		
		detailmenu[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						//tableshow(0);
					}
				}
			}
			
		});
		
		detailmenu[1].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				// TODO Auto-generated method stub
				if(!detailmenu[1].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						//tableshow(1);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
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
			Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			//System.out.println("���� ����");
			
			if(num == 0) {
				sql = "select * from meatmenu";
			}
//			else if(num == 1) {
//				sql = "select * from "
//			}
			
			pstmt = conn.prepareStatement(sql);	//java statement ����
			rs = pstmt.executeQuery(sql);	//���� execute, ��ü ����
			
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
			System.out.println("����̹� �ε� ����");
		}catch(SQLException se) {
			System.out.println("����: "+se);
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
	
	
	//���� �ֹ��� �޴��� ȭ�鿡 ���
	public void printMenu(){
		
	}
}








