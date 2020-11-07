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
	
	private JPanel menu_panel;	//�޴� ī�װ� �ִ� panel
	private JPanel detail_panel; 	//�� �޴����� ������ panel
	private JPanel print_panel;
	
	private JButton[] menucategory;
	private JButton[] detailmenu;	//��ư 15�� ����
	
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
	
	//���α׷� �ϼ��Ǹ� ���� main
	public static void main(String[] args) {
			new OrderMenu();
	}
		
	//������ �޼���
	public OrderMenu() {
		
		this.getContentPane().repaint();	//������Ʈ ���ġ(���ΰ�ħ ����)
		setTitle("�ֹ� ��� ȭ��");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//âũ�� ���� X
		setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		getContentPane().setBackground(new Color(231, 230, 230));
		
		title_label = new JLabel("�ֹ� ���");
		title_label.setFont(new Font("��ü�� ���� ��ü", Font.PLAIN, 40));
		title_label.setBounds(448, 2, 187, 60);
		add(title_label);
		
//		printMenu(sql, menu);
		menu();
	}
	
	
	public void menu() {	//�޴� ��ư�� ����
		
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

		
		detailmenu = new JButton[15];
		for (int i = 0; i<15; i++) {
			detail_panel.add(detailmenu[i] = new JButton(""));
			
			detailmenu[i].setBackground(new Color(179, 197, 207));
			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border�÷� ����
		}
	}
	
	//�޴� ī�װ� ���� �� �޴� �ٲ�� ����
		public void menueffect(int num) {
			String sql="";
			try {
				Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
				String url = "jdbc:mysql://localhost/yumyum1";
				conn = DriverManager.getConnection(url,"gogi1","2203");
				//System.out.println("���� ����");
				
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
				
				pstmt = conn.prepareStatement(sql);	//java statement ����
				rs = pstmt.executeQuery(sql);	//���� execute, ��ü ����
				
				int i = 0;
					if(num == 0) {	//��ư ���� �� �ʱ�ȭ�����ִ� �ڵ�
						for(int j = 0; j<detailmenu.length; j++) {
							detailmenu[j].setText("");
						}
						while(rs.next()) {
							sql = "select * from meatmenu where gid = " +(i+1)+";";
							detailmenu[i].setText("<html><body>" + rs.getString("meat_name") + "<br>"+ rs.getInt("meat_price")+ "</body></html>");
							i++;	//gid���� detailmenu��ư �ε��� �÷��ֱ� ���� i++
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
	
	
	public void order(String sql) {	//���ϴ� �޴� ������ 
		detailmenu[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,1);
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
						printMenu(sql,2);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,3);
					}
				}
			}
		});
		
		detailmenu[3].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[3].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,4);
					}
				}
			}
		});
		
		detailmenu[4].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[4].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,5);
					}
				}
			}
		});
		
		detailmenu[5].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[5].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,6);
					}
				}
			}
		});
		
		detailmenu[6].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[6].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,7);
					}
				}
			}
		});
		
		detailmenu[7].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[7].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,8);
					}
				}
			}
		});
		
		detailmenu[8].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[8].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,9);
					}
				}
			}
		});
		
		detailmenu[9].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[9].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,10);
					}
				}
			}
		});
		
		detailmenu[10].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,11);
					}
				}
			}
		});
		
		detailmenu[11].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,12);
					}
				}
			}
		});
		detailmenu[12].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[12].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,13);
					}
				}
			}
		});
		detailmenu[13].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[13].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,14);
					}
				}
			}
		});
		detailmenu[14].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[14].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(sql,15);
					}
				}
			}
		});
	}

	
	//���� �ֹ��� �޴��� ȭ�鿡 ���
	public void printMenu(String sql, int menu){
		print_panel = new JPanel();
		print_panel.setBounds(0, 71, 486, 582);
		print_panel.setLayout(null);
		print_panel.setBackground(new Color(231, 230, 230));
		
		colNames = new String[3];
//		colNames[0] = "�޴�";
		
		
		String answer[] = new String[3];
		int a = 1;
		model = new DefaultTableModel(colNames, 0);
		menutable = new JTable(model);
		menutable.getTableHeader().setReorderingAllowed(false); //table header �̵� �Ұ�
		menutable.getTableHeader().setResizingAllowed(false); // ũ�� ���� �Ұ�

		scrollPane = new JScrollPane(menutable);
		
		scrollPane.setBounds(0, 0, 486, 385);
		scrollPane.getViewport().setBackground(Color.WHITE);
		print_panel.add(scrollPane);
		getContentPane().add(print_panel);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			pstmt = conn.prepareStatement(sql);	//java statement ����
			rs = pstmt.executeQuery(sql);	//���� execute, ��ü ����
			
			while(rs.next()) {//ResultSet�� ����� ������ ���
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
}








