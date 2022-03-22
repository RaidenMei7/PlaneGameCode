package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

//做面板

public class GamePanel extends JPanel {
	BufferedImage bg;
	Hero hero=new Hero();
	
	
	
	
	List<Ep>eps=new ArrayList<Ep>();
	
	List<Fire>fs=new ArrayList<Fire>();
	
	int score;
	boolean gameover;
	int power=1;
	
	Button st;

	
	
	public void action() {
		//创建启动线程，控制游戏场景中物体移动。
		new Thread() {
			public void run() {
	               while(true) {
	            	   if(!gameover) {
	            		   
	            	   epEnter();
	            	   repaint();//自己刷新
	            	   epMove();
	            	   
	            	   }
	            	   
	            	   try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	   }
	               }
			
		}.start();
}
	public void action1() {
		new Thread() {
			public void run() {
				while(true) {
					if(!gameover) {
					shoot();
					repaint();
					fireMove();
					shootEp();
					hit();
					}
					 try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		}.start();
	}
	
	
	
	protected void hit() {
		for(int i=0;i<eps.size();i++)
		{
			Ep e=eps.get(i);
			if(e.hitBy(hero)) {
				eps.remove(e);
				hero.hp--;
				power=1;
			}
			if(hero.hp<=0) {
				gameover=true;
			}
		}
		
	}
	protected void shootEp() {
		for(int i=0;i<fs.size();i++) {
			Fire f=fs.get(i);
			bang(f);
		}
		
		
	}
	
	private void bang(Fire f) {
		for(int i=0;i<eps.size();i++) {
			Ep e=eps.get(i);
			if(e.shootBy(f)) {
				e.hp--;
				if(e.hp<=0) {
					if(e.type==12) {
						power++;
						if(power>=3) {
						   hero.hp++;
						   power=3;
						}
						
					}
				eps.remove(e);
				score+=10;
				}
				fs.remove(f);
				
			}
		}
	}
		
	
	protected void fireMove() {
		for(int i=0;i<fs.size();i++) {
			Fire f=fs.get(i);
			f.move();
		}
		
	}



	int findex=0;
	protected void shoot() {
		findex++;
		if(findex>=20) {
		if(power==1) {
		Fire fire1=new Fire(hero.x+43,hero.y);//保证跟随
		fs.add(fire1);}
		else if(power==2) {
			Fire fire3=new Fire(hero.x+75,hero.y);//保证跟随
			fs.add(fire3);
			Fire fire2=new Fire(hero.x+15,hero.y);
		fs.add(fire2);}
		else if(power==3) {
			Fire fire1=new Fire(hero.x+43,hero.y);//保证跟随
			fs.add(fire1);
			Fire fire2=new Fire(hero.x+15,hero.y);
		fs.add(fire2);
		Fire fire3=new Fire(hero.x+75,hero.y);
		fs.add(fire3);
		}
		findex=0;
		}
	}



	protected void epMove() {
		// TODO Auto-generated method stub
		for(int i=0;i<eps.size();i++) {
			Ep e=eps.get(i);
			e.move();
		}
			
		
	}

//出现太密集，所以考虑执行多次enter方法后，出现一次敌机

	public static int index =0,level=0;
	
	protected void epEnter() {
		// TODO Auto-generated method stub
		index++;
		if(index>=level) {
			//System.out.println(level);
			Ep e=new Ep();
			eps.add(e);
			index=0;
		}
	}


	public GamePanel(GameFrame frame) {
		setBackground(Color.pink);
		bg=App.getImg("/Img/bg1.jpg");
		
		
		
		MouseAdapter adapter=new MouseAdapter() {
			//mouseMoved
			
			//mousePressed();
			//mouseEntered();
			//mouseExited();
		
			//
            
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gameover) {
					hero =new Hero();
					eps=new ArrayList<Ep>();//或,eps.clear();
					fs.clear();
					gameover=false;
					score=0;
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					bg=App.getImg("/Img/bg"+index+".jpg"); 
					repaint();
					
				}
				
			}
			
			public void mouseMoved(MouseEvent e) {
			
				int mx=e.getX();
				int my=e.getY();
				if(!gameover) {
				hero.moveToMouse(mx, my);
				repaint();
				}
			}
			
			
		};
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		
		
		KeyAdapter kd=new KeyAdapter() {
		
			
		public void keyPressed(KeyEvent e) {
			
			
			int keyCode=e.getKeyCode();
			if (keyCode==KeyEvent.VK_UP) {hero.moveUP();}
			else if(keyCode==KeyEvent.VK_DOWN) {hero.moveDown();}
			else if(keyCode==KeyEvent.VK_LEFT) {hero.moveLeft();}
			else if(keyCode==KeyEvent.VK_RIGHT) {hero.moveRight();}
			else if(keyCode==KeyEvent.VK_ESCAPE) {
				
				//b.reset(pause);
				}
			
			repaint();
		}
			
		};
		frame.addKeyListener(kd);
		
		
	}
	@Override
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		g.drawImage(bg, 0, 0, null);
		
		g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);
		//先画会被后画的覆盖。
		for(int i=0;i<eps.size();i++) {
		
			Ep ep=eps.get(i);
			g.drawImage(ep.img,ep.x,ep.y,ep.w,ep.h,null);
		}
		
		for(int i=0;i<fs.size();i++)
		{
			Fire fire=fs.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w, fire.h, null);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.drawString("分数"+score,10,30);
		
		for(int i=0;i<hero.hp;i++) {
			g.drawImage(hero.img, 455-35*i, 5, 30, 30, null);
			
		}
		if(gameover) {
		g.setColor(Color.red);
		g.setFont(new Font("楷体",Font.BOLD,35));
		g.drawString("Game Over",170,300);
		g.drawString("单击鼠标左键开始游戏",40,350);
		
		}	
		
		
	}
	

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
