package UI;
//做窗体

import javax.swing.JFrame;
import javax.swing.*;

public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("Hero Ploit");
		setSize(512,768);
		setLocationRelativeTo(null);
		setResizable(false);//不允许玩家修改界面大小;/坐标原始0，0在左上角
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
