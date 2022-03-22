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
			String path="/img/ep"+(index<10?"0":"")+index+".png";//�����ŷ�ֹ��ǰ������ӳ��ַ���
			
			img=App.getImg(path);
			
			w=img.getWidth();
			h=img.getHeight();	
			
			x=rd.nextInt(512-w);//��ֹ�ɻ�������Ե�����õĴ�����Ϊ512��ͬ�����棩
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

		public boolean shootBy(Fire f) {//�ӵ��ľ���+���>=�л����룬�л�����>=�ӵ������ȥ�л���ȣ�ͬʱ���㡣
			boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;
			
			return hit;
		}

		public boolean hitBy(Hero f) {
			boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;
			return hit;
		}
}
