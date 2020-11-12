package yumyum;

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
	
	private JPanel menu_panel;	//�޴� ī�װ��� �ִ� panel
	private JPanel detail_panel; 	//�� �޴����� ������ panel
	private JPanel print_panel;	//�ֹ� ����, ���, �ֹ��ִ� panel
	
	private JButton[] menucategory;	//ū �޴� ī�װ���
	private JButton[] detailmenu;	//��ư 15�� ����
	private JButton orderBtn; //�ֹ���ư
	private JButton cancelBtn; //������ư
	private JButton before;		//������ư
	private JButton payBtn;
	
	private JTable menutable;	//�ֹ����� �����ִ� JTable
	
	private DefaultTableModel model;	//menutable��ü�� ���� model
	private JScrollPane scrollPane;		//menutable��ü �߰��ϴ� JScrollPane
	private ArrayList<String> list = new ArrayList<>();	//colNames ��� �߰��ϴ� ArrayList
	private String[] colNames;	
	private String[] answer;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String sql;
	private String sql2;	//�ٸ� �޼����� �Ѱ��ִ� sql
	
	private int[][] cnt = new int[3][15];	//�� �޴����� ���� �˷��ִ� �迭
	private int[][] num_cnt = new int[3][15];	
	private int[][] price = new int[3][15];	//�� �޴������� ����
	
	private int row;	//�ֹ� ����� ��
	private int num = 0;	//�޴� ī�װ��� �ε��� �޾ƿ��� ����
	private int sumprice = 0;	//�� �޴��� �� ����
	private int totalprice = 0;	//��ü ����
	private int menu = 0;
	private int tableNum = 0;
	
	private String result = "";
	private String finalprice = "";
	
	Table_main t_main;
	Pay pay;
	
		
	//������ �޼���
	public OrderMenu(String table_num, Table_main t_main) {
		
		this.getContentPane().repaint();	//������Ʈ ���ġ(���ΰ�ħ ����)
		setTitle("�ֹ� ��� ȭ��");
		setSize(1101, 682);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//x������ â �����
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//âũ�� ���� X
		getContentPane().setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		getContentPane().setBackground(new Color(231, 230, 230));
		
		title_label = new JLabel("�ֹ� ���");
		title_label.setFont(new Font("��ü�� ���� ��ü", Font.PLAIN, 40));
		title_label.setBounds(448, 2, 187, 60);
		add(title_label);
				
		menu(table_num, t_main);
	}
	
	public void menu(String table_num, Table_main t_main) {	//�޴� ��ư�� ����
		
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
			
			menucategory[i].setBackground(new Color(135,152,168));
			menucategory[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		}
		
		menucategory[0].setText("�����");
			
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
		
		print_panel = new JPanel();
		print_panel.setBounds(0, 71, 486, 582);
		print_panel.setLayout(null);
		print_panel.setBackground(new Color(231, 230, 230));
		
		list.add("�޴�");
		list.add("����");
		list.add("����");
		colNames = list.toArray(new String[list.size()]);
		answer = new String[3];
		

		model = new DefaultTableModel(colNames, 0) {
			public boolean isCellEditable(int i, int c) {//���̺� ���� Ŭ���� ���� �Ұ�
				return false;
			}
		};
		
		menutable = new JTable(model);
		menutable.getTableHeader().setReorderingAllowed(false); //table header �̵� �Ұ�
		menutable.getTableHeader().setResizingAllowed(false); // ũ�� ���� �Ұ�
		scrollPane = new JScrollPane(menutable);
		
		scrollPane.setBounds(0, 0, 486, 385);
		scrollPane.getViewport().setBackground(Color.WHITE);
		print_panel.add(scrollPane);
		
		orderBtn = new JButton("�ֹ�");
		orderBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableNum = Integer.parseInt(table_num.substring(4));
				if(menutable.getRowCount()!=0) {
					printOrder();
					printTable(t_main);
					setVisible(false);
					t_main.setVisible(true);					
				}
			}
		});
		
		
		cancelBtn = new JButton("���");
		cancelBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					if(menutable.getSelectedRow()!=1) {
//						cancel();
					}
				}				
			}
		});
		
		before = new JButton("����");
		before.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					setVisible(false);
					t_main.setVisible(true);
				}
			}
		});
		
		payBtn = new JButton("����");
		payBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					setVisible(false);
					pay = new Pay(t_main, table_num);
					pay.setVisible(true);
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
	
	//�޴� ī�װ��� ���� �� �޴� �ٲ�� ����
	public void menueffect(int num) {
		try {
			Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
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
				
			pstmt = conn.prepareStatement(sql2);	//java statement ����
			rs = pstmt.executeQuery(sql2);	//���� execute, ��ü ����
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
	
	
	public void order() {	//���ϴ� �޴� ������
			
		detailmenu[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				// TODO Auto-generated method stub
				if(!detailmenu[0].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(1);
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
						printMenu(2);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(3);
					}
				}
			}
		});
		
		detailmenu[3].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[3].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(4);
					}
				}
			}
		});
		
		detailmenu[4].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[4].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(5);
					}
				}
			}
		});
		
		detailmenu[5].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[5].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(6);
					}
				}
			}
		});
		
		detailmenu[6].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[6].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(7);
					}
				}
			}
		});
		
		detailmenu[7].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[7].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(8);
					}
				}
			}
		});
		
		detailmenu[8].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[8].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(9);
					}
				}
			}
		});
		
		detailmenu[9].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[9].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(10);
					}
				}
			}
		});
		
		detailmenu[10].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(11);
					}
				}
			}
		});
		
		detailmenu[11].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[11].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(12);
						
					}
				}
			}
		});
		detailmenu[12].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[12].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(13);
					}
				}
			}
		});
		detailmenu[13].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[13].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(14);
					}
				}
			}
		});
		detailmenu[14].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[14].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						printMenu(15);
					}
				}
			}
		});
	}

	
	//������ �ֹ��� �޴��� ȭ�鿡 ���
	public void printMenu(int me){
		menu = me;
		try {
			Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2209");
			pstmt = conn.prepareStatement(sql2);	//java statement ����
			rs = pstmt.executeQuery(sql2);	//���� execute, ��ü ����
			cnt[num][menu]++;
			while(rs.next()) {
				if(cnt[num][menu] == 1) {	
				//�� ��ư�� ó�� ������ �Ŷ��(��, �� �޴��� ������ 1�̶��)
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
				else {	//�� �޴��� �� �� �̻� �ֹ��ϰ� �ȴٸ�
					model.setValueAt(cnt[num][menu],num_cnt[num][menu], 1);
					//cnt[num][menu]�� ����ִ� ���� 0��1���� ������Ʈ��
					sumprice = price[num][menu] * cnt[num][menu];
					model.setValueAt(sumprice, num_cnt[num][menu], 2);
					totalprice += price[num][menu];
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
	
	
	public void printOrder() {
		result = "";
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
		result += finalprice + "��";
		result += "</html>";

		try {
			Class.forName("com.mysql.jdbc.Driver");	//�������� ����ϴ� �ڵ�
			String url = "jdbc:mysql://localhost/yumyum1";
			conn = DriverManager.getConnection(url,"gogi1","2203");
			switch(tableNum) {
			case 1:
				sql = "insert into table_1(menu, num, price) values(?,?,?)";break;
			case 2:
				sql = "insert into table_2(menu, num, price) values(?,?,?)";break;
			case 3:
				sql = "insert into table_3(menu, num, price) values(?,?,?)";break;
			case 4:
				sql = "insert into table_4(menu, num, price) values(?,?,?)";break;
			case 5:
				sql = "insert into table_5(menu, num, price) values(?,?,?)";break;
			case 6:
				sql = "insert into table_6(menu, num, price) values(?,?,?)";break;
			case 7:
				sql = "insert into table_7(menu, num, price) values(?,?,?)";break;
			case 8:
				sql = "insert into table_8(menu, num, price) values(?,?,?)";break;
			}
			pstmt = conn.prepareStatement(sql);	//java statement ����
			
			for(int i = 0; i < menutable.getRowCount(); i++) {
				pstmt.setString(1, menutable.getValueAt(i, 0).toString());
				pstmt.setInt(2, Integer.parseInt(String.valueOf(menutable.getValueAt(i, 1))));
				pstmt.setInt(3, Integer.parseInt(String.valueOf(menutable.getValueAt(i, 2))));
				pstmt.executeUpdate();
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
}