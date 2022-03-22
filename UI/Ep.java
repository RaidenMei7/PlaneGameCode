package UI;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Ep extends FlyObject{
	int sp;
	int hp;
	int type;

	 
		// TODO Auto-generated method stub
		
		
		public Ep() {
			Random rd=new Random();
			int index=rd.nextInt(15)+1;
			type=index;
			String path="/img/ep"+(index<10?"0":"")+index+".png";//加括号防止被前面的连接成字符串
			
			img=App.getImg(path);
			
			w=img.getWidth();
			h=img.getHeight();	
			
			x=rd.nextInt(512-w);//防止飞机卡到边缘（设置的窗体宽度为512，同理下面）
			y=-h;
			sp=17-index;
			hp=index;
			
		}
		
		public static void main(String[] args) {
			
		}
		public void move() {
			if(type==5) {
				x-=5;
				y+=sp;
			}else if(type==6) {
				x+=5;
				y+=sp;
			}//else if(type==12) {
				//x+=sp;
			//}
			
			y+=sp;
		}

		public boolean shootBy(Fire f) {//子弹的距离+宽度>=敌机距离，敌机距离>=子弹距离减去敌机宽度，同时满足。
			boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;
			
			return hit;
		}

		public boolean hitBy(Hero f) {
			boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;
			return hit;
		}
}
