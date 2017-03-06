package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

public class FP extends JFrame implements ActionListener,KeyListener{
	
	JFrame []frame = new JFrame[10];
	static JFrame jump = new JFrame("通知");
	static JLabel text = new JLabel();
	static JPanel[]panelmap = new JPanel[10];
	static JButton []button = new JButton[10];
	static JTextPane [][]icejtp = new JTextPane[15][11];
	static ImageIcon []img = new ImageIcon[30];
	
	JPanel panel = new JPanel();
	JPanel black = new JPanel();
	JPanel people = new JPanel();
	JPanel prize = new JPanel();

	JLabel p = new JLabel();
	JLabel ball = new JLabel();
	JLabel tree = new JLabel();
	JLabel maze = new JLabel();
	JLabel water = new JLabel();
	JLabel dark = new JLabel();
	JLabel []fire = new JLabel[3];
	JLabel []element = new JLabel[10];
	
	Boolean skill = false;
	
	//mazemove判斷傳送用,c切換音樂,swim判斷有無在水上,get取的獎勵時的判斷
	static Boolean mazemove = false, c = false, swim = false, get = false;
	
	//x,y為座標 //next為走一步距離 //nowmap為當前地圖數值
	static int x=271,y=440,next,nowmap=0,watergun=0,key=0;
	
