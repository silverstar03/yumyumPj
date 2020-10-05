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
	
	private JLabel title_label = new JLabel("주문 등록");
	
	private JPanel menu_panel = new JPanel();	//메뉴 카테고리 있는 panel
	private JPanel detail_panel = new JPanel(); 	//상세 메뉴들이 나오는 panel
	
	private JButton[] menucategory = new JButton[10];
	private JButton[] detailmenu = new JButton[15];	//버튼 15개 생성
	
	private int[][] cnt = new int[3][15];
	private int[][] num = new int[3][15];
	private int sumprice = 0;
	
	private String[][] menu;
	private String[][] price;
	
	private DefaultTableModel model;
	
	private boolean[] flag = new boolean[5];
	
	
	public static void main(String[] args) {

			OrderMenu frame = new OrderMenu();
			frame.setVisible(true);	//창을 화면에 나타냄
		
	}
		
	public OrderMenu() {
		orderMenu2Frame();
		this.getContentPane().repaint();	//컴포넌트 재배치(새로고침 개념)
	}
	
	public void orderMenu2Frame() {
		
		setTitle("주문 등록 화면");
		setSize(1100, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);	//창크기 조절 X
		getContentPane().setLayout(null); 	//자유롭게 위치 조정 가능
		
		title_label.setFont(new Font("문체부 쓰기 정체", Font.PLAIN, 40));
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
	
	
	public void detailedMenu() {
		
		detail_panel.setBounds(490, 201, 600, 297);
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
						tableshow(0);
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
						tableshow(1);
					}
				}
			}
			
		});
		
		detailmenu[2].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {//마우스가 해당 컴포넌트를 클릭했을 때
				if(!detailmenu[2].getText().equals("")) {
					if(e.getClickCount()==1) {	//한 번 클릭할 때를 말함(getClickCount는 마우스 클릭 횟수 가져오기)
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
	}//주석
	
}








