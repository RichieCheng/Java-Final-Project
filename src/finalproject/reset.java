package finalproject;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class reset extends FP{
	
	//重設冰之洞
	public static void reseticemap() {

		for (int i=0;i<15;i++)
		{
			for (int j=0;j<11;j++)
			{
				panelmap[3].remove(icejtp[i][j]);
			}
		}
		
		img[1] = new ImageIcon("src/finalproject/ice.jpg");
		img[2] = new ImageIcon("src/finalproject/stone.jpg");
		img[3] = new ImageIcon("src/finalproject/stair1.jpg");
		img[4] = new ImageIcon("src/finalproject/floor.jpg");
		img[5] = new ImageIcon("src/finalproject/brokeice.jpg");
		img[6] = new ImageIcon("src/finalproject/stair2.jpg");
		
		for (int i=0;i<15;i++)
		{
			for (int j=0;j<11;j++)
			{
				icejtp[i][j] = new JTextPane();
				icejtp[i][j].setEditable(false);
				panelmap[3].add(icejtp[i][j]);
			}
		}

		
		
		//冰之洞梯子
		icejtp[1][5].insertIcon(img[3]);
		icejtp[6][5].insertIcon(img[3]);
		icejtp[10][5].insertIcon(img[3]);
		
		//冰之洞石頭
		icejtp[11][4].insertIcon(img[2]);
		icejtp[13][6].insertIcon(img[2]);
		icejtp[8][3].insertIcon(img[2]);
		icejtp[8][7].insertIcon(img[2]);
		icejtp[2][2].insertIcon(img[2]);
		icejtp[3][6].insertIcon(img[2]);
		icejtp[3][9].insertIcon(img[2]);
		icejtp[4][1].insertIcon(img[2]);
		icejtp[4][4].insertIcon(img[2]);
		icejtp[5][8].insertIcon(img[2]);

		//冰之洞冰塊
		//第一層
		
		for (int i=4;i<=5;i++)
		{
			icejtp[11][i+1].insertIcon(img[1]);
			icejtp[12][i].insertIcon(img[1]);
			icejtp[13][i].insertIcon(img[1]);
		}		

		icejtp[12][6].insertIcon(img[1]);

		//第二層
		
		for (int i=2;i<=8;i++)
		{
			icejtp[9][i].insertIcon(img[1]);
		}	
		
		icejtp[8][2].insertIcon(img[1]);
		icejtp[8][4].insertIcon(img[1]);
		icejtp[8][5].insertIcon(img[1]);
		icejtp[8][6].insertIcon(img[1]);
		icejtp[8][8].insertIcon(img[1]);
		
		for (int i=2;i<=8;i++)
		{
			icejtp[7][i].insertIcon(img[1]);
		}

		//第三層
		icejtp[2][0].insertIcon(img[1]);
		icejtp[2][1].insertIcon(img[1]);
		
		for (int i=3;i<=10;i++)
		{
			icejtp[2][i].insertIcon(img[1]);
		}
		
		for (int i=0;i<=5;i++)
		{
			icejtp[3][i].insertIcon(img[1]);
		}

		icejtp[3][7].insertIcon(img[1]);
		icejtp[3][8].insertIcon(img[1]);
		icejtp[3][10].insertIcon(img[1]);
		icejtp[4][0].insertIcon(img[1]);
		icejtp[4][2].insertIcon(img[1]);
		icejtp[4][3].insertIcon(img[1]);
		
		for (int i=5;i<=10;i++)
		{
			icejtp[4][i].insertIcon(img[1]);
		}
		
		for (int i=0;i<=7;i++)
		{
			icejtp[5][i].insertIcon(img[1]);
		}

		icejtp[5][9].insertIcon(img[1]);
		icejtp[5][10].insertIcon(img[1]);

		
		for (int i=0;i<15;i++)
		{
			for (int j=0;j<11;j++)
			{
				icejtp[i][j].insertIcon(img[4]);
			}
		}
		
		panelmap[3].repaint();
		//第一階
		icemap[13][4]=3;
		icemap[13][5]=3;
		icemap[12][4]=3;
		icemap[12][5]=3;
		icemap[12][6]=3;
		icemap[11][5]=3;
		icemap[11][6]=3;
		//第二階
		for (int i=2;i<=8;i++)
			icemap[9][i]=3;

		icemap[8][2]=3;
		icemap[8][4]=3;
		icemap[8][5]=3;
		icemap[8][6]=3;
		icemap[8][8]=3;
		for (int i=2;i<=8;i++)
			icemap[7][i]=3;

		//第三階
		icemap[2][0]=3;
		icemap[2][1]=3;
		for (int i=3;i<=10;i++)
			icemap[2][i]=3;
		for (int i=0;i<=5;i++)
			icemap[3][i]=3;
		icemap[3][7]=3;
		icemap[3][8]=3;
		icemap[3][10]=3;
		icemap[4][0]=3;
		icemap[4][2]=3;
		icemap[4][3]=3;
		for (int i=5;i<=10;i++)
			icemap[4][i]=3;
		for (int i=0;i<=7;i++)
			icemap[5][i]=3;
		icemap[5][9]=3;
		icemap[5][10]=3;
		//梯子
		icemap[1][5]=1;
		icemap[6][5]=1;
		icemap[10][5]=1;
	}
}
