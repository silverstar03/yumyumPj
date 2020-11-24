package yumyum;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import yumyum.OrderMenu;
import yumyum.Pay;

public class Table_main extends JFrame {
	//정말로 프로그램 종료를 할 것인지 아닌지 확인
	private JDialog checkOut;
	//private JDialog choose; //주문할건지 결제한건지 선택
	private JPanel jp;
	
	//테이블 8개
	private JButton table1;
	private JButton table2;
	private JButton table3;
	private JButton table4;
	private JButton table5;
	private JButton table6;
	private JButton table7;
	private JButton table8;
	//매출확인하는 버튼 
	private JButton check_maechul;
	private JButton LogOut; //프로그램종료
	//프로그램을 종료하시겠습니까? Jdialog checkOut에서 쓰이는 버튼
	private JButton yes;
	private JButton no;
	//글자 
	private JLabel answer; //로그아웃 하시겠습니까?
	
	private int[] click = new int[8];
	
	OrderMenu ordermenu1;
	OrderMenu ordermenu2;
	OrderMenu ordermenu3;
	OrderMenu ordermenu4;
	OrderMenu ordermenu5;
	OrderMenu ordermenu6;
	OrderMenu ordermenu7;
	OrderMenu ordermenu8;
	Pay pay;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Table_main();
	}
	public Table_main() {
		initialize();
	}
	
	private void initialize() {
		
		setTitle("냠냠쩝쩝");
		setSize(761,520);
		setLocationRelativeTo(null);	//창이 가운데에서 실행
		setResizable(false);
		
		jp=new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(242,241,241));
		
		table1 = new JButton("테이블 1");
		table1.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table1.setBackground(new Color(231, 225, 219));
		
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[0] += 1;
				if(click[0] == 1) {
					ordermenu1 = new OrderMenu("테이블 1", Table_main.this);
				}else if(click[0] >= 2) {
					ordermenu1.setVisible(true);
				}
			}
		});
		table1.setBounds(29, 47, 147, 127);
				
		table2 = new JButton("테이블 2");
		table2.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table2.setBackground(new Color(231, 225, 219));
		
		table2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[1] += 1;
				if(click[1] == 1) {
					ordermenu2 = new OrderMenu("테이블 2", Table_main.this);
				}else if(click[1] >= 2) {
					ordermenu2.setVisible(true);
				}
			}
		});
		table2.setBounds(215, 47, 147, 127);
		
		table3 = new JButton("테이블 3");
		table3.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table3.setBackground(new Color(231, 225, 219));
		table3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[2] += 1;
				if(click[2] == 1) {
					ordermenu3 = new OrderMenu("테이블 3", Table_main.this);
				}else if(click[2] >= 2) {
					ordermenu3.setVisible(true);
				}
			}
		});
		table3.setBounds(397, 47, 147, 127);
		
		table4 = new JButton("테이블 4");
		table4.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table4.setBackground(new Color(231, 225, 219));
		
		table4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[3] += 1;
				if(click[3] == 1) {
					ordermenu4 = new OrderMenu("테이블 4", Table_main.this);
				}else if(click[3] >= 2) {
					ordermenu4.setVisible(true);
				}
			}
		});
		table4.setBounds(582, 47, 147, 127);
		
		table5 = new JButton("테이블 5");
		table5.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table5.setBackground(new Color(231, 225, 219));
		table5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[4] += 1;
				if(click[4] == 1) {
					ordermenu5 = new OrderMenu("테이블 5", Table_main.this);
				}else if(click[4] >= 2) {
					ordermenu5.setVisible(true);
				}
			}
		});
		table5.setBounds(29, 247, 147, 127);
		
		table6 = new JButton("테이블 6");
		table6.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table6.setBackground(new Color(231, 225, 219));
		table6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[5] += 1;
				if(click[5] == 1) {
					ordermenu6 = new OrderMenu("테이블 6", Table_main.this);
				}else if(click[5] >= 2) {
					ordermenu6.setVisible(true);
				}
			}
		});
		table6.setBounds(215, 247, 147, 127);

		table7 = new JButton("테이블 7");
		table7.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table7.setBackground(new Color(231, 225, 219));
		table7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[6] += 1;
				if(click[6] == 1) {
					ordermenu7 = new OrderMenu("테이블 7", Table_main.this);
				}else if(click[6] >= 2) {
					ordermenu7.setVisible(true);
				}
			}
		});
		table7.setBounds(397, 247, 147, 127);
		
		table8 = new JButton("테이블 8");
		table8.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		table8.setBackground(new Color(231, 225, 219));
		table8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				click[7] += 1;
				if(click[7] == 1) {
					ordermenu8 = new OrderMenu("테이블 8", Table_main.this);
				}else if(click[7] >= 2) {
					ordermenu8.setVisible(true);
				}
			}
		});
		table8.setBounds(582, 247, 147, 127);

		check_maechul = new JButton("매출확인");
		check_maechul.setBackground(new Color(213, 218, 214));
		check_maechul.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		check_maechul.setBounds(609, 410, 120, 37);
		check_maechul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SalesCheck(Table_main.this);
			}
		});
				
		//프로그램 종료 확인하는 dialog
		LogOut=new JButton("프로그램 종료");
		LogOut.setBackground(new Color(213, 218, 214));
		LogOut.setFont(new Font("나눔바른고딕",Font.PLAIN,15));
		LogOut.setBounds(480, 410, 120, 37);
				
				
		jp.add(table1); 
		jp.add(table2); jp.add(table3); jp.add(table4);
		jp.add(table5); jp.add(table6); jp.add(table7); jp.add(table8);
		jp.add(check_maechul); jp.add(LogOut);
		add(jp);
				
		checkOut = new JDialog(this,"프로그램 종료",true);
		checkOut.setSize(316,191);
		checkOut.setLayout(null);
				
		answer = new JLabel("프로그램을 종료하시겠습니까?",JLabel.CENTER);
		answer.setBounds(41,27,226,56);
		answer.setFont(new Font("나눔바른고딕",Font.PLAIN,17));
				
		yes=new JButton("Yes");
		yes.setBackground(new Color(223, 228, 220));
		yes.setBounds(39, 95, 115, 35);
		
		no = new JButton("No");
		no.setBackground(new Color(223, 228, 220));
		no.setBounds(160, 95, 115, 35);
		
		checkOut.add(answer);
		checkOut.add(yes);
		checkOut.add(no);
		checkOut.setVisible(false);
		LogOut.addActionListener(new OutActionListener());
				//Dialog끝				
		setVisible(true);
	}
	
	//전체폰트설정
	private void changeAllSwingComponentDefaultFont() {
		try {
			UIDefaults swingComponentDefaultTable = UIManager.getDefaults();
			Enumeration allDefaultKey = swingComponentDefaultTable.keys();
			while(allDefaultKey.hasMoreElements()) {
				 String defaultKey = allDefaultKey.nextElement().toString();
				 if(defaultKey.indexOf("font") != -1) {
				      Font newDefaultFont = new Font("나눔바른고딕", Font.PLAIN, 14);
				      UIManager.put(defaultKey, newDefaultFont);
				  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//다이얼로그 창 주문인지 결제인지 선택

	class OutActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==LogOut) {
				checkOut.setLocationRelativeTo(null);
				yes.addActionListener(new checkOutListener());
				no.addActionListener(new checkOutListener());
				checkOut.setVisible(true);
			}
		}
		
	}
	
	class checkOutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==yes) {
				System.exit(0);
			}else if(ae.getSource()==no) {
				checkOut.setVisible(false);
			}
		}
	}
	
	public void Table1(String result) {
		table1.setText(result);
	}
	public void Table2(String result) {
		table2.setText(result);
	}
	public void Table3(String result) {
		table3.setText(result);
	}
	public void Table4(String result) {
		table4.setText(result);
	}
	public void Table5(String result) {
		table5.setText(result);
	}
	public void Table6(String result) {
		table6.setText(result);
	}
	public void Table7(String result) {
		table7.setText(result);
	}
	public void Table8(String result) {
		table8.setText(result);
	}
}
