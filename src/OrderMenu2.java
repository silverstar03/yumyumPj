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

public class OrderMenu2 extends JFrame {
	
	private JLabel title_label = new JLabel("�ֹ� ���");
	
	private JPanel menu_panel = new JPanel();	//�޴� ī�װ� �ִ� panel
	private JPanel detail_panel = new JPanel(); 	//�� �޴����� ������ panel
	
	private JButton[] menucategory = new JButton[10];
	
	private JButton detailmenu1 = new JButton();
	private JButton detailmenu2 = new JButton();
	private JButton detailmenu3 = new JButton();
	private JButton detailmenu4 = new JButton();
	private JButton detailmenu5 = new JButton();
	private JButton detailmenu6 = new JButton();
	private JButton detailmenu7 = new JButton();
	private JButton detailmenu8 = new JButton();
	private JButton detailmenu9 = new JButton();
	private JButton detailmenu10 = new JButton();
	private JButton detailmenu11 = new JButton();
	private JButton detailmenu12 = new JButton();
	private JButton detailmenu13 = new JButton();
	private JButton detailmenu14 = new JButton();
	private JButton detailmenu15 = new JButton();
	
	
	private int[][] cnt = new int[3][15];
	private int[][] num = new int[3][15];
	private int sumprice = 0;
	
	private String[][] menu;
	private String[][] price;
	
	
	public static void main(String[] args) {

			OrderMenu2 frame = new OrderMenu2();
			frame.setVisible(true);	//â�� ȭ�鿡 ��Ÿ��
		
	}
		
	public OrderMenu2() {
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
		 
		menu_panel.setBounds(490, 93, 600, 105);
		menu_panel.setBackground(Color.LIGHT_GRAY);
		menu_panel.setLayout(new GridLayout(2,5));
		getContentPane().add(menu_panel);
		
		
//		JButton meatbt = new JButton("����");
//		meatbt.setBounds(238, 0, 119, 50);
//		meatbt.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				menueffect(2);
//			}
//		});
//		menu_panel.add(meatbt);
		
		for (int i = 0; i<10; i++) {
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
	}
	
	
	public void detailedMenu() {
		
		detail_panel.setBounds(490, 201, 600, 297);
		detail_panel.setBackground(Color.WHITE);
		detail_panel.setLayout(null);
		getContentPane().add(detail_panel);
		
		
//		for (int i = 0; i<15; i++) {
//			detail_panel.add(detailmenu[i] = new JButton(""));
//			
//			detailmenu[i].setBackground(new Color(204, 169, 221));
//			detailmenu[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));//Border�÷� ����
//		}
		
		detailmenu1.setBackground(new Color(204, 169, 221));
		detailmenu1.setBounds(2, 2, 117, 96);
		detail_panel.add(detailmenu1);
		
		detailmenu2.setBackground(new Color(204, 169, 221));
		detailmenu2.setBounds(122, 2, 117, 96);
		detail_panel.add(detailmenu2);
	
		detailmenu3.setBackground(new Color(204, 169, 221));
		detailmenu3.setBounds(242, 2, 117, 96);
		detail_panel.add(detailmenu3);
		
		detailmenu4.setBackground(new Color(204, 169, 221));
		detailmenu4.setBounds(362, 2, 117, 96);
		detail_panel.add(detailmenu4);
		
		detailmenu5.setBackground(new Color(204, 169, 221));
		detailmenu5.setBounds(482, 2, 117, 96);
		detail_panel.add(detailmenu5);
		
		detailmenu6.setBackground(new Color(204, 169, 221));
		detailmenu6.setBounds(2, 100, 117, 96);
		detail_panel.add(detailmenu6);
		
		detailmenu7.setBackground(new Color(204, 169, 221));
		detailmenu7.setBounds(122, 100, 117, 96);
		detail_panel.add(detailmenu7);
		
		detailmenu8.setBackground(new Color(204, 169, 221));
		detailmenu8.setBounds(242, 100, 117, 96);
		detail_panel.add(detailmenu8);
		
		detailmenu9.setBackground(new Color(204, 169, 221));
		detailmenu9.setBounds(362, 100, 117, 96);
		detail_panel.add(detailmenu9);
		
		detailmenu10.setBackground(new Color(204, 169, 221));
		detailmenu10.setBounds(482, 100, 117, 96);
		detail_panel.add(detailmenu10);
		
		detailmenu11.setBackground(new Color(204, 169, 221));
		detailmenu11.setBounds(2, 198, 117, 96);
		detail_panel.add(detailmenu11);
		
		detailmenu12.setBackground(new Color(204, 169, 221));
		detailmenu12.setBounds(122, 198, 117, 96);
		detail_panel.add(detailmenu12);
		
		detailmenu13.setBackground(new Color(204, 169, 221));
		detailmenu13.setBounds(242, 198, 117, 96);
		detail_panel.add(detailmenu13);
		
		detailmenu14.setBackground(new Color(204, 169, 221));
		detailmenu14.setBounds(362, 198, 117, 96);
		detail_panel.add(detailmenu14);
		
		detailmenu15.setBackground(new Color(204, 169, 221));
		detailmenu15.setBounds(482, 198, 117, 96);
		detail_panel.add(detailmenu15);
		
		
			
		
		
		detailmenu1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//���콺�� �ش� ������Ʈ�� Ŭ������ ��
				// TODO Auto-generated method stub
				if(!detailmenu1.getText().equals("")) {
					if(e.getClickCount()==1) {	//�� �� Ŭ���� ���� ����(getClickCount�� ���콺 Ŭ�� Ƚ�� ��������)
						//printMenu(0);
					}
				}
			}
			
		});
	}
	
	
//	public void printMenu(int se) {//�޴� ��ٱ��Ͽ� �ִ� �޼���
//		String inputstr[] = new String[2];
//		int temp = 0;
//	//		//String.valueof(���ڿ���)
//		cnt[temp][se]++;
//		if(cnt[temp][se]==1 && !menu[temp][se].equals("")) {
//			inputstr[0]=menu[temp][se];
//			inputstr[1]=String.valueOf(cnt[temp][se]));
//			
//		}
//	}
	
	public void menueffect(int num) {
		detailmenu1.setText("<html><body>" + menu[num][0] + "<br>"+ price[num][0]+ "</body></html>");
		detailmenu2.setText("<html><body>" + menu[num][1] + "<br>"+ price[num][1]+ "</body></html>");
		detailmenu3.setText("<html><body>" + menu[num][2] + "<br>"+ price[num][2]+ "</body></html>");
		detailmenu4.setText("<html><body>" + menu[num][3] + "<br>"+ price[num][3]+ "</body></html>");
		detailmenu5.setText("<html><body>" + menu[num][4] + "<br>"+ price[num][4]+ "</body></html>");
		detailmenu6.setText("<html><body>" + menu[num][5] + "<br>"+ price[num][5]+ "</body></html>");
		detailmenu7.setText("<html><body>" + menu[num][6] + "<br>"+ price[num][6]+ "</body></html>");
		detailmenu8.setText("<html><body>" + menu[num][7] + "<br>"+ price[num][7]+ "</body></html>");
		detailmenu9.setText("<html><body>" + menu[num][8] + "<br>"+ price[num][8]+ "</body></html>");
		detailmenu10.setText("<html><body>" + menu[num][9] + "<br>"+ price[num][9]+ "</body></html>");
		detailmenu11.setText("<html><body>" + menu[num][10] + "<br>"+ price[num][10]+ "</body></html>");
		
	}
	
}








