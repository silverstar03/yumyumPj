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
		table1=new JButton("���̺�1");
		table1.setSize(178,161);
		table1.setLocation(45,57);
		add(table1);
		
		table2=new JButton("���̺�2");
		table2.setBounds(254,57,178,161);
		add(table2);
		
		table3=new JButton("���̺�3");
		table3.setBounds(465,57,178,161);
		add(table3);
		
		table4=new JButton("���̺�4");
		table4.setBounds(670, 57, 178, 161);
		add(table4);
		
		table5=new JButton("���̺�5");
		table5.setBounds(31, 303, 178, 161);
		add(table5);
		
		table6=new JButton("���̺�6");
		table6.setBounds(254, 303, 178, 161);
		add(table6);
		
		table7=new JButton("���̺�7");
		table7.setBounds(465, 303, 178, 161);
		add(table7);
		
		table8=new JButton("���̺�8");
		table8.setBounds(670,303,178,161);
		add(table8);
		
		table1.addActionListener(new MyaddActionListener());
	}
	
	class MyaddActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) { //��ư������ �г�02 ȣ��
			//win.change("Counter");
			
		}
	}
}
