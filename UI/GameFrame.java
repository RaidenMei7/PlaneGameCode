package UI;
//������

import javax.swing.JFrame;
import javax.swing.*;

public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("Hero Ploit");
		setSize(512,768);
		setLocationRelativeTo(null);
		setResizable(false);//����������޸Ľ����С;/����ԭʼ0��0�����Ͻ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	public static void main(String[] args)
	{
		GameFrame frame=new GameFrame();
		
		
		GamePanel panel=new GamePanel(frame);
		panel.action();
		panel.action1();
		
		frame.add(panel);
		
		frame.setVisible(true);
		
	
	}
}
