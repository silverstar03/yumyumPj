/*
 * mysql -ugogi1 -p2203 yumyum1
 */
public class Menulist {
	public String[][] menu(){
		String[][] menu = new String[][]{
			{"������", "��䰥��", "��������","������", "�����", "�������", "�õ�����", "�õ����", "�õ������", "���л���", "��ȸ", "���ű��", "������", "���긮��" },
			{"�����", "�����", "�����", "���ø�", "����ø�", "������", "ġ���߰�"," "," "," "," ", " ", " ", " " },
			{"���̴�(355ml)", "�ݶ�(355ml)","ȯŸ(355ml)", "���̴�(1.25L)", "�ݶ�(1.25L)","ȯŸ(1.25L)", "����", "����", "û��","���ɸ�"," "," "," "," "," " }			
		};
		return menu;
	}
	
	public String[][] price(){
		String[][] price = new String[][] {
			{" 6000", " 11000", " 10000", " 13000", " 12900", " 11900", " 11000", " 6000"," 6000", " 9900", " 25000", " 12900", " 15000", " 11900"  },
			{" 2000", " 1000", " 3000", " 5000", " 5000", " 2000", "1000", "", "", "", "", "", "", ""  },
			{" 1000", " 1000", "1000", " 2000", " 2000","2000", " 4500", "4000", "4000", "3000", "", "", "", "", "",""  }
		};
		return price;
	}
}
