import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
	
	private String[][] menu;
	private String[][] price;
	
	private DefaultTableModel model;
	
	private boolean[] flag = new boolean[5];
	
	
	public static void main(String[] args) {

			OrderMenu frame = new OrderMenu();
			frame.setVisible(true);	//â�� ȭ�鿡 ��Ÿ��
		
	}
		
	public OrderMenu() {
		orderMenu2Frame();
		this.getContentPane().repaint();	//������Ʈ ���ġ(���ΰ�ħ ����)
	}
	
	public void orderMenu2Frame() {
		
		setTitle("�ֹ� ��� ȭ��");
		setSize(1100, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//âũ�� ���� X
		getContentPane().setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		
		title_label.setFont(new Font("��ü�� ���� ��ü", Font.PLAIN, 40));
		title_label.setBounds(448, 2, 187, 60);
		getContentPane().add(title_label);
		
		Menulist ml = new Menulist();
		this.menu = ml.menu();
		this.price = ml.price();
		
		menu();
		detailedMenu();

		
	}
	
	public void menu() {
		 
		menu_panel.setBounds(490, 114, 600, 84);
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
	
	
	public void detailedMenu() {
		
		detail_panel.setBounds(490, 201, 600, 297);
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
						tableshow(0);
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
						tableshow(1);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						tableshow(2);
					}
				}
			}
			
		});
		
	}
	

	
	public void menueffect(int num) {
		detailmenu[0].setText("<html><body>" + menu[num][0] + "<br>"+ price[num][0]+ "</body></html>");
		detailmenu[1].setText("<html><body>" + menu[num][1] + "<br>"+ price[num][1]+ "</body></html>");
		detailmenu[2].setText("<html><body>" + menu[num][2] + "<br>"+ price[num][2]+ "</body></html>");
		detailmenu[3].setText("<html><body>" + menu[num][3] + "<br>"+ price[num][3]+ "</body></html>");
		detailmenu[4].setText("<html><body>" + menu[num][4] + "<br>"+ price[num][4]+ "</body></html>");
		detailmenu[5].setText("<html><body>" + menu[num][5] + "<br>"+ price[num][5]+ "</body></html>");
		detailmenu[6].setText("<html><body>" + menu[num][6] + "<br>"+ price[num][6]+ "</body></html>");
		detailmenu[7].setText("<html><body>" + menu[num][7] + "<br>"+ price[num][7]+ "</body></html>");
		detailmenu[8].setText("<html><body>" + menu[num][8] + "<br>"+ price[num][8]+ "</body></html>");
		detailmenu[9].setText("<html><body>" + menu[num][9] + "<br>"+ price[num][9]+ "</body></html>");
		detailmenu[10].setText("<html><body>" + menu[num][10] + "<br>"+ price[num][10]+ "</body></html>");
		detailmenu[11].setText("<html><body>" + menu[num][11] + "<br>"+ price[num][11]+ "</body></html>");
		detailmenu[12].setText("<html><body>" + menu[num][12] + "<br>"+ price[num][12]+ "</body></html>");
		detailmenu[13].setText("<html><body>" + menu[num][13] + "<br>"+ price[num][13]+ "</body></html>");
//		detailmenu[14].setText("<html><body>" + menu[num][14] + "<br>"+ price[num][14]+ "</body></html>");
		
	}
	public void tableshow(int se){
		String inputstr[]= new String[2];
		int temp=0;
		for(int i=0;i<5;i++){
			if(flag[i]){
				temp=i;
			}
		}
		cnt[temp][se]++;
		if(cnt[temp][se]==1&& !menu[temp][se].equals("")){
			try{
				inputstr[0]= menu[temp][se];
				inputstr[1]= String.valueOf(cnt[temp][se]);
				model.addRow(inputstr);
				num[temp][se] = model.getRowCount()-1;
				sumprice += Integer.parseInt(price[temp][se].trim());
			}
			catch(Exception aa){
			}
		}
		else{
			model.setValueAt(cnt[temp][se],num[temp][se],1);
			sumprice += Integer.parseInt(price[temp][se].trim());
		}	
	}//�ּ�
	
}








