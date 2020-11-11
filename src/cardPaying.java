//�����������α׷�
//��ĥ��: ������ҵ� �����
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

class TimerThread implements Runnable{
	private JLabel timerLabel;
	private JDialog d;
	//private PayTest3 pt = new PayTest3();
	public TimerThread(JLabel timerLabel,JDialog d) {
		this.timerLabel=timerLabel;
		this.d=d;
	}
	
	@Override
	public void run() {
		int n=3;
		while(true) {
			timerLabel.setText(Integer.toString(n));
			n--;
			try {
				Thread.sleep(1000);
				if(n==0) {
					d.dispose();
				}
			}catch(InterruptedException e) {
				return ;
			}
		}
	}
	
}
public class cardPaying extends JFrame implements ActionListener{
	private JLabel counting;
	private JLabel cResult;
	private JLabel count;
	private JDialog dialog;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cardPaying();
	}
	
	public cardPaying() {
		dialog=new JDialog(this,"������",true);
		dialog.setSize(300,200);
		dialog.setLayout(null);
		counting = new JLabel("���� ���Դϴ�...",JLabel.CENTER);
		counting.setFont(new Font("�����ٸ����",Font.PLAIN,19));
		counting.setBounds(70, 38, 150, 30);
		
		count=new JLabel("",JLabel.CENTER);
		count.setFont(new Font("�����ٸ����",Font.BOLD,20));
 		count.setBounds(70, 80, 20, 30);
		
		cResult=new JLabel("�� �Ŀ� �Ϸ�˴ϴ�.");
		cResult.setFont(new Font("�����ٸ����",Font.BOLD,18));
		cResult.setBounds(90, 80, 150, 30);
		
		TimerThread th=new TimerThread(count,dialog);
		Thread td = new Thread(th);
		td.start();
		dialog.add(counting);dialog.add(count);dialog.add(cResult);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}