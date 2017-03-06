package finalproject;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Music extends FP{
	
	static int size = 1024*1024;
	static Thread t1 = new Thread(new select());
	static Thread t2 = new Thread(new prize());
	public static void change(){
		
		if (c)
		{
			t1.stop();
			t1 = new Thread(new select());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t1.start();
		}
		else
		{
			t1.start();
		}

	}
	public static void ps(){
		if (get)
		{
			t2 = new Thread(new prize());
			t2.start();
		}
	}
	public static class prize implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			File sf = new File(getClass().getResource("get.wav").getFile());
			playmusic(sf);
		}
		
	}
	public static class select implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			if (nowmap == 0 && key!= 7){
				File sf = new File(getClass().getResource("road.wav").getFile());
				playmusic(sf);
			}
			else if(nowmap == 0 && key==7)
			{
				File sf = new File(getClass().getResource("cc.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 2 && get==false)
			{
				File sf = new File(getClass().getResource("teamrocket.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 3)
			{
				File sf = new File(getClass().getResource("ice.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 4)
			{
				File sf = new File(getClass().getResource("fire.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 5)
			{
				File sf = new File(getClass().getResource("forest.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 6 && swim == false)
			{
				File sf = new File(getClass().getResource("water.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 6 && swim == true)
			{
				File sf = new File(getClass().getResource("ocean.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 7)
			{
				File sf = new File(getClass().getResource("cave.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 8)
			{
				File sf = new File(getClass().getResource("lastroad.wav").getFile());
				playmusic(sf);
			}
			else if (nowmap == 9)
			{
				File sf = new File(getClass().getResource("End.wav").getFile());
				playmusic(sf);
			}

		}
		
	}
	
	public static void playmusic(File sf)
	{
		try 
		{ 

			AudioInputStream astr = AudioSystem.getAudioInputStream(sf);  // ���o�n����J��y
			AudioFormat afmt = astr.getFormat(); // ���o�n���Φ�  
			DataLine.Info inf = new DataLine.Info(SourceDataLine.class,afmt);// �إ߰T���u��T����  
			SourceDataLine l = (SourceDataLine) AudioSystem.getLine(inf); // ���o�ŦX���w�T���u��T���T���u  
			l.open(afmt); // �H���w�Φ��}�ҰT���u l.start();
			l.start();// �}�l�T���u��Ū�g 
			byte[] buf = new byte[size]; // Ū�g�w�İ� 
			for( int n=0; (n=astr.read(buf,0,buf.length))>0;)// �q�n����yŪ�J��Ƽg�J�V����  
			{ 
				l.write(buf,0,n); 
			} 
			l.drain();// �M���V�����������  
			l.close();// ���� 

		}
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	


}



