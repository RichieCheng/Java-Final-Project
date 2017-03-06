package finalproject;

public class activeskill extends FP{
	
	public static void judge(){
		
		switch (nowmap)
		{
			case 2:
				watergun++;
				button[4].setText("¤ôºj x " + watergun);
				button[4].setEnabled(true);
				mazemap[y/30][x/30]=0;
				break;
			case 3:
				button[1].setEnabled(true);
				watergun++;
				button[4].setText("¤ôºj x " + watergun);
				button[4].setEnabled(true);
				icemap[y/30][x/30]=0;
				break;
			case 4:
				button[2].setEnabled(true);
				firemap[y/30][x/30]=0;
				break;
			case 5:
				button[3].setEnabled(true);
				treemap[y/30][x/30]=0;
				break;
			case 6:
				watergun++;
				button[4].setText("¤ôºj x " + watergun);
				button[4].setEnabled(true);
				watermap[y/30][x/30]=0;
				break;
			case 7:
				darkmap[y/30][x/30]=0;
				break;
				
		}
		key++;
		get=true;
		Music.ps();
		
	}
	
	
	
}
