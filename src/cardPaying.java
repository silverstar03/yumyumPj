//카드결제 눌렀을 때 3초후에 자동으로 다이얼로그창 닫히게 하는 클래스
//결제관리프로그램
//고칠점: 결제취소도 만들기
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
					d.setVisible(false);
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
	private final JDialog dialog;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new cardPaying();
	}
	
	public cardPaying() {
		dialog=new JDialog(this,"결제중",true);
		dialog.setSize(300,200);
		dialog.setLayout(null);
		counting = new JLabel("결제 중입니다...",JLabel.CENTER);
		counting.setFont(new Font("나눔바른고딕",Font.PLAIN,19));
		counting.setBounds(70, 38, 150, 30);
		
		count=new JLabel("",JLabel.CENTER);
		count.setFont(new Font("나눔바른고딕",Font.BOLD,20));
		count.setBounds(70, 80, 20, 30);
		
		cResult=new JLabel("초 후에 완료됩니다.");
		cResult.setFont(new Font("나눔바른고딕",Font.BOLD,18));
		cResult.setBounds(90, 80, 150, 30);
		
		TimerThread th=new TimerThread(count,dialog);
		Thread td = new Thread(th);
		td.start();
		dialog.add(counting);dialog.add(count);dialog.add(cResult);
//		Timer timer = new Timer(3000,new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//다이얼로그가 보이지않게 한다.
//				dialog.setVisible(false);
//				//다이얼로그를 메모리에서 삭제한다.
//				//dialog.dispose();
//			}
//		});
//		timer.setRepeats(false);
//		timer.start();
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
