import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Choose extends JFrame implements ActionListener{
	private JPanel chooseP; //패널명
	private JButton goOrderMenu; //주문
	private JButton goPay; //결제
	private JButton previous;
	public static void main(String[] args) {
		new Choose();
	}
	
	public Choose() {
		setTitle("기능 선택");
		setResizable(false);
		chooseP=new JPanel();
		chooseP.setLayout(null);
		goOrderMenu = new JButton("주문");
		goOrderMenu.setBounds(14,12,130,87);
		
		goPay = new JButton("결제");
		goPay.setBounds(162, 12, 126, 87);
		chooseP.add(goPay);
		chooseP.add(goOrderMenu);
		
		previous = new JButton("이전");
		previous.setBounds(211, 111, 73, 21);
		chooseP.add(previous);
		add(chooseP);
		
		setSize(310,185);
		setLocationRelativeTo(null); //창 가운데에서 실행
		setVisible(true);
		
		goOrderMenu.addActionListener(this);
		goPay.addActionListener(this);
		previous.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==goOrderMenu) {
			new OrderMenu();
			setVisible(false);
//			tt= new tableTest ();
//			tt.setVisible(false);
		}else if(e.getSource()==goPay) {
			new Pay();
			setVisible(false);
			//tt.setVisible(false);
		}else if (e.getSource()==previous) {
			setVisible(false);
		}
	}

}
