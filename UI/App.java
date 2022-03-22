package UI;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class App {
	public static BufferedImage getImg(String path) {
		
		try {
	BufferedImage img=ImageIO.read(App.class.getResource(path));
	return img;
	
		}catch(IOException e) {
		e.printStackTrace();
	}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