	//nowmap 0-主地圖 2-移動迷宮 3-冰之洞 4-火之洞 5-木之洞 6-水之洞 7-暗之洞 8-最後洞 9-獎勵洞
	static int [][]mainmap = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	static int [][]mazemap = {
		{0,	15,	15,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	1,	0,	0,	0},
		{1,	11,	11,	0,	1,	1,	0,	0,	1,	12,	12,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0},
		{1,	11,	11,	0,	0,	0,	0,	1,	1,	12,	0,	0,	13,	13,	1,	0,	12,	12,	1,	0,	5,	0},
		{0,	11,	15,	0,	1,	1,	12,	12,	0,	0,	0,	13,	13,	0,	1,	12,	12,	12,	1,	0,	0,	0},
		{0,	11,	11,	1,	1,	12,	0,	0,	0,	13,	12,	13,	13,	0,	0,	12,	15,	15,	1,	0,	0,	0},
		{1,	11,	11,	1,	1,	12,	0,	0,	12,	13,	13,	1,	1,	12,	0,	12,	0,	0,	1,	0,	0,	0},
		{1,	11,	11,	14,	0,	0,	14,	0,	0,	14,	14,	15,	0,	12,	0,	12,	1,	0,	1,	0,	0,	0},
		{1,	11,	11,	0,	1,	0,	0,	0,	12,	1,	1,	0,	0,	12,	0,	12,	1,	0,	1,	0,	0,	0},
		{0,	11,	11,	0,	1,	0,	1,	0,	12,	1,	1,	1,	0,	12,	0,	12,	1,	0,	1,	0,	0,	0},
		{0,	11,	11,	0,	1,	0,	0,	0,	12,	1,	1,	1,	0,	12,	0,	12,	1,	14,	14,	15,	0,	0},
		{0,	11,	11,	0,	1,	12,	0,	0,	0,	0,	0,	1,	0,	12,	0,	12,	1,	1,	1,	1,	1,	1},
		{0,	11,	11,	0,	1,	12,	0,	14,	0,	0,	0,	15,	14,	0,	0,	12,	0,	0,	1,	0,	0,	0},
		{0,	11,	0,	0,	1,	14,	14,	14,	0,	14,	14,	14,	14,	0,	15,	12,	0,	0,	1,	0,	0,	0},
		{0,	11,	15,	13,	13,	13,	13,	13,	13,	13,	13,	13,	13,	12,	1,	0,	0,	0,	0,	0,	0,	0},
		{0,	11,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	11,	0,	13,	0,	13,	13,	13,	0,	0,	0},
		{0,	11,	1,	1,	0,	0,	15,	13,	13,	13,	13,	13,	13,	13,	1,	0,	14,	14,	14,	15,	0,	0},
		{0,	0,	1,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0},
		{1,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	15,	0,	1,	1,	0,	0,	0}};
	static int [][]icemap = {
		{0,0,0,0,0,5,0,0,0,0,0},
		{1,1,1,1,1,1,1,1,1,1,1},
		{3,3,1,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,1,3,3,1,3},
		{3,1,3,3,1,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,1,3,3},
		{1,1,1,1,1,1,1,1,1,1,1},
		{1,1,3,3,3,3,3,3,3,1,1},
		{1,1,3,1,3,3,3,1,3,1,1},
		{1,1,3,3,3,3,3,3,3,1,1},
		{1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,3,3,1,1,1,1},
		{1,1,1,1,3,3,3,1,1,1,1},
		{1,1,1,1,3,3,1,1,1,1,1},
		{1,1,1,1,1,0,1,1,1,1,1}};
	static int [][]firemap = {
		{0,0,5,0,0},
		{0,0,0,0,0},
		{1,1,1,1,1},
		{0,0,0,0,0},
		{1,1,1,1,1},
		{0,0,0,0,0},
		{1,1,1,1,1},
		{0,0,0,0,0},
		{0,0,0,0,0}};	
	static int [][]treemap = {
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0}};
	static int [][]watermap = {
		{0,0,5,0,0},
		{0,0,0,0,0},
		{1,1,1,1,1},
		{1,1,1,1,1},
		{1,1,1,1,1},
		{1,1,1,1,1},
		{1,1,1,1,1},
		{0,0,0,0,0},
		{0,0,0,0,0}};
	static int [][]darkmap = {
		{0,5,0,0,0,1},
		{1,1,0,1,1,0},
		{0,0,0,1,0,0},
		{0,1,1,1,0,1},
		{0,0,0,1,0,1},
		{1,1,1,0,0,1},
		{1,1,1,0,1,1},
		{0,0,0,0,1,0},
		{0,1,1,1,0,0},
		{0,0,1,0,0,1},
		{1,0,0,0,1,1},
		{1,1,0,1,1,1}};
	
	//0-可行走區域 1-障礙物  3-冰塊 4-碎冰 5-獎勵

	FP() {
		
		for (int i=0;i<10;i++)
		{
			panelmap[i] = new JPanel();
		}
		//暗之洞背景
		dark();
		//水之洞背景
		water();
		
		//木之洞背景
		tree();

		//火之洞背景
		fire();
		
		//冰之洞背景
		ice();
		
		//移動迷宮背景
		maze();
		
		//主地圖背景
		panelmap[nowmap].setSize(570,450);
		img[1] = new ImageIcon("src/finalproject/mainmap.jpg");
		element[0] = new JLabel(img[1]);
		panelmap[nowmap].add(element[0]);
		
		//人物
		img[0] = new ImageIcon("src/finalproject/mouse.jpg");
		p = new JLabel(img[0]);
		people.setSize(30, 30);
		people.add(p, BorderLayout.NORTH);
		people.setLocation(x,y);
		
		//按鈕區
		button();
		
		//獎勵洞
		img[9] = new ImageIcon("src/finalproject/prize.jpg");
		element[9] = new JLabel(img[9]);
		panelmap[9].add(element[9]);
		frame[9] = new JFrame("獎勵");
		frame[9].setSize(img[9].getIconWidth(),img[9].getIconHeight());

		//最後
		img[8] = new ImageIcon("src/finalproject/final.jpg");
		element[8] = new JLabel(img[8]);
		panelmap[8].add(element[8]);
		frame[8] = new JFrame("最後試煉");
		frame[8].setSize(img[8].getIconWidth(),img[8].getIconHeight());

		//暗之洞視窗
		frame[7] = new JFrame("暗之洞");
		frame[7].setSize(231,409);

		//水之洞視窗
		frame[6] = new JFrame("水之洞");
		frame[6].setSize(170,323);
		
		//木之洞視窗
		frame[5] = new JFrame("木之洞");
		frame[5].setSize(172,198);
		
		//火之洞視窗
		frame[4] = new JFrame("火之洞");
		frame[4].setSize(170,323);
		
		//冰之洞視窗
		frame[3] = new JFrame("冰之洞");
		frame[3].setSize(380,525);

		//移動迷宮視窗
		frame[2] = new JFrame("移動迷宮");
		frame[2].setSize(717,614);

		//次視窗
		frame[1] = new JFrame("技能視窗");
		frame[1].add(panel);
		frame[1].setSize(420, 140);
		frame[1].setLocation(1258,262);
		frame[1].setVisible(true);
		
		//主要視窗
		frame[0] = new JFrame("阿祺大冒險");
		frame[0].add(people);
		frame[0].add(panelmap[nowmap]);
		frame[0].setSize(588, 516);
		frame[0].setVisible(true);
		
		for (int i=0;i<=9;i++)
		{
			if (i!=1)
			{
				frame[i].addKeyListener(this);
				frame[i].setLocationRelativeTo(null);
			}
		}
		
	}
	
	public void button() {
		
		button[0] = new JButton("傳送");
		button[1] = new JButton("游泳");
		button[2] = new JButton("居合斬");
		button[3] = new JButton("閃光術");	
 		button[4] = new JButton("水槍 x " + watergun);
		
		panel = new JPanel(new GridLayout(1,4));
		
		for (int i=0;i<5;i++)
		{
			button[i].addActionListener(this);
			panel.add(button[4-i]);
		}
		
		for (int i=1;i<5;i++)
		{
			button[i].setEnabled(false);
		}
		
	}
	
	public void maze() {
		
		panelmap[2].setSize(540,660);
		img[11] = new ImageIcon("src/finalproject/mazemap.jpg");
		maze = new JLabel(img[11]);
		maze.setSize(540,660);
		panelmap[2].add(maze);
		
	}
	
	public void ice() {
		
		panelmap[3].setSize(450,330);
		panelmap[3] = new JPanel(new GridLayout(15,11));
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
				icejtp[i][j].addKeyListener(this);
				icejtp[i][j].insertIcon(img[4]);
				panelmap[3].add(icejtp[i][j]);
			}
		}

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
		//冰之洞梯子
		icejtp[1][5].insertIcon(img[3]);
		icejtp[6][5].insertIcon(img[3]);
		icejtp[10][5].insertIcon(img[3]);
	}
	public void fire() {
		
		panelmap[4].setSize(270,150);
		img[17] = new ImageIcon("src/finalproject/firemap.jpg");
		element[4] = new JLabel(img[17]);
		panelmap[4].add(element[4]);

	}
	public void tree() {
		
		panelmap[5].setSize(150,150);
		
		img[7] = new ImageIcon("src/finalproject/treemap.jpg");
		tree = new JLabel(img[7]);
		tree.setSize(150,150);
		panelmap[5].add(tree);
		
		img[8] = new ImageIcon("src/finalproject/notreemap.jpg");
		element[5] = new JLabel(img[8]);
		
	}
	public void water() {
		
		panelmap[6].setSize(270,150);
		img[19] = new ImageIcon("src/finalproject/watermap.jpg");
		water = new JLabel(img[19]);
		water.setSize(270,150);
		panelmap[6].add(water);

	}
	
	public void dark() {
		panelmap[7].setSize(360,180);
		img[9] = new ImageIcon("src/finalproject/darkmap.jpg");
		dark = new JLabel(img[9]);
		panelmap[7].add(dark);
		img[10] = new ImageIcon("src/finalproject/darkmap2.jpg");
		element[7] = new JLabel(img[10]);
		black.setBackground(new Color(0,0,0));

	}
	
	public static void main(String[] e) {
		// TODO Auto-generated method stub
		new FP();
		Music.change();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==button[0])//回傳技能
		{	
			if (nowmap==3)
			{
				reset.reseticemap();
				for (int i=0;i<15;i++)
				{
					for (int j=0;j<11;j++)
					{
						icejtp[i][j].addKeyListener(this);
					}
				}
			}
				
			frame[nowmap].setVisible(false);//使前一張地圖消失
			nowmap = 0 ;//改為主地圖
			x=271;
			y=440;
			people.setLocation(x,y);//設定人物座標
			frame[nowmap].add(people);//放人物
			frame[nowmap].add(panelmap[nowmap]);//放地圖
			frame[nowmap].setVisible(true);//叫出當前地圖
			c = true;
			Music.change();
		}
		else if (e.getSource()==button[1])//游泳
		{
			
			swim = true;
			c = true;
			Music.change();
			if (nowmap==6)
			{
				if (y/30==7 || y/30==1)
				{
					for (int i=2;i<=6;i++)
					{
						for (int j=0;j<=4;j++)
						{
							watermap[i][j] = 0 ;
						}
					}
					switch (y/30){
						case 7:
							y = y - 30;
							people.setLocation(x,y);
							break;
						case 1:
							y = y + 30;
							people.setLocation(x,y);
							break;	
					}
				}
				
			}
			
		}
		else if (e.getSource()==button[2])//居合斬
		{
			if (nowmap==5)
			{
				if ((y/30)==1 && (x/30)==2)
				{
					panelmap[nowmap].remove(tree);
					panelmap[nowmap].add(element[5]);
					frame[nowmap].repaint();
					treemap[2][2] = 5;
				}
				else if ((y/30)==3 && (x/30)==2)
				{
					panelmap[nowmap].remove(tree);
					panelmap[nowmap].add(element[5]);
					frame[nowmap].repaint();
					treemap[2][2] = 5;
				}
				else if ((y/30)==2 && (x/30)==1)
				{
					panelmap[nowmap].remove(tree);
					panelmap[nowmap].add(element[5]);
					frame[nowmap].repaint();	
					treemap[2][2] = 5;
				}
				else if ((y/30)==2 && (x/30)==3)
				{
					panelmap[nowmap].remove(tree);
					panelmap[nowmap].add(element[5]);
					frame[nowmap].repaint();
					treemap[2][2] = 5;
				}
			}
			else if (nowmap==7)
			{
				if ((y/30)==5 && (x/30)==3)
				{
					panelmap[nowmap].remove(dark);
					panelmap[nowmap].add(element[7]);
					frame[nowmap].repaint();
					darkmap[5][2] = 0;
				}
			}
			frame[nowmap].validate();
		}
		else if (e.getSource()==button[3])//閃光
		{
			black.setVisible(false);
			frame[nowmap].add(panelmap[nowmap]);//放地圖
			frame[nowmap].repaint();
		}
		else if (e.getSource()==button[4])//水槍
		{
			watergun--;
			button[4].setText("水槍 x " + watergun);
			img[17] = new ImageIcon("src/finalproject/firemap1.jpg");
			img[18] = new ImageIcon("src/finalproject/firemap2.jpg");
			img[19] = new ImageIcon("src/finalproject/firemap3.jpg");
			fire[0] = new JLabel(img[17]);
			fire[1] = new JLabel(img[18]);
			fire[2] = new JLabel(img[19]);
			if (nowmap==4)
			{
				if (y/30==7 || y/30==5 || y/30==3)
				{
					switch (y/30)
					{
					case 7:
						panelmap[nowmap].remove(element[4]);
						panelmap[nowmap].add(fire[0]);
						for (int i=0 ;i<5;i++)
							firemap[6][i] = 0;
						break;
					case 5:
						panelmap[nowmap].removeAll();
						panelmap[nowmap].add(fire[1]);
						for (int i=0 ;i<5;i++)
						firemap[4][i] = 0;
						break;
					case 3:
						panelmap[nowmap].removeAll();
						panelmap[nowmap].add(fire[2]);
						for (int i=0 ;i<5;i++)
						firemap[2][i] = 0;
						break;	
					}
					frame[nowmap].repaint();
					
				}
			}
			if (watergun==0)
				button[4].setEnabled(false);
			
			frame[nowmap].validate();
		}
		
	}

	public void keyPressed(KeyEvent e) {
			
		int key = e.getKeyCode();
			
		if (nowmap==0)//主地圖走路
		{
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (mainmap[y/30-1][x/30]!=1)
					{
						next = 31 ;
						y = y - next ;
						people.setLocation(x,y);
					}
				}	
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<15)
				{
					if (mainmap[y/30+1][x/30]!=1)
					{
						next = 31 ;
						y = y + next ;
						people.setLocation(x,y);
					}
				}	
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<19)
				{
					if (mainmap[y/30][x/30+1]!=1 )
					{
						next = 30 ;
						x = x + next ;
						people.setLocation(x,y);
					}
				}		
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (mainmap[y/30][x/30-1]!=1)
					{
						next= 30 ;
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap == 2)//移動迷宮走路
		{
			next = 31 ;
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (mazemap[y/30-1][x/30]!=1)
					{
						y = y - next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<18)
				{
					if (mazemap[y/30+1][x/30]!=1)
					{
						y = y + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<22)
				{
					if (mazemap[y/30][x/30+1]!=1 )
					{
						x = x + next ;
						people.setLocation(x,y);
					}
				}				
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (mazemap[y/30][x/30-1]!=1)
					{
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap == 3)//冰之洞走路
		{
			next = 32 ;
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (icemap[y/30-1][x/30]!=1)
					{
						y = y - next ;
						people.setLocation(x,y);
						if (icemap[y/30][x/30]==3)
						{
							icemap[y/30][x/30]=1;
							icejtp[y/30][x/30].insertIcon(img[5]);
						}
					}
				}	
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<15)
				{
					if (icemap[y/30+1][x/30]!=1)
					{
						y = y + next ;
						people.setLocation(x,y);
						if (icemap[y/30][x/30]==3)
						{
							icemap[y/30][x/30]=1;
							icejtp[y/30][x/30].insertIcon(img[5]);
						}
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<11)
				{
					if (icemap[y/30][x/30+1]!=1 )
					{
						x = x + next ;
						people.setLocation(x,y);
						if (icemap[y/30][x/30]==3)
						{
							icemap[y/30][x/30]=1;
							icejtp[y/30][x/30].insertIcon(img[5]);
						}
					}
				}		
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (icemap[y/30][x/30-1]!=1)
					{
						x = x - next ;
						people.setLocation(x,y);
						if (icemap[y/30][x/30]==3)
						{
							icemap[y/30][x/30]=1;
							icejtp[y/30][x/30].insertIcon(img[5]);
						}
					}
				}
			}
		}
		else if (nowmap==4)//火之洞走路
		{
			next = 30;
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (firemap[y/30-1][x/30]!=1)
					{
						y = y - next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<9)
				{
					if (firemap[y/30+1][x/30]!=1)
					{
						y = y + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<5)
				{
					if (firemap[y/30][x/30+1]!=1 )
					{
						x = x + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (firemap[y/30][x/30-1]!=1)
					{
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap==5)//木之洞走路
		{
			next = 30;
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (treemap[y/30-1][x/30]!=1)
					{
						y = y - next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<5)
				{
					if (treemap[y/30+1][x/30]!=1)
					{
						y = y + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<5)
				{
					if (treemap[y/30][x/30+1]!=1 )
					{
						x = x + next ;
						people.setLocation(x,y);
					}
				}	
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (treemap[y/30][x/30-1]!=1)
					{
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap==6)//水之洞走路
		{
			next = 30;
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (watermap[y/30-1][x/30]!=1)
					{
						y = y - next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<9)
				{
					if (watermap[y/30+1][x/30]!=1)
					{
						y = y + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<5)
				{
					if (watermap[y/30][x/30+1]!=1 )
					{
						x = x + next ;
						people.setLocation(x,y);
					}
				}	
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (watermap[y/30][x/30-1]!=1)
					{
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap==7)//暗之洞走路
		{
			if (key==KeyEvent.VK_UP)
			{
				if (y/30-1>=0)
				{
					if (darkmap[y/30-1][x/30]!=1)
					{
						next = 30;
						y = y - next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y/30+1<12)
				{
					if (darkmap[y/30+1][x/30]!=1)
					{
						next = 30;
						y = y + next ;
						people.setLocation(x,y);
					}
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x/30+1<6)
				{
					if (darkmap[y/30][x/30+1]!=1 )
					{
						next = 35;
						x = x + next ;
						people.setLocation(x,y);
					}
				}	
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x/30-1>=0)
				{
					if (darkmap[y/30][x/30-1]!=1)
					{
						next = 35;
						x = x - next ;
						people.setLocation(x,y);
					}
				}
			}
		}
		else if (nowmap==8)
		{
			if (key==KeyEvent.VK_UP)
			{
				next = 30;
				y = y - next ;
				people.setLocation(x,y);
			}
		}
		else if (nowmap==9)
		{
			if (key==KeyEvent.VK_UP)
			{
				if (y>120)
				{
					next = 30;
					y = y - next ;
					people.setLocation(x,y);

				}
			}
			else if (key==KeyEvent.VK_DOWN)
			{
				if (y<280)
				{
					next = 30;
					y = y + next ;
					people.setLocation(x,y);
				}
			}
			else if (key==KeyEvent.VK_RIGHT)
			{
				if (x<660)
				{
					next = 30;
					x = x + next ;
					people.setLocation(x,y);
				}	
			}
			else if (key==KeyEvent.VK_LEFT)
			{
				if (x>30)
				{
					next = 30;
					x = x - next ;
					people.setLocation(x,y);
				}
			}
		}
		
		if (e.getKeyChar()=='s')//開關技能視窗
		{
			if (skill)
			{
				frame[1].setVisible(true);
				skill = false;
			}
			else
			{
				frame[1].setVisible(false);
				skill = true;
			}		
		}
		
		if (e.getKeyChar()=='r')//金手指
		{
			for (int i=1;i<=4;i++)
				button[i].setEnabled(true);
			watergun=99;
			button[4].setText("水槍 x " + watergun);
			mainmap[1][9]=8;
		}
	}

	public void keyReleased(KeyEvent e){
		if ( nowmap==0 && mainmap[y/30][x/30] != 0)
		{
			if (mainmap[y/30][x/30]==2)//進入移動迷宮
			{
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 2 ;//改為移動迷宮
				x = 659;
				y = 532;
			}
			else if (mainmap[y/30][x/30]==3)//進入冰之洞
			{	
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 3 ;//改為冰之洞
				x = 167;
				y = 449;
			}
			else if (mainmap[y/30][x/30]==4)//進入火之洞
			{	
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 4 ;//改為火之洞
				x = 61;
				y = 244;
			}
			else if (mainmap[y/30][x/30]==5)//進入木之洞
			{	
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 5 ;//改為木之洞
				x = 63;
				y = 126;
			}
			else if (mainmap[y/30][x/30]==6)//進入水之洞
			{	
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 6 ;//改為水之洞
				x = 61;
				y = 246;
			}
			else if (mainmap[y/30][x/30]==7)//進入暗之洞
			{	
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 7 ;//改為暗之洞
				x = 74;
				y = 336;
			}
			else if (mainmap[y/30][x/30]==8)//進入最後洞
			{
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 8 ;//改為最後洞
				x = 275;
				y = 930;
			}
			if (nowmap!=7)
			{
				people.setLocation(x,y);//設定人物座標
				frame[nowmap].add(people);//放人物
				frame[nowmap].add(panelmap[nowmap]);//放地圖
				frame[nowmap].setVisible(true);//叫出當前地圖
			}
			else
			{
				people.setLocation(x,y);//設定人物座標
				frame[nowmap].add(people);//放人物
				frame[nowmap].add(black);//放地圖
				frame[nowmap].setVisible(true);//叫出當前地圖
			}
			
			frame[nowmap].validate();
			c = true;
			Music.change();
		}
		else if (nowmap==2)
		{
			if (mazemap[y/30][x/30]==11 || mazemap[y/30][x/30]==12 || mazemap[y/30][x/30]==13 || mazemap[y/30][x/30]==14)
			{
				mazemove = true;
				while(mazemove)
				{
					mazeuse.move();
					people.setLocation(x,y);
				}
			}	
			if (mazemap[y/30][x/30]==5)//移動迷宮獎勵
			{
				activeskill.judge();
			}
		}
		else if (nowmap==3)
		{
			
			if (icemap[y/30][x/30]==5)//冰之洞獎勵
			{
				activeskill.judge();
			}
			
			if ((y/30)==11 && (x/30) == 5)//第一層梯子判斷
			{
				if (icemap[13][4]==1 && icemap[13][5]==1)
				{
					if (icemap[12][4]==1 && icemap[12][5]==1 && icemap[12][6]==1)
					{
						if (icemap[11][5]==1 && icemap[11][6]==1)
						{
							icejtp[10][5].insertIcon(img[6]);
							icemap[10][5]=0;
						}
					}
				}
			}
			
			if ((y/30)==7 && (x/30) == 5)//第二層梯子判斷
			{
				if (icemap[9][2]==1 && icemap[9][3]==1 && icemap[9][4]==1 && icemap[9][5]==1 && icemap[9][6]==1 && icemap[9][7]==1 && icemap[9][8]==1)
				{
					if (icemap[8][2]==1 && icemap[8][4]==1 && icemap[8][5]==1 && icemap[8][6]==1 && icemap[8][8]==1)
					{
						if (icemap[7][2]==1 && icemap[7][3]==1 && icemap[7][4]==1 && icemap[7][5]==1 && icemap[7][6]==1 && icemap[7][7]==1 && icemap[7][8]==1)
						{
							icejtp[6][5].insertIcon(img[6]);
							icemap[6][5]=0;
						}
					}

				}
			}
			
			if ((y/30)==2 && (x/30) == 5)//第三層梯子判斷
			{
				if (icemap[2][0]==1 && icemap[2][1]==1 &&  icemap[2][3]==1 && icemap[2][4]==1 && icemap[2][5]==1 && icemap[2][6]==1 && icemap[2][7]==1 && icemap[2][8]==1 && icemap[2][9]==1 && icemap[2][10]==1)
				{
					if (icemap[3][0]==1 && icemap[3][1]==1 &&  icemap[3][3]==1 && icemap[3][4]==1 && icemap[3][5]==1 && icemap[3][6]==1 && icemap[3][7]==1 && icemap[3][8]==1 && icemap[3][10]==1)
					{
						if (icemap[4][0]==1 && icemap[4][2]==1 &&  icemap[4][3]==1 && icemap[4][5]==1 && icemap[4][6]==1 && icemap[4][7]==1 && icemap[4][8]==1 && icemap[4][9]==1 && icemap[4][10]==1)
						{
							if (icemap[5][0]==1 && icemap[5][1]==1 &&  icemap[5][2]==1 &&  icemap[5][3]==1 &&  icemap[5][4]==1 && icemap[5][5]==1 && icemap[5][6]==1 && icemap[5][7]==1 && icemap[5][9]==1 && icemap[5][10]==1)
							{
								icejtp[1][5].insertIcon(img[6]);
								icemap[1][5]=0;
							}
						}
					}

				}
			}
	
		}
		else if (nowmap==4)
		{
			if (firemap[y/30][x/30]==5)//火之洞獎勵
			{
				activeskill.judge();
			}
		}
		else if (nowmap==5)
		{
			if (treemap[y/30][x/30]==5)//木之洞獎勵
			{
				activeskill.judge();
			}
		}
		else if (nowmap==6)
		{
			if (y/30<2 || y/30>6)
			{
				for (int i=2;i<=6;i++)
				{
					for (int j=0;j<=4;j++)
					{
						watermap[i][j] = 1 ;
					}
				}
				if (swim == true)
				{
					swim = false;
					c = true;
					Music.change();
				}

			}
			if (watermap[y/30][x/30]==5)//水之洞獎勵
			{			
				activeskill.judge();
			}
		}
		else if (nowmap==7)
		{
			if (darkmap[y/30][x/30]==5)//暗之洞獎勵
			{			
				activeskill.judge();
			}
		}
		else if (nowmap==8)
		{
			if (y<130)
			{
				frame[nowmap].setVisible(false);//使前張地圖消失
				nowmap = 9 ;//改為獎勵洞
				x = 380;
				y = 280;
				people.setLocation(x,y);//設定人物座標
				frame[nowmap].add(people);//放人物
				frame[nowmap].add(panelmap[nowmap]);//放地圖
				frame[nowmap].setVisible(true);//叫出當前地圖
				c = true;
				Music.change();
			}
		}
		else if (nowmap == 9)
		{
			if (x>340 && x<380)
			{
				if (y<190 && y>150)
				{
					text.setText("恭喜你完成所有關卡... 遊戲結束囉~");
					jump.add(text);
					jump.setSize(250,100);
					jump.setLocationRelativeTo(null);
					jump.setVisible(true);
					Close.active();
				}
			}
		}
		if (key==6)
		{
			text.setText("洞窟開啟了...");
			jump.add(text);
			jump.setSize(250,100);
			jump.setLocationRelativeTo(null);
			jump.setVisible(true);
			mainmap[1][9] = 8 ;
			key++;
			panelmap[0].remove(element[0]);
			img[0] = new ImageIcon("src/finalproject/mainmap2.jpg");
			element[0] = new JLabel(img[0]);
			panelmap[0].add(element[0]);
		}
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

}