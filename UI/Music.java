
package UI;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
public class Music extends JFrame{
	File f;
	 URI uri;
	    URL url;
	// Music(){
	//     bgMusic();
	//  }
	Music(){
		  try {
		      f = new File("src/Img/game_music.wav");
		      uri = f.toURI();
		      url = uri.toURL();  //??????ַ
		      AudioClip aau;
		      aau = Applet.newAudioClip(url);
		      aau.loop();  //ѭ??????
		  } catch (Exception e)
		  { e.printStackTrace();
		  }
	}
	
	public static void main(String args[]) {
		new Music();
	}
}