package yumyum;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesCheck extends JFrame{
	private JPanel menuMaechul;
	private JPanel dayMaechul;
/*
 * totalPrice : ���� �ֹ��� �޴��� �� ����
 * sales : �� ������ �����Ǿ� �Ϸ� ������ ��� ����
 * 
 * SalesCheck() : ������ �޼ҵ�
 * CountSales() : ������ ������ִ� �޼ҵ�
 * printSales() : ������ ������ ȭ�鿡 ������ִ� �޼ҵ�
 */
	
	private JLabel title_l;	
	private JLabel menu_l;
	private JLabel day_l,month_l,year_l; //��,��,�� 
	private JButton previous;
	
	JComboBox month_list, date_list; //��¥ ����
	
	public SalesCheck() {
		setTitle("���� Ȯ��");
		setSize(980, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null); 	//�����Ӱ� ��ġ ���� ����
		setBackground(new Color(231, 230, 230));
		
		setting();
	}

	public void setting() {
		title_l = new JLabel("���� Ȯ��");
		title_l.setFont(new Font("�����ٸ����", Font.PLAIN, 33));
		title_l.setBounds(420, 8, 187, 60);
		
		dayMaechul = new JPanel();
		dayMaechul.setLayout(null);
		dayMaechul.setBounds(20,91,460,432);
		dayMaechul.setBackground(Color.WHITE);
		
		menuMaechul = new JPanel();
		menuMaechul.setLayout(null);
		menuMaechul.setBounds(500, 91, 460, 432);
		menuMaechul.setBackground(Color.WHITE);
		
		previous = new JButton("���ư���");
		previous.setBounds(20,12,105,60);
		previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				Table_main tm=new Table_main();
				tm.setVisible(true);
			}
		});
		
		menu_l=new JLabel("[�޴��� ����]");
		menu_l.setBounds(10, 10, 100, 30);
		menuMaechul.add(menu_l);
		
		String [] months = new String[12];
		for (int i=0;i<months.length;i++) {
			months[i]=Integer.toString(i+1);
		}
		
		String [] dates = new String [31];
		for (int i=0;i<dates.length;i++) {
			dates[i] = Integer.toString(i+1);
		}
		
		month_list=new JComboBox(months);
		month_list.setBounds(20, 52, 50, 30);
		dayMaechul.add(month_list);
		
		date_list=new JComboBox(dates);
		date_list.setBounds(80, 52, 50, 30);
		dayMaechul.add(date_list);
		
		day_l=new JLabel("[�Ϻ� ����]");
		day_l.setBounds(14,22,100,30);
		/*month_l=new JLabel("[���� ����]");
		month_l.setBounds(14, 160, 100, 30);
		year_l=new JLabel("[������ ����]");
		year_l.setBounds(14, 302, 100, 30);*/
		
		dayMaechul.add(day_l);
		//dayMaechul.add(month_l);
		//dayMaechul.add(year_l);
		
		
		add(title_l);
		add(dayMaechul);
		add(menuMaechul);
		add(previous);
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new SalesCheckTest();
//	}

}
