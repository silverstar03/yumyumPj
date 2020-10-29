import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Table_main extends JPanel {
	private JButton table1;
	private JButton table2;
	private JButton table3;
	private JButton table4;
	private JButton table5;
	private JButton table6;
	private JButton table7;
	private JButton table8;
	
	private Table win;
	
	public Table_main(Table win) {
		this.win=win;
		setLayout(null);
		table1=new JButton("테이블1");
		table1.setSize(178,161);
		table1.setLocation(45,57);
		add(table1);
		
		table2=new JButton("테이블2");
		table2.setBounds(254,57,178,161);
		add(table2);
		
		table3=new JButton("테이블3");
		table3.setBounds(465,57,178,161);
		add(table3);
		
		table4=new JButton("테이블4");
		table4.setBounds(670, 57, 178, 161);
		add(table4);
		
		table5=new JButton("테이블5");
		table5.setBounds(31, 303, 178, 161);
		add(table5);
		
		table6=new JButton("테이블6");
		table6.setBounds(254, 303, 178, 161);
		add(table6);
		
		table7=new JButton("테이블7");
		table7.setBounds(465, 303, 178, 161);
		add(table7);
		
		table8=new JButton("테이블8");
		table8.setBounds(670,303,178,161);
		add(table8);
		
		table1.addActionListener(new MyaddActionListener());
	}
	
	class MyaddActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) { //버튼눌리면 패널02 호출
			//win.change("Counter");
			
		}
	}
}
